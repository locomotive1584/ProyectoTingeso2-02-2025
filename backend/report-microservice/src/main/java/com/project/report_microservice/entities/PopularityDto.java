package com.project.report_microservice.entities;

import com.project.report_microservice.models.Tool;

public class PopularityDto {

    private long loanQuantity;

    private Tool tool;

    public long getLoanQuantity() {
        return loanQuantity;
    }

    public void setLoanQuantity(long loanQuantity) {
        this.loanQuantity = loanQuantity;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }
}
