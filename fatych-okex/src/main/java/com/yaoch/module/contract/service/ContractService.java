package com.yaoch.module.contract.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.common.utils.response.ApiResponse;
import com.yaoch.common.utils.response.ApiResult;
import com.yaoch.module.account.entity.InstrumentVo;
import com.yaoch.module.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ContractService extends BaseService {

    //永续合约持仓信息
    private final String swapApi = "/api/swap/v3/accounts";

    public List<InstrumentVo> getAccountInstrument(){
        String result = doGet(swapApi);
        //log.info("永续合约:"+result);
        if(!StringUtils.isEmpty(result)){
            JSONObject object = JSON.parseObject(result);
            JSONArray array = object.getJSONArray("info");
            if(CollectionUtils.isEmpty(array)){
                return null;
            }
            List<InstrumentVo> instrumentVos = array.toJavaList(InstrumentVo.class);
            return instrumentVos;
        }
        return null;
    }
}
