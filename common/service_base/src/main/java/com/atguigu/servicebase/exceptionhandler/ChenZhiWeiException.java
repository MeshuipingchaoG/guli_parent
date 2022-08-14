package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 自定义异常类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChenZhiWeiException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 报错信息
     */
    private String msg;

}
