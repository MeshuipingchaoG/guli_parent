package com.atguigu.server_sta.service;

import com.atguigu.server_sta.entity.StatisticsDaily;
import com.atguigu.server_sta.entity.vo.ConditionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-08-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    Map<String, Object> getData(ConditionVo conditionVo);
}
