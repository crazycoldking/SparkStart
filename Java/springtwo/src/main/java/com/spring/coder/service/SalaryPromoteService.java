package com.spring.coder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryPromoteService implements PromoteService {

    private BasicInfo basicInfo;

    public SalaryPromoteService(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    @Override
    public void requestPromotion() {
        System.out.println("Salary promotion request from " + basicInfo.getName());
    }
}
