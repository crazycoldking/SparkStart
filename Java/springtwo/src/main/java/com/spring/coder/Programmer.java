package com.spring.coder;

import com.spring.coder.service.PromoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
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

    @Autowired
    public Programmer(@Qualifier("salaryPromoteService") PromoteService promoteService) {
        this.promoteService = promoteService;
    }

    @Override
    void requestPromotion() {
        this.promoteService.requestPromotion();
    }
}
