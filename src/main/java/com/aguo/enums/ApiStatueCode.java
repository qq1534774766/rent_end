package com.aguo.enums;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/13 16:58
 * @Description: TODO
 */
public enum ApiStatueCode {
    /**
     * 响应成功状态码
     */
    SUCCESS(200, "成功响应"),
    NOT_FOUND(404, "未找到资源"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权访问"),


    ADD_RENTER_FAIL(601, "身份证格式错误或用户名已存在"),
    USER_DISABLE(602, "用户/租客 账号被禁用，请联系房东激活"),
    //    (,""),
    ROOM_ID_ERROR(603, "房屋Id不正确"),
    CREATE_TIME_AND_STOP_TIME_BOTH_ERROR(604, "起租时间应早于停租时间"),
    USER_NOT_EXIST(605, "用户/租客 账户不存在"),
    RENTING_ID_NOT_EXIST(606, "租用流水号不存在"),
    INSERT_RENTING_INFO_ERROR(607, "无法更新/插入租用记录"),
    UNABLE_UPDATE_RENTING(608, "无法更新租用记录"),
    //    (,""),
//    (,""),
//    (,""),
    OTHER_ERROR(998, "其他错误"),
    UNKNOWN_ERROR(999, "未知错误");
    private final Integer code;
    private final String msg;

    ApiStatueCode(int code, String msg) {
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
