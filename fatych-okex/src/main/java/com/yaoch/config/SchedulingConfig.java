package com.yaoch.config;

import com.yaoch.common.utils.DateUtil;
import com.yaoch.module.account.entity.AccountVo;
import com.yaoch.module.account.entity.InstrumentVo;
import com.yaoch.module.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@EnableScheduling
@Configuration
public class SchedulingConfig {

    private final static Logger log = LoggerFactory.getLogger(SchedulingConfig.class);

    @Autowired
    private AccountService accountService;


    @Scheduled(cron = "*/20 * * * * ?")
    public void getAccount(){
        System.out.println("🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮🐮");
        System.out.println(DateUtil.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
        AccountVo accountVo = accountService.getAccount();
        if(accountVo == null){
            return;
        }
        System.out.println("$"+accountVo.getMoney());
        for(InstrumentVo instrumentVo:accountVo.getInstruments()){
            System.out.println(instrumentVo.getName()+"  " +instrumentVo.getNum() +"  "+"$"+instrumentVo.getPrice());
        }

    }
}
