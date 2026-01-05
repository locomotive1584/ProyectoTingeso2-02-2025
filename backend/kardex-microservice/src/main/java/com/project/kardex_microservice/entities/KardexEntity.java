package com.project.kardex_microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "kardex")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KardexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long toolId;

    @Column(nullable = false)
    private Long ingress;

    @Column(nullable = false)
    private Long devolutions;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Long loans;

    @Column(nullable = false)
    private Long discharged;

    @Column(nullable = false)
    private Long repairing;

    @Column(nullable = false)
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDevolutions() {
        return devolutions;
    }

    public void setDevolutions(Long devolutions) {
        this.devolutions = devolutions;
    }

    public Long getDischarged() {
        return discharged;
    }

    public void setDischarged(Long discharged) {
        this.discharged = discharged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIngress() {
        return ingress;
    }

    public void setIngress(Long ingress) {
        this.ingress = ingress;
    }

    public Long getLoans() {
        return loans;
    }

    public void setLoans(Long loans) {
        this.loans = loans;
    }

    public Long getRepairing() {
        return repairing;
    }

    public void setRepairing(Long repairing) {
        this.repairing = repairing;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }
}
