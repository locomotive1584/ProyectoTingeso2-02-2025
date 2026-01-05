package com.project.kardex_microservice.repositories;

import com.project.kardex_microservice.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    public List<MovementEntity> findAll();

    public MovementEntity findById(long id);

    public List<MovementEntity> findByDateBetween(LocalDate date1, LocalDate date2);

    public List<MovementEntity> findByDateBefore(LocalDate date);

    public List<MovementEntity> findByDateAfter(LocalDate date);

    public List<MovementEntity> findByUnitId(Long unitId);
}
