package com.yaoch.api.module.laofu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.common.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 加密货币工具类
 * @author 肥美的洪洪哥
 * @version 2019-03-31
 */
public class CrtptocurrencyUtil {

    private static final Logger log = LoggerFactory.getLogger(CrtptocurrencyUtil.class);

    //币世界获取行情
    private static final String BISHIJIE_COINS_API = "https://www.bishijie.com/api/coins/major";
    //币世界获取前100市值的货币信息
    private static final String BISHIJIE_COINS_LIST = "https://www.bishijie.com/hangqing";

    /**
     * 获取币世界的货币行情
     * @return
     */
    public static JSONArray getCrtptocurrency(){
        String result = null;
        try {
            result = HttpUtil.get(BISHIJIE_COINS_API);
        } catch (IOException e) {
            log.error("获取币世界货币行情失败:",e);
            return null;
        }
        log.debug("获取币世界货币行情返回结果:"+result);
        JSONObject resultJson = JSON.parseObject(result);
        if( 0 == resultJson.getInteger("error")){
            return resultJson.getJSONArray("data");
        }
        return null;
    }

    /**
     * 获取币世界前100市值的货币信息
     * @return
     */
    public static JSONArray getCrtptocurrencyList(){
        String result = null;
        try {
            result = HttpUtil.get(BISHIJIE_COINS_LIST);
        } catch (IOException e) {
            log.error("获取币世界货币行情失败:",e);
        }
        Document document = Jsoup.parse(result);
        Element total = document.selectFirst("div[class=warpper clearfix]")
                .selectFirst("div[class=main_warpper]")
                .selectFirst("div[class=main_warpper_body]")
                .selectFirst("article")
                .selectFirst("div")
                .selectFirst("table")
                .selectFirst("tbody");
        Elements coins = total.select("tr");
        JSONArray coinArray = new JSONArray();
        for(Element coin:coins){
            JSONObject coinJson = new JSONObject();
            coinJson.put("coin",coin.selectFirst("td[class=coinname]").text());
            Elements priceEle = coin.select("td[class*=td_right]");
            coinJson.put("price",priceEle.get(0).text());
            coinJson.put("rise",priceEle.get(1).text());
            coinJson.put("volume",priceEle.get(2).text());
            coinJson.put("markey",priceEle.get(3).text());
            coinArray.add(coinJson);
        }
        //System.out.println(coinArray.toJSONString());
        return coinArray;
    }
    public static void main(String[] agrs){
        getCrtptocurrencyList();
    }
}
