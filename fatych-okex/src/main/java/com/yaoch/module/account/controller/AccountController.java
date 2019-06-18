package com.yaoch.module.account.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.common.utils.HttpUtil;
import com.yaoch.common.utils.response.ApiResponse;
import com.yaoch.common.utils.response.ApiResult;
import com.yaoch.module.account.entity.AccountVo;
import com.yaoch.module.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账户模块")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation("查询永续合约钱包余额")
    @GetMapping("/contract")
    public ApiResponse<AccountVo> contract(){
        return new ApiResponse<>(accountService.getAccount());
    }
}
