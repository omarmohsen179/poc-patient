package org.example.dataservice;

import lombok.Data;


@Data
public class PatientFHIR {
    private String id;
    private String name;
    private String birthdate;
    private String gender;
    private String phone;

    // Getters and setters
}
