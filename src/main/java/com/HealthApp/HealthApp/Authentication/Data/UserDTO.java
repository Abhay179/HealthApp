package com.HealthApp.HealthApp.Authentication.Data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @Enumerated(EnumType.STRING)
    private UserType type;
    private  String firstName;
    private  String lastName;
    private  String dateOfBirth;
    private  String email;
    private String password;
    private  String mobilePhone;
    private  String title;


}
