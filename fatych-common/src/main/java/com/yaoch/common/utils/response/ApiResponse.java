package com.yaoch.common.utils.response;

import lombok.Data;

/**
 * 接口返回JSON格式
 * @param <T>
 */
@Data
public class ApiResponse<T> {

    private String code;
    private String msg;
    private T data;

    //默认构造为成功请求
    public ApiResponse(T data){
        this.code = ApiResult.SUCCESS.getCode();
        this.msg = ApiResult.SUCCESS.getMsg();
        this.data = data;
    }

    public ApiResponse(String code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public  ApiResponse(ApiResult apiResult,String msg){
        this.msg = msg;
        this.code = apiResult.getCode();
    }


}
