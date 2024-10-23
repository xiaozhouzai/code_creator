package com.example.demo.interceptor;


import com.example.demo.common.HttpCode;

public class DemoException extends RuntimeException {

    private String msg;
    private Integer code;
    private HttpCode httpCode;

    public DemoException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DemoException(HttpCode httpCode) {
        super(httpCode.getMsg());
        this.msg = httpCode.getMsg();
        this.code = httpCode.getCode();
    }

    public DemoException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public DemoException() {
    }

    public DemoException(String msg, Integer code, HttpCode httpCode) {
        this.msg = msg;
        this.code = code;
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

}
