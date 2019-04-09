package com.yaoch.api.module.laofu.utils;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yaoch.common.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class WeatherUtil {

    private static final Logger log = LoggerFactory.getLogger(WeatherUtil.class);
    private static final String sinaWeather = "http://weather.sina.com.cn/";

    public static HashMap<String,Object> getSinaWeather(String city) throws Exception{
        HashMap<String,Object> weatherMap = Maps.newHashMap();
        String weatherUrl = sinaWeather + city;
        String result = HttpUtil.get(weatherUrl);
        Document document = Jsoup.parse(result);
        Element element = document.selectFirst("body")
                .selectFirst("div[class=wrap]")
                .selectFirst("div[class=slider_w]")
                .selectFirst("div[class=slider]")
                .selectFirst("div[class=slider_i]");
        //获取城市以及日期
        Element cityEle = element.selectFirst("div[class=slider_ct]");
        weatherMap.put("city",cityEle.selectFirst("div[class=slider_ct_header]").selectFirst("h4[id=slider_ct_name]").text());
        weatherMap.put("date",cityEle.selectFirst("p[class=slider_ct_date]").text());
        weatherMap.put("time",cityEle.selectFirst("div[class=slider_ct_time png24]").text());
        //获取天气信息
        Element weatherEle = element.selectFirst("div[class=slider_states]");
        weatherMap.put("temperature",weatherEle.selectFirst("div[class=slider_degree]").text());
        String detail = weatherEle.selectFirst("p[class=slider_detail]").text();
        //weatherMap.put("detail",weatherEle.selectFirst("p[class=slider_detail]").text());
        //获取污染指数
        Element pollutionEle = element.selectFirst("div[class=slider_warn]");
        detail = detail + " | " + pollutionEle.selectFirst("div[class=slider_warn_i_tt]").selectFirst("h6").text()+"："+
                pollutionEle.selectFirst("div[class=slider_warn_i_tt]").selectFirst("p").text()
                +" "+pollutionEle.selectFirst("div[class=slider_warn_i_c]").selectFirst("p").text();
        weatherMap.put("detail",detail);
        log.debug("老夫要的结果:"+ JSON.toJSONString(weatherMap));
        return weatherMap;
    }

    public static void main(String[] args){
        try {
            getSinaWeather("guangzhou");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
