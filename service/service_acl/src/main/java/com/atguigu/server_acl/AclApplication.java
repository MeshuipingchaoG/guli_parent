package com.atguigu.server_acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @BelongsProject: guli_parent
 * @BelongsPackage: com.atguigu.server_acl
 * @Author: chenzhiwei
 * @CreateTime: 2022-08-09  22:00
 * @Description: TODO
 * @Version: 1.0
 */

@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.server_acl.mapper")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
