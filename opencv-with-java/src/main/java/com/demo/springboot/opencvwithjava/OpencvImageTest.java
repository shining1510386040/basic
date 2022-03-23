package com.demo.springboot.opencvwithjava;

import com.demo.springboot.opencvwithjava.utils.ImageUtils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.photo.Photo;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试opencv
 * @date 2022/1/14 11:05
 * @see
 */
public class OpencvImageTest {

    public static void main(String[] args) throws Exception {

        //加载动态库，main方法无法通过配置文件加载
        System.load("D:\\opencv\\opencv\\build\\java\\x64\\opencv_java451.dll");
        String markContent = "测试生成水印";
        String waterPath = "D:\\img\\water.jpg";
        //通过原图加水印获得初始水印图
        ImageUtils.addWaterMark("D:\\img\\1.png", markContent, waterPath);
        //蒙版图路径
        String maskPath = "D:\\img\\mask.jpg";

        //获取蒙版图，黑底+透明水印字
        ImageUtils.getMask(waterPath, markContent, maskPath);
        //opencv读取水印图
        Mat image = Imgcodecs.imread(waterPath);
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        //opencv读取蒙版
        Mat mask = Imgcodecs.imread(maskPath, CvType.CV_8UC1);
        Mat dst = new Mat(image.size(), CvType.CV_8UC3);
        Photo.inpaint(image, mask, dst, 20, Photo.INPAINT_TELEA);
        HighGui.imshow("原图", image);
        HighGui.imshow("蒙版", mask);
        HighGui.imshow("结果", dst);
        String originPath = "D:\\img\\test.jpg";
        Imgcodecs.imwrite(originPath, dst);
        HighGui.waitKey();
    }
}
