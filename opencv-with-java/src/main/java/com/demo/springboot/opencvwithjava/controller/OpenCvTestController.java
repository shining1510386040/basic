package com.demo.springboot.opencvwithjava.controller;

import com.demo.springboot.opencvwithjava.utils.ImageUtils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.photo.Photo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试opencv去除水印
 * @date 2022/1/14 11:07
 * @see
 */
@RestController
@RequestMapping("/opencv")
public class OpenCvTestController {

    @PostMapping("/clearWater")
    public String clearWater() {

        String markContent = "测试生成水印";
        String waterPath = "D:\\IdeaProjects\\basic\\opencv-with-java\\src\\main\\resources\\images\\water.jpg";
        //通过原图加水印获得初始水印图
        ImageUtils.addWaterMark("D:\\IdeaProjects\\basic\\opencv-with-java\\src\\main\\resources\\images\\1.png", markContent, waterPath);
        //蒙版图路径
        String maskPath = "D:\\IdeaProjects\\basic\\opencv-with-java\\src\\main\\resources\\images\\mask.jpg";

        //获取蒙版图，黑底+透明水印字
        ImageUtils.getMask(waterPath, markContent, maskPath);
        //opencv读取水印图
        Mat image = Imgcodecs.imread(waterPath);
        //opencv读取蒙版
        Mat mask = Imgcodecs.imread(maskPath, CvType.CV_8UC1);
        Mat dst = new Mat(image.size(), CvType.CV_8UC3);

        Photo.inpaint(image, mask, dst, 20, Photo.INPAINT_TELEA);
        String originPath = "D:\\IdeaProjects\\basic\\opencv-with-java\\src\\main\\resources\\images\\test.jpg";
        Imgcodecs.imwrite(originPath, dst);
        return "成功";
    }
}
