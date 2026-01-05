package com.project.kardex_microservice.services;

import com.project.kardex_microservice.entities.MovementEntity;
import com.project.kardex_microservice.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovementService {
    @Autowired
    MovementRepository movementRepository;

    public List<MovementEntity> getAllMovements() {
        return movementRepository.findAll();
    }

    public MovementEntity getMovementById(long id) {
        return movementRepository.findById(id);
    }

    public List<MovementEntity> getMovementByClientId(long clientId) {
        return movementRepository.findByClientId(clientId);
    }

    public List<MovementEntity> getMovementByDate(LocalDate date){
        return movementRepository.findByDate(date);
    }

    public List<MovementEntity> getMovementByDatesBetween(LocalDate date1, LocalDate date2){
        return movementRepository.findByDateBetween(date1, date2);
    }

    public List<MovementEntity> getMovementByDatesBefore(LocalDate date){
        return movementRepository.findByDateBefore(date);
    }

    public List<MovementEntity> getMovementByDatesAfter(LocalDate date){
        return movementRepository.findByDateAfter(date);
    }

    public List<MovementEntity> getMovementByUnitId(long unitId){
        return movementRepository.findByUnitId(unitId);
    }

    public MovementEntity saveMovement(MovementEntity movement) {
        return movementRepository.save(movement);
    }

    public void deleteMovement(MovementEntity movement) {
        movementRepository.delete(movement);
    }
}
