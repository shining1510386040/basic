package com.demo.springboot.rocketmqwithspringboot.utils;

import java.io.*;
import java.net.URL;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 通用工具类
 * @date 2021/4/16 14:35
 * @see
 */
public class CommonUtils {

    public static void main(String[] args) throws Exception {

        URL resource = CommonUtils.class.getClassLoader().getResource("");
        File orign = new File(resource.getPath() + "/originFile.txt");

        // 读取
        StringBuffer sb = new StringBuffer("");
        FileReader reader = new FileReader(orign);

        BufferedReader br = new BufferedReader(reader);
        String str = null;
        while ((str = br.readLine()) != null) {
            // 处理
            str = "\"" + str.replaceAll("=", "\":`") + "`";
            sb.append(str + "\n");
            System.out.println(str);
        }
        br.close();
        reader.close();
        // 写
        File dest = new File(resource.getPath() + "/dest.txt");
        FileWriter writer = new FileWriter(dest);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(sb.toString());
        bw.close();
        writer.close();
    }
}
