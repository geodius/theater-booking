import { Component, OnInit } from '@angular/core';
import {Play, Schedule, TheaterService} from '../theater.service';

@Component({
  selector: 'app-calendar-play',
  templateUrl: './calendar-play.component.html',
  styleUrls: ['./calendar-play.component.css']
})
export class CalendarPlayComponent implements OnInit {
  private theaterService: TheaterService;
  public showTicketOverlay: boolean;
  public overlaySchedule: Schedule;

  plays: Play[];
  days: Date[] = [];
  selectedDay = -1;
  playDataByDay = [[], [], [], [], [], [], []];

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
    for (let i = 0; i < 7; ++i) {
      this.days.push(new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * i));
    }
  }

  ngOnInit(): void {
    this.theaterService.getAllPlay().subscribe(next => this.playsLoaded(next), error => console.error(error));
  }

  playsLoaded(plays: Play[]): void {
    this.plays = plays;
    console.log(plays);
    for (const play of plays) {
      for (const schedule of play.schedules){
        const startDay = Math.floor(schedule.start.getTime() / (1000 * 3600 * 24));
        for (let j = 0; j < this.days.length; ++j) {
          const day = Math.floor(this.days[j].getTime() / (1000 * 3600 * 24));
          if (startDay === day) {
            // @ts-ignore
            this.playDataByDay[j].push(
              {
                name: play.name,
                start: new Date(schedule.start),
                end: new Date(new Date(schedule.start).getTime() + play.length * 1000 *  60),
                length: play.length,
                logline: play.logline,
                schedule
              }
            );
          }
        }
      }
    }
  }

}
