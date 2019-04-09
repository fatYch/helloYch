package com.yaoch.api.module.laofu.utils;

import com.google.common.collect.Maps;
import com.yaoch.common.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * 贵金属信息工具类
 * 通过爬虫获取网页数据
 * @author 肥美的洪洪哥
 * @version 2019-03-30
 */
public class MetalUtil {

    public enum  Metal{
        CNY_AU("人民币账户黄金"),
        CNY_AG("人民币账户白银"),
        CNY_PT("人民币账户铂金"),
        CNY_PD("人民币账户钯金"),
        USD_AU("美元账户黄金"),
        USD_AG("美元账户白银"),
        USD_PT("美元账户铂金"),
        USD_PD("美元账户钯金");

        private String type;
         Metal(String type){
            this.type = type;
        }
        public String getType(){
             return type;
        }
    }

    //工商银行获取贵金属行情网址
    private static final String ICBC_METAL = "http://www.icbc.com.cn/ICBCDynamicSite/Charts/GoldTendencyPicture.aspx";

    /**
     * 获取贵金属实时行情
     * 信息来源：中国工商银行
     * @throws Exception
     */
    public static HashMap<String,String> getMetalInfo() throws Exception{
        String result = HttpUtil.get(ICBC_METAL);
        Document document = Jsoup.parse(result);
        Elements elements = document.select("body")
                .select("form").
                        select("table")
                .select("tr")
                .select("td")
                .select("table");
        Element element = elements.get(7); //账户贵金属行情
        elements = element.select("tbody")
                .select("tr")
                .select("td")
                .select("div")
                .select("table")
                .select("tbody")
                .select("tr");
        HashMap<String,String> metalMap = Maps.newHashMap();
        for(int i = 1;i<elements.size();i++){
            Element metal = elements.get(i);
            Elements infos = metal.select("td");
            metalMap.put(infos.get(0).text(),infos.get(2).text());
            System.out.println("品种:"+infos.get(0).text()+",当前买入价:"+infos.get(2).text());
        }
        return metalMap;
    }

    public static void main (String[] args){
        try {
            getMetalInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
