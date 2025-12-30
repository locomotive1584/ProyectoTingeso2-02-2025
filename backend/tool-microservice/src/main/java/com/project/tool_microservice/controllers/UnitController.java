package com.project.tool_microservice.controllers;

import com.project.tool_microservice.entities.UnitEntity;
import com.project.tool_microservice.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
@CrossOrigin("*")
public class UnitController {
    @Autowired
    UnitService unitService;

    @GetMapping("/")
    public ResponseEntity<List<UnitEntity>> getAllUnits() {
        List<UnitEntity> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitEntity> getUnitById(@PathVariable("id") long id) {
        UnitEntity unitEntity = unitService.getUnitById(id);
        return ResponseEntity.ok(unitEntity);
    }

    @PostMapping("/")
    public ResponseEntity<UnitEntity> createUnit(@RequestBody UnitEntity unitEntity) throws Exception {
        UnitEntity unitEntitySaved = unitService.saveUnit(unitEntity);
        return ResponseEntity.ok(unitEntitySaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UnitEntity> deleteUnit(@PathVariable("id") long id) throws Exception{
        var unitEntity = unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<UnitEntity> updateUnit(@RequestBody UnitEntity unitEntity) throws Exception {
        UnitEntity unit = unitService.updateUnit(unitEntity);
        return ResponseEntity.ok(unit);
    }
}
