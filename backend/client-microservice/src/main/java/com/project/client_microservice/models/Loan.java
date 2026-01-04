package com.project.client_microservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private Long id;

    private LocalDate initialDate;

    private LocalDate agreedDate;

    private Long toolId;

    private Long unitId;

    private Long clientId;
}
