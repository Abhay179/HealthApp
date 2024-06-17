package com.HealthApp.HealthApp.Authentication;

import com.HealthApp.HealthApp.Authentication.Data.LoginDTO;
import com.HealthApp.HealthApp.Authentication.Data.UserDTO;
import com.HealthApp.HealthApp.Utils.JwtUtils;
import com.HealthApp.HealthApp.Patient.Data.PateintsDTO;
import com.HealthApp.HealthApp.Patient.Service.PatientService;
import com.HealthApp.HealthApp.Provider.Data.ProviderDTO;
import com.HealthApp.HealthApp.Provider.Service.ProviderServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthenticationController {

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
        String type=data.getType().toString();
        if(type.toLowerCase().equals("patient")){
            data.setTitle(null);
            PateintsDTO patient=new PateintsDTO();
            model.map(data,patient);
            patientService.createPatient(patient);
        }
        else if (type.toLowerCase().equals("provider")){
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
