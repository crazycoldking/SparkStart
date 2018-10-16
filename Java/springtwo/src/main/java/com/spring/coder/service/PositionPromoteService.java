package com.spring.coder.service;

import org.springframework.stereotype.Component;

@Component
public class PositionPromoteService implements PromoteService {
    private BasicInfo basicInfo;

    public PositionPromoteService(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    @Override
    public void requestPromotion() {
        System.out.println("Position promotion request from " + basicInfo.getName());
    }
}
