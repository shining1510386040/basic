package com.demo.springboot.opencvwithjava.utils;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 图片工具类
 * @date 2022/1/14 11:03
 * @return
 */
public class ImageUtils {

    /**
     * @param srcImgPath       原始图片路径
     * @param waterMarkContent 水印内容
     * @param waterImgPath     添加水印后图片路径
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 添加水印
     * @date 2022/1/14 10:57
     */
    public static void addWaterMark(String srcImgPath, String waterMarkContent, String waterImgPath) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            int fontSize = (srcImgWidth - 20) / waterMarkContent.length();
            Font font = new Font("宋体", Font.PLAIN, fontSize);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(Color.white); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
            //设置水印的坐标
            int x = 10;
            int y = (srcImgHeight) / 2;
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(waterImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMask(String path, String waterMarkContent, String maskPath) {
        try {
            Image originImg = ImageIO.read(new File(path));
            int width = originImg.getWidth(null);
            int height = originImg.getHeight(null);
            int fontSize = (width - 20) / waterMarkContent.length();
            Font font = new Font("宋体", Font.PLAIN, fontSize);
            BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, width, height);
            g.setColor(Color.white);
            g.setFont(font);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
            //设置水印的坐标
            int x = 10;
            int y = (height) / 2;
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(maskPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给定一组url，将此集合地址下对应的资源下载到浏览器
     *
     * @param urls url地址的集合
     */
    public static void downloadPic(java.util.List<String> urls, HttpServletResponse response) throws Exception {
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        try {
            //文件的名称
            String downloadFilename = "图片.zip";
            //转换中文否则可能会产生乱码
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            //指明response的返回对象是文件流
            response.setContentType("application/octet-stream;charset=UTF-8");
            //设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);

            for (String url : urls) {
                String imgName = LocalDateTime.now().toString();
                String suffix = url.substring(url.lastIndexOf("."));
                zos.putNextEntry(new ZipEntry(imgName + suffix));
                InputStream fis = getInputStreamByGet(url);
                byte[] bytes = new byte[1024];
                int len;
                while (true) {
                    assert fis != null;
                    if ((len = fis.read(bytes)) == -1) {
                        break;
                    }
                    zos.write(bytes, 0, len);
                }
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zos.flush();
            zos.close();
        }
    }


    public static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
