import { Component, OnInit } from '@angular/core';
import {Person, Play, TheaterService} from '../theater.service';

@Component({
  selector: 'app-admin-plays',
  templateUrl: './admin-plays.component.html',
  styleUrls: ['./admin-plays.component.css']
})
export class AdminPlaysComponent implements OnInit {
  private theaterService: TheaterService;
  public plays: Play[];
  public playToEdit: number = null;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
    this.reloadPlays();
  }

  public reloadPlays(): void {
    this.theaterService.getAllPlay().subscribe(next => this.plays = next, error => console.log(error));
  }

  addNewPlay(form: HTMLFormElement): void {
    const name = (form.elements.namedItem('newPlayName') as HTMLInputElement).value;
    const logline = (form.elements.namedItem('newPlayLogline') as HTMLInputElement).value;
    const length = Number((form.elements.namedItem('newPlayLength') as HTMLInputElement).value);

    const newPlay = new Play(undefined);
    newPlay.name = name;
    newPlay.logline = logline;
    newPlay.length = length;
    this.theaterService.createPlay(newPlay).subscribe(next => {
      (form.elements.namedItem('newPlayName') as HTMLInputElement).value = '';
      (form.elements.namedItem('newPlayLogline') as HTMLInputElement).value = '';
      (form.elements.namedItem('newPlayLength') as HTMLInputElement).value = '';
    }, error => console.log(error));
  }

  deletePlay(play: Play): void {
    if (!confirm('Biztos törölni akarod a(z) ' + play.name + ' nevű előadást??')) { return; }
    this.theaterService.deletePlay(play).subscribe(next => this.reloadPlays(), error => console.log(error));
  }

  modifyPlay(play: Play): void {
    this.playToEdit = null;
    if (!confirm('Módósítások megtartása?')) { return; }
    this.theaterService.modifyPlay(play).subscribe(next => this.reloadPlays(), error => console.log(error));
  }
}
