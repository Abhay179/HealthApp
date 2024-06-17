package com.HealthApp.HealthApp.Authority;

import com.HealthApp.HealthApp.Authentication.Utils.JwtUtils;
import com.HealthApp.HealthApp.Patient.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SelfOrProviderAspect {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PatientRepository patientRepository;

    @Before("@annotation(com.HealthApp.HealthApp.Authority.SelfOrProvider) && args(ID,..)")
    public void checkToken(String ID) {
        System.out.println("CHECKING OF TOKEN AUTHORITES BASED ON ID");
        System.out.println(ID);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new RuntimeException("Request attributes are null. Make sure this aspect is executed in a web context.");
        }
        HttpServletRequest request = attributes.getRequest();
        if (request == null) {
            throw new RuntimeException("HttpServletRequest is null.");
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String token = header.substring(7);
        if (token == null || jwtUtils.extractEmail(token) == null) {
            throw new RuntimeException("Credentials are invalid or access is not allowed");
        }

        if(jwtUtils.extractType(token).toLowerCase().equals("patient")){
            if (!ID.equals(patientRepository.findByEmail(jwtUtils.extractEmail(token)).getId())) {
                throw new RuntimeException("ACCESS DENIED WITH THIS USERID");
            }
        }


    }

}