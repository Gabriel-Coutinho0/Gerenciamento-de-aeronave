import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AircraftTable } from './aircraft-table';

describe('AircraftTable', () => {
  let component: AircraftTable;
  let fixture: ComponentFixture<AircraftTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AircraftTable],
    }).compileComponents();

    fixture = TestBed.createComponent(AircraftTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
