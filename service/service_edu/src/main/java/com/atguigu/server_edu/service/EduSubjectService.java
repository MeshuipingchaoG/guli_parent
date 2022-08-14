package com.atguigu.server_edu.service;

import com.atguigu.server_edu.entity.EduSubject;
import com.atguigu.server_edu.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-05
 */
public interface EduSubjectService extends IService<EduSubject> {

    // 添加课程分类
    void addSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
