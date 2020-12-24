package com.demo.springboot.web.utils;

import com.alibaba.fastjson.JSON;
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 对象复制工具类（深度拷贝）
 * @date 2020/12/24 10:50
 * @see
 */
public class BeanUtils {

    private static final Cloner cloner = new Cloner();

    /**
     * 复制对象（深度拷贝）
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T clone(final T object) {
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制集合（深度拷贝）
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(final List<T> object) {
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制对象到指定类（深度拷贝）
     *
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> T clone(final Object object, Class<T> destclas) {
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);
        return JSON.parseObject(json, destclas);
    }

    /**
     * 复制集合到指定类（深度拷贝）
     *
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(List<?> object, Class<T> destclas) {
        if (object == null) {
            return new ArrayList<T>();
        }
        String json = JSON.toJSONString(object);
        return JSON.parseArray(json, destclas);
    }
}
