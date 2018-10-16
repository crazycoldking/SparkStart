package com.spring.coder;

import com.spring.coder.service.PromoteService;

public abstract class Employee {

    PromoteService promoteService;

    abstract String updateWorkStatus();
    abstract String reportWorkPlan();
    abstract void requestPromotion();
}
