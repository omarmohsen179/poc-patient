import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PatientService } from '../patient.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-patient-search',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './patient-search.component.html',
  styleUrl: './patient-search.component.scss',
})
export class PatientSearchComponent implements OnInit {
  searchForm: FormGroup = new FormGroup({});
  patients: any[] = [];
  isLoading = false;
  constructor(private fb: FormBuilder, private patientService: PatientService) {
    this.searchForm = this.fb.group({
      query: ['', [Validators.minLength(3)]],
    });
  }
  ngOnInit() {
    this.searchForm = this.fb.group({
      query: [''],
    });
  }
  onSearch(): void {
    if (this.searchForm.valid) {
      this.isLoading = true;
      const query = this.searchForm.contains('query')
        ? this.searchForm.get('query')?.value
        : '';
      console.log(query);
      this.patientService.searchPatients(query).subscribe(
        (response) => {
          this.patients = response;
          this.isLoading = false;
        },
        (error) => {
          console.error('Error searching patients:', error);
          this.isLoading = false;
        }
      );
    }
  }
  onUpdate(patient: any): void {
    if (this.searchForm.valid) {
      this.isLoading = true;
      const query = this.searchForm.contains('query')
        ? this.searchForm.get('query')?.value
        : '';
      this.patientService.searchPatients(query).subscribe(
        (response) => {
          this.patients = response;
          this.isLoading = false;
        },
        (error) => {
          console.error('Error searching patients:', error);
          this.isLoading = false;
        }
      );
    }
  }
}
