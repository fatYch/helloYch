package com.yaoch.module.account.service;

import com.yaoch.common.utils.MathUtil;
import com.yaoch.module.account.entity.AccountVo;
import com.yaoch.module.account.entity.InstrumentVo;
import com.yaoch.module.base.BaseService;
import com.yaoch.module.contract.controller.ContractController;
import com.yaoch.module.contract.service.ContractService;
import com.yaoch.module.market.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AccountService extends BaseService {

    @Autowired
    private ContractService contractService;
    @Autowired
    private MarketService marketService;

    /**
     * 获取永续合约的总值
     * @return
     */
    public AccountVo getAccount(){
        List<InstrumentVo> instrumentVos = contractService.getAccountInstrument();
        if(CollectionUtils.isEmpty(instrumentVos)){
            return null;
        }
        AccountVo accountVo = new AccountVo();
        Double total = 0.0;
        for(InstrumentVo instrumentVo : instrumentVos){
            String name = instrumentVo.getName();
            name = name.substring(0,name.indexOf("-"));
            instrumentVo.setName(name);
            instrumentVo.setPrice(marketService.getInstrumentPrice(name));
            total += (instrumentVo.getPrice()*instrumentVo.getNum());
        }
        accountVo.setInstruments(instrumentVos);
        accountVo.setMoney(MathUtil.round(total,2));
        return accountVo;
    }

}
