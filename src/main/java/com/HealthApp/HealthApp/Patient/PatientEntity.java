package com.HealthApp.HealthApp.Patient;

import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document("Patient")
public class PatientEntity {
    @Id
    private ObjectId id;
    private  String firstName;
    private  String lastName;
    private  String dateOfBirth;
    private  String email;
    private  String mobilePhone;
    private LocalDateTime updatedDate;
    private  LocalDateTime createdDate;
}
