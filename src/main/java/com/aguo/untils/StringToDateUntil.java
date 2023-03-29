//package com.aguo.untils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.convert.converter.Converter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class StringToDateUntil implements Converter<String, Date> {
//
//    @Override
//    public Date convert(String birth) {
//        if (StringUtils.isBlank(birth)) return null;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = null;
//        try {
//            parse = simpleDateFormat.parse(birth);
//        } catch (ParseException e) {
////            e.printStackTrace();
//        }
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            parse = simpleDateFormat2.parse(birth);
//        } catch (ParseException e) {
////            e.printStackTrace();
//        }
//        return parse;
//    }
//
//}
