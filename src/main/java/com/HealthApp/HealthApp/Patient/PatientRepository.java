package com.HealthApp.HealthApp.Patient;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientEntity , String> {

}
