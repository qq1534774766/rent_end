package com.aguo.handle;

import com.aguo.enums.ApiStatueCode;
import com.aguo.vo.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/13 23:43
 * @Description: TODO
 */
@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {
    /**
     * 进行异常处理，处理Exception类型的异常
      * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResponse doException(Exception e) {
        e.printStackTrace();
        return ApiResponse.error(ApiStatueCode.UNKNOWN_ERROR,"未知错误");
    }
}
