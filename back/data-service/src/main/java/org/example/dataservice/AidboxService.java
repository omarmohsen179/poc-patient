package org.example.dataservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AidboxService {

    private final RestTemplate restTemplate;

    @Value("${aidbox.base-url}")
    private String aidboxBaseUrl;

    public PatientFHIR upsertPatientInAidbox(PatientFHIR patientFHIR) {
        String url = aidboxBaseUrl + "/Patient";
        return restTemplate.postForObject(url, patientFHIR, PatientFHIR.class);
    }
}