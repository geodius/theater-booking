import { Component, OnInit } from '@angular/core';
import {Play, Room, Schedule, TheaterService} from '../theater.service';

@Component({
  selector: 'app-admin-schedule',
  templateUrl: './admin-schedule.component.html',
  styleUrls: ['./admin-schedule.component.css']
})
export class AdminScheduleComponent implements OnInit {
  private theaterService: TheaterService;
  public plays: Play[];
  public rooms: Room[];
  public schedules: Schedule[];
  public scheduleToEdit: number = null;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.reloadData();
  }

  public reloadData(): void {
    this.theaterService.getAllPlay().subscribe(next => this.plays = next, error => console.log(error));
    this.theaterService.getAllRoom().subscribe(next => this.rooms = next, error => console.log(error));
    this.theaterService.getAllSchedule().subscribe(next => this.schedules = next, error => console.log(error));
  }

  addNewSchedule(form: HTMLFormElement): void {
    const playId = Number((form.elements.namedItem('newSchedulePlay') as HTMLSelectElement).value);
    if (playId === -1) { return; }
    const play = this.plays.find(p => p.id === playId);
    const roomId = Number((form.elements.namedItem('newScheduleRoom') as HTMLSelectElement).value);
    if (roomId === -1) { return; }
    const room = this.rooms.find(r => r.id === roomId);
    const startStr = (form.elements.namedItem('newScheduleStart') as HTMLInputElement).value;
    const start = new Date(new Date(startStr).getTime() + 3600 * 1000);

    const newSchedule = new Schedule(undefined);
    newSchedule.play = play;
    newSchedule.room = room;
    newSchedule.start = start;
    this.theaterService.createSchedule(newSchedule).subscribe(next => {
      (form.elements.namedItem('newSchedulePlay') as HTMLSelectElement).selectedIndex = 0;
      (form.elements.namedItem('newScheduleRoom') as HTMLSelectElement).selectedIndex = 0;
      (form.elements.namedItem('newScheduleStart') as HTMLInputElement).value = '';
      this.reloadData();
    }, error => console.log(error));
  }

  modifySchedule(schedule: Schedule): void {
    this.scheduleToEdit = null;
    if (!confirm('Módósítások megtartása?')) { return; }
    schedule.start = new Date(new Date(schedule.startStr).getTime() + 3600 * 1000);
    this.theaterService.modifySchedule(schedule).subscribe(next => this.reloadData(), error => console.log(error));
  }

  deletePlay(schedule: Schedule): void {
    if (!confirm('Biztos törölni akarod a műsort?')) { return; }
    this.theaterService.deleteSchedule(schedule).subscribe(next => this.reloadData(), error => console.log(error));
  }

  getRoomIndex(room: Room): number {
    return this.rooms.findIndex(r => r.id === room.id);
  }
}
