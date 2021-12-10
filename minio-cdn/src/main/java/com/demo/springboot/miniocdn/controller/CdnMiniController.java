package com.demo.springboot.miniocdn.controller;

import com.demo.springboot.miniocdn.common.ServiceResult;
import com.demo.springboot.miniocdn.service.MinioService;
import com.demo.springboot.miniocdn.vo.CommReqParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description CDN-minio controller
 * @date 2021/5/27 9:48
 * @see
 */
@RestController
@RequestMapping("/cdn")
public class CdnMiniController {

    @Autowired
    private MinioService minioService;


    /**
     * @param commReqParams 参数封装：
     *                      {
     *                      "head": {},
     *                      "body": {
     *                      "fileInfo": [
     *                      {
     *                      "fileName": "aaa/test1.js",
     *                      "fileContent": "hahhaha哈哈22223",
     *                      "bucketName": "test"
     *                      },
     *                      {
     *                      "fileName": "aaa/test1.js",
     *                      "fileContent": "hahhaha哈哈22223",
     *                      "bucketName": "test"
     *                      }
     *                      ]
     *                      }
     *                      }
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 上传文件到minIO-支持多个
     * @date 2021/9/23 14:32
     */
    @PostMapping("/uploadFile")
    public ServiceResult uploadFile(@RequestBody CommReqParams commReqParams) {

        Assert.notNull(commReqParams, "参数错误");
        Map<String, Object> body = commReqParams.getBody();
        Assert.notNull(body, "参数错误");
        List<Map<String, String>> mapList = (List<Map<String, String>>) body.get("fileInfo");
        for (Map item : mapList) {
            String fileName = item.get("fileName") + "";
            String fileContent = item.get("fileContent") + "";
            String bucketName = item.get("bucketName") + "";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileContent.getBytes());
            minioService.putObject(byteArrayInputStream, bucketName, fileName);
        }
        return new ServiceResult("200", "上传成功");
    }

    /**
     * @param commReqParams 参数封装：{
     *                      "head":{},
     *                      "body":{
     *                      "bucketName":"", // 桶名称
     *                      "objectName":"" // 文件名带后缀例如 2.jpg
     *                      }
     *                      }
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取Cdn上某个对象的访问路径
     * @date 2021/9/24 11:53
     */
    @PostMapping("/minioGetObjUrl")
    public ServiceResult testGetObjUrl(@RequestBody CommReqParams commReqParams) {

        Assert.notNull(commReqParams, "参数错误");
        Map<String, Object> body = commReqParams.getBody();
        Assert.notNull(body, "参数错误");
        String bucketName = body.get("bucketName") + "";
        String objectName = body.get("objectName") + "";
        String url = minioService.getObjectUrl(bucketName, objectName);
        ServiceResult ret = new ServiceResult("200", "操作成功");
        ret.setData(url);
        return ret;
    }

}
