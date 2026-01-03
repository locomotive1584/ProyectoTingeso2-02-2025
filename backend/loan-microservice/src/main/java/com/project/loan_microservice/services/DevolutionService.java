package com.project.loan_microservice.services;

import com.project.loan_microservice.entities.DevolutionEntity;
import com.project.loan_microservice.entities.LoanEntity;
import com.project.loan_microservice.entities.Tool;
import com.project.loan_microservice.repositories.DevolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class DevolutionService {
    @Autowired
    DevolutionRepository devolutionRepository;

    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private LoanService loanService;

    String toolUrl = "http://tool-microservice/api/v1/tools";

    public List<DevolutionEntity> getAllDevolutions() {
        return devolutionRepository.findAll();
    }

    public DevolutionEntity createDevolution(long loanId) {

        DevolutionEntity devolutionEntity = new DevolutionEntity();

        LocalDate date = LocalDate.now();
        devolutionEntity.setDevolutionDate(date);
        devolutionEntity.setLoanId(loanId);

        return devolutionRepository.save(devolutionEntity);
    }

    public long calculateCost(long id){
        DevolutionEntity devolution = devolutionRepository.findById(id);
        LoanEntity loan = loanService.getLoanById(devolution.getLoanId());
        Tool tool = restTemplate.getForObject(toolUrl + "/" + loanService.getLoanById(devolution.getLoanId()).getToolId(), Tool.class);
        long daysOfLoan;

        if (devolution.getDevolutionDate().equals(loan.getInitialDate())) {
            return tool.getDailyCost();
        }

        if (devolution.getDevolutionDate().isAfter(loan.getAgreedDate())) {
            daysOfLoan = ChronoUnit.DAYS.between(loan.getInitialDate(), loan.getAgreedDate());
            long daysWithFine = ChronoUnit.DAYS.between(loan.getAgreedDate(), devolution.getDevolutionDate());

            return (daysOfLoan*tool.getDailyCost()) + (daysWithFine*tool.getDailyFine());
        }

        daysOfLoan = ChronoUnit.DAYS.between(loan.getInitialDate(), devolution.getDevolutionDate());

        return daysOfLoan * tool.getDailyCost();
    }
}
