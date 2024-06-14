package com.HealthApp.HealthApp.Provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("Provider")
public class ProviderEntity {
    @Id
    private String id;
    private  String firstName;
    private  String lastName;
    private  String title;
    private  String dateOfBirth;
    private  String email;
    private String password;
    private  String mobilePhone;
    private LocalDateTime updatedDate;
    private  LocalDateTime createdDate;



}
