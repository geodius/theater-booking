import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketReservationOverlayComponent } from './ticket-reservation-overlay.component';

describe('TicketReservationOverlayComponent', () => {
  let component: TicketReservationOverlayComponent;
  let fixture: ComponentFixture<TicketReservationOverlayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketReservationOverlayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketReservationOverlayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
