package com.spring.coder;

import com.spring.coder.service.BasicInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class SpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Employee e1 = context.getBean("manager", Employee.class);
        Employee e2 = context.getBean("manager", Employee.class);

        // e1 and e2 are the same spring bean
        if (e1 == e2) {
            print("Default scope of Spring bean is singleton.");
        }

        Employee e3 = context.getBean("programmer", Employee.class);
        e3.requestPromotion();

        BasicInfo info1 = context.getBean("tom", BasicInfo.class);
        BasicInfo info2 = context.getBean("tom", BasicInfo.class);

        if (info1 != info2) {
            print("info1 and info2 are different objects while use prototype scope.");
        }

    }

    private static void print(String str) {
        System.out.println(str);
    }

}
