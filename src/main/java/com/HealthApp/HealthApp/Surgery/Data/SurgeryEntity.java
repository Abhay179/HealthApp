package com.HealthApp.HealthApp.Surgery.Data;

import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Document("SurgeryEntity")
public class SurgeryEntity {
    private String name;
    private String description;
    private Date surgeryDate;

    @Enumerated(EnumType.STRING)
    private SurgeryStatus surgeryStatus;

    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
    @DBRef
    private PatientEntity patient;

}
