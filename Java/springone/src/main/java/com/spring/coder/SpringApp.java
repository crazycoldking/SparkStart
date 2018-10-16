package com.spring.coder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class SpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Employee jack = context.getBean("Jack", Employee.class);
        Employee anderson = context.getBean("Anderson", Employee.class);

        print(jack.updateWorkStatus());
        print(jack.reportWorkPlan());

        print(anderson.updateWorkStatus());
        print(anderson.reportWorkPlan());

        jack.requestPromotion();
        anderson.requestPromotion();
    }

    private static void print(String str) {
        System.out.println(str);
    }

}
