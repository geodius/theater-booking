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

  pageChanged(e: string): void {
    this.currentPage = e;
  }
}
