import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarPlayComponent } from './calendar-play.component';

describe('CalendarPlayComponent', () => {
  let component: CalendarPlayComponent;
  let fixture: ComponentFixture<CalendarPlayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarPlayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarPlayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
