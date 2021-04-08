package com.demo.springboot.es7withresthighlevelclient.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 日期操作工具类
 * @date 2021/4/8 11:27
 * @see
 */
public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 转化日期为毫秒数
     * @date 2021/4/8 11:28
     */
    public static long convertTimeToLong(String date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse.getTime();
    }
}
