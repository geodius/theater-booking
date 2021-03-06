import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TheaterService} from '../theater.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Output() navbarSelectionChanged = new EventEmitter<string>();
  public theaterService: TheaterService;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
  }

  navLinkClicked(e: Event, context: any): void {
    // @ts-ignore
    let targetPage = e.target.hash;
    if (targetPage === undefined) {
      // @ts-ignore
      const href = e.target.parentElement.href;
      targetPage = href.substr(href.lastIndexOf('/') + 1);
    }
    context.navbarSelectionChanged.emit(targetPage);
  }
}
