package com.HealthApp.HealthApp.Surgery.Data;

import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import com.HealthApp.HealthApp.Problem.Data.ProblemStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
@Getter
@Setter
public class SurgeryDTO {

    private String name;
    private String description;
    private Date surgeryDate;

    @Enumerated(EnumType.STRING)
    private ProblemStatus problemStatus;

    @DBRef
    private PatientEntity patient;

}
