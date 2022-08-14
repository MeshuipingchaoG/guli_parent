package com.atguigu.server_sta.service.impl;

import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_sta.client.UcenterClient;
import com.atguigu.server_sta.entity.StatisticsDaily;
import com.atguigu.server_sta.entity.vo.ConditionVo;
import com.atguigu.server_sta.mapper.StatisticsDailyMapper;
import com.atguigu.server_sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-08-03
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    public static final String LOGIN_NUM = "login_num";
    public static final String REGISTER_NUM = "register_num";
    public static final String VIDEO_VIEW_NUM = "video_view_num";
    public static final String COURSE_NUM = "course_num";

    @Override
    public void registerCount(String day) {
        // 添加记录之前，先删除相同日期的数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", day);
        baseMapper.delete(queryWrapper);

        // 远程调用，获取到某一天注册的人数
        ResultResponse resultResponse = ucenterClient.getRegisterCount(day);
        int userCount = (int) resultResponse.getData().get("count");

        // 把数据添加到数据库中的统计分析表里面
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(userCount);  // 注册人数
        statisticsDaily.setDateCalculated(day);   // 统计日期

        // 造的假数据
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getData(ConditionVo conditionVo) {

        String type = conditionVo.getType();
        String begin = conditionVo.getBegin();
        String end = conditionVo.getEnd();

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        queryWrapper.select("date_calculated", type);

        List<StatisticsDaily> list = baseMapper.selectList(queryWrapper);

        List<String> dataCalculatedList = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();

        // 遍历查询所有数据的list集合，进行封装
        for (StatisticsDaily statisticsDaily : list) {
            // 封装日期list集合
            dataCalculatedList.add(statisticsDaily.getDateCalculated());
            // 封装数量list集合
            if (type.equals(LOGIN_NUM)) {
                numList.add(statisticsDaily.getLoginNum());
            }
            if (type.equals(REGISTER_NUM)) {
                numList.add(statisticsDaily.getRegisterNum());
            }
            if (type.equals(VIDEO_VIEW_NUM)) {
                numList.add(statisticsDaily.getVideoViewNum());
            }
            if (type.equals(COURSE_NUM)) {
                numList.add(statisticsDaily.getCourseNum());
            }
        }

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("dataCalculatedList", dataCalculatedList);
        hashMap.put("numList", numList);
        return hashMap;
    }
}
