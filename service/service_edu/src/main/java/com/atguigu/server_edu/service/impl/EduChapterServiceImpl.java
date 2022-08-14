package com.atguigu.server_edu.service.impl;

import com.atguigu.server_edu.entity.EduChapter;
import com.atguigu.server_edu.entity.EduVideo;
import com.atguigu.server_edu.entity.chapter.ChapterVo;
import com.atguigu.server_edu.entity.chapter.VideoVo;
import com.atguigu.server_edu.mapper.EduChapterMapper;
import com.atguigu.server_edu.service.EduChapterService;
import com.atguigu.server_edu.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        // 1 查出所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);
        
        // 2 查出所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> videoList = videoService.list(wrapperVideo);
        
        // 3 封装所有章节
        List<ChapterVo> finalChapterList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter eduChapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalChapterList.add(chapterVo);

            // 4 封装所有的小节
            List<VideoVo> finalVideoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo eduVideo = videoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    finalVideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(finalVideoList);
        }
        return finalChapterList;
    }

    // 删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {

        // 1 根据章节id查询，章节下面的小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        //判断，如果章节里面有小节就不能删除
        if (count == 0) {
            int i = baseMapper.deleteById(chapterId);
            return i > 0 ;
        } else {
            throw new ChenZhiWeiException(20001, "章节里面有小节，删除失败！");
        }

    }

    @Override
    public void removeChapterByCourseId(String id) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
