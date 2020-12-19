import { Component, OnInit } from '@angular/core';
import {Person, Reservation, Schedule, TheaterService} from '../theater.service';

@Component({
  selector: 'app-admin-reservation',
  templateUrl: './admin-reservation.component.html',
  styleUrls: ['./admin-reservation.component.css']
})
export class AdminReservationComponent implements OnInit {
  public theaterService: TheaterService;
  public reservations: Reservation[];
  public people: Person[];
  public schedules: Schedule[];
  public reservationToEdit: number;
  public canSelectSeat: boolean;
  public showTicketOverlay: boolean;
  public overlaySchedule: Schedule;
  public overlayPerson: Person;
  public pendingReservation: Reservation;
  public reservationAdded: boolean;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.reloadData();
  }

  public reloadData(): void {
    this.theaterService.getAllPerson().subscribe(next => this.people = next, error => console.log(error));
    this.theaterService.getAllReservation().subscribe(next => this.reservations = next, error => console.log(error));
    this.theaterService.getAllSchedule().subscribe(next => this.schedules = next, error => console.log(error));
  }

  public deleteReservation(reservation: Reservation): void {
    this.theaterService.deleteReservation(reservation).subscribe(next => this.reloadData(), error => console.log(error));
  }

  public getScheduleById(id: number): Schedule {
    const schedule = this.schedules.find(s => s.id == id);
    return schedule !== undefined ? schedule : null;
  }

  public getPersonById(id: number): Person {
    const person = this.people.find(p => p.id == id);
    return person !== undefined ? person : null;
  }
}
