package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.vo.CourseInfoVo;
import com.atguigu.server_edu.entity.vo.CoursePublishVo;
import com.atguigu.server_edu.entity.vo.CourseQry;
import com.atguigu.server_edu.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired

    private EduCourseService courseService;

    // 添加课程信息
    @PostMapping("/addCourseInfo")
    public ResultResponse saveCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        // 调用service里面的添加课程方法
        String id = courseService.addCourseInfo(courseInfoVo);
        return ResultResponse.ok().data("courseId", id);
    }

    // 根据课程id查询课程基本信息
    @GetMapping("/getAllCourseInfoById/{courseId}")
    public ResultResponse getAllCourseInfo(@PathVariable String courseId) {

        CourseInfoVo courseInfoVo = courseService.getAllCourseInfoById(courseId);
        return ResultResponse.ok().data("courseInfoVo", courseInfoVo);
    }

    // 修改课程信息
    @PostMapping("/updateCourseInfo")
    public ResultResponse updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        courseService.updateCourseInfo(courseInfoVo);
        return ResultResponse.ok();
    }

    // 通过id查最终发布界面的信息
    @GetMapping("/getCoursePublishInfo/{id}")
    public ResultResponse getCoursePublishInfo(@PathVariable String id) {

        CoursePublishVo publishInfo = courseService.getPublishInfo(id);
        return ResultResponse.ok().data("publish", publishInfo);
    }

    //  最终发布,通过修改里面的状态来判断发布
    @PostMapping("/publishCourse/{id}")
    public ResultResponse publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return ResultResponse.ok();
    }

    // 查询所有课程和条件带分页
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public ResultResponse pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                                @RequestBody(required = false) CourseQry courseQry) {


        // 创建page对象
        Page<EduCourse> pageCourse = new Page<>(current, limit);

        // 构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = courseQry.getTitle();
        String status = courseQry.getStatus();

        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("gmt_create");

        courseService.page(pageCourse, wrapper);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return ResultResponse.ok().data("total", total).data("list", records);
    }

    // 通过课程id逻辑删除
    @DeleteMapping("/{id}")
    public ResultResponse deleteCourse(@PathVariable String id) {

        courseService.removeCourse(id);
        return ResultResponse.ok();
    }

    // 通过课程Id查对象
    @GetMapping("/getListById/{id}")
    public ResultResponse getListById(@PathVariable String id) {

        EduCourse eduCourse = courseService.getById(id);
        return ResultResponse.ok().data("list", eduCourse);
    }

    // 修改课程列表
    @PostMapping("/updateList")
    public ResultResponse updateList(@RequestBody EduCourse eduCourse) {

        courseService.updateById(eduCourse);
        return ResultResponse.ok();
    }
}

