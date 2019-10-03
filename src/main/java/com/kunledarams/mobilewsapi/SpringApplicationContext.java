/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author TremendocLimited
 */
public class SpringApplicationContext  implements ApplicationContextAware{

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context=ac;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static Object getBean(String bean){
        return context.getBean(bean);
        
    }
}
