package com.spring.coder.service;

public class PositionPromoteService implements PromoteService {
    private BasicInfo basicInfo;

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    @Override
    public void requestPromotion() {
        System.out.println("Position promotion request from " + basicInfo.getName());
    }
}
