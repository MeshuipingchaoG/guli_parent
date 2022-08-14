package com.atguigu.server_edu.mapper;

import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.frontvo.CourseDetailsVo;
import com.atguigu.server_edu.entity.vo.CoursePublishVo;
import com.atguigu.server_edu.entity.vo.CourseQry;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishInfo(String id);

    CourseDetailsVo getCourseDetailByCourseId(String courseId);
}
