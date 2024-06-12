package com.HealthApp.HealthApp.Surgery;

import com.HealthApp.HealthApp.Patient.PatientRepository;
import com.HealthApp.HealthApp.Problem.ProblemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SuregeryServices {
        @Autowired
        private SurgeryRepository surgeryRepository;
        @Autowired
        private PatientRepository patientRepository;

        public List<SurgeryEntity> getAll(){
                return surgeryRepository.findAll();
        }

        public SurgeryEntity getById(String Pid , String id){
            if(!Pid.equals(surgeryRepository.findById(id).orElse(null).getPatient().getId())){
                new RuntimeException("USER DOES NOT EXISTS OR RESTRICTED");
            }
            return surgeryRepository.findById(id).orElse(null);
        }

        public SurgeryEntity create(String Pid , SurgeryDTO data){
            SurgeryEntity surgery=new SurgeryEntity();

            ModelMapper model=new ModelMapper();
            model.getConfiguration().setSkipNullEnabled(true);
            model.map(data,surgery);

            surgery.setCreatedDate(LocalDateTime.now());
            surgery.setUpdatedDate(LocalDateTime.now());
            surgery.setPatient(patientRepository.findById(Pid).orElse(null));
            surgery.setSurgeryStatus(SurgeryStatus.PENDING);
            surgeryRepository.save(surgery);
            return surgery;
        }

    public SurgeryEntity update(String Pid , String id , SurgeryDTO data){
        SurgeryEntity old=surgeryRepository.findById(id).orElseThrow(()->new RuntimeException("USER ID DOES NOT EXISTS"+id));
        if(Pid.equals(old.getPatient().getId())){
            ModelMapper model=new ModelMapper();
            model.getConfiguration().setSkipNullEnabled(true);
            model.map(data,old);
            old.setUpdatedDate(LocalDateTime.now());
            surgeryRepository.save(old);
        }
        else{
            throw new RuntimeException("USER IS NOT PERMITTED TO UPDATE");
        }
        return old;

    }

    public boolean deleteById(String Pid ,String id){
        SurgeryEntity surgery=surgeryRepository.findById(id).orElse(null);
        if(surgery !=null && (surgery.getPatient().getId()).equals(Pid) ){
            surgeryRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
