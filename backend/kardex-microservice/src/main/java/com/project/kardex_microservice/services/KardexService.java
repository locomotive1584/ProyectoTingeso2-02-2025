package com.project.kardex_microservice.services;

import com.project.kardex_microservice.entities.KardexEntity;
import com.project.kardex_microservice.repositories.KardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KardexService {
    @Autowired
    KardexRepository kardexRepository;

    public List<KardexEntity> getAllKardex() {
        return kardexRepository.findAll();
    }

    public KardexEntity getKardexById(long id) {
        return kardexRepository.findById(id);
    }

    public List<KardexEntity> getKardexByToolId(long id) {
        return kardexRepository.findByToolId(id);
    }

    public List<KardexEntity> getKardexByDate(LocalDate date) {
        return kardexRepository.findByDate(date);
    }

    public List<KardexEntity> getKardexByDatesBetween(LocalDate startDate, LocalDate endDate) {
        return kardexRepository.findByDateBetween(startDate, endDate);
    }

    public List<KardexEntity> getKardexByDatesBefore(LocalDate date) {
        return kardexRepository.findByDateBefore(date);
    }

    public List<KardexEntity> getKardexByDatesAfter(LocalDate date) {
        return kardexRepository.findByDateAfter(date);
    }

    public List<KardexEntity> getKardexByToolIdAndDate(long id, LocalDate date) {
        return kardexRepository.findByToolIdAndDate(id, date);
    }

    public List<KardexEntity> getKardexByToolIdAndDatesBetween(long id, LocalDate date1, LocalDate date2) {
        return kardexRepository.findByToolIdAndDateBetween(id, date1, date2);
    }

    public List<KardexEntity> getKardexByToolIdAndDatesBefore(long id, LocalDate date) {
        return kardexRepository.findByToolIdAndDateBefore(id, date);
    }

    public List<KardexEntity> getKardexByToolIdAndDatesAfter(long id, LocalDate date) {
        return kardexRepository.findByToolIdAndDateAfter(id, date);
    }

    public KardexEntity saveKardex(KardexEntity kardex) {
        return kardexRepository.save(kardex);
    }

    public KardexEntity updateKardex(KardexEntity kardex) {
        return kardexRepository.save(kardex);
    }

    public void deleteKardex(long id) {
        kardexRepository.findById(id);
    }
}
