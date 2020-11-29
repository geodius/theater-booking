import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'theater-booking';
  currentPage: string;

  ngOnInit(): void {
    this.currentPage = document.location.hash;
  }

  changePage(e: string): void {
    document.location.hash = e;
    this.currentPage = e;
  }

  loggedIn(): void {
    this.changePage('#home');
  }
}
