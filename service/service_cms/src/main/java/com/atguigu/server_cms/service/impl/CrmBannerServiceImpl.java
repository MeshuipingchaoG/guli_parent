package com.atguigu.server_cms.service.impl;

import com.atguigu.server_cms.entity.CrmBanner;
import com.atguigu.server_cms.mapper.CrmBannerMapper;
import com.atguigu.server_cms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {

        // 根据bannerId降序查找两条数据
        QueryWrapper<CrmBanner> bannerWrapper = new QueryWrapper<>();
        bannerWrapper.orderByDesc("id");
        bannerWrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
