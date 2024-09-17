package org.example.searchservice;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Api(tags = "Patient Management")
public class PatientController {

    private final PatientService patientService;

    // Upsert a patient
    @PostMapping("/upsert")
    @ApiOperation(value = "Upsert a patient (insert or update)")
    public ResponseEntity<Patient> upsertPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.upsertPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    // Search patients by partial name (case insensitive)
    @GetMapping("/search")
    @ApiOperation(value = "Search patients by partial name (case insensitive)")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String name) {
        if (name.length() < 3) {
            return ResponseEntity.badRequest().build();
        }
        List<Patient> patients = patientService.searchPatientsByName(name);
        return ResponseEntity.ok(patients);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    @ApiOperation(value = "Get patient by ID")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
