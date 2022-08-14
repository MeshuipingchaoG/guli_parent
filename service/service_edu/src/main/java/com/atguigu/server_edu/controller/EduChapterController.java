package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduChapter;
import com.atguigu.server_edu.entity.chapter.ChapterVo;
import com.atguigu.server_edu.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public ResultResponse getChapterVideoByCourseId(@PathVariable String courseId) {

        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return ResultResponse.ok().data("chapterVideoList", list);
    }

    // 添加章节
    @PostMapping("/addChapter")
    public ResultResponse addChapter(@RequestBody EduChapter eduChapter) {

        // 调用service层的添加方法
        chapterService.save(eduChapter);
        return  ResultResponse.ok();
    }

    // 通过章节id查询
    @GetMapping("/getChapterById/{chapterId}")
    public ResultResponse getChapterById(@PathVariable String chapterId) {

        EduChapter eduChapter = chapterService.getById(chapterId);
        return ResultResponse.ok().data("chapter", eduChapter);
    }

    // 修改章节
    @PostMapping("/updateChapter")
    public ResultResponse updateChapter(@RequestBody EduChapter eduChapter) {

        chapterService.updateById(eduChapter);
        return ResultResponse.ok();
    }

    //删除章节
    @DeleteMapping("/{chapterId}")
    public ResultResponse deleteChapter(@PathVariable String chapterId) {

       boolean flag = chapterService.deleteChapter(chapterId);
       if (flag) {
           return ResultResponse.ok();
       } else {
           return ResultResponse.error();
       }
    }


}

