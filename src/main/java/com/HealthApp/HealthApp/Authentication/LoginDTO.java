package com.HealthApp.HealthApp.Authentication;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType usertype;
}
