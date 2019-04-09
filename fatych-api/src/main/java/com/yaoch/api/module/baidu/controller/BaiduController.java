package com.yaoch.api.module.baidu.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaoch.api.module.baidu.util.BaiduUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.yaoch.common.utils.response.ApiResponse;


@Api(tags = "百度开放平台",description = "身份证识别、生成短网址")
@RestController
@RequestMapping("/baidu")
public class BaiduController {


    @ApiOperation("识别身份证")
    @PostMapping(value = "/recognizeIdcard",consumes = "application/x-www-form-urlencoded")
    public ApiResponse recognizeIdcard(@RequestParam @ApiParam("图片base64") String photo){
        JSONObject jsonObject = BaiduUtil.recognizeIdcard(photo);
        if(jsonObject == null){
            return new ApiResponse("识别身份证失败，请稍后再试！");
        }
        if(!StringUtils.isEmpty(jsonObject.getString("error"))){
            return new ApiResponse(jsonObject.getString("error"));
        }
        return new ApiResponse(jsonObject);
    }

    @ApiOperation("获取短网址")
    @GetMapping(value = "/getShortUrl")
    public ApiResponse createShortUrl(@RequestParam @ApiParam("原网址") String url){
        return new ApiResponse(BaiduUtil.createShortUrl(url));
    }






}
