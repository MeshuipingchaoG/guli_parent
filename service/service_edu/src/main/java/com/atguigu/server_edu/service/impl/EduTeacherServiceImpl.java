package com.atguigu.server_edu.service.impl;

import com.atguigu.server_edu.entity.EduTeacher;
import com.atguigu.server_edu.mapper.EduTeacherMapper;
import com.atguigu.server_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    @Cacheable(value = "teacher", key = "'selectIndexList'")
    public List<EduTeacher> queryFourTeacher() {
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = baseMapper.selectList(teacherWrapper);
        return teacherList;
    }

    @Override
    public Map<String, Object> getTeacherList(Page<EduTeacher> teacherPage) {

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage, queryWrapper);

        // 取出分页数据
        long current = teacherPage.getCurrent();
        List<EduTeacher> records = teacherPage.getRecords();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean next = teacherPage.hasNext();
        boolean previous = teacherPage.hasPrevious();

        // 把分页的数据放到map集合里
        Map<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("records", records);
        map.put("size", size);
        map.put("total", total);
        map.put("next", next);
        map.put("previous", previous);
        return map;
    }
}
