package com.HealthApp.HealthApp.Provider;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProviderRepository extends MongoRepository<ProviderEntity, String> {
}
