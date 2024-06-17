package com.HealthApp.HealthApp.Problem.Data;

import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Document("Problems")
public class ProblemEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Date startdate;
    @Enumerated(EnumType.STRING)
    private ProblemStatus problemStatus;
    private String comment;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
    @DBRef
    private PatientEntity patientEntity;

}
