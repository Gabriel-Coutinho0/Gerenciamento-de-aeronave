import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { AircraftService } from '../../services/aircraft.service';

import { Aircraft } from '../../utils/interfaces/aircraft';
import { Dashboard } from '../../utils/interfaces/dashboard';

import { AircraftDialogComponent } from '../../components/aircraft-dialog/aircraft-dialog';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog';
import { AircraftFormComponent } from '../../components/aircraft-form/aircraft-form';
import { DashboardComponent } from '../../components/dashboard/dashboard';
import { MarcaCardComponent } from '../../components/marca/marca-card';
import { SearchComponent } from '../../components/search/search';
import { AircraftTableComponent } from '../../components/aircraft-table/aircraft-table';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    AircraftFormComponent,
    DashboardComponent,
    MarcaCardComponent,
    SearchComponent,
    AircraftTableComponent,
  ],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent implements OnInit {
  private readonly aircraftService = inject(AircraftService);
  private readonly dialog = inject(MatDialog);

  dashboard = signal<Dashboard | null>(null);

  aircrafts = signal<Aircraft[]>([]);

  ngOnInit(): void {
    this.loadInitialData();
  }

  private loadInitialData(): void {
    this.loadAircraft();
    this.loadDashboard();
  }

  private loadDashboard(): void {
    this.aircraftService.dashboard().subscribe({
      next: (dashboard) => this.dashboard.set(dashboard),
      error: (error) => console.error('Erro ao carregar dashboard', error),
    });
  }

  private loadAircraft(): void {
    this.aircraftService.getAircraft().subscribe({
      next: (aircrafts) => this.aircrafts.set(aircrafts),
      error: (error) => console.error('Erro ao carregar aeronaves', error),
    });
  }

  saveAircraft(aircraft: Aircraft): void {
    this.aircraftService.save(aircraft).subscribe({
      next: () => this.loadInitialData(),
      error: (error) => console.error(error),
    });
  }

  searchAircraft(term: string): void {
    if (!term.trim()) {
      this.loadAircraft();
      return;
    }

    this.aircraftService.search(term).subscribe({
      next: (aircrafts) => this.aircrafts.set(aircrafts),
      error: (error) => console.error('Erro ao pesquisar aeronaves', error),
    });
  }

  delete(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      data: {
        title: 'Excluir aeronave',
        message: 'Deseja realmente excluir esta aeronave?',
      },
    });

    dialogRef.afterClosed().subscribe((confirmed) => {
      if (!confirmed) {
        return;
      }

      this.aircraftService.delete(id).subscribe({
        next: () => this.loadInitialData(),
        error: (error) => {
          console.error('Erro ao excluir aeronave.', error);
        },
      });
    });
  }

  edit(aircraft: Aircraft): void {
    const dialogRef = this.dialog.open(AircraftDialogComponent, {
      width: '650px',
      data: aircraft,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (!result) return;

      this.aircraftService.update(result.id!, result).subscribe(() => this.loadInitialData());
    });
  }

  newAircraft(): void {
    const dialogRef = this.dialog.open(AircraftDialogComponent, {
      width: '650px',
      data: null,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (!result) return;

      this.aircraftService.save(result).subscribe(() => this.loadInitialData());
    });
  }
}
