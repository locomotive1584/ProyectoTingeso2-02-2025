package com.project.loan_microservice.services;

import com.project.loan_microservice.entities.Client;
import com.project.loan_microservice.entities.LoanEntity;
import com.project.loan_microservice.entities.Tool;
import com.project.loan_microservice.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    RestTemplate restTemplate;

    String clientUrl = "http://client-microservice/api/v1/clients";
    String unitUrl = "http://unit-microservice/api/v1/units";
    String toolUrl = "http://tool-microservice/api/v1/tools";
    String movementsUrl = "http://kardex-microservice/api/v1/movements";

    public List<LoanEntity> getLoans() { return loanRepository.findAll(); }

    public LoanEntity getLoanById(long id) { return loanRepository.findById(id); }

    public List<LoanEntity> getLoanByClientId(long id) {return loanRepository.findAllByClientId(id); }

    public List<LoanEntity> getLoanByUnitId(long id) {return loanRepository.findAllByUnitId(id); }

    public List<LoanEntity> getLoanByToolId(long id) {return loanRepository.findByToolId(id); }

    public LoanEntity addLoan(LoanEntity loan) throws Exception{
        boolean clientExists = restTemplate.getForObject(clientUrl + "/clientExist/" + loan.getClientId(), Boolean.class);
        boolean toolExists = restTemplate.getForObject(toolUrl + "/toolExist/" + loan.getToolId(), Boolean.class);
        if(!clientExists || !toolExists) {
            throw new Exception("Cliente o herramienta nulas");
        }
        if(loan.getAgreedDate().isBefore(loan.getInitialDate())) {
            throw new Exception("Fecha de entrega es anterior a la fecha inicial");
        }
        boolean hasStock = restTemplate.getForObject(toolUrl + "/hasStock/" + loan.getToolId(), Boolean.class);
        if(!hasStock){
            throw new Exception("Stock no disponible");
        }
        return loanRepository.save(loan);
    }
}
