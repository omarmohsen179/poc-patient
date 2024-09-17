package org.example.dataservice;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SearchServiceClient {

    private final RestTemplate restTemplate;

    @Value("${search-service.base-url}")
    private String searchServiceBaseUrl;

    public void upsertPatientInSearchService(PatientFHIR patientFHIR) {
        String url = searchServiceBaseUrl + "/patients/upsert";
        restTemplate.postForEntity(url, patientFHIR, Void.class);
    }
}