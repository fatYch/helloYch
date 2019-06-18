package com.yaoch.module.contract.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.common.utils.HttpUtil;
import com.yaoch.common.utils.response.ApiResponse;
import com.yaoch.common.utils.response.ApiResult;
import com.yaoch.module.account.entity.InstrumentVo;
import com.yaoch.module.base.BaseController;
import com.yaoch.module.contract.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "合约模块")
@RequestMapping("/contract")
public class ContractController extends BaseController {

    @Autowired
    private ContractService contractService;


    @ApiOperation("永续合约持仓信息")
    @GetMapping("/swapPosition")
    public ApiResponse<InstrumentVo> swapPosition(){
        return new ApiResponse(contractService.getAccountInstrument());
    }

}
