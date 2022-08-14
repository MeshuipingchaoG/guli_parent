package com.atguigu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @BelongsProject: guli_parent
 * @BelongsPackage: com.atguigu.gateway
 * @Author: chenzhiwei
 * @CreateTime: 2022-08-09  10:48
 * @Description: TODO
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient  // nacos注册
public class ApiGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class, args);
    }
}
