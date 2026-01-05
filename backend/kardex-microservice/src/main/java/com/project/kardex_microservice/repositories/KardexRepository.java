package com.project.kardex_microservice.repositories;

import com.project.kardex_microservice.entities.KardexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KardexRepository extends JpaRepository<KardexEntity, Long> {

    public List<KardexEntity> findAll();

    public KardexEntity findById(long id);

    public List<KardexEntity> findByToolId(long id);

    public List<KardexEntity> findByDate(LocalDate localDate);

    public List<KardexEntity> findByDateBetween(LocalDate date1, LocalDate date2);

    public List<KardexEntity> findByDateBefore(LocalDate localDate);

    public List<KardexEntity> findByDateAfter(LocalDate localDate);

    public List<KardexEntity> findByToolIdAndDate(Long id, LocalDate localDate);

    public List<KardexEntity> findByToolIdAndDateBetween(long id, LocalDate date1, LocalDate date2);

    public List<KardexEntity> findByToolIdAndDateBefore(long id, LocalDate localDate);

    public List<KardexEntity> findByToolIdAndDateAfter(long id, LocalDate localDate);
}
