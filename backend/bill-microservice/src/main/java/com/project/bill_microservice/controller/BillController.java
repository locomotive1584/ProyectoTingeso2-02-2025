package com.project.bill_microservice.controller;

import com.project.bill_microservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
@CrossOrigin("*")
public class BillController {
    @Autowired
    BillService billService;

    @PutMapping("/DC/{id}/{cost}")
    public void setDailyCost(@PathVariable("id") long id, @PathVariable("cost") int cost) throws Exception{
        billService.setDailyCost(id, cost);
    }

    @PutMapping("/DF/{id}/{cost}")
    public void setDailyFine(@PathVariable("id") long id, @PathVariable("cost") int cost) throws Exception{
        billService.setDailyFine(id, cost);
    }

    @PutMapping("/RC/{id}/{cost}")
    public void setRepositionCost(@PathVariable("id") long id, @PathVariable("cost") int cost) throws Exception{
        billService.setRepositionCost(id, cost);
    }
}
