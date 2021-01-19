package com.demo.springboot.web.controller;

import com.demo.springboot.web.service.UploadPicService;
import com.demo.springboot.web.vo.FileObjVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 阿里云controller 测试
 * @date 2021/1/18 11:33
 * @see
 */
@Controller
@RequestMapping("/aliyun")
public class AliyunController {

    @Autowired
    private UploadPicService uploadPicService;


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 操作界面
     * @date 2021/1/18 11:38
     */
    @GetMapping("/index")
    public String index() {
        return "aliyunoss/ossIndex";
    }

    /**
     * @param file 多部件文件
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 文件上传
     * @date 2021/1/18 11:37
     */
    @PostMapping("/upload")
    @ResponseBody
    public FileObjVo upload(@RequestParam("file") MultipartFile file) {
        return uploadPicService.upload(file);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 列举文件
     * @date 2021/1/18 12:48
     */
    @GetMapping("/list")
    @ResponseBody
    public List<String> listObject(@RequestParam String keyPrefix) {
        return uploadPicService.listObject(keyPrefix);
    }


}
