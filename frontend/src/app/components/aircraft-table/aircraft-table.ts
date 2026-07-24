import { Component, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { Aircraft } from '../../utils/interfaces/aircraft';

@Component({
  selector: 'app-aircraft-table',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './aircraft-table.html',
  styleUrl: './aircraft-table.css',
})
export class AircraftTableComponent {
  aircrafts = input.required<Aircraft[]>();

  edit = output<Aircraft>();

  delete = output<number>();

  displayedColumns = ['id', 'marca', 'nome', 'ano', 'vendido', 'acoes'];

  onEdit(aircraft: Aircraft): void {
    this.edit.emit(aircraft);
  }

  onDelete(id: number): void {
    this.delete.emit(id);
  }
}
