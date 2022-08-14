package com.atguigu.server_edu.service;

import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.frontvo.CourseDetailsVo;
import com.atguigu.server_edu.entity.frontvo.CourseFrontVo;
import com.atguigu.server_edu.entity.vo.CourseInfoVo;
import com.atguigu.server_edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
public interface EduCourseService extends IService<EduCourse> {

    // 添加课程方法
    String addCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getAllCourseInfoById(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    public CoursePublishVo getPublishInfo(String id);

    void removeCourse(String id);

    List<EduCourse> queryEightCourse();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseDetailsVo getCourseDetailByCourseId(String courseId);
}
