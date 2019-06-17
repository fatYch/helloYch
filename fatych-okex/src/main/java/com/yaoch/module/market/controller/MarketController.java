package com.yaoch.module.market.controller;

import com.yaoch.common.utils.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("市场行情模块")
@RestController
@RequestMapping("/market")
public class MarketController {

    @ApiOperation("测试")
    @GetMapping("/test")
    public ApiResponse test(){
        return new ApiResponse("ok");
    }
}
