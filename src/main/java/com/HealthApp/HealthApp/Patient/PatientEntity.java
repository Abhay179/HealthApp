package com.HealthApp.HealthApp.Patient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("Patient")
public class PatientEntity {
    @Id
    private String id;
    private  String firstName;
    private  String lastName;
    private  String dateOfBirth;
    private  String email;
    private  String mobilePhone;
    private LocalDateTime updatedDate;
    private  LocalDateTime createdDate;
}
