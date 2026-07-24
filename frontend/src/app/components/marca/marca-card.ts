import { Component, input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';

import { Dashboard } from '../../utils/interfaces/dashboard';

@Component({
  selector: 'app-marca-card',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './marca-card.html',
  styleUrl: './marca-card.css',
})
export class MarcaCardComponent {
  marca = input.required<Dashboard['aircraftByMarca']>();
}
