import { Component, OnInit } from '@angular/core';
import {Play, TheaterService} from '../theater.service';

@Component({
  selector: 'app-plays',
  templateUrl: './plays.component.html',
  styleUrls: ['./plays.component.css']
})
export class PlaysComponent implements OnInit {
  private theaterService: TheaterService;

  plays: Play[] = [];

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.theaterService.getAllPlay().subscribe(next => this.playsLoaded(next), error => console.error(error));
  }

  playsLoaded(plays: Play[]): void {
    this.plays = plays;
  }

  isOnThisWeek(date: Date): boolean {
    const today = Math.floor( new Date().getTime() / (1000 * 3600 * 24));
    const dateDay = Math.floor( date.getTime() / (1000 * 3600 * 24));
    return today < dateDay && today + 7 >= dateDay;
  }

  getDisplayDate(start: Date): string {
    return ('' + start.getMonth()).padStart(2, '0') + '.' + ('' + start.getDate()).padStart(2, '0') + ' (' +
           start.toLocaleString('default', {weekday: 'long'}) + ')';
  }
}
