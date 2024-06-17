package com.HealthApp.HealthApp.Provider;

import com.HealthApp.HealthApp.Provider.Data.ProviderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProviderRepository extends MongoRepository<ProviderEntity, String> {

    ProviderEntity findByEmail(String email);
}
