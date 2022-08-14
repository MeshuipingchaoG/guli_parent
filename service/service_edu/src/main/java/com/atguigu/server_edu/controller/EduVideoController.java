package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.client.VodClient;
import com.atguigu.server_edu.entity.EduVideo;
import com.atguigu.server_edu.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    // 添加小节
    @PostMapping("/addVideo")
    public ResultResponse addVideo(@RequestBody EduVideo eduVideo) {

        videoService.save(eduVideo);
        return ResultResponse.ok();
    }

    // 删除小节
    @DeleteMapping("/{id}")
    public ResultResponse deleteVideo(@PathVariable String id) {
        //删除小节，顺便把视频也删了
        // 通过小节id，查到EduVideo对象（该对象里面有视频id）
        EduVideo eduVideo = videoService.getById(id);
        //通过对象拿到视频id
        String videoSourceId = eduVideo.getVideoSourceId();

        // 判断videoSourceId是否为空，为空就直接删除小节，不为空就删除视频和小节
        if (!StringUtils.isEmpty(videoSourceId)) {
            ResultResponse resultResponse = vodClient.removeAliyunVideo(videoSourceId);

            if (resultResponse.getCode() == 20001) {
                throw new ChenZhiWeiException(20001, "删除视频失败，执行熔断器.....");
            }
        }
       // 删除小节
        videoService.removeById(id);
        return ResultResponse.ok();
    }

    // 通过id查找小节
    @GetMapping("/getVideoById/{id}")
    public ResultResponse getVideoById(@PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        return ResultResponse.ok().data("video" , eduVideo);
    }

    // 修改小节
    @PostMapping("/updateVideo")
    public ResultResponse updateVideo(@RequestBody EduVideo eduVideo) {

        videoService.updateById(eduVideo);
        return ResultResponse.ok();
    }
}

