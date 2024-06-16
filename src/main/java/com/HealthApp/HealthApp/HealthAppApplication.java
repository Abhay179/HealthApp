package com.HealthApp.HealthApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.HealthApp")
public class HealthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthAppApplication.class, args);
	}

}
