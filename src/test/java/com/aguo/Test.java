package com.aguo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        try {
            throw new  NoSuchFieldError("牛逼");
        }catch (Throwable a){
            System.out.println(a.getMessage());
        }

    }
}
