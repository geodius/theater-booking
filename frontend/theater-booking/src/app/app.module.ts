import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarPlayComponent } from './calendar-play/calendar-play.component';
import { CalendarScheduleComponent } from './calendar-schedule/calendar-schedule.component';
import { LoginComponent } from './login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { TicketsComponent } from './tickets/tickets.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { PlaysComponent } from './plays/plays.component';
import {CommonModule} from '@angular/common';
import { TicketReservationOverlayComponent } from './ticket-reservation-overlay/ticket-reservation-overlay.component';
import { AdminMainComponent } from './admin-main/admin-main.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminPlaysComponent } from './admin-plays/admin-plays.component';
import { AdminScheduleComponent } from './admin-schedule/admin-schedule.component';
import { AdminReservationComponent } from './admin-reservation/admin-reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    CalendarPlayComponent,
    CalendarScheduleComponent,
    LoginComponent,
    TicketsComponent,
    ScheduleComponent,
    PlaysComponent,
    TicketReservationOverlayComponent,
    AdminMainComponent,
    AdminUsersComponent,
    AdminPlaysComponent,
    AdminScheduleComponent,
    AdminReservationComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
