package com.project.client_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.client_microservice.entities.ClientEntity;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public List<ClientEntity> findAll();

    public ClientEntity findById(long id);

    public ClientEntity findByEmail(String email);

    public ClientEntity findByRut(String rut);

}