package com.project.kardex_microservice.services;

import com.project.kardex_microservice.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementService {
    @Autowired
    MovementRepository movementRepository;


}
