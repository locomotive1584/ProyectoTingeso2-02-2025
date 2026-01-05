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
}
