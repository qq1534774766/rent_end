package com.aguo.untils.code;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/21 1:46
 * @Description: TODO
 */
public class StringUntil {
    private StringUntil() {
    }

    public static String trimStringOrEmpty(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }
}
