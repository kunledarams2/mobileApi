/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.shared.utils;

import java.security.SecureRandom;
import java.util.Random;
import org.springframework.stereotype.Component;

/**
 *
 * @author TremendocLimited
 */
@Component
public class Utils {

    private Random random = new SecureRandom();
    private String ALPHNUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstvwyz";

    public String getUserId(int length) {
        return getGeneratedId(length);
    }

    public String getAddressId(int length) {
        return getGeneratedId(length);
    }

    private String getGeneratedId(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHNUMERIC.charAt(random.nextInt(ALPHNUMERIC.length())));
        }
        return returnValue.toString();
    }
}
