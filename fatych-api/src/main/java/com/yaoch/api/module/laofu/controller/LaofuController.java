package com.yaoch.api.module.laofu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yaoch.api.module.laofu.utils.CityUtil;
import com.yaoch.api.module.laofu.utils.CrtptocurrencyUtil;
import com.yaoch.api.module.laofu.utils.MetalUtil;
import com.yaoch.api.module.laofu.utils.WeatherUtil;
import com.yaoch.api.module.laofu.utils.stock.Stock;
import com.yaoch.api.module.laofu.utils.stock.StockUtil;
import com.yaoch.common.utils.HttpUtil;
import com.yaoch.common.utils.response.ApiResponse;
import com.yaoch.common.utils.response.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ych")
@Api(tags = "肥肠开放平台",description = "获取贵金属、数字货币行情、获取当前IP的城市、获取城市天气信息")
public class LaofuController {

    private static final Logger log = LoggerFactory.getLogger(LaofuController.class);
    @ApiOperation("获取贵金属行情")
    @GetMapping("/getMetalInfo")
    public ApiResponse getMetalInfo(@RequestParam(required = false) @ApiParam("贵金属种类，不传默认获取当前所知贵金属信息") MetalUtil.Metal metal){
        try {
            JSONArray array = new JSONArray();
            HashMap<String,String> metalMap = MetalUtil.getMetalInfo();
            for(Map.Entry<String,String> entry : metalMap.entrySet()){
                if(metal != null && !metal.getType().equals(entry.getKey())){
                    continue;
                }
                JSONObject object = new JSONObject();
                object.put("type",entry.getKey());
                object.put("price",entry.getValue());
                array.add(object);
            }
            return new ApiResponse(array);
        }catch (Exception e){
            log.error("获取贵金属行情失败：",e);
            return new ApiResponse(ApiResult.FAIL,"获取贵金属行情失败,请联系肥肠!");
        }
    }

    @ApiOperation("获取城市天气")
    @GetMapping("/getWeather/{city}")
    public ApiResponse getWeather(@ApiParam(value = "城市拼音简称",defaultValue = "guangzhou")@PathVariable("city")String city){
        try {
            HashMap<String,Object> weatherMap  = WeatherUtil.getSinaWeather(city);
            return new ApiResponse(weatherMap);
        } catch (Exception e) {
            log.error("获取当前城市天气信息失败：",e);
            return new ApiResponse(ApiResult.FAIL,"获取城市天气信息失败！");
        }
    }

    @ApiOperation("获取当前城市")
    @GetMapping("getCity")
    public ApiResponse getCity(HttpServletRequest request){
        String ip = HttpUtil.getIp(request);
        log.info("获取的ip地址:"+ip);
        JSONObject cityJson = CityUtil.getCityByIp(ip);
        if(cityJson == null){
            return new ApiResponse(ApiResult.FAIL,"获取当前IP的城市信息失败!");
        }
        return new ApiResponse(cityJson);
    }

    @ApiOperation("获取数字货币价格")
    @GetMapping("/getCryptocurrency")
    public ApiResponse getCryptocurrency(){
        JSONArray result = CrtptocurrencyUtil.getCrtptocurrency();
        if(result == null){
            return new ApiResponse(ApiResult.FAIL,"获取加密货币行情失败!");
        }
        return new ApiResponse(result);
    }

    @ApiOperation("获取市值前100的数字货币")
    @GetMapping("/getCoinList")
    public ApiResponse getCoinList(){
        JSONArray array = CrtptocurrencyUtil.getCrtptocurrencyList();
        if(array == null){
            return new ApiResponse(ApiResult.FAIL,"获取加密货币信息失败!");
        }
        return new ApiResponse(array);
    }

    @ApiOperation("获取股票行情")
    @GetMapping("/getStockInfo/{stockCode}")
    public ApiResponse<Stock> getStockInfo(@PathVariable("stockCode")@ApiParam(value = "股票编码",defaultValue = "601006") String stockCode){
        try {
            return new ApiResponse<>(StockUtil.getStockDetail(stockCode));
        } catch (Exception e) {
            log.error("获取股票行情失败:",e);
            return new ApiResponse<>(ApiResult.FAIL,"获取股票行情失败!");
        }
    }
}

