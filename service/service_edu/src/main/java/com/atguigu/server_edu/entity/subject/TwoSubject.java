package com.atguigu.server_edu.entity.subject;

import lombok.Data;

// 二级分类
@Data
public class TwoSubject {
    /**
     * 分类id
     */
    private String id;

    /**
     * 分类名称
     */
    private String title;
}
