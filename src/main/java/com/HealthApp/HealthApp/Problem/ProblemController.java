package com.HealthApp.HealthApp.Problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("/getProblems")
    public List<?> getall(){
        return problemService.getAll();
    }
    @GetMapping("/{patientID}/problem/{id}")
    public ProblemEntity getById(@PathVariable String patientID,@PathVariable String id){
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

}
