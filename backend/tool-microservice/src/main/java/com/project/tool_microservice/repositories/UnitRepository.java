package com.project.tool_microservice.repositories;

import com.project.tool_microservice.entities.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {

    public List<UnitEntity> findAll();

    public UnitEntity findById(long id);

    public List<UnitEntity> findByToolId(long id);

    public List<UnitEntity> findByState(String state);

    public List<UnitEntity> findByToolIdAndState(long toolId, String state);
}
