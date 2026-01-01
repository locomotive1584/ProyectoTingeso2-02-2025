package com.project.tool_microservice.repositories;

import com.project.tool_microservice.entities.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Long> {

    public List<ToolEntity> findAll();

    public ToolEntity findById(long id);

    public List<ToolEntity> findByCategory(String category);

    public List<ToolEntity> findByStockGreaterThan(int minStock);
}
