import { Component, OnInit } from '@angular/core';
import {TheaterService} from '../theater.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private theaterService: TheaterService;
  public loginFailed = false;


  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
  }

  login(): void {

  }

  register(): void {

  }
}
