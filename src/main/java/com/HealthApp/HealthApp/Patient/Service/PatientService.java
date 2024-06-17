package com.HealthApp.HealthApp.Patient.Service;

import com.HealthApp.HealthApp.Patient.Data.PateintsDTO;
import com.HealthApp.HealthApp.Patient.Data.PatientEntity;
import com.HealthApp.HealthApp.Patient.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repo;

    public List<PatientEntity> getAll(){
        return repo.findAll();
    }

    public PatientEntity getById(String id){
        return  repo.findById(id).orElse(null);
    }

    public PatientEntity findByEmail(String email){
        return repo.findByEmail(email);
    }

    public Boolean createPatient(PateintsDTO data){

        PatientEntity patientEntity = new PatientEntity();

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(data,patientEntity);

        patientEntity.setCreatedDate(LocalDateTime.now());
        patientEntity.setUpdatedDate(LocalDateTime.now());

        repo.save(patientEntity);

        return true;
    }

    public boolean deleteById(String id){
        PatientEntity user=repo.findById(id).orElse(null);
        if(user!=null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public PatientEntity updateById(HttpServletRequest request,String id , PateintsDTO updates){
        String type= (String) request.getAttribute("userType");
        System.out.println("***************"+type);
        PatientEntity old=repo.findById(id).orElseThrow(()->new RuntimeException("USER DOES NOT EXISTS IN DATABASE"+id));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(updates,old);

        old.setUpdatedDate(LocalDateTime.now());
        repo.save(old);

        return old;
    }






}
