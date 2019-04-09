package com.yaoch.api.config;

import com.yaoch.common.utils.response.ApiResponse;
import com.yaoch.common.utils.response.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse defaultHandle(){
        return new ApiResponse(ApiResult.FAIL,"服务器被肥肠抱走了");
    }
}
