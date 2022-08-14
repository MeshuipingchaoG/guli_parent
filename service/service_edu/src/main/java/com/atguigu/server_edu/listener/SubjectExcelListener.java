package com.atguigu.server_edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.server_edu.entity.EduSubject;
import com.atguigu.server_edu.entity.excel.SubjectData;
import com.atguigu.server_edu.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

   public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    /**
     * 一行一行读取
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new ChenZhiWeiException(20001, "文件数据为空");
        }
        // 一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        // 判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        // 添加二级分类
        // 判断二级分类是否重复
        EduSubject existTowSubject = this.existTowSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTowSubject == null) {
            existTowSubject = new EduSubject();
            existTowSubject.setTitle(subjectData.getTwoSubjectName());
            existTowSubject.setParentId(pid);
            subjectService.save(existTowSubject);
        }

    }

    // 判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }
    // 判断二级分类不能重复添加
    private EduSubject existTowSubject(EduSubjectService subjectService, String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject two = subjectService.getOne(wrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
