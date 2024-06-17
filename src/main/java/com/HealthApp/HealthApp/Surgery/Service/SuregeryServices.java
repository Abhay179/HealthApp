package com.HealthApp.HealthApp.Surgery.Service;

import com.HealthApp.HealthApp.Patient.Service.PatientService;
import com.HealthApp.HealthApp.Surgery.Data.SurgeryDTO;
import com.HealthApp.HealthApp.Surgery.Data.SurgeryEntity;
import com.HealthApp.HealthApp.Surgery.SurgeryRepository;
import com.HealthApp.HealthApp.Surgery.Data.SurgeryStatus;
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
        private PatientService patientService;

        public List<SurgeryEntity> getAll(String pid){
                if(patientService.getById(pid)==null){
                    throw  new RuntimeException("USER DOES NOT EXIST");
                }
                return surgeryRepository.findAll();
        }

        public SurgeryEntity getById(String pid , String id){
            if(!pid.equals(surgeryRepository.findById(id).orElse(null).getPatient().getId())){
                new RuntimeException("USER DOES NOT EXISTS OR RESTRICTED");
            }
            return surgeryRepository.findById(id).orElse(null);
        }

        public SurgeryEntity create(String pid , SurgeryDTO data){
            if(patientService.getById(pid)==null){
                throw new RuntimeException("PATIENT DOES NOT EXIST");
            }
            SurgeryEntity surgery=new SurgeryEntity();

            ModelMapper model=new ModelMapper();
            model.getConfiguration().setSkipNullEnabled(true);
            model.map(data,surgery);

            surgery.setCreatedDate(LocalDateTime.now());
            surgery.setUpdatedDate(LocalDateTime.now());
            surgery.setPatient(patientService.getById(pid));
            surgery.setSurgeryStatus(SurgeryStatus.PENDING);
            surgeryRepository.save(surgery);
            return surgery;
        }

    public SurgeryEntity update(String pid , String id , SurgeryDTO data){
        SurgeryEntity current=surgeryRepository.findById(id).orElseThrow(()->new RuntimeException("USER OR SURGERY ID DOES NOT EXISTS"+id));
        if(pid.equals(current.getPatient().getId())){
            ModelMapper model=new ModelMapper();
            model.getConfiguration().setSkipNullEnabled(true);
            model.map(data,current);
            current.setUpdatedDate(LocalDateTime.now());
            surgeryRepository.save(current);
        }
        else{
            throw new RuntimeException("USER IS NOT PERMITTED TO UPDATE");
        }
        return current;

    }

    public boolean deleteById(String pid ,String id){
        SurgeryEntity surgery=surgeryRepository.findById(id).orElse(null);
        if(surgery !=null && (surgery.getPatient().getId()).equals(pid) ){
            surgeryRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("INVALID CRENDENTIAL OR DEMAND");
    }


}
