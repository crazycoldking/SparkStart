package com.spring.coder;

import com.spring.coder.service.PromoteService;

public class Programmer extends Employee {
    @Override
    String updateWorkStatus() {
        return "Programmer: Started to investigate Spring Framework.";
    }

    @Override
    String reportWorkPlan() {
        return "Programmer: Implement new feature with Spring";
    }

    private PromoteService promoteService;

    public Programmer(PromoteService promoteService) {
        this.promoteService = promoteService;
    }

    @Override
    void requestPromotion() {
        this.promoteService.requestPromotion();
    }
}
