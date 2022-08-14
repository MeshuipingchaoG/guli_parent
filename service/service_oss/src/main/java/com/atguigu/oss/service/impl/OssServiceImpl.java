package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        //String objectName = "exampledir/exampleobject.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            // 1 在文件名称里面添加一个随机唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            // 2 把文件按照日期分类
            // 获取当前的日期
            String datePath = new DateTime().toString("yyyy/MM/dd");

            // 拼接
            fileName = datePath + "/" + fileName;
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, inputStream);

            ossClient.shutdown();

            // 把上传的的阿里云oss路径手动拼接出来
            // https://chenzhiwei-edu.oss-cn-beijing.aliyuncs.com/%E5%A3%81%E7%BA%B8/2022-05-15%2009-33-29.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" +fileName;
            return url;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
