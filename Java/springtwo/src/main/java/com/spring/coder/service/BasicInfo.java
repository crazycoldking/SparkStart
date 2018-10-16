package com.spring.coder.service;

import org.springframework.stereotype.Component;

@Component
public class BasicInfo {

    private String name;
    private int age;
    private String position;

    public String getName() {
        if ( name != null && !name.isEmpty()) return name;
        name = "Lorry";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
