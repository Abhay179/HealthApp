package com.HealthApp.HealthApp.Authentication;

import com.HealthApp.HealthApp.Patient.PateintsDTO;
import com.HealthApp.HealthApp.Patient.PatientService;
import com.HealthApp.HealthApp.Provider.ProviderServices;
import com.HealthApp.HealthApp.Provider.providerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class CreateUser {
    @Autowired
    private PatientService patientService;
    @Autowired
    private ProviderServices providerServices;

    @PostMapping()
    public boolean create(@RequestBody providerDTO data ){
        if(data.getTitle()==null){
            ModelMapper modelMapper=new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
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
