import {ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Person, Reservation, Room, Schedule, Seat, TheaterService} from '../theater.service';

@Component({
  selector: 'app-ticket-reservation-overlay',
  templateUrl: './ticket-reservation-overlay.component.html',
  styleUrls: ['./ticket-reservation-overlay.component.css']
})
export class TicketReservationOverlayComponent implements OnInit {
  @Input() schedule: Schedule;
  @Input() person: Person;
  @Output() closeWindow: EventEmitter<void> = new EventEmitter<void>();
  @Output() reservationAdded: EventEmitter<void> = new EventEmitter<void>();

  private theaterService: TheaterService;
  private changeDetectorRef: ChangeDetectorRef;
  public roomLayout: Seat[];

  constructor(theaterService: TheaterService, changeDetector: ChangeDetectorRef) {
    this.theaterService = theaterService;
    this.changeDetectorRef = changeDetector;
  }


  async ngOnInit(): Promise<void> {
    this.loadRoomLayout(this.schedule.room);
    if (this.person === undefined) { this.person = await this.theaterService.getCurrentPerson().toPromise(); }
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
    this.theaterService.getRoomLayout(room).subscribe(next => this.roomLayout = this.prepareLayout(next), error => console.error(error));
  }

  prepareLayout(seats: Seat[]): Seat[] {
    for (const res of this.schedule.reservations) {
      const seat = seats.find(s => s.seat === res.seat);
      seat.reserved = true;
      seat.reservedByCurrentPerson = res.person.id === this.person.id;
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

  handleSeatAlreadyReserved(seat: Seat): void {
    const reservation = this.schedule.reservations.find(r => r.seat === seat.seat);

    if (reservation.person.id === this.person.id) {
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

  reserveSeat(seat: Seat): void {
    if (seat.reserved) {
      this.handleSeatAlreadyReserved(seat);
      return;
    }

    const reservation = new Reservation(undefined);
    reservation.person = this.person;
    reservation.seat = seat.seat;
    reservation.schedule = this.schedule;

    this.theaterService.createReservation(reservation).subscribe(
      next =>  {
              this.softReload();
              alert('Sikeres foglalás!');
              this.reservationAdded.emit();
            },
      error => console.log(error)
    );
  }

  closeOverlay(): void {
    this.closeWindow.emit();
  }

}
