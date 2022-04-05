package com.aguo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bC = new BCryptPasswordEncoder();
//        System.out.println(bC.encode("1"));
//        System.out.println(bC.encode("12345678"));
//        System.out.println(Pattern.matches("^[\\u4E00-\\u9FA5A-Za-z0-9]+$", "adm`i"));
    }
}
