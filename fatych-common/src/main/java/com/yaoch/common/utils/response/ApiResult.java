package com.yaoch.common.utils.response;

/**
 * 常用的返回结果
 * @author 肥美的洪洪哥
 * @version 2019-04-09
 */
public enum ApiResult {

    SUCCESS("0000","请求成功"),
    FAIL("1000","请求失败"), //请求失败，主要有系统抛异常或业务逻辑错误
    NO_DATA("2000","无数据"), //请求成功，但是查询不到数据
    NET_ERROR("3000","网络错误");

    private String code;
    private String msg;

    ApiResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
