package com.aguo.untils.code;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/21 1:46
 * @Description: TODO
 */
public class ParamUntil {
    private ParamUntil() {
    }

    /**
     * 处理参数为null以及前后空格
     *
     * @param s
     * @return
     */
    public static String trimStringOrEmpty(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }

    /**
     * 将布尔转为1/0.同时避免NPE
     *
     * @param b
     * @return
     */
    public static String handleBooleanOrEmpty(Boolean b) {
        if (null == b) {
            return "";
        } else {
            return b ? "1" : "0";
        }
    }
}
