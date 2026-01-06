package com.project.client_microservice.controllers;

import com.project.client_microservice.entities.ClientEntity;
import com.project.client_microservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        List<ClientEntity> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") long id) {
        ClientEntity clientEntity = clientService.getClientById(id);
        return ResponseEntity.ok(clientEntity);
    }

    @GetMapping("/isActive/{id}")
    public ResponseEntity<Boolean> isClientActive(@PathVariable("id") long id) throws Exception {
        boolean answer = clientService.isActive(id);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/isAllowed/{id}/{toolId}")
    public ResponseEntity<Boolean> isClientAllowed(@PathVariable("id") long id, @PathVariable("toolId") long toolId) throws Exception {
        return ResponseEntity.ok(clientService.isAllowed(id, toolId));
    }

    @GetMapping("/clientExist/{id}")
    public ResponseEntity<Boolean> clientExist(@PathVariable("id") long id) throws Exception {
        return ResponseEntity.ok(clientService.clientExists(id));
    }

    @PostMapping("/")
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity clientEntity) throws Exception{
        ClientEntity clientEntitySaved = clientService.saveClient(clientEntity);
        return ResponseEntity.ok(clientEntitySaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientEntity> deleteClient(@PathVariable("id") long id) throws Exception {
        var clientEntity = clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity clientEntity) throws Exception {
        ClientEntity client = clientService.updateClient(clientEntity);
        return ResponseEntity.ok(client);
    }
}
