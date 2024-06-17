package com.HealthApp.HealthApp.Surgery;

import com.HealthApp.HealthApp.Surgery.Data.SurgeryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurgeryRepository extends MongoRepository<SurgeryEntity, String> {
}
