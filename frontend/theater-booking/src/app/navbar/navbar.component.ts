import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Output() navbarSelectionChanged = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
    const links = document.getElementById('navbar').getElementsByTagName('a') as HTMLCollectionOf<HTMLAnchorElement>;

    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < links.length; ++i) {
      links[i].addEventListener('click', (e: Event) => this.navLinkClicked(e, this));
    }
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
