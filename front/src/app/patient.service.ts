import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private apiUrl = 'http://localhost:8081'; // URL for Search Service
  private apiUrlData = 'http://localhost:8082';
  constructor(private http: HttpClient) {}

  // Create or update a patient
  upsertPatient(patientData: any): Observable<any> {
    return this.http.post(`${this.apiUrlData}/patients/upsert`, patientData);
  }

  // Retrieve a patient by ID
  getPatientById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/patients/${id}`);
  }

  // Search patients by name
  searchPatients(name: string): Observable<any[]> {
    let params = new HttpParams().set('name', name);
    return this.http.get<any[]>(`${this.apiUrl}/patients/search`, { params });
  }
}
