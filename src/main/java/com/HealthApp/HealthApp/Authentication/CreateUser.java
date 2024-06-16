package com.HealthApp.HealthApp.Authentication;

import com.HealthApp.HealthApp.Patient.PateintsDTO;
import com.HealthApp.HealthApp.Patient.PatientService;
import com.HealthApp.HealthApp.Problem.ProblemDTO;
import com.HealthApp.HealthApp.Provider.ProviderDTO;
import com.HealthApp.HealthApp.Provider.ProviderServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class CreateUser {

    private static final PasswordEncoder passwardEncoder=new BCryptPasswordEncoder();
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PatientService patientService;
    @Autowired
    private ProviderServices providerServices;
    @Autowired
    UserDetailConfiguration userDetailConfiguration;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public boolean create(@RequestBody UserDTO data ){
        data.setPassword(passwardEncoder.encode(data.getPassword()));
        ModelMapper model=new ModelMapper();
        model.getConfiguration().setSkipNullEnabled(true);
        if(data.isPatient()==true){
            data.setTitle(null);
            PateintsDTO patient=new PateintsDTO();
            model.map(data,patient);
            patientService.createPatient(patient);
        }
        else if (data.isPatient()==false){
            ProviderDTO provider=new ProviderDTO();
            model.map(data,provider);
            providerServices.createProvider(provider);

        }
        else{
            throw new RuntimeException("USER TYPE NOT DEFINED");
        }
        return true;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO data){
        try {
            authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
//          UserDetails is the inbuild Interface
            UserDetails user = userDetailConfiguration.loadUserByUsername(data.getEmail());
            String jwt = jwtUtils.generateToken(user.getUsername() ,data);
            System.out.println("******************************"+jwt);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("eXCEPTION OCCURED WHICLE CREATING AUTHENTICATION");

            return new ResponseEntity<>("incorrect username and password", HttpStatus.BAD_REQUEST);
        }

    }
}
