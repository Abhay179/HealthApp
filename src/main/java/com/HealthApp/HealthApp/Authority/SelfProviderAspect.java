package com.HealthApp.HealthApp.Authority;

import com.HealthApp.HealthApp.Authentication.Utils.JwtUtils;
import com.HealthApp.HealthApp.Patient.PatientRepository;
import com.HealthApp.HealthApp.Provider.ProviderRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SelfProviderAspect {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ProviderRepository providerRepository;

    @Before("@annotation(com.HealthApp.HealthApp.Authority.SelfProvider) && args(ID,..)")
    public void checkToken(String ID) {
        System.out.println("CHECKING OF TOKEN PROVIDERS AUTHORITIES  BASED ON ID");
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

        if(jwtUtils.extractType(token).toLowerCase().equals("provider")){
            if (!ID.equals(providerRepository.findByEmail(jwtUtils.extractEmail(token)).getId())) {
                throw new RuntimeException("ACCESS DENIED WITH THIS USERID");
            }
        }
        else{
            throw new RuntimeException("USER IS NOT PROVIDER");
        }


    }

}