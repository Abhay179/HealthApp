package com.HealthApp.HealthApp.Public;

import com.HealthApp.HealthApp.PateintsDTO;
import com.HealthApp.HealthApp.Patient.PatientService;
import com.HealthApp.HealthApp.Provider.ProviderServices;
import com.HealthApp.HealthApp.providerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class CreateUser {
    @Autowired
    private PatientService patientService;
    @Autowired
    private ProviderServices providerServices;

    @PostMapping("/create")
    public boolean create(@RequestBody providerDTO data){
        if(data.getTitle()==null){
            ModelMapper modelMapper=new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true) ;
            PateintsDTO p=new PateintsDTO();
            modelMapper.map(data,p);
            patientService.createPatient(p);
        }
        else{
            providerServices.createProvider(data);
        }
        return true;
    }
}
