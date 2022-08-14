package com.atguigu.server_edu.service.impl;

import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.EduCourseDescription;
import com.atguigu.server_edu.entity.frontvo.CourseDetailsVo;
import com.atguigu.server_edu.entity.frontvo.CourseFrontVo;
import com.atguigu.server_edu.entity.vo.CourseInfoVo;
import com.atguigu.server_edu.entity.vo.CoursePublishVo;
import com.atguigu.server_edu.entity.vo.CourseQry;
import com.atguigu.server_edu.mapper.EduCourseMapper;
import com.atguigu.server_edu.service.EduChapterService;
import com.atguigu.server_edu.service.EduCourseDescriptionService;
import com.atguigu.server_edu.service.EduCourseService;
import com.atguigu.server_edu.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService{

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        // 1 向课程表添加基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert == 0) {
            throw new ChenZhiWeiException(20001, "添加课程失败");
        }
        // 2 向课程简介表添加信息
        String cid = eduCourse.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    @Override
    public CourseInfoVo getAllCourseInfoById(String courseId) {

        // 1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);

        // 2 查询课程简介表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);

        // 3 把查出来的东西放到CourseInfoVo对象中
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        // 1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int flag = baseMapper.updateById(eduCourse);

        if (flag == 0) {
            throw new ChenZhiWeiException(20001, "修改课程信息失败");
        }

        // 2 修改课程简介表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishInfo(String id) {
        CoursePublishVo publishInfo = baseMapper.getPublishInfo(id);
        return publishInfo;
    }

    @Override
    public void removeCourse(String id) {

        // 1 根据课程id 删除所有小节
        videoService.removeVideoByCourseId(id);

        // 2 根据课程id 删除所有章节
        chapterService.removeChapterByCourseId(id);

        // 3 根据课程id 删除课程描述
        courseDescriptionService.removeById(id);

        // 4 根据课程id 删除课程表所有信息
        baseMapper.deleteById(id);
        /*int i = baseMapper.deleteById(id);
        if (i == 0) {
            throw new ChenZhiWeiException(20001, "删除失败！");
        }*/

    }


    // 查八条课程的具体实现
    @Override
    @Cacheable(value = "course", key = "'selectIndexList'")
    public List<EduCourse> queryEightCourse() {
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");
        List<EduCourse> courseList = baseMapper.selectList(courseWrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 判断条件是否为空
        // 如果一级分类不为空，
        String subjectParentId = courseFrontVo.getSubjectParentId();
        String subjectId = courseFrontVo.getSubjectId();
        String buyCountSort = courseFrontVo.getBuyCountSort();
        String gmtCreateSort = courseFrontVo.getGmtCreateSort();
        String priceSort = courseFrontVo.getPriceSort();

        if (!StringUtils.isEmpty(subjectParentId)) {
            /**
             * 相当于 select * from edu_course
             *        where subject_parent_id = 'subjectParentId'
             */
            wrapper.eq("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }

        if (!StringUtils.isEmpty(buyCountSort)) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(priceSort)) {
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse, wrapper);
        // 取出分页数据
        long current = pageCourse.getCurrent();
        List<EduCourse> records = pageCourse.getRecords();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();

        // 将值存入map集合里
        Map<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("records", records);
        map.put("size", size);
        map.put("total", total);
        return map;
    }

    @Override
    public CourseDetailsVo getCourseDetailByCourseId(String courseId) {
        CourseDetailsVo courseDetailsVo = baseMapper.getCourseDetailByCourseId(courseId);
        return courseDetailsVo;
    }

}
