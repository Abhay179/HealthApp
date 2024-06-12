package com.HealthApp.HealthApp.Surgery;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurgeryRepository extends MongoRepository<SurgeryEntity , String> {
}
