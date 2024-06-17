package com.HealthApp.HealthApp.Surgery;

import com.HealthApp.HealthApp.Surgery.Data.SurgeryDTO;
import com.HealthApp.HealthApp.Surgery.Data.SurgeryEntity;
import com.HealthApp.HealthApp.Surgery.Service.SuregeryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class SurgeryController {
    @Autowired
    private SuregeryServices suregeryServices;
    @GetMapping("/{patientID}/surgery")
    public List<SurgeryEntity> getAll(@PathVariable String patientID){
        return suregeryServices.getAll(patientID);
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
    @PutMapping("{providerID}/surgery/{surgeryID}/status")
    public SurgeryEntity updateStatus(@PathVariable String providerID, @PathVariable String surgeryID , @RequestBody SurgeryDTO data){
        return suregeryServices.updateStatus(providerID, surgeryID,data);
    }

}
