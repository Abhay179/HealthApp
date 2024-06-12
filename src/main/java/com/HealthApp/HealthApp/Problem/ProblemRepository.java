package com.HealthApp.HealthApp.Problem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository  extends MongoRepository<ProblemEntity ,String> {
}
