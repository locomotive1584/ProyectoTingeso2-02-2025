package com.project.kardex_microservice.controllers;

import com.project.kardex_microservice.entities.MovementEntity;
import com.project.kardex_microservice.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movements")
@CrossOrigin("*")
public class MovementController {
    @Autowired
    MovementService movementService;

    @GetMapping("/")
    public ResponseEntity<List<MovementEntity>> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementEntity> getMovementById(@PathVariable("id") long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @GetMapping("/clientId/{id}")
    public ResponseEntity<List<MovementEntity>> getMovementByClientId(@PathVariable("id") long id) {
        return ResponseEntity.ok(movementService.getMovementByClientId(id));
    }

    @GetMapping("/byDate/{date}")
    public ResponseEntity<List<MovementEntity>> getMovementByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(movementService.getMovementByDate(date));
    }

    @GetMapping("/byDatesBetween/{date1}/{date2}")
    public ResponseEntity<List<MovementEntity>> getMovementByDateBetween(@PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
        return ResponseEntity.ok(movementService.getMovementByDatesBetween(date1, date2));
    }

    @GetMapping("/byDatesBefore/{date}")
    public ResponseEntity<List<MovementEntity>> getMovementByDatesBefore(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(movementService.getMovementByDatesBefore(date));
    }

    @GetMapping("/byDatesAfter/{date}")
    public ResponseEntity<List<MovementEntity>> getMovementByDatesAfter(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(movementService.getMovementByDatesAfter(date));
    }

    @GetMapping("/unitId/{id}")
    public ResponseEntity<List<MovementEntity>> getMovementByUnitId(@PathVariable("id") long id) {
        return ResponseEntity.ok(movementService.getMovementByUnitId(id));
    }

    @PostMapping("/")
    public ResponseEntity<MovementEntity> addMovement(@RequestBody MovementEntity movement) {
        return ResponseEntity.ok(movementService.saveMovement(movement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovementEntity> deleteMovement(@PathVariable("id") long id) {
        movementService.deleteMovement(movementService.getMovementById(id));
        return ResponseEntity.noContent().build();
    }
}