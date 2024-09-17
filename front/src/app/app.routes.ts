import { Routes } from '@angular/router';
import { PatientCreateComponent } from './patient-create/patient-create.component';
import { PatientSearchComponent } from './patient-search/patient-search.component';

export const routes: Routes = [
  { path: 'patient-create', component: PatientCreateComponent },
  { path: 'patient-create/:id', component: PatientCreateComponent }, // For update
  { path: 'patient-search', component: PatientSearchComponent },
  { path: '', redirectTo: '/patient-search', pathMatch: 'full' }, // Default route
];
