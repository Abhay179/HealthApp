package com.HealthApp.HealthApp.Problem;

import com.HealthApp.HealthApp.Problem.Data.ProblemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository  extends MongoRepository<ProblemEntity,String> {
}
