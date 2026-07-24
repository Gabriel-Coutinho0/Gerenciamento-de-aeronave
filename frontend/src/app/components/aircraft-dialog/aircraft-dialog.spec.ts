import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AircraftDialog } from './aircraft-dialog';

describe('AircraftDialog', () => {
  let component: AircraftDialog;
  let fixture: ComponentFixture<AircraftDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AircraftDialog],
    }).compileComponents();

    fixture = TestBed.createComponent(AircraftDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
