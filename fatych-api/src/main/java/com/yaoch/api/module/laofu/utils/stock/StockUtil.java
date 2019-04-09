package com.yaoch.api.module.laofu.utils.stock;

import com.yaoch.common.utils.HttpUtil;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 股票工具
 *
 * @author 肥美的洪洪哥
 * @version 2019-04-01
 */
public class StockUtil {

    private static final Logger log = LoggerFactory.getLogger(StockUtil.class);

    //新浪获取股票信息接口
    private static final String SINA_STOCK_API = "http://hq.sinajs.cn/list=";

    /**
     * 根据股票编码获取当前信息
     *
     * @param stockCode
     * @return
     */
    public static Stock getStockDetail(String stockCode) throws Exception {
        String url = SINA_STOCK_API + stockEncode(stockCode);
        log.info("获取股票行情接口:"+url);
        String result = HttpUtil.get(url);
        log.info("获取新浪股票行情接口返回:" + result);
        result = result.substring(result.indexOf("=")+2,result.length());
        String[] stockInfo = result.split(",");
        Stock stock = new Stock();
        stock.setStockName(stockInfo[0]);
        stock.setTodayOpenPrice(stockInfo[1]);
        stock.setYesterdayClosePrice(stockInfo[2]);
        stock.setPrice(stockInfo[3]);
        stock.setTodayMaxPrice(stockInfo[4]);
        stock.setTodayMixPrice(stockInfo[5]);
        stock.setBuy1Price(stockInfo[6]);
        stock.setSell1Price(stockInfo[7]);
        stock.setDealNum(stockInfo[8]);
        stock.setDealMoney(stockInfo[9]);
        stock.setBuy1Num(stockInfo[10]);
        stock.setBuy1Price(stockInfo[11]);
        stock.setBuy2Num(stockInfo[12]);
        stock.setBuy2Price(stockInfo[13]);
        stock.setBuy3Num(stockInfo[14]);
        stock.setBuy3Price(stockInfo[15]);
        stock.setBuy4Num(stockInfo[16]);
        stock.setBuy4Price(stockInfo[17]);
        stock.setBuy5Num(stockInfo[18]);
        stock.setBuy5Price(stockInfo[19]);
        stock.setSell1Num(stockInfo[20]);
        stock.setSell1Price(stockInfo[21]);
        stock.setSell2Num(stockInfo[22]);
        stock.setSell2Price(stockInfo[23]);
        stock.setSell3Num(stockInfo[24]);
        stock.setSell3Price(stockInfo[25]);
        stock.setSell4Num(stockInfo[26]);
        stock.setSell4Price(stockInfo[27]);
        stock.setSell5Num(stockInfo[28]);
        stock.setSell5Price(stockInfo[29]);
        stock.setDate(stockInfo[30]);
        stock.setTime(stockInfo[31]);
        return stock;
    }

    /**
     * 股票编码
     * @param stockCode
     */
    private static String  stockEncode(String stockCode){
        //沪
        if(stockCode.startsWith("6")){
            stockCode = "sh" + stockCode;
        }
        if(stockCode.startsWith("0")){
            stockCode = "sz" +stockCode;
        }
        return stockCode;
    }

    public static void main(String[] agrs) {
        try {
            getStockDetail("sz002925");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
