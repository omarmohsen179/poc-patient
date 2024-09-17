import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../patient.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-create',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './patient-create.component.html',
  styleUrl: './patient-create.component.scss',
})
export class PatientCreateComponent implements OnInit {
  patientForm: FormGroup;
  patientId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private patientService: PatientService
  ) {
    this.patientForm = this.fb.group({
      name: ['', Validators.required],
      birthdate: ['', Validators.required],
      gender: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern(/^[\d-]+$/)]],
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.patientId = params['id'] || null;
      if (this.patientId) {
        this.loadPatientData(this.patientId);
      }
    });
  }

  loadPatientData(id: string): void {
    this.patientService.getPatientById(id).subscribe((patient) => {
      this.patientForm.patchValue(patient);
    });
  }

  onSubmit(): void {
    if (this.patientForm.valid) {
      if (this.patientId) {
        this.patientService
          .updatePatient(this.patientId, this.patientForm.value)
          .subscribe(() => {
            this.router.navigate(['/search']);
          });
      } else {
        this.patientService
          .createPatient(this.patientForm.value)
          .subscribe(() => {
            this.router.navigate(['/search']);
          });
      }
    }
  }
}
