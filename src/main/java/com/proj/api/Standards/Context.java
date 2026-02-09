package com.proj.api.Standards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class Context {
    @Autowired
    private static ApplicationContext context;
    public static <T> T get(Class<T> beanName){
        return context.getBean(beanName);
    }
}
