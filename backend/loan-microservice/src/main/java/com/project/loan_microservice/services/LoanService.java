package com.project.loan_microservice.services;

import com.project.loan_microservice.entities.LoanEntity;
import com.project.loan_microservice.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    RestTemplate restTemplate;

    String gatewayUrl = "http://localhost:8081";
    String toolUrl = gatewayUrl + "/tools";
    String clientUrl = gatewayUrl + "/clients";
    String unitUrl = gatewayUrl + "/units";
    String movementsUrl = gatewayUrl + "/movements";

    public List<LoanEntity> getLoans() { return loanRepository.findAll(); }

    public LoanEntity getLoanById(long id) { return loanRepository.findById(id); }

    public List<LoanEntity> getLoanByClientId(long id) {return loanRepository.findAllByClientId(id); }

    public List<LoanEntity> getLoanByUnitId(long id) {return loanRepository.findAllByUnitId(id); }

    public List<LoanEntity> getLoanByToolId(long id) {return loanRepository.findByToolId(id); }

    public List<LoanEntity> getLoanByInitialDateBetween(LocalDate date1, LocalDate date2) {return loanRepository.findByInitialDateBetween(date1, date2); }

    public List<LoanEntity> getLoanByInitialDateAfter(LocalDate date) {return loanRepository.findByInitialDateAfter(date); }

    public List<LoanEntity> getLoanByInitialDateBefore(LocalDate date) {return loanRepository.findByInitialDateBefore(date); }

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
