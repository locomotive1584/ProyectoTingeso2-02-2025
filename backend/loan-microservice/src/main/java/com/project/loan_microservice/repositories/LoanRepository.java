package com.project.loan_microservice.repositories;

import com.project.loan_microservice.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    public LoanEntity findById(long id);

    public List<LoanEntity> findAll();

    public List<LoanEntity> findAllByClientId(long id);

    public List<LoanEntity> findAllByUnitId(long id);
}
