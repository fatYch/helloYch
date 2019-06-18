package com.yaoch.module.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.module.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MarketService extends BaseService {

    private static final String INSTRUMENT_TICKER = "/api/spot/v3/instruments/<instrument-id>/ticker";

    /**
     * 获取某币的最新成交价
     * @param instrument
     * @return
     */
    public Double getInstrumentPrice(String instrument){
        //默认查询USDT交易对
        instrument = instrument + "-USDT";
        String url = INSTRUMENT_TICKER.replace("<instrument-id>",instrument);
        String result = doGet(url);
        //log.info("查询某币最新信息:"+result);
        if(result != null){
            JSONObject jsonObject = JSON.parseObject(result);
            Double price = jsonObject.getDouble("last");
            return price;
        }
        return 0.0;
    }
}
