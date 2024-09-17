package org.example.dataservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/upsert")
    public ResponseEntity<PatientFHIR> upsertPatient(@RequestBody PatientFHIR patientFHIR) {
        PatientFHIR upsertedPatient = patientService.upsertPatient(patientFHIR);
        return ResponseEntity.ok(upsertedPatient);
    }
}