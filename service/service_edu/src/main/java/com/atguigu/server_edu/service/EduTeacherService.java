package com.atguigu.server_edu.service;

import com.atguigu.server_edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> queryFourTeacher();

    Map<String, Object> getTeacherList(Page<EduTeacher> teacherPage);
}
