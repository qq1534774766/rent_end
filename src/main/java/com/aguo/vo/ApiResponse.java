package com.aguo.vo;

import com.aguo.enums.ApiStatueCode;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ApiResponse {
    private Integer code;
    private String message;
    private Object data;
    private boolean success;

    public ApiResponse(Integer code, String message, Object data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static ApiResponse success(){
        return new ApiResponse(ApiStatueCode.SUCCESS.getCode(), ApiStatueCode.SUCCESS.getMsg(), null, true);
    }
    public static ApiResponse success(Object data) {
        return success(ApiStatueCode.SUCCESS,ApiStatueCode.SUCCESS.getMsg(),data);
    }
    public static ApiResponse success(ApiStatueCode code, String message, Object data) {
        return new ApiResponse(code.getCode(),message,data,true);
    }
    public static ApiResponse error(ApiStatueCode code, String message) {
        return new ApiResponse(code.getCode(),message,null,false);
    }
    public static ApiResponse error(String message) {
        return new ApiResponse(ApiStatueCode.OTHER_ERROR.getCode(),message,null,false);
    }
    public static ApiResponse result(ApiStatueCode code, String message, Object data, boolean success) {
        return new ApiResponse(code.getCode(),message,data,success);
    }
}