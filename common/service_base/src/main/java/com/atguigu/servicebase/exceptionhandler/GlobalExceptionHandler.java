package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 指定执行什么异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultResponse error(Exception exception) {
        exception.printStackTrace();
        return ResultResponse.error().msg("执行了全局异常处理.....");
    }

    // 特定异常    用得较少
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultResponse error(ArithmeticException exception) {
        exception.printStackTrace();
        return ResultResponse.error().msg("执行了ArithmeticException异常....");
    }

    // 自定义异常
    @ExceptionHandler(ChenZhiWeiException.class)
    @ResponseBody
    public ResultResponse error(ChenZhiWeiException exception) {
        log.error(exception.getMsg());
        exception.printStackTrace();

        return ResultResponse.error().code(exception.getCode()).msg(exception.getMsg());
    }

}
