package com.njbandou.web.enums;

/**
 * Author: CANONYANG
 * Date: 2018/11/23
 * Describe: 错误码枚举
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public enum ResultCode {

    SYSTEM_BUSY(-1, "system too busy"),
    SUCCESS(0, "success"),


    TOKEN_EXPIRE(42001, "token expire"),


    INVALID_PARAM(40002, "invalid param"),


    ACCESS_DENIED(50001, "access denied"),
    INVALID_SMS_CODE(50003, "invalid sms code"),
    INVALID_OPERATING(50004, "invalid operating"),


    UNKNOWN_ERROR(100000, "unknown error"),
    METHOD_NOT_ALLOW(100002, "method not allow"),
    MESSAGE_NOT_READABLE(100003, "message not readable"),
    UNSUPPORTED_MEDIA_TYPE(100004, "unsupported media type"),
    PAGE_NOT_FOUND(100005, "page not found"),


    USER_NOT_FOUND(200001, "user not found"),
    USER_PASSWORD_NOT_MATCH(200002, "account and password mismatch "),
    ACCOUNT_EXIST(200003, "account already exist");


    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
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
