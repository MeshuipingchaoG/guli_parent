package com.atguigu.server_edu.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 小节
 */
@Data
public class VideoVo {

    /**
     * 小节id
     */
    private String id;

    /**
     * 小节名称
     */
    private String title;

    // 云视频资源id
    private String videoSourceId;
}
