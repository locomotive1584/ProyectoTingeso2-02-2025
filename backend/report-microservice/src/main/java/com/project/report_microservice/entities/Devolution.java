package com.project.report_microservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Devolution {

    private Long id;

    private LocalDate devolutionDate;

    private Long loanId;

    public LocalDate getDevolutionDate() {
        return devolutionDate;
    }

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }
}
