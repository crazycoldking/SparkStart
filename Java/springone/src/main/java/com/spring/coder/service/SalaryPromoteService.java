package com.spring.coder.service;

public class SalaryPromoteService implements PromoteService {

    private BasicInfo basicInfo;

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    @Override
    public void requestPromotion() {
        System.out.println("Salary promotion request from " + basicInfo.getName());
    }
}
