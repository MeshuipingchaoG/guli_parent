package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduTeacher;
import com.atguigu.server_edu.entity.vo.TeacherQry;
import com.atguigu.server_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    //访问地址： http://localhost:8081/server/teacher/findAll
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "查询所有教师")
    @GetMapping("/findAll")
    public ResultResponse findAllTeacher() {
        //调用sevice的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return ResultResponse.ok().data("items", list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public ResultResponse removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return ResultResponse.ok();
        } else {
            return ResultResponse.error();
        }

    }

    //3 分页查询方法
    @ApiOperation(value = "分页查询")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public ResultResponse pageListTeacher(@PathVariable long current,
                                          @PathVariable long limit) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，把分页所有数据封装到pageTeacher对象中
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        
        return ResultResponse.ok().data("total", total).data("records", records);
    }

    //4 条件查询带分页
    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResultResponse pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                               @RequestBody(required = false) TeacherQry teacherQry) {

        /*try {
            int i = 10/0;
        }catch (Exception exception) {
            // 执行自定义异常
            throw new ChenZhiWeiException(20001, "执行了自定义异常....");

        }*/

        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String name = teacherQry.getName();
        Integer level = teacherQry.getLevel();
        String begin = teacherQry.getBegin();
        String end = teacherQry.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }
        // 排序


        // 调用方法实现分页查询
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return ResultResponse.ok().data("total", total).data("records", records);
    }

    // 5 添加讲师功能
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public ResultResponse addTeacher(@RequestBody EduTeacher teacher){

        boolean save = teacherService.save(teacher);
        if (save) {
            return ResultResponse.ok();
        }else {
            return ResultResponse.error();
        }

    }

    // 6 根据讲师id查询
    @ApiOperation(value = "根据讲师id查询")
    @GetMapping("/getTeacher/{id}")
    public ResultResponse selectTeacherById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return ResultResponse.ok().data("teacher",teacher);
    }

    // 7 讲师修改功能
    @ApiOperation(value = "修改教师信息")
    @PostMapping("/updateTeacher")
    public ResultResponse updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return ResultResponse.ok();
        }else {
            return ResultResponse.error();
        }
    }
}

