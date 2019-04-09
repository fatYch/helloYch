package com.yaoch.api.module.baidu.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yaoch.common.utils.Base64Util;
import com.yaoch.common.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @author 肥美的洪洪哥
 * @version 2019-02-22
 */

@Component
public class BaiduUtil {

    private static final Logger log = LoggerFactory.getLogger(BaiduUtil.class);
    private static String appId;
    private static String appKey;
    private static String secretKey;
    private static String accessToken;

    //获取授权标识url
    private static String authAccessToken;
    //识别身份证url
    private static String recognizeIdcard;
    //短网址token
    private static String shortUrlToken = "8356bf8ff6a763761a55e77b5b5734a7";
    //创建短网址
    private static String createShortUrl = "https://dwz.cn/admin/v2/create";
    //恢复短网址
    private static String recoverShortUrl;

    @Value("${baidu.appId}")
    public void setAppId(String appId) {
        BaiduUtil.appId = appId;
    }

    @Value("${baidu.appKey}")
    public void setAppKey(String appKey) {
        BaiduUtil.appKey = appKey;
    }

    @Value("${baidu.secretKey}")
    public void setSecretKey(String secretKey) {
        BaiduUtil.secretKey = secretKey;
    }

    @Value("${baidu.url.getAccessToken}")
    public void setAuthAccessToken(String authAccessToken) {
        BaiduUtil.authAccessToken = authAccessToken;
    }

    @Value("${baidu.url.recognizeIdcard}")
    public void setRecognizeIdcard(String recognizeIdcard) {
        BaiduUtil.recognizeIdcard = recognizeIdcard;
    }

    @Value("${baidu.url.shortUrlToken}")
    public void setShortUrlToken(String shortUrlToken) {
        BaiduUtil.shortUrlToken = shortUrlToken;
    }

    @Value("${baidu.url.createShortUrl}")
    public void setCreateShortUrl(String createShortUrl) {
        BaiduUtil.createShortUrl = createShortUrl;
    }

    @Value("${baidu.url.recoverShortUrl}")
    public void setRecoverShortUrl(String recoverShortUrl) {
        BaiduUtil.recoverShortUrl = recoverShortUrl;
    }


    /**
     * 身份证识别
     *
     * @param imgStr
     * @return
     */
    public static JSONObject recognizeIdcard(String imgStr) {
        //去掉base64前缀（data:image/jpeg;base64,）
        imgStr = Base64Util.cleanBase64Prefix(imgStr);
        if (StringUtils.isEmpty(accessToken)) {
            accessToken = getAccessToken();
        }
        JSONObject jsonResult = new JSONObject();
        try {
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(recognizeIdcard + "?access_token=" + accessToken,params);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (!StringUtils.isEmpty(jsonObject.getString("error_code"))) {
                jsonResult.put("error", jsonObject.getString("error_msg"));
                return jsonResult;
            }
            //判断图片是否身份证
            String imageStatus = jsonObject.getString("image_status");
            if (!"normal".equals(imageStatus)) {
                jsonResult.put("error", imageStatus);
                return jsonResult;
            }
            jsonResult.put("name", jsonObject.getJSONObject("words_result").getJSONObject("姓名").get("words"));
            jsonResult.put("nation", jsonObject.getJSONObject("words_result").getJSONObject("民族").get("words"));
            jsonResult.put("address", jsonObject.getJSONObject("words_result").getJSONObject("住址").get("words"));
            jsonResult.put("no", jsonObject.getJSONObject("words_result").getJSONObject("公民身份号码").get("words"));
            jsonResult.put("birthday", jsonObject.getJSONObject("words_result").getJSONObject("出生").get("words"));
            jsonResult.put("sex", jsonObject.getJSONObject("words_result").getJSONObject("性别").get("words"));
        } catch (Exception e) {
            log.error("识别身份证失败,", e);
        }
        return jsonResult;
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private static String getAccessToken() {
        log.info("----------开始获取accessToken------------");
        // 获取token地址
        String getAccessTokenUrl = authAccessToken
                // 1. grant_type为固定参数
                + "?grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + appKey
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + secretKey;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            log.debug(result);
            JSONObject jsonObject = JSON.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            accessToken = access_token;
            return access_token;
        } catch (Exception e) {
            log.error("获取accessToken失败，", e);
        }
        return null;
    }

    /**
     * 创建短网址
     *
     * @param longUrl 长网址：即原网址
     * @return 成功：短网址
     * 失败：返回空字符串
     */
    public static String createShortUrl(String longUrl) {
        String params = "{\"url\":\"" + longUrl + "\"}";

        BufferedReader reader = null;
        try {
            // 创建连接
            URL url = new URL(createShortUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.setRequestProperty("Token", shortUrlToken); // 设置发送数据的格式");
            // 发起请求
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            JSONObject resultJson = JSON.parseObject(res);
            if(resultJson.getInteger("Code") == 0){
                return resultJson.getString("ShortUrl");
            }else {
                log.error(longUrl+"获取短网址失败:"+resultJson.getString("ErrMsg"));
                return longUrl;
            }
        } catch (Exception e) {
            log.error(longUrl+"获取短网址失败:",e);
        }
        return longUrl;
    }

    public static void main(String[] args){
        createShortUrl("http://www.baidu.com");
    }
}



