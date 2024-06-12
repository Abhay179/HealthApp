package com.HealthApp.HealthApp.Provider;

import com.HealthApp.HealthApp.Patient.PatientEntity;
import com.HealthApp.HealthApp.Patient.PatientService;
import com.HealthApp.HealthApp.providerDTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderServices providerServices;
    @Autowired
    private PatientService patientService;



    @GetMapping
    public List<ProviderEntity> getAll(){
        return providerServices.getAll();
    }
    @GetMapping("/{ID}")
    public ProviderEntity getById(@PathVariable ObjectId ID){
        return providerServices.getById(ID);
    }

    @DeleteMapping("/{ID}")
    public  boolean delete(@PathVariable  ObjectId ID){
        return providerServices.deleteById(ID);

    }

    @PutMapping("/{ID}")
    public ProviderEntity update(@PathVariable  ObjectId ID , @RequestBody providerDTO data){
        return providerServices.updateById(ID,  data);
    }

}
