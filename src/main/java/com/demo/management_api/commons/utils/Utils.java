package com.demo.management_api.commons.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.time.Year;

public class Utils {

    public static String generateCode(String prefix){
        String year = Year.now().toString();
        return year.concat(RandomStringUtils.random(11, 0, 0, Boolean.TRUE, Boolean.TRUE, null, new SecureRandom()));
    }

    private Utils () {}
}
