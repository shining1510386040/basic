package com.demo.springboot.web.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.demo.springboot.web.config.AliyunOssConfig;
import com.demo.springboot.web.utils.CommonUtils;
import com.demo.springboot.web.vo.FileObjVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 上传图片到阿里云oss服务
 * @date 2021/1/18 10:51
 * @see
 */
@Service
public class UploadPicService {

    /**
     * 允许上传的图片格式
     */
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunOssConfig aliyunConfig;

    /**
     * @param multipartFile 多部件file
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 文件上传操作
     * @date 2021/1/18 10:53
     */
    public FileObjVo upload(MultipartFile multipartFile) {
        // 1. 对上传的图片进行校验: 这里简单校验后缀名
        // 另外可通过ImageIO读取图片的长宽来判断是否是图片,校验图片的大小等。
        // TODO 图片校验
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;  // 只要与允许上传格式其中一个匹配就可以
            }
        }
        FileObjVo fileObjVo = new FileObjVo();
        // 格式错误, 返回与前端约定的error
        if (!isLegal) {
            fileObjVo.setStatus("error");
            return fileObjVo;
        }

        // 2. 准备上传API的参数:文件名，文件存储相对路径
        String fileName = multipartFile.getOriginalFilename();
        String filePath = this.getFilePath(fileName);

        // 3. 上传至阿里OSS
        try {
            // 字节流
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            // 上传失败
            fileObjVo.setStatus("error");
            return fileObjVo;
        }

        // 上传成功
        fileObjVo.setStatus("done");
        // 文件名(即直接访问的完整路径)
        fileObjVo.setName(aliyunConfig.getUrlPrefix() + filePath);
        // uid
        fileObjVo.setUid(String.valueOf(System.currentTimeMillis()));
        return fileObjVo;
    }

    /**
     * 上传的目录
     * 目录规则: 根据年月日归档
     * 文件名: 时间戳 + 随机数
     *
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        CommonUtils commonUtils = new CommonUtils();
        String mid = commonUtils.formatDateStr("yyyy") + "/" + commonUtils.formatDateStr("MM") + "/"
                + commonUtils.formatDateStr("dd") + "/";
        return "images/" + mid + System.currentTimeMillis() +
                commonUtils.nextInt(100, 9999) + commonUtils.getSuffix(fileName, ".");
    }

    /**
     * @param keyPrefix 文件前缀
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 列举文件，最多一个bucket下的100个文件
     * @date 2021/1/18 12:43
     */
    public List<String> listObject(String keyPrefix) {
        List<String> ret = new ArrayList<>(16);
        String bucketName = aliyunConfig.getBucketName();
        ObjectListing objectListing = ossClient.listObjects(bucketName, keyPrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            // s.getKey() 获取的是文件存储的相对路径
            ret.add(aliyunConfig.getUrlPrefix() + s.getKey());
        }
        return ret;
    }

}
