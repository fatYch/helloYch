package com.yaoch.api.module.laofu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.common.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


public class CityUtil {

    private static final Logger log = LoggerFactory.getLogger(CityUtil.class);

    //淘宝获取ip城市信息API
    private static final String TAOBAO_IP_CITY = "http://ip.taobao.com/service/getIpInfo.php";

    public static JSONObject getCityByIp(String ip){
        String result = null;
        try {
            result = HttpUtil.get(TAOBAO_IP_CITY+"?ip="+ip);
        } catch (IOException e) {
            log.error("获取城市信息失败:",e);
            return null;
        }
        log.debug("通过ip获取城市信息返回结果:"+result);
        JSONObject resultJson = JSON.parseObject(result);
        if(0 == resultJson.getInteger("code")){
            return resultJson.getJSONObject("data");
        }
        return null;
    }



}
