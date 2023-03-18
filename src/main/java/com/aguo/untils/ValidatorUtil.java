package com.aguo.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 验证租客表单合法性
 * @author Administrator
 */
public class ValidatorUtil {

    /**
     * 校验用户名
     * @param username 用户名
     * @return 校验结果
     */
    public static boolean validateUsername(String username) {
        if (username == null || username.length() < 4 || username.length() > 20) {
            return false;
        }
        return true;
    }

    /**
     * 校验密码
     * @param password 密码
     * @return 校验结果
     */
    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 6 || password.length() > 18) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasLetter && hasDigit;
    }

    /**
     * 校验电话号码
     * @param phone 电话号码
     * @return 校验结果
     */
    public static boolean validatePhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验身份证号码
     * @param idCard 身份证号码
     * @return 校验结果
     */
    public static boolean validateIdCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }
        for (char c : idCard.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        String province = idCard.substring(0, 2);
        if (!isValidProvince(province)) {
            return false;
        }
        String year = idCard.substring(6, 10);
        String month = idCard.substring(10, 12);
        String day = idCard.substring(12, 14);
        if (!isValidDate(year, month, day)) {
            return false;
        }
        return true;
    }

    private static boolean isValidProvince(String province) {
        String[] provinces = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82"};
        for (String p : provinces) {
            if (p.equals(province)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidDate(String year, String month, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(year + month + day);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}