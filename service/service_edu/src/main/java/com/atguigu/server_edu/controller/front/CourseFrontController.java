package com.atguigu.server_edu.controller.front;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.commonutils.ordervo.CourseDetailsOrder;
import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.chapter.ChapterVo;
import com.atguigu.server_edu.entity.frontvo.CourseDetailsVo;
import com.atguigu.server_edu.entity.frontvo.CourseFrontVo;
import com.atguigu.server_edu.service.EduChapterService;
import com.atguigu.server_edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    // 分页条件查询： 课程信息
    @PostMapping("/getCourse/{current}/{limit}")
    public ResultResponse getCourse(@PathVariable long current, @PathVariable long limit,
                                    @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        Page<EduCourse> pageCourse = new Page<>(current, limit);

        // 调用courseService里的分页方法
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);

        return ResultResponse.ok().data(map);
    }

    // 课程详情页
    @GetMapping("/courseDetails/{courseId}")
    public ResultResponse getCourseDetails(@PathVariable String courseId) {
        // 通过课程ID查找到章节小节，之前写过该接口，直接拿来用
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        // 通过课程Id，通过左连接查询，查找到新创建的VO对象
        CourseDetailsVo courseDetailsVo = courseService.getCourseDetailByCourseId(courseId);
        return ResultResponse.ok().data("chapterAndVideo", chapterVideoList).data("courseDetails", courseDetailsVo);
    }

    /**
     * @description: 根据课程id查找到课程信息
     * @author: chenzhiwei
     * @date: 2022/8/3 16:29
     * @param: [id]
     * @return: com.atguigu.commonutils.ordervo.CourseDetailsOrder
     **/
    @GetMapping("/getCourseInfo/{id}")
    public CourseDetailsOrder getCourseInfo(@PathVariable String id) {
        CourseDetailsVo courseDetailsVo = courseService.getCourseDetailByCourseId(id);

        CourseDetailsOrder courseDetailsOrder = new CourseDetailsOrder();
        BeanUtils.copyProperties(courseDetailsVo, courseDetailsOrder);
        return courseDetailsOrder;
    }


}
