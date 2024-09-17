package org.example.searchservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // Upsert patient data (insert or update)
    public Patient upsertPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Find patients by partial name, ignoring case
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    // Get patient by ID
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }
}
