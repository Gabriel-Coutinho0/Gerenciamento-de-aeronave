import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';

import { Aircraft } from '../../utils/interfaces/aircraft';

@Component({
  selector: 'app-aircraft-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './aircraft-dialog.html',
  styleUrl: './aircraft-dialog.css',
})
export class AircraftDialogComponent {
  private fb = inject(FormBuilder);
  public aircraft: Aircraft | null;
  form: any;

  constructor(
    @Inject(MAT_DIALOG_DATA)
    aircraft: Aircraft | null,
    private dialogRef: MatDialogRef<AircraftDialogComponent>,
  ) {
    this.aircraft = aircraft;
    this.form = this.fb.group({
      marca: [this.aircraft?.marca?.toUpperCase() ?? '', Validators.required],
      nome: [this.aircraft?.nome ?? '', Validators.required],
      ano: [this.aircraft?.ano ?? 0, [Validators.required, Validators.min(1903)]],
      vendido: [this.aircraft?.vendido ?? false],
    });
  }

  save() {
    if (this.form.invalid) return;

    const value = this.form.getRawValue();

    this.dialogRef.close({
      id: this.aircraft?.id,
      marca: value.marca!,
      nome: value.nome!,
      ano: value.ano!,
      vendido: value.vendido!,
    });
  }

  cancel() {
    this.dialogRef.close();
  }
}
