package com.project.client_microservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private Long id;

    private Date initialDate;

    private Date agreedDate;

    private Long toolId;

    private Long unitId;

    private Long clientId;
}

