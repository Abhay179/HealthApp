package com.HealthApp.HealthApp.Problem.Service;

import com.HealthApp.HealthApp.Patient.Service.PatientService;
import com.HealthApp.HealthApp.Problem.Data.ProblemDTO;
import com.HealthApp.HealthApp.Problem.Data.ProblemEntity;
import com.HealthApp.HealthApp.Problem.Data.ProblemStatus;
import com.HealthApp.HealthApp.Problem.ProblemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private PatientService patientService;

    public List<ProblemEntity> getAll(String pid){
        if(patientService.getById(pid)==null){
            throw new RuntimeException("PATIENT DOES NOT EXISTS");
        }
        return problemRepository.findAll();
    }

    public ProblemEntity getByid(String pid ,String id){
        if(!pid.equals(problemRepository.findById(id).orElse(null).getPatientEntity().getId())){
            throw new RuntimeException("USER DOES NOT EXISTS  OR RESTRICTED");
        }
        return problemRepository.findById(id).orElse(null);
    }

    public boolean deleteById(String pid ,String id){
        ProblemEntity problem=problemRepository.findById(id).orElse(null);
        if(problem !=null && (problem.getPatientEntity().getId()).equals(pid) ){
            problemRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("INVALID CREDENTIALS OR INVALID DEMAND");

    }
    public ProblemEntity create(String pid , ProblemDTO data){
        if(patientService.getById(pid)==null){
            throw new RuntimeException("PATIENT DOES NOT EXIST");
        }
        ProblemEntity problem=new ProblemEntity();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(data,problem);
        problem.setProblemStatus(ProblemStatus.PENDING);
        problem.setPatientEntity(patientService.getById(pid));
        problem.setCreatedDate(LocalDateTime.now());
        problem.setUpdatedDate(LocalDateTime.now());
        problemRepository.save(problem);
        return problem;
    }

    public ProblemEntity update(String pid ,String id , ProblemDTO data){
        ProblemEntity old=problemRepository.findById(id).orElseThrow(()->new RuntimeException("ID DOES NOT EXISTS "+id));
        if(pid.equals(old.getPatientEntity().getId())) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(data, old);
            old.setUpdatedDate(LocalDateTime.now());
            problemRepository.save(old);

        }
        else{
            throw  new RuntimeException("USER DOES NOT HAVE ACCESS");
        }
        return old;
    }
}
