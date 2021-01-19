package com.demo.springboot.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 通用工具类
 * @date 2021/1/18 11:04
 * @see
 */
public class CommonUtils {

    /**
     * @param pattern 格式：yyyy 、MM 、dd
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 按格式返回当前日期的字符串
     * @date 2021/1/18 11:07
     */
    public String formatDateStr(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String ret = sdf.format(new Date());
        return ret;
    }


    /**
     * @param begin 开始
     * @param end   结束
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 返回一个【begin，end】的随机数：
     * @date 2021/1/18 11:11
     */
    public String nextInt(int begin, int end) {
        int ret = (int) (Math.random() * (end - begin) + begin);
        return ret + "";
    }


    /**
     * @param fileName 文件名，例如：/abd/def/name.img
     * @param seperate 分割符
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取文件后缀名
     * @date 2021/1/18 11:14
     */
    public String getSuffix(String fileName, String seperate) {
        return fileName.substring(fileName.lastIndexOf(seperate));
    }

    public static void main(String[] args) {

        CommonUtils commonUtils = new CommonUtils();
        String s = commonUtils.nextInt(100, 999);
        System.out.println(s);

        String yyyy = commonUtils.formatDateStr("yyyy");
        String mm = commonUtils.formatDateStr("MM");
        String dd = commonUtils.formatDateStr("dd");
        System.out.println(yyyy + "/" + mm + "/" + dd);
    }
}
