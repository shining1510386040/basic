package com.demo.springboot.miniocdn.service;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description minio常用操作服务封装;
 * 文档地址：http://docs.minio.org.cn/docs/master/java-client-api-reference
 * @date 2021/8/30 14:51
 * @see
 */
@Service
@Slf4j
public class MinioService {


    @Autowired
    private MinioClient minioClient;

    /**
     * @param bucketName 桶的名称
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建一个bucket
     * @date 2021/8/30 15:00
     */
    public boolean createBucket(String bucketName) {
        try {
            // 如存储桶不存在，创建之。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                log.info(bucketName + "桶已经存在，请勿重复创建");
            } else {
                // 创建名为'my-bucketname'的存储桶。
                minioClient.makeBucket(bucketName);
                log.info(bucketName + "桶创建成功");
            }
            return true;
        } catch (Exception e) {
            log.error(bucketName + "桶创建时发生异常，ErrorMsg：" + e.getMessage());
            return false;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询所有的桶
     * @date 2021/8/30 15:04
     */
    public List<Bucket> listBucket() {
        try {
            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            return bucketList;
        } catch (Exception e) {
            log.error("列表查询bucket是出现异常，ErrorMsg：" + e.getMessage());
            return null;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 判断一个bucket是否存在
     * @date 2021/8/30 15:06
     */
    public boolean existBucket(String bucketName) {

        try {
            // 检查'my-bucketname'是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            return found;
        } catch (Exception e) {
            log.error("判断bucket是否存在时出现异常，ErrorMsg：" + e.getMessage());
            return false;
        }
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除一个bukcet
     * @date 2021/8/30 15:08
     */
    public void deleteBucket(String bucketName) {
        try {
            // 删除之前先检查`my-bucket`是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 删除`my-bucketname`存储桶，注意，只有存储桶为空时才能删除成功。
                minioClient.removeBucket(bucketName);
                log.info("删除bucket成功");
            } else {
                log.info(bucketName + "桶不存在");
            }
        } catch (Exception e) {
            log.error("删除bucket桶时发生异常，ErrorMsg：" + e.getMessage());
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 列出某个存储桶中的所有对象
     * @date 2021/8/30 15:18
     */
    public List<Object> listObject(String bucketName, String prefix) {
        try {
            // 检查'mybucket'是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 列出'my-bucketname'里的对象
                // 桶名称、对象前缀、是否递归查找(默认true)
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName, prefix);
                List ret = new ArrayList(10);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    ret.add(item);
                }
                return ret;
            } else {
                log.info(bucketName + "桶不存在");
                return null;
            }
        } catch (Exception e) {
            log.error("查询bucket桶下的对象时发生异常，ErrorMsg：" + e.getMessage());
            return null;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 以流的形式下载一个对象
     * @date 2021/8/30 15:28
     */
    public InputStream getObject(String bucketName, String objectName) {
        try {
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            minioClient.statObject(bucketName, objectName);

            // 获取"myobject"的输入流。
            InputStream stream = minioClient.getObject(bucketName, objectName);
            // todo ..
//            // 读取输入流直到EOF并打印到控制台。
//            byte[] buf = new byte[16384];
//            int bytesRead;
//            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
//                System.out.println(new String(buf, 0, bytesRead));
//            }

//            stream.close();
            return stream;
        } catch (Exception e) {
            log.error("下载对象出现异常，ErrorMsg：" + e.getMessage());
        }
        return null;
    }

    /**
     * @param bais       上传对象的输入流
     * @param objectName 对象名
     * @param bucketName 要上传到的桶名称
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 上传对象
     * 拿到流的数据，使用随机生成的content key进行加密，并上传到指定存储桶中。同时将加密后的content key和iv做为加密对象有header也上传到存储桶中。content key使用传入到该方法的master key进行加密。
     * <p>
     * 如果对象大于5MB,客户端会自动进行multi part上传
     * @date 2021/8/30 15:38
     */
    public void putObject(ByteArrayInputStream bais, String bucketName, String objectName) {
        try {

            // 先创建桶
            if (!existBucket(bucketName)){
                boolean bucket = createBucket(bucketName);
                try {
                    // 创建桶和保存对象是异步的，保证桶创建成功
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 创建一个对象
            PutObjectOptions putObjectOptions = new PutObjectOptions(bais.available(), -1);
            // 文件类型：js文件
            putObjectOptions.setContentType("application/x-javascript");
            minioClient.putObject(bucketName, objectName, bais, putObjectOptions);
            bais.close();
            log.info("上传成功");
        } catch (Exception e) {
            log.error("上传文件到minio出现异常，ErrorMsg：" + e.getMessage());
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除bucket中的对象
     * @date 2021/8/30 15:52
     */
    public void deleteObjects(String bucketName, List<String> objectList) {
        try {
            // 删除my-bucketname里的多个对象
            for (Result<DeleteError> errorResult : minioClient.removeObjects(bucketName, objectList)) {
                DeleteError error = errorResult.get();
                log.error("删除对象发生异常，ErrorMsg：" + error.message());
            }
        } catch (Exception e) {
            log.error("删除对象时发生异常，ErrorMsg：" + e.getMessage());
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除bucket下某个对象
     * @date 2021/8/30 15:58
     */
    public void deleteObject(String bucketName, String objectName) {
        try {
            minioClient.removeObject(bucketName, objectName);
        } catch (Exception e) {
            log.error("删除对象时发生异常，ErrorMsg：" + e.getMessage());
        }
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取对象的连接url-（无过期时间的）
     * @date 2021/8/30 16:59
     */
    public String getObjectUrl(String bucketName, String objectName) {

        try {
            return minioClient.getObjectUrl(bucketName, objectName);
        } catch (Exception e) {
            log.error("获取对象连接url失败，ErrorMsg：" + e.getMessage());
            return null;
        }

    }

}
