/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.security;

import com.kunledarams.mobilewsapi.SpringApplicationContext;

/**
 *
 * @author TremendocLimited
 */
public class SecurityConstant {
    
    public static final long EXPIRATION_TIME=864000000;
    public static final String TOKEN_PREFIX="Bearer";
    public static final String SIGN_UP_URL= "/customer";
    public static final String HEADER_STRING="Authorization";
    
    public static String getTokenSecret() {
    	AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("AppProperties");
    	return appProperties.getTokenSecret();
    }
}
