package com.HealthApp.HealthApp.Authentication;

import com.HealthApp.HealthApp.Patient.PatientEntity;
import com.HealthApp.HealthApp.Patient.PatientRepository;
import com.HealthApp.HealthApp.Patient.PatientService;
import com.HealthApp.HealthApp.Problem.ProblemService;
import com.HealthApp.HealthApp.Provider.ProviderEntity;
import com.HealthApp.HealthApp.Provider.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Configuration
public class UserDetailConfiguration implements UserDetailsService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails;
        if(providerRepository.findByEmail(email)==null){
            if(patientRepository.findByEmail(email)==null){
                throw  new UsernameNotFoundException("USER NOT FOUND WITH THIS EMAIL :"+email);
            }
            else{
                PatientEntity patient=patientRepository.findByEmail(email);
                 userDetails=org.springframework.security.core.userdetails.User.builder()
                        .username(patient.getEmail())
                        .password(patient.getPassword())
                        .build();

            }
        }
        else{
            ProviderEntity provider=providerRepository.findByEmail(email);
             userDetails=org.springframework.security.core.userdetails.User.builder()
                    .username(provider.getEmail())
                    .password(provider.getPassword())
                    .build();
        }
        return  userDetails;
    }
}
