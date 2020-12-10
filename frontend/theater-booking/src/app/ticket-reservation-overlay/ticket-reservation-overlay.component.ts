import {ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Reservation, Room, Schedule, Seat, TheaterService} from '../theater.service';

@Component({
  selector: 'app-ticket-reservation-overlay',
  templateUrl: './ticket-reservation-overlay.component.html',
  styleUrls: ['./ticket-reservation-overlay.component.css']
})
export class TicketReservationOverlayComponent implements OnInit {
  @Input() schedule: Schedule;
  @Output() closeWindow: EventEmitter<void> = new EventEmitter<void>();
  private theaterService: TheaterService;
  private changeDetectorRef: ChangeDetectorRef;
  public roomLayout: Seat[];

  constructor(theaterService: TheaterService, changeDetector: ChangeDetectorRef) {
    this.theaterService = theaterService;
    this.changeDetectorRef = changeDetector;
  }


  ngOnInit(): void {
    this.loadRoomLayout(this.schedule.room);
  }

  private softReload(): void {
    this.theaterService.getScheduleById(this.schedule.id).subscribe(
      next => {
        this.schedule = next;
        this.loadRoomLayout(this.schedule.room);
      },
      error => console.log(error)
    );
  }

  loadRoomLayout(room: Room): void {
    this.theaterService.getRoomLayout(room).subscribe(
      next => {
        this.prepareLayout(next).then(
          nextLayout => {
            this.roomLayout = nextLayout;
            this.changeDetectorRef.detectChanges();
          }
        );
      },
      error => console.error(error));
  }

  async prepareLayout(seats: Seat[]): Promise<Seat[]> {
    const currentPerson = await this.theaterService.getCurrentPerson().toPromise();
    for (const res of this.schedule.reservations) {
      const seat = seats.find(s => s.seat === res.seat);
      seat.reserved = true;
      seat.reservedByCurrentPerson = res.person.id === currentPerson.id;
    }
    return seats;
  }

  getRoomLayoutWidth(): number {
    if (this.roomLayout === undefined) { return 0; }
    let leftElement = this.roomLayout[0];
    let rightElement = this.roomLayout[0];
    for (let i = 1; i < this.roomLayout.length; ++i) {
      const current = this.roomLayout[i];
      if (leftElement.x > current.x) { leftElement = current; }
      if (rightElement.x < current.x) { rightElement = current; }
    }
    return rightElement.x + rightElement.width + leftElement.width - leftElement.x;
  }

  getTopOffset(): number {
    if (this.roomLayout === undefined) { return 0; }
    let topOffset = Infinity;
    for (const seat of this.roomLayout) {
      if (topOffset > seat.y) { topOffset = seat.y; }
    }
    return topOffset;
  }

  async handleSeatAlreadyReserved(seat: Seat): Promise<void> {
    const reservation = this.schedule.reservations.find(r => r.seat === seat.seat);
    const currentPerson = await this.theaterService.getCurrentPerson().toPromise();
    if (reservation.person.id === currentPerson.id) {
      const deleteReservation = confirm('Szeretnéd lemondani a foglalást erről a székről: ' + seat.seat);
      if (deleteReservation) {
        this.theaterService.deleteReservation(reservation).subscribe(
          next => {
                this.softReload();
                alert('Sikeres lemondás!');
              },
          error => console.log(error)
        );
      }
    }
  }

  async reserveSeat(seat: Seat): Promise<void> {
    if (seat.reserved) {
      await this.handleSeatAlreadyReserved(seat);
      return;
    }
    const currentPerson = await this.theaterService.getCurrentPerson().toPromise();

    const reservation = new Reservation(undefined);
    reservation.person = currentPerson;
    reservation.seat = seat.seat;
    reservation.schedule = this.schedule;

    this.theaterService.createReservation(reservation).subscribe(
      next =>  {
              this.softReload();
              alert('Sikeres foglalás!');
            },
      error => console.log(error)
    );
  }

  closeOverlay(): void {
    this.closeWindow.emit();
  }

}
