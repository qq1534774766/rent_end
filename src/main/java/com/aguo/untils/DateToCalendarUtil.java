package com.aguo.untils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/28 20:14
 * @Description: TODO
 */
public class DateToCalendarUtil {
    public static Map<String, Integer> dateToCalendarMap(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("year", instance.get(Calendar.YEAR));
        map.put("month", instance.get(Calendar.MONTH) + 1);
        map.put("day", instance.get(Calendar.DAY_OF_MONTH));
        return map;
    }

}
