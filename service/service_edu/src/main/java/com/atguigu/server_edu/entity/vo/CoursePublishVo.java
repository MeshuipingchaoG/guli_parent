package com.atguigu.server_edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePublishVo {

    @ApiModelProperty(value = "课程id")
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程价格")
    private BigDecimal price;

    @ApiModelProperty(value = "课程封面")
    private String cover;

    @ApiModelProperty(value = "课程描述")
    private String description;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "一级分类")
    private String oneSubject;

    @ApiModelProperty(value = "二级分类")
    private String twoSubject;
}
