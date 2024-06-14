package com.HealthApp.HealthApp.Patient;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;


    @GetMapping
    public List<PatientEntity> getAll(){
        return patientService.getAll();
    }

    @GetMapping("/{ID}")
    public PatientEntity getById(@PathVariable String ID){
        return patientService.getById(ID);
    }



    @PutMapping("/{ID}")
    public PatientEntity update(@PathVariable String ID , @RequestBody PateintsDTO data){
        patientService.updateById(ID, data);
        return patientService.getById(ID);
    }


    @DeleteMapping("{ID}")
    public  boolean delete(@PathVariable  String  ID){
        return patientService.deleteById(ID);

    }

//    @GetMapping("{email}")
//    public PatientEntity findByEmail(String email){
//        return  patientService.findByEmail(email);
//    }




}
