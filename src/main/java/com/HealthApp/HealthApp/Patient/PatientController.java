package com.HealthApp.HealthApp.Patient;

import com.HealthApp.HealthApp.PateintsDTO;
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
    public PatientEntity getById(@PathVariable  ObjectId ID){
        return patientService.getById(ID);
    }



    @PutMapping("/{ID}")
    public boolean update(@PathVariable  ObjectId ID , @RequestBody PateintsDTO data){
        return patientService.updateById(ID, data);
    }


    @DeleteMapping("/{ID}")
    public  boolean delete(@PathVariable  ObjectId ID){
        return patientService.deleteById(ID);

    }




}
