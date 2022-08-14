package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果类
@Data
public class ResultResponse {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ResultResponse(){}

    //成功静态方法
    public static ResultResponse ok(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setSuccess(true);
        resultResponse.setCode(20000);
        resultResponse.setMsg("成功");
        return resultResponse;
    }

    //失败静态方法
    public static ResultResponse error(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setSuccess(false);
        resultResponse.setCode(20001);
        resultResponse.setMsg("失败");
        return resultResponse;
    }

    public ResultResponse success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultResponse code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultResponse msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ResultResponse data(String key, Object value){
        this.data.put(key, value);
        return  this;
    }

    public ResultResponse data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
