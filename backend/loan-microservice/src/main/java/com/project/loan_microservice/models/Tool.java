package com.project.loan_microservice.models;

public class Tool {

    private Long id;

    private String name;

    private String category;

    private int stock;

    private int repositionCost;

    private int dailyCost;

    private  int dailyFine;

    public String getCategory() {
        return category;
    }

    public int getDailyCost() {
        return dailyCost;
    }

    public int getDailyFine() {
        return dailyFine;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRepositionCost() {
        return repositionCost;
    }

    public int getStock() {
        return stock;
    }
}
