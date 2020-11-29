import {ChangeDetectorRef, EventEmitter, Component, OnInit, Output} from '@angular/core';
import {TheaterService} from '../theater.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() loggedIn = new EventEmitter<void>();
  private theaterService: TheaterService;
  public loginStatus = 0;
  public registerStatus = 0;

  public loginEmail: string;
  public loginPassword: string;
  public registerName: string;
  public registerEmail: string;
  public registerPassword: string;

  constructor(theaterService: TheaterService) {
    this.theaterService = theaterService;
  }

  ngOnInit(): void {
  }

  login(): void {
    this.theaterService.login(this.loginEmail, this.loginPassword).subscribe(
    next => this.loggedIn.emit(),
    error => this.loginStatus = error.status
    );
  }

  register(): void {
    this.theaterService.register(this.registerName, this.registerEmail, this.registerPassword).subscribe(
      next => this.registerStatus = 200,
      error => this.registerStatus = error.status
    );
  }
}
