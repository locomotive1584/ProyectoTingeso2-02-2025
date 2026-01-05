package com.project.tool_microservice.controllers;

import com.project.tool_microservice.entities.UnitEntity;
import com.project.tool_microservice.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
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

    @PostMapping("/{toolId}/{quantity}/{state}")
    public ResponseEntity<UnitEntity> createQuantityUnits(@PathVariable("toolId") long toolId, @PathVariable("quantity") int quantity, @PathVariable("state") String state) throws Exception {
        UnitEntity unitEntity = unitService.saveQuantity(toolId, quantity, state);
        return ResponseEntity.ok(unitEntity);
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
