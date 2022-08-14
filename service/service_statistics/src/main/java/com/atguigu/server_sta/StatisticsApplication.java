package com.atguigu.server_sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @BelongsProject: guli_parent
 * @BelongsPackage: PACKAGE_NAME
 * @Author: chenzhiwei
 * @CreateTime: 2022-08-03  23:13
 * @Description: TODO
 * @Version: 1.0
 */
@EnableDiscoveryClient  // 注册nacos
@EnableFeignClients     // 调用端使用该注解
@MapperScan("com.atguigu.server_sta.mapper")
@ComponentScan("com.atguigu")
@SpringBootApplication
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);

    }
}
