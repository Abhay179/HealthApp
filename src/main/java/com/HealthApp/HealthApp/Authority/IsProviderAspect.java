package com.HealthApp.HealthApp.Authority;

import com.HealthApp.HealthApp.Authentication.Utils.JwtUtils;
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
public class IsProviderAspect {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ProviderRepository providerRepository ;
    @Before("@annotation(com.HealthApp.HealthApp.Authority.IsProvider) ")
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

        if(jwtUtils.extractType(token).toLowerCase().equals("provider")){
            String email=jwtUtils.extractEmail(token);
            if(email==null || providerRepository.findByEmail(email)==null){
                throw new RuntimeException("INVALID EMAIL OR NO SUCH PROVIDER WITH THIS EMAIL");
            }
        }
        else{
            throw new RuntimeException("PATIENTS ARE NOT ALLOWED TO ACCESS THIS");
        }


    }

}