package com.cwj.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil 时间格式化工具类
 *
 * @author ChengWenjia
 * @since 2022-02-15 11:13
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String nowTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(now);
    }

    /**
     * 判断时间是否在7天内
     *
     * @param target 被判断时间
     * @return 是否在7天内
     */
    public static boolean isLatestWeek(Date target) {
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(new Date());
        //设置为7天前
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        //得到7天前的时间
        Date before7days = calendar.getTime();
        return before7days.getTime() < target.getTime();
    }
}
