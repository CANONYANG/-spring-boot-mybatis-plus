package com.njbandou.web.result;

/**
 *  Created by D丶Cheng on 2018/7/4.
 * @param <T>
 */
public class RestResult<T> {

    private int errCode;
    private String errMsg;
    private T data;

    private RestResult(){}

    protected RestResult(int errCode, String errMsg, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public RestResult setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RestResult setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }


    public T getData() {
        return data;
    }

    public RestResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
