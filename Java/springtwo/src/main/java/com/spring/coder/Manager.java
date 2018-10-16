package com.spring.coder;

import com.spring.coder.service.PromoteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("manager")
public class Manager extends Employee {

    @Override
    String updateWorkStatus() {
        return "Manager: Got a new project from JP Morgan.";
    }

    @Override
    String reportWorkPlan() {
        return "Manager: Finish our new project within 3 months.";
    }

    public Manager(@Qualifier("positionPromoteService") PromoteService promoteService){
        this.promoteService = promoteService;
    }

    @Override
    public void requestPromotion(){
        this.promoteService.requestPromotion();
    }
}
