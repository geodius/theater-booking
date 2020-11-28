import { Component, OnInit } from '@angular/core';
import {Play, TheaterService} from '../theater.service';

@Component({
  selector: 'app-calendar-schedule',
  templateUrl: './calendar-schedule.component.html',
  styleUrls: ['./calendar-schedule.component.css']
})
export class CalendarScheduleComponent implements OnInit {
  private theaterService: TheaterService;

  plays: Play[] = [];

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.theaterService.getAllPlay().subscribe(next => this.playsLoaded(next), error => console.error(error));
  }

  playsLoaded(plays: Play[]): void {
    for (const play of plays) {
      let i = 0;
      while (i < play.schedules.length && !this.isToday(play.schedules[i].start)) { ++i; }
      if (i < play.schedules.length) {
        this.plays.push(play);
      }
    }
  }

  getAsDate(epoch: number): Date {
    return new Date(epoch);
  }

  isToday(date: Date): boolean {
    const today = Math.floor( new Date().getTime() / (1000 * 3600 * 24));
    const dateDay = Math.floor( date.getTime() / (1000 * 3600 * 24));
    console.log(today + ' ' + dateDay + ' ' + (today === dateDay));
    return today === dateDay;
  }

  padTime(time: number): string {
    let str = '';
    if (time < 10) { str = '0'; }
    return str + time.toString();
  }
}
