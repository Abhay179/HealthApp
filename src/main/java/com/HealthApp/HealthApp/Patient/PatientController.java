package com.HealthApp.HealthApp.Patient;

import com.HealthApp.HealthApp.Authority.IsProvider;
import com.HealthApp.HealthApp.Authority.SelfOrProvider;
import com.HealthApp.HealthApp.Patient.Data.PateintsDTO;
import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import com.HealthApp.HealthApp.Patient.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;



    @GetMapping
    @IsProvider
    public List<PatientEntity> getAll(HttpServletRequest request){
        return patientService.getAll();
    }

    @GetMapping("/{patientID}")
    @SelfOrProvider
    public PatientEntity getById(@PathVariable String patientID){
        return patientService.getById(patientID);
    }



    @PutMapping("/{patientID}")
    @SelfOrProvider
    public PatientEntity update(HttpServletRequest request ,@PathVariable String patientID , @RequestBody PateintsDTO data){

        patientService.updateById(request,patientID, data);
        return patientService.getById(patientID);
    }


    @DeleteMapping("/{patientID}")
    @SelfOrProvider
    public  boolean delete(@PathVariable  String  ID){
        return patientService.deleteById(ID);

    }
}
