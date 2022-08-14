package com.atguigu.server_cms.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_cms.entity.CrmBanner;
import com.atguigu.server_cms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author czw
 * @since 2022/7/19
 *
 * <p>
 *     Banner前台显示
 * </p>
 */
@RestController
@RequestMapping("/servercms/bannerFront")
@CrossOrigin
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/getAllBanner")
    public ResultResponse getAllBanner() {

        List<CrmBanner> bannerList = bannerService.selectAllBanner();
        return ResultResponse.ok().data("list", bannerList);
    }
}
