package com.HealthApp.HealthApp.Authentication.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private boolean patient;
    private  String firstName;
    private  String lastName;
    private  String dateOfBirth;
    private  String email;
    private String password;
    private  String mobilePhone;
    private  String title;


}
