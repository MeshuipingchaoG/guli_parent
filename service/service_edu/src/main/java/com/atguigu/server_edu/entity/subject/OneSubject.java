package com.atguigu.server_edu.entity.subject;


import lombok.Data;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

// 一级分类
@Data
public class OneSubject {

    /**
     * 分类id
     */
    private String id;

    /**
     * 课程分类名称
     */
    private String title;

    // 一级分类中应该包含二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
