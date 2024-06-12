package com.HealthApp.HealthApp.Surgery;

import com.HealthApp.HealthApp.Problem.ProblemDTO;
import com.HealthApp.HealthApp.Problem.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class SurgeryController {
    @Autowired
    private SuregeryServices suregeryServices;
    @GetMapping("/{patientID}/getSurgerys")
    public List<SurgeryEntity> getAll(){
        return suregeryServices.getAll();
    }
    @GetMapping("{patientID}/Surgery/{SurgeryID}")
    public SurgeryEntity getById(@PathVariable  String patientID ,@PathVariable String SurgeryID){
        return suregeryServices.getById(patientID , SurgeryID);
    }
    @PostMapping("{patientID}/surgery/create")
    public SurgeryEntity create(@PathVariable String patientID, @RequestBody SurgeryDTO data){
        return suregeryServices.create(patientID,data);
    }
    @DeleteMapping("{patientID}/surgery/{surgeryID}")
    public boolean delete (@PathVariable String patientID, @PathVariable String surgeryID){
        return suregeryServices.deleteById(patientID , surgeryID);
    }
    @PutMapping("{patientID}/surgery/{surgeryID}")
    public SurgeryEntity update(@PathVariable String patientID, @PathVariable String surgeryID , @RequestBody SurgeryDTO data){
        return suregeryServices.update(patientID, surgeryID,data);
    }

}
