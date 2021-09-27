package com.hdjtlgbbs.program.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSClient {
    @Value("${alibaba.cloud.accessKeyId}")
    private String accessKeyId;
    @Value("${alibaba.cloud.accessKeySecret}")
    private String accessKeySecret;
    @Value("${alibaba.cloud.BucketName}")
    private String BucketName ;
    @Value("${alibaba.cloud.endpoint}")
    private String endpoint;
    @Bean
    public OSS ossClient(){
        // 创建ClientConfiguration实例，按照您的需要修改默认参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 关闭CNAME选项。
        conf.setSupportCname(false);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);
        return ossClient;
    }
}
