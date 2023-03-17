package com.aguo.enums;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/13 16:58
 * @Description: TODO
 */
public enum ApiStatueCode {
    /**
     * 响应成功状态码
     */
    SUCCESS(200,"成功响应"),
    NOT_FOUND(404,"未找到资源"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权访问"),


    ADD_RENTER_FAIL(601,"身份证格式错误或用户名已存在"),
    OTHER_ERROR(998,"其他错误"),
    UNKNOWN_ERROR(999,"未知错误");
    private final Integer code;
    private final String msg;

    ApiStatueCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
