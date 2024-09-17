package org.example.dataservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final AidboxService aidboxService;
    private final SearchServiceClient searchServiceClient;

    // Upsert patient into Aidbox and Search Service
    public PatientFHIR upsertPatient(PatientFHIR patientFHIR) {
        // Upsert patient in Aidbox (FHIR resource storage)
        PatientFHIR savedPatient = aidboxService.upsertPatientInAidbox(patientFHIR);

        // Upsert the patient in Search Service (Elasticsearch/OpenSearch)
        searchServiceClient.upsertPatientInSearchService(savedPatient);

        return savedPatient;
    }
}
