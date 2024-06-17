package com.HealthApp.HealthApp.Patient;

import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    PatientEntity findByEmail(String email);
}
