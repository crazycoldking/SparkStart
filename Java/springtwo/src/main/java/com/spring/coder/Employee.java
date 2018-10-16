package com.spring.coder;

import com.spring.coder.service.PromoteService;
import org.springframework.stereotype.Component;

@Component
public abstract class Employee {

    PromoteService promoteService;

    abstract String updateWorkStatus();
    abstract String reportWorkPlan();
    abstract void requestPromotion();
}
