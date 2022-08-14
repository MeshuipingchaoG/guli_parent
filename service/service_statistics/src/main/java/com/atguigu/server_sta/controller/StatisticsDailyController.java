package com.atguigu.server_sta.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_sta.entity.vo.ConditionVo;
import com.atguigu.server_sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author chenzhiwei
 * @since 2022-08-03
 */
@RestController
@CrossOrigin
@RequestMapping("/serversta/statistics")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;


    /**
     * @description: 获取每天注册的人数
     * @author: chenzhiwei
     * @date: 2022/8/4 22:47
     * @param: [day]
     * @return: com.atguigu.commonutils.ResultResponse
     **/
    @PostMapping("/registerCount/{day}")
    public ResultResponse registerCount(@PathVariable String day) {

        statisticsDailyService.registerCount(day);
        return ResultResponse.ok();
    }


    /**
     * @description: 通过条件查询数据
     * @author: chenzhiwei
     * @date: 2022/8/6 16:29
     * @param: [conditionVo]
     * @return: com.atguigu.commonutils.ResultResponse
     **/
    @PostMapping("/getData")
    public ResultResponse getData(@RequestBody ConditionVo conditionVo) {

        Map<String, Object> hashMap = statisticsDailyService.getData(conditionVo);
        return ResultResponse.ok().data(hashMap);
    }

}

