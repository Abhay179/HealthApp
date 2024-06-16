package com.HealthApp.HealthApp.Patient;

import com.HealthApp.HealthApp.Authentication.JwtUtils;
import com.HealthApp.HealthApp.Authority.CheckToken;
import com.HealthApp.HealthApp.Authority.IdCheck;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    JwtUtils jwtUtils;


    @GetMapping
    @CheckToken
    public List<PatientEntity> getAll(HttpServletRequest request){
        return patientService.getAll();
    }

    @GetMapping("/{ID}")
    @IdCheck
    public PatientEntity getById(@PathVariable String ID){
        return patientService.getById(ID);
    }



    @PutMapping("/{ID}")
    @IdCheck
    public PatientEntity update(@PathVariable String ID , @RequestBody PateintsDTO data){
        patientService.updateById(ID, data);
        return patientService.getById(ID);
    }


    @DeleteMapping("{ID}")
    @IdCheck
    public  boolean delete(@PathVariable  String  ID){
        return patientService.deleteById(ID);

    }





}
