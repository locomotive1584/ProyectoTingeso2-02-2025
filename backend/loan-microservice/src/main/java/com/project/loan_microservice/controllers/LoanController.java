package com.project.loan_microservice.controllers;

import com.project.loan_microservice.entities.LoanEntity;
import com.project.loan_microservice.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans")
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

    @GetMapping("/getByClientId/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByClientId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByClientId(id));
    }

    @GetMapping("/getByUnitId/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByUnitId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByUnitId(id));
    }

    @GetMapping("/tool/{id}")
    public ResponseEntity<List<LoanEntity>> getLoanByToolId(@PathVariable("id") int id) {
        return ResponseEntity.ok(loanService.getLoanByToolId(id));
    }

    @GetMapping("/Between/{date1}/{date2}")
    public ResponseEntity<List<LoanEntity>> getLoanByDate(@PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
        return ResponseEntity.ok(loanService.getLoanByInitialDateBetween(date1, date2));
    }

    @GetMapping("/Before/{date}")
    public ResponseEntity<List<LoanEntity>> getBeforeLoanByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(loanService.getLoanByInitialDateBefore(date));
    }

    @GetMapping("/After/{date}")
    public ResponseEntity<List<LoanEntity>> getAfterLoanByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(loanService.getLoanByInitialDateAfter(date));
    }

    @PostMapping("/")
    public ResponseEntity<LoanEntity> createLoan(@RequestBody LoanEntity loan) throws Exception {
        LoanEntity loanSaved = loanService.addLoan(loan);
        return ResponseEntity.ok(loanSaved);
    }
}
