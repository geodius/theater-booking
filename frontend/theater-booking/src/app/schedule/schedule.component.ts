import { Component, OnInit } from '@angular/core';
import {Play, Schedule, TheaterService} from '../theater.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  private theaterService: TheaterService;
  public days: ScheduledDay[] = [];
  public showTicketOverlay: boolean;
  public overlaySchedule: Schedule;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.theaterService.getAllPlay().subscribe(next => this.playsLoaded(next), error => console.error(error));
  }

  playsLoaded(plays: Play[]): void {
    const temp = new Map();
    for (const play of plays) {
      for (const schedule of play.schedules) {
        if (this.getAsDay(schedule.start).getTime() < this.getAsDay(new Date()).getTime()) { continue; }
        const dayEpoch = this.getAsDay(schedule.start).getTime();
        if (!temp.has(dayEpoch)) {
          temp.set(dayEpoch, new ScheduledDay(new Date(dayEpoch)));
        }
        const scheduledDay = temp.get(dayEpoch);
        if (!scheduledDay.plays.find(p => p.id === play.id)) {
          scheduledDay.plays.push(play);
        }
      }
    }
    const sortedMap = new Map([...temp.entries()].sort());
    this.days = Array.from(sortedMap.values());
  }

  getAsDay(date: Date): Date{
    const dateDay = Math.floor( date.getTime() / (1000 * 3600 * 24)) * (1000 * 3600 * 24);
    return new Date(dateDay);
  }

  isSameDay(lhs: Date, rhs: Date): boolean {
    return this.getAsDay(lhs).getTime() === this.getAsDay(rhs).getTime();
  }

  getAsDate(epoch: number): Date {
    return new Date(epoch);
  }

  padTime(time: number): string {
    let str = '';
    if (time < 10) { str = '0'; }
    return str + time.toString();
  }

  isStarted(start: Date): boolean {
    return start.getTime() < new Date().getTime();
  }
}

export class ScheduledDay {
  constructor(date: Date) {
    this.date = date;
  }
  public date: Date;
  public plays: Play[] = [];
}
