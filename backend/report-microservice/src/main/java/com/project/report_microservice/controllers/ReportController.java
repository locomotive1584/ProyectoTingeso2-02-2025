package com.project.report_microservice.controllers;

import com.project.report_microservice.entities.PopularityDto;
import com.project.report_microservice.entities.ValidityDto;
import com.project.report_microservice.models.Client;
import com.project.report_microservice.services.QuerryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {
    @Autowired
    QuerryService querryService;


    @GetMapping("/getValidity")
    public ResponseEntity<List<ValidityDto>> getValidity() {
        return ResponseEntity.ok(querryService.getValidityOfLoans());
    }

    @GetMapping("/getClientsWithDelays")
    public ResponseEntity<List<Client>> getClientsWithDelays() {
        return ResponseEntity.ok(querryService.getClientsWithDelays());
    }

    @GetMapping("/getPopularTools")
    public ResponseEntity<List<PopularityDto>> getPopularTools() {
        return ResponseEntity.ok(querryService.toolRanking());
    }
}
