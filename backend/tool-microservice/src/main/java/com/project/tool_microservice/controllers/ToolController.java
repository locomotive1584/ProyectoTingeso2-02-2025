package com.project.tool_microservice.controllers;

import com.project.tool_microservice.entities.ToolEntity;
import com.project.tool_microservice.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tools")
@CrossOrigin("*")
public class ToolController {
    @Autowired
    ToolService toolService;

    @GetMapping("/")
    public ResponseEntity<List<ToolEntity>> getAllTools() {
        List<ToolEntity> tools = toolService.getTools();
        return ResponseEntity.ok(tools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolEntity> getToolById(@PathVariable long id) {
        ToolEntity toolEntity = toolService.getToolById(id);
        return ResponseEntity.ok(toolEntity);
    }

    @PostMapping("/")
    public ResponseEntity<ToolEntity> addTool(@RequestBody ToolEntity toolEntity) throws Exception {
        ToolEntity toolEntitySaved = toolService.saveTool(toolEntity);
        return ResponseEntity.ok(toolEntitySaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTool(@PathVariable long id) throws Exception {
        var toolEntity = toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<ToolEntity> updateTool(@RequestBody ToolEntity toolEntity) throws Exception {
        ToolEntity tool = toolService.updateTool(toolEntity);
        return ResponseEntity.ok(tool);
    }
}
