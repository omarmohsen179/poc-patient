package org.example.searchservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String id;
    private String name;
    private String birthdate;
    private String gender;
    private String phone;
}
