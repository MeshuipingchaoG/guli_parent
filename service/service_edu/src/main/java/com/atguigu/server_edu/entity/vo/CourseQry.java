package com.atguigu.server_edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CourseQry {


    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

}
