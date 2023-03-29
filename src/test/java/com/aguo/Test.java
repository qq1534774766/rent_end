package com.aguo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        System.out.println(getDate());
    }

    public static Date getDate() {
        String source = "2023-03";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (ParseException e) {
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
        }
        try {
            return new SimpleDateFormat("yyyy-MM").parse(source);
        } catch (ParseException e) {
        }
        return null;
    }

}
