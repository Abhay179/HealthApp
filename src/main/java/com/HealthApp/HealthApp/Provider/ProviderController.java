package com.HealthApp.HealthApp.Provider;

import com.HealthApp.HealthApp.Authority.IsProvider;
import com.HealthApp.HealthApp.Authority.SelfProvider;
import com.HealthApp.HealthApp.Patient.Service.PatientService;
import com.HealthApp.HealthApp.Provider.Data.ProviderDTO;
import com.HealthApp.HealthApp.Provider.Data.ProviderEntity;
import com.HealthApp.HealthApp.Provider.Service.ProviderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderServices providerServices;
    @Autowired
    private PatientService patientService;



    @GetMapping
    @IsProvider
    public List<ProviderEntity> getAll(){
        return providerServices.getAll();
    }

    @GetMapping("/{ID}")
    @SelfProvider
    public ProviderEntity getById(@PathVariable String ID){
        return providerServices.getById(ID);
    }

    @DeleteMapping("/{ID}")
    @SelfProvider
    public  boolean delete(@PathVariable  String ID){
        return providerServices.deleteById(ID);

    }

    @PutMapping("/{ID}")
    @SelfProvider
    public ProviderEntity update(@PathVariable  String ID , @RequestBody ProviderDTO data){
        return providerServices.updateById(ID,  data);
    }

}
