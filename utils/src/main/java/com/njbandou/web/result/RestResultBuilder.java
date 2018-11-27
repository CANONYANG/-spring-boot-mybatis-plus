package com.njbandou.web.result;


import java.util.HashMap;

/**
 * Created by D丶Cheng on 2018/7/4.
 *
 * @param <T>
 */
public class RestResultBuilder<T> {

    private int errCode = 0;

    private String errMsg = "返回成功";

    private T data;

    public RestResultBuilder<T> setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public RestResultBuilder<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public RestResultBuilder<T> setData(T data) {
        this.data = data;
        return this;
    }

    public RestResult<T> build() {
        return new RestResult<T>(errCode, errMsg, data != null ? data : (T) new HashMap());
    }

    public RestResult<T> error(String msg) {
        return new RestResult<T>(-1, msg, (T) new HashMap());
    }

    public RestResult<T> success() {
        return new RestResult<T>(0, "返回成功", (T) new HashMap());
    }

    public RestResult<T> success(String msg) {
        return new RestResult<T>(0, msg, (T) new HashMap());
    }

    public RestResult<T> success(String msg, T data) {
        return new RestResult<T>(0, msg,
                data != null ? data : (T) new HashMap());
    }
}
