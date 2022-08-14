package com.atguigu.server_order.client;

import com.atguigu.commonutils.ordervo.CourseDetailsOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "server-edu", fallback = EduClientImpl.class)
public interface EduClient {

    // 根据课程id查找到课程信息
    @GetMapping("/eduservice/coursefront/getCourseInfo/{id}")
    public CourseDetailsOrder getCourseInfo(@PathVariable("id") String id);
}
