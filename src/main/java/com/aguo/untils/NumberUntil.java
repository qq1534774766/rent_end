package com.aguo.untils;

import org.apache.commons.lang3.ObjectUtils;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/17 22:05
 * @Description: TODO
 */
public class NumberUntil {
    private NumberUntil(){}
    public static boolean isNumber(Object obj){
        if(null==obj) {
            return false;
        }
        if(!(obj instanceof Number)) {
            return false;
        }
        return true;
    }
}
