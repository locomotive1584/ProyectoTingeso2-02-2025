package com.project.report_microservice.entities;

public class ValidityDto {

    private Loan loan;

    private String state;

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void setState(String state) {
        this.state = state;
    }
}
