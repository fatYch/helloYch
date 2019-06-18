package com.yaoch.module.base;

import com.google.common.collect.Maps;
import com.yaoch.common.utils.Base64Util;
import com.yaoch.common.utils.HttpUtil;
import com.yaoch.common.utils.SHA256Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

//@Component
public class BaseController {
//    private final static Logger log = LoggerFactory.getLogger(BaseController.class);
//    @Value("${okex.domain}")
//    protected static String domain = "https://www.okex.me";
//    @Value("${okex.apiKey}")
//    protected static String apiKey = "0d556f15-c41b-477f-baf1-715e9803f184";
//    @Value("${okex.passphrase}")
//    protected static String passphrase = "1111111112";
//    @Value("${okex.secretKey}")
//    protected static String secretKey = "C001783899C1F4C62A6340768ABD2EDA";
//
//    protected final static String HTTP_GET = "GET";
//    protected final static String HTTP_POST = "POST";
//
//    protected static String doGet(String url){
//        try {
//            /**
//             * 所有REST请求头都必须包含以下内容：
//             * OK-ACCESS-KEY字符串类型的API Key。
//             * OK-ACCESS-SIGN使用base64编码签名(请参阅签名)。
//             * OK-ACCESS-TIMESTAMP发起请求的时间戳。
//             * OK-ACCESS-PASSPHRASE您在创建API密钥时指定的Passphrase。
//             */
//            String timestamp = getISO8601Timestamp(new Date());
//            System.out.println(timestamp);
//            HashMap<String, String> headMap = Maps.newHashMap();
//            headMap.put("OK-ACCESS-KEY", apiKey);
//            headMap.put("OK-ACCESS-SIGN", getSign(url,HTTP_GET,null,timestamp));
//            headMap.put("OK-ACCESS-TIMESTAMP", timestamp);
//            headMap.put("OK-ACCESS-PASSPHRASE", passphrase);
//            //url = URLEncoder.encode(domain + url, "UTF-8");
//            String result = HttpUtil.get(domain+url, headMap);
//            return result;
//        }catch (Exception e){
//        }
//        return null;
//    }
//
//
//    /**
//     * OK-ACCESS-SIGN的请求头是对timestamp + method + requestPath + body字符串(+表示字符串连接)，以及secretKey，使用HMAC SHA256方法加密，通过BASE64编码输出而得到的。
//     * 例如： sign=CryptoJS.enc.Base64.Stringify(CryptoJS.HmacSHA256(timestamp + 'GET' + '/users/self/verify', secretKey))
//     * 其中，timestamp的值与OK-ACCESS-TIMESTAMP请求头相同，必须是UTC时区Unix时间戳的十进制秒数格式或ISO8601标准的时间格式，精确到毫秒。。
//     * method是请求方法，字母全部大写：GET/POST。
//     * requestPath是请求接口路径。例如：/orders?before=2&limit=30
//     * body是指请求主体的字符串，如果请求没有主体(通常为GET请求)则body可省略。例如：{"product_id":"BTC-USD-0309","order_id":"377454671037440"}
//     * secretKey为用户申请API Key时所生成。例如：prehash String：2018-03-08T10:59:25.789ZPOST/orders?before=2&limit=30{"product_id":"BTC-USD-0309","order_id":"377454671037440"}
//     * @param url
//     * @return
//     */
//    private static String getSign(String url,String method,String param,String timestamp){
//        String signStr = timestamp+method+url;
//        log.info(signStr);
//        String sign = Base64Util.encode(SHA256Util.HMACSHA256(signStr,secretKey));
//        log.info(sign);
//        return sign;
//    }
//
//    public static String getISO8601Timestamp(Date date){
//        TimeZone tz = TimeZone.getTimeZone("UTC");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        df.setTimeZone(tz);
//        String nowAsISO = df.format(date);
//        return nowAsISO;
//    }
//
//
//    public static void main(String[] args){
//        System.out.println(doGet("/api/spot/v3/instruments/BTC-USDT/ticker"));
//    }


}
