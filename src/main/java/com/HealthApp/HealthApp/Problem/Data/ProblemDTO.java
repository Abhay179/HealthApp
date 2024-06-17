package com.HealthApp.HealthApp.Problem.Data;

import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Getter
@Setter
public class ProblemDTO {
    private String name;
    private String description;
    private Date startdate;
    @Enumerated(EnumType.STRING)
    private ProblemStatus problemStatus;
    private String comment;
    @DBRef
    private PatientEntity patientEntity;

}
