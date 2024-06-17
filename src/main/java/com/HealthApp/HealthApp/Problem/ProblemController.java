package com.HealthApp.HealthApp.Problem;

import com.HealthApp.HealthApp.Problem.Data.ProblemDTO;
import com.HealthApp.HealthApp.Problem.Data.ProblemEntity;
import com.HealthApp.HealthApp.Problem.Service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("{patient_id}/problems")
    public List<?> getall(@PathVariable String patient_id){
        return problemService.getAll(patient_id);
    }
    @GetMapping("/{patientID}/problem/{id}")
    public ProblemEntity getById(@PathVariable String patientID, @PathVariable String id){
        return problemService.getByid(patientID , id);
    }
    @PostMapping("{patientID}/problem/create")
    public ProblemEntity create(@PathVariable String patientID,  @RequestBody ProblemDTO data){
        return problemService.create(patientID , data);
    }
    @DeleteMapping("{patientID}/problem/{problemID}")
    public boolean delete (@PathVariable String patientID, @PathVariable String problemID){
        return problemService.deleteById(patientID , problemID);
    }
    @PutMapping("{patientID}/problem/{problemID}")
    public ProblemEntity update(@PathVariable String patientID, @PathVariable String problemID , @RequestBody ProblemDTO data){
        return problemService.update(patientID, problemID,data);
    }
    @PutMapping("{providerID}/problem/{problemID}")
    public ProblemEntity updateStatus(@PathVariable String providerID, @PathVariable String problemID , @RequestBody ProblemDTO data){
        return problemService.updateStatus(providerID, problemID,data);
    }

}
