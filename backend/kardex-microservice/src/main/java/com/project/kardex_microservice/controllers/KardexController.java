package com.project.kardex_microservice.controllers;

import com.project.kardex_microservice.entities.KardexEntity;
import com.project.kardex_microservice.services.KardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/kardex")
public class KardexController {
    @Autowired
    KardexService kardexService;

    @GetMapping("/")
    public ResponseEntity<List<KardexEntity>> getKardex() {
        return ResponseEntity.ok(kardexService.getAllKardex());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KardexEntity> getKardexById(@PathVariable("id") long id) {
        return ResponseEntity.ok(kardexService.getKardexById(id));
    }

    @GetMapping("toolId/{id}")
    public ResponseEntity<List<KardexEntity>> getKardexByToolId(@PathVariable("id") long id) {
        return ResponseEntity.ok(kardexService.getKardexByToolId(id));
    }

    @GetMapping("byDate/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByDate(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByDate(date));
    }

    @GetMapping("/byDatesBetween/{date1}/{date2}")
    public ResponseEntity<List<KardexEntity>> getKardexByDateBetween(@PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
        return ResponseEntity.ok(kardexService.getKardexByDatesBetween(date1, date2));
    }

    @GetMapping("/byDatesBefore/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByDatesBefore(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByDatesBefore(date));
    }

    @GetMapping("/byDatesAfter/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByDatesAfter(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByDatesAfter(date));
    }

    @GetMapping("/toolId/{id}/byDate/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByToolIdAndDate(@PathVariable("id") long id, @PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByToolIdAndDate(id, date));
    }

    @GetMapping("/toolId/{id}/byDatesBetween/{date1}/{date2}")
    public ResponseEntity<List<KardexEntity>> getKardexByToolIdAndDateBetween(@PathVariable("id") long id, @PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
        return ResponseEntity.ok(kardexService.getKardexByToolIdAndDatesBetween(id, date1, date2));
    }

    @GetMapping("/toolId/{id}/byDatesBefore/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByToolIdAndDatesBefore(@PathVariable("id") long id, @PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByToolIdAndDatesBefore(id, date));
    }

    @GetMapping("/toolId/{id}/byDatesAfter/{date}")
    public ResponseEntity<List<KardexEntity>> getKardexByToolIdAndDatesAfter(@PathVariable("id") long id, @PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(kardexService.getKardexByToolIdAndDatesAfter(id, date));
    }

    @PostMapping("/")
    public ResponseEntity<KardexEntity> addKardex(@RequestBody KardexEntity kardex) {
        return ResponseEntity.ok(kardexService.saveKardex(kardex));
    }

    @PutMapping("/")
    public ResponseEntity<KardexEntity> updateKardex(@RequestBody KardexEntity kardex) {
        return ResponseEntity.ok(kardexService.updateKardex(kardex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KardexEntity> deleteKardex(@PathVariable("id") long id) {
        kardexService.deleteKardex(id);
        return ResponseEntity.noContent().build();
    }
}
