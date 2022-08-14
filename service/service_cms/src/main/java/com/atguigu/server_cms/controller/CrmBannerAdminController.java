package com.atguigu.server_cms.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_cms.entity.CrmBanner;
import com.atguigu.server_cms.service.CrmBannerService;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 后台控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-19
 */
@RestController
@RequestMapping("/servercms/bannerAdmin")
@CrossOrigin
public class CrmBannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;


    // 简单分页查询
    @GetMapping("/getAllBanners/{current}/{limit}")
    @ApiOperation(value = "简单分页查询")
    public ResultResponse selectAllBanners(@PathVariable long current, @PathVariable long limit) {

        Page<CrmBanner> pageBanner = new Page<>(current, limit);
        crmBannerService.page(pageBanner, null);
        List<CrmBanner> records = pageBanner.getRecords();
        long total = pageBanner.getTotal();
        return ResultResponse.ok().data("list", records).data("total", total);
    }

    // 增加Banner
    @PostMapping("/addBanner")
    @ApiOperation(value = "添加Banner信息")
    public ResultResponse addBanner(@RequestBody CrmBanner crmBanner) {

        boolean flag = crmBannerService.save(crmBanner);
        if (flag) {
            return ResultResponse.ok();
        } else {
            return ResultResponse.error();
        }
    }

    @DeleteMapping("/deleteBanner/{id}")
    @ApiOperation(value = "物理删除")
    public ResultResponse deleteBanner( @PathVariable String id) {

        boolean flag = crmBannerService.removeById(id);
        if (flag) {
            return ResultResponse.ok();
        } else {
            return ResultResponse.error();
        }

    }

    @GetMapping("/getBannerById/{id}")
    @ApiOperation(value = "通过id查询")
    public ResultResponse getBannerById(@PathVariable String id) {

        CrmBanner banner = crmBannerService.getById(id);
        return ResultResponse.ok().data("banner", banner);
    }

    @PutMapping("/updateBanner")
    @ApiOperation(value = "修改Banner信息")
    public ResultResponse updateBanner(@RequestBody CrmBanner crmBanner) {

        crmBannerService.updateById(crmBanner);
        return ResultResponse.ok();
    }

}

