import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AircraftForm } from './aircraft-form';

describe('AircraftForm', () => {
  let component: AircraftForm;
  let fixture: ComponentFixture<AircraftForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AircraftForm],
    }).compileComponents();

    fixture = TestBed.createComponent(AircraftForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
