package com.project.client_microservice.services;

import com.project.client_microservice.entities.ClientEntity;
import com.project.client_microservice.entities.Loan;
import com.project.client_microservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    RestTemplate restTemplate;

    String loanUrl = "http://loan-microservice/api/loans";

    public List<ClientEntity> getClients() {
        return clientRepository.findAll();
    }

    public ClientEntity getClientById(long id) {
        return clientRepository.findById(id);
    }

    public ClientEntity getClientByEmail(String email) { return clientRepository.findByEmail(email); }

    public boolean detectRepeatedRut(String rut){
        ClientEntity client = clientRepository.findByRut(rut);
        return client != null;
    }

    public ClientEntity saveClient(ClientEntity client) throws Exception{
        if (client == null) {
            throw new Exception("El cliente no existe");
        }
        else if (!detectRepeatedRut(client.getRut())) {
            return clientRepository.save(client);
        }
        throw new Exception("No se puede guardar el Cliente; RAZON: Ya existe un cliente con ese rut");
    }


    public boolean detectIfRestricted(long id) throws Exception{
        var possibleClient = clientRepository.findById(id);
        if (possibleClient == null) {
            throw new Exception("El cliente no existe");
        };
        try {
            return clientRepository.findById(id).getState().equals("Restricted");
        } catch (Exception e) {
            throw new Exception("No se encontro un cliente");
        }
    }

    public boolean detectUnpaidLoans(long id) throws Exception{
        try {
            List<Loan> loans = restTemplate.getForObject(loanUrl + "/getLoansByIdClient/" + id, List.class);
            return loans != null && !loans.isEmpty();
        } catch (Exception e) {
            throw new Exception("No se encontro un cliente");
        }
    }

    public boolean deleteClient(long id) throws Exception {
        try{
            if(!detectIfRestricted(id) && !detectUnpaidLoans(id)){
                clientRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("No se puede eliminar este cliente; RAZON: tiene cuentas pendientes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ClientEntity updateClient(ClientEntity client) throws Exception{
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el cliente; RAZON: No existe el cliente ha actualizar");
        }
    }


}