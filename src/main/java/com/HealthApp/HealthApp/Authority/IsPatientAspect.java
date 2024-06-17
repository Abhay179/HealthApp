package com.HealthApp.HealthApp.Authority;

import com.HealthApp.HealthApp.Utils.JwtUtils;
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
public class IsPatientAspect {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PatientRepository patientRepository ;
    @Before("@annotation(com.HealthApp.HealthApp.Authority.IsPatient) ")
    public void isProvider() {
        System.out.println("CHECKING OF TOKEN AUTHORITES BASED ON ID");
//        System.out.println(ID);
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
            String email=jwtUtils.extractEmail(token);
            if(email==null || patientRepository.findByEmail(email)==null){
                throw new RuntimeException("INVALID EMAIL OR NO SUCH PATIENT WITH THIS EMAIL");
            }
        }
        else{
            throw new RuntimeException("PROVIDERS ARE NOT ALLOWED TO ACCESS THIS");
        }


    }

}