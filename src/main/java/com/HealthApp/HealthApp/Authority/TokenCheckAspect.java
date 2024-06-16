package com.HealthApp.HealthApp.Authority;

import com.HealthApp.HealthApp.Authentication.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class TokenCheckAspect {

    @Autowired
    private JwtUtils jwtUtils;

    @Before("@annotation(com.HealthApp.HealthApp.Authority.CheckToken)")
    public void checkToken() {
        System.out.println("CHECKING OF TOKEN AUTHORITES");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("Request attributes are null. Make sure this aspect is executed in a web context.");
        }

        HttpServletRequest request = attributes.getRequest();
        String header = request.getHeader("Authorization");
        String token = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            if (token != null && jwtUtils.extractType(token) != null && jwtUtils.extractEmail(token) != null) {
                System.out.println(jwtUtils.extractType(token));
                if (!jwtUtils.extractType(token).toLowerCase().equals("provider")) {
                    throw new RuntimeException("ACCESS IS NOT ALLOWED WITH THIS CREDENTIALS");
                }
            } else {
                throw new RuntimeException("CREDENTIALS ACCESS IS NOT ALLOWED");
            }
        } else {
            throw new RuntimeException("INVALID TOKEN AT PATIENT CONTROLLER OR EXPIRED TOKEN");
        }
    }
}
