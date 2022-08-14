package com.atguigu.server_edu.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseFrontVo {

    @ApiModelProperty(value = "课程专业ID，二级分类ID")
    private String subjectId;

    @ApiModelProperty(value = "一级分类ID")
    private String subjectParentId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
