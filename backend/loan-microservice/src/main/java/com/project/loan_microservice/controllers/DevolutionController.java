package com.project.loan_microservice.controllers;

import com.project.loan_microservice.entities.DevolutionEntity;
import com.project.loan_microservice.services.DevolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devolutions")
@CrossOrigin("*")
public class DevolutionController {
    @Autowired
    DevolutionService devolutionService;

    @GetMapping("/")
    public ResponseEntity<List<DevolutionEntity>> getAllDevolutions() {
        return ResponseEntity.ok(devolutionService.getAllDevolutions());
    }

    @GetMapping("/cost/{id}")
    public ResponseEntity<Long> getDevolutionCost(@PathVariable long id) {
        return ResponseEntity.ok(devolutionService.calculateCost(id));
    }

    @PostMapping("/")
    public ResponseEntity<DevolutionEntity> createDevolution(@RequestBody long loanId) {
        DevolutionEntity devolutionSaved = devolutionService.createDevolution(loanId);
        return ResponseEntity.ok(devolutionSaved);
    }
}
