package com.project.client_microservice.services;

import com.project.client_microservice.entities.ClientEntity;
import com.project.client_microservice.models.Loan;
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

    String gatewayUrl = "http://localhost:8081";
    String loanUrl = gatewayUrl + "/loans";

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
            return clientRepository.findById(id).getState().equals("restringido");
        } catch (Exception e) {
            throw new Exception("No se encontro un cliente");
        }
    }

    public boolean clientExists(long id){
        return clientRepository.findById(id) != null;
    }

    public boolean detectUnpaidLoans(long id) throws Exception{
        try {
            List<Loan> loans = restTemplate.getForObject(loanUrl + "/getByClientId/" + id, List.class);
            return loans != null && !loans.isEmpty();
        } catch (Exception e) {
            throw new Exception("No se encontro un cliente");
        }
    }

    public boolean isActive(long id) throws Exception{
        try {
            return !detectUnpaidLoans(id) && !detectUnpaidLoans(id);
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

    public ClientEntity updateState(ClientEntity client, String state) throws Exception{
        try{
            if(state.equals("activo")|| state.equals("restringido")){
                client.setState(state);
                return updateClient(client);
            }
            else {
                throw new Exception("Estado no permitido");
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar el estado del cliente");
        }
    }

    public boolean toolIsNotInLoans(List<Loan> loans, long toolId) {
        if (loans == null || loans.isEmpty()) {
            return true;
        }
        for (int i = 0; i < loans.size(); i++) {
            if (loans.get(i).getToolId() == toolId) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllowed(long clientId, long toolId) throws Exception{
        try {
            List<Loan> loans = restTemplate.getForObject(loanUrl + "/getByClientId/" + clientId, List.class);

            if (loans.size() < 5 && isActive(clientId)){

                return toolIsNotInLoans(loans, toolId);
            }
            else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error al revisar si el cliente es activo");
        }
    }
}