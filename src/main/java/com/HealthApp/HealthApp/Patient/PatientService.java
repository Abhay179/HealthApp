package com.HealthApp.HealthApp.Patient;

import com.HealthApp.HealthApp.PateintsDTO;
import org.bson.types.ObjectId;
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

    public PatientEntity getById(ObjectId id){
        return  repo.findById(id).orElseThrow(()->new RuntimeException("USER DOES NOT EXIST"+id));
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

    public boolean deleteById(ObjectId id){
        PatientEntity user=repo.findById(id).orElse(null);
        if(user!=null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateById(ObjectId id , PateintsDTO updates){
        PatientEntity old=repo.findById(id).orElseThrow(()->new RuntimeException("USER DOES NOT EXISTS IN DATABASE"+id));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(updates,old);

        old.setUpdatedDate(LocalDateTime.now());
        repo.save(old);

        return true;
    }






}
