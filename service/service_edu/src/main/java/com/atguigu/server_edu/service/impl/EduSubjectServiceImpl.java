package com.atguigu.server_edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.server_edu.entity.EduSubject;
import com.atguigu.server_edu.entity.excel.SubjectData;
import com.atguigu.server_edu.entity.subject.OneSubject;
import com.atguigu.server_edu.entity.subject.TwoSubject;
import com.atguigu.server_edu.listener.SubjectExcelListener;
import com.atguigu.server_edu.mapper.EduSubjectMapper;
import com.atguigu.server_edu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void addSubject(MultipartFile file, EduSubjectService subjectService) {
        // EasyExcel读操作
        try {
            // 文件输入流
            InputStream inputStream = file.getInputStream();
            // 调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        // 1 查询所有一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id", 0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper1);

        // 2 查询所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", 0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);

        // 创建最终的list集合，存放最终封装的一级分类数据
        List<OneSubject> finalList = new ArrayList<>();

        // 3 封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            // 得到一级分类列表中每个eduSubject对象
            EduSubject eduSubject1 = oneSubjectList.get(i);
            // 把eduSubject对象里面的值取出来，放到OneSubject里
            OneSubject oneSubject = new OneSubject();
            /*oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());*/
            BeanUtils.copyProperties(eduSubject1, oneSubject);   // 等同与上面两行代码，将旧对象里面的所有值复制到新对象中
            // 多个oneSubject放到finalList中
            finalList.add(oneSubject);

            // 嵌套for循环，来遍历二级分类，因为二级分类在一级分类中
            // 创建最终集合存放二级分类
            List<TwoSubject> twoFinalList = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject eduSubject2 = twoSubjectList.get(j);

                // 判断二级分类是哪个一级分类下的，parent_id = id
                if (eduSubject2.getParentId().equals(eduSubject1.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2, twoSubject);
                    twoFinalList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalList);
        }
        // 4 封装二级分类
        return finalList;
    }
}
