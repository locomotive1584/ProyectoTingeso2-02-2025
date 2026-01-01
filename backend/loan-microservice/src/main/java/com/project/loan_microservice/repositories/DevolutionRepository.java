package com.project.loan_microservice.repositories;

import com.project.loan_microservice.entities.DevolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevolutionRepository extends JpaRepository<DevolutionEntity, Long> {

    public DevolutionEntity findById(long id);

    public List<DevolutionEntity> findAll();

    public DevolutionEntity findByLoanId(long loanId);
}
