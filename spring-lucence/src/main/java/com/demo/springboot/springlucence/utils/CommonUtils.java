package com.demo.springboot.springlucence.utils;

import lombok.extern.slf4j.Slf4j;
import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.sphx.api.SphinxMatch;
import org.sphx.api.SphinxResult;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 通用工具类
 * @date 2021/1/13 14:49
 * @see
 */
@Slf4j
public class CommonUtils {


    /**
     * @param prefix 前缀
     * @return 20位字符串：前缀+年份+后缀
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成记录id
     * @date 2021/1/13 14:52
     */
    public static String generateRecordId(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String date = sdf.format(new Date());
        String suffix = UUID.randomUUID().toString().replaceAll("-", "");
        String ret = (prefix + date + suffix).substring(0, 20);
        return ret;
    }

    /**
     * @param
     * @return 20位字符串：年份+后缀
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成Id
     * @date 2021/1/13 14:52
     */
    public static String generateId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String prefix = sdf.format(new Date());
        String suffix = UUID.randomUUID().toString().replaceAll("-", "");
        String ret = (prefix + suffix).substring(0, 20);
        return ret;
    }

    /**
     * @param
     * @return 13 位字符串
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成工单id：年月+7位uuid
     * @date 2021/3/17 17:30
     */
    public static String generateWorkOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String prefix = sdf.format(new Date());
        String suffix = UUID.randomUUID().toString().replaceAll("-", "");
        String ret = (prefix + suffix).substring(0, 13);
        return ret;
    }

    /**
     * @param length 长度，要求大于0
     * @return 唯一字符串：数字+字母，uuid
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成唯一UUID
     * @date 2021/1/13 14:52
     */
    public static String generateUUID(int length) {
        // uuid 32 位
        int off = length / 32 + 1;
        String suffix = UUID.randomUUID().toString().replaceAll("-", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < off; i++) {
            sb.append(suffix);
        }
        String ret = sb.substring(0, length);
        return ret;
    }

    /**
     * @param pattern 日期格式
     * @param date    待转化日期
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 日期转字符串
     * @date 2021/1/13 15:39
     */
    public static String convertDateToStr(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            // 默认格式
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * @param dateStr 日期格式字符串
     * @param pattern 格式，
     * @return 日期
     * @author Wenyi Cao
     * @version 1.0
     * @description 将日期格式字符串转化为日期
     * @date 2021/3/5 16:38
     */
    public static Date convertStrToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            // 默认值
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }


    /**
     * @param pattern 格式：yyyy 、MM 、dd
     * @return 当前日期指定格式的返回str
     * @author Wenyi Cao
     * @version 1.0
     * @description 按格式返回当前日期的字符串
     * @date 2021/1/18 11:07
     */
    public static String formatDateStr(String pattern) {
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
    public static String getSuffix(String fileName, String seperate) {
        return fileName.substring(fileName.lastIndexOf(seperate));
    }


    /**
     * @param orgiStr 原始字符串
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 替换指定位置字符为***
     * @date 2021/3/25 14:30
     */
    public static String replaceWithChar(String orgiStr, int begin, int end) {

        if (StringUtils.isEmpty(orgiStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(orgiStr);
        if (end > sb.length()) {
            end = sb.length();
        }
        if (begin <= 0) {
            begin = 1;
        }
        return sb.replace(begin, end, "***").toString();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description url解码：
     * 将application/x-www-form-urlencoded字符串转换成普通字符串
     * 默认采用字符集UTF-8
     * @date 2021/3/16 14:38
     */
    public static String urlDecode(String urlOrign) {
        try {
            return URLDecoder.decode(urlOrign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return urlOrign;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 基于jdk的表单参数编码：特殊字符和中文处理
     * = -> %3D
     * @date 2021/3/16 14:38
     */
    public static String urlEncode(String urlOrign) {
        try {
            return URLEncoder.encode(urlOrign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return urlOrign;
        }
    }

//    /**
//     * @param bytes 字节
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 基于org.apache.commons.codec.binary.Base64 的base64 解码
//     * @date 2021/3/26 10:27
//     */
//    public static String base64Decode(byte[] bytes) {
//        return new String(org.apache.commons.codec.binary.Base64.decodeBase64(bytes));
//    }
//
//    /**
//     * @param bytes 字节
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 基于org.apache.commons.codec.binary.base64 的base64 编码
//     * @date 2021/3/26 10:27
//     */
//    public static String base64Encode(byte[] bytes) {
//        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(bytes));
//    }

    /**
     * @param bean 待转化Javabean
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description javaBean转化为map（java内省机制）
     * @date 2021/4/6 18:15
     */
    public static Map<String, Object> bean2map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>(8);
        //获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);
        //获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        //对属性迭代
        for (PropertyDescriptor pd : pds) {
            //属性名称
            String propertyName = pd.getName();
            //属性值,用getter方法获取
            Method m = pd.getReadMethod();
            //用对象执行getter方法获得属性值
            Object properValue = m.invoke(bean);
            //把属性名-属性值 存到Map中
            map.put(propertyName, properValue);
        }
        return map;
    }

    /**
     * @param map 待转化的map
     * @param clz 要转化为的clazz
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 把Map转化为JavaBean(java的内省机制)
     * @date 2021/4/6 18:16
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) throws Exception {
        //创建一个需要转换为的类型的对象
        T obj = clz.newInstance();
        //从Map中获取和属性名称一样的值，把值设置给对象(setter方法)
        //得到属性的描述器
        BeanInfo b = Introspector.getBeanInfo(clz, Object.class);
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //得到属性的setter方法
            Method setter = pd.getWriteMethod();
            //得到key名字和属性名字相同的value设置给属性
            setter.invoke(obj, map.get(pd.getName()));
        }
        return obj;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description LocalDate -> Date
     * @date 2021/4/16 11:46
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description LocalDateTime -> Date
     * @date 2021/4/16 11:46
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description Date -> LocalDate
     * @date 2021/4/16 11:47
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description Date -> LocalDateTime
     * @date 2021/4/16 11:47
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static void main(String[] args) throws SphinxException {
//        System.out.println("===========>>>ID:" + generateId());
//        System.out.println("===========>>>recordId:" + generateRecordId("bab"));
//        System.out.println("==============>>>uuid:" + generateUUID(40));
//        String dateStr = "2021-02-01T16:00:00.000Z";
//        // ParseException 异常
////        System.out.println("date:" + convertStrToDate(dateStr, "yyyy-MM-dd hh:mm:ss"));
//
//        String url1 = "http://localhost:8002/index/clientLogin?username=张三@cloudcc.com&orgId=CUST_2006012F46A30A";
//        String url2 = "username=onlineuser@cloudcc.com&orgId=CUST_2006012F46A30A";
//        String url = "张三@cloudcc.com";
////        String url = "CUST_2006012F46A30A";
//        String urlde = urlEncode(url);
//        System.out.println("=============>>url编码后：" + urlde);
//        System.out.println("=============>>url解码后：" + urlDecode(urlde));
//
//
//        WorkOrderFeedback one = new WorkOrderFeedback();
//        one.setOrderBy(new Date(Instant.now().getEpochSecond()));
//        WorkOrderFeedback two = new WorkOrderFeedback();
//        two.setOrderBy(new Date(Instant.now().getEpochSecond()));
//
//        WorkOrderReplay replay1 = new WorkOrderReplay();
//        replay1.setOrderBy(new Date());
//        WorkOrderReplay replay2 = new WorkOrderReplay();
//        replay2.setOrderBy(new Date());
//
//
//        List<CommunicateSort> list = new ArrayList<>();
//        list.add(one);
//        list.add(two);
//        list.add(replay1);
//        list.add(replay2);
//        // java8 排序
//        list.sort(Comparator.comparing(CommunicateSort::getOrderBy));
//        System.out.println(list);

//        String fileName = "/abd/def/name.img";
//        System.out.println(getSuffix(fileName,"."));

//        String orgId = "2010000000050855WyHh";
//        String urlEncode = urlEncode(orgId);
//        System.out.println("一次编码：" + urlEncode);
//        System.out.println("两次编码：" + urlEncode(urlEncode));
//
//

//        String oriStr = "client1@cloudcc.com";
//        String replaceWithChar = replaceWithChar(oriStr, 3, 7);
//        System.out.println("=====>>>替换为*：" + replaceWithChar);
//
//        String urlParams = "loginType=postsale&accessToken=postsale2021c965b83e";
//        System.out.println(urlEncode(urlParams));


//        String string = "loginType=postsale&accessToken=postsale2021c965b83e";
//        //编码
//        String encode = base64Encode(string.getBytes());
//        System.out.println(string + "\t编码后的字符串为：" + encode);
//        //解码
//        String decode = base64Decode(encode.getBytes());
//        System.out.println(encode + "\t字符串解码后为：" + decode);

        String sphinxHost = "localhost";
        int sphinxPort = 9312;

        //创建客户端
        SphinxClient sphinxClient = new SphinxClient(sphinxHost, sphinxPort);


        //设置排序模式，根据排序属性排序，属性指定 为group_id
        //SPH_SORT_RELEVANCE 模式, 按相关度降序排列（最好的匹配排在最前面）
        //SPH_SORT_ATTR_DESC 模式, 按属性降序排列 （属性值越大的越是排在前面）
        //SPH_SORT_ATTR_ASC 模式, 按属性升序排列（属性值越小的越是排在前面）
        //SPH_SORT_TIME_SEGMENTS 模式, 先按时间段（最近一小时/天/周/月）降序，再按相关度降序
        //SPH_SORT_EXTENDED 模式, 按一种类似SQL的方式将列组合起来，升序或降序排列。
        //SPH_SORT_EXPR 模式，按某个算术表达式排序。

        sphinxClient.SetSortMode(SphinxClient.SPH_SORT_ATTR_DESC, "group_id");
        //分页查询的范围
        sphinxClient.SetLimits(1, 10);
        //查询
//        String keyword = "测试 工单 中国哈";
        String keyword = "number";
        SphinxResult sphinxResult = sphinxClient.Query(keyword);
        List<Long> idList = new ArrayList<Long>();
        //检索后匹配到的内容
        for (SphinxMatch m : sphinxResult.matches) {
            //得到所到记录Id
            log.debug("  found in sphinx:" + m.docId);
            idList.add(m.docId);
        }

    }
}
