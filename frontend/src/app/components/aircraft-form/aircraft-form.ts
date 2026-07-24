import { Component, inject, output } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormBuilder, ReactiveFormsModule, Validators, FormGroupDirective } from '@angular/forms';
import { ViewChild } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { Aircraft } from '../../utils/interfaces/aircraft';

@Component({
  selector: 'app-aircraft-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
  ],
  templateUrl: './aircraft-form.html',
  styleUrl: './aircraft-form.css',
})
export class AircraftFormComponent {
  @ViewChild(FormGroupDirective)
  private formDirective!: FormGroupDirective;
  private fb = inject(FormBuilder);

  save = output<Aircraft>();

  form = this.fb.group({
    marca: ['', Validators.required],
    modelo: ['', Validators.required],
    ano: [0, [Validators.required, Validators.min(1903)]],
    vendido: [false],
  });

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const formValue = this.form.getRawValue();

    const aircraft: Aircraft = {
      marca: formValue.marca ?? '',
      nome: formValue.modelo ?? '',
      ano: formValue.ano ?? 0,
      vendido: formValue.vendido ?? false,
    };

    this.save.emit(aircraft);

    this.formDirective.resetForm({
      marca: '',
      modelo: '',
      ano: null,
      vendido: false,
    });
  }

  hasError(control: string, error: string): boolean {
    const field = this.form.get(control);

    return !!field && field.hasError(error) && field.touched;
  }
}
