package com.project.loan_microservice.controllers;

import com.project.loan_microservice.entities.LoanEntity;
import com.project.loan_microservice.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@CrossOrigin("*")
public class LoanController {
    @Autowired
    LoanService loanService;

    @GetMapping("/")
    public ResponseEntity<List<LoanEntity>> getAllLoans() {
        return ResponseEntity.ok(loanService.getLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanEntity> getLoanById(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByClientId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByClientId(id));
    }

    @GetMapping("unit/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByUnitId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByUnitId(id));
    }

    @GetMapping("/tool/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByToolId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByToolId(id));
    }

    @PostMapping("/")
    public ResponseEntity<LoanEntity> createLoan(@RequestBody LoanEntity loan) throws Exception {
        LoanEntity loanSaved = loanService.addLoan(loan);
        return ResponseEntity.ok(loanSaved);
    }
}
