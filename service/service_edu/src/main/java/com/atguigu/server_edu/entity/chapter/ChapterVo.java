package com.atguigu.server_edu.entity.chapter;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节
 */
@Data
public class ChapterVo {
    /**
     * 章节id
     */
    private String id;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 章节中含有小节
     */
    private List<VideoVo> children = new ArrayList<>();

}
