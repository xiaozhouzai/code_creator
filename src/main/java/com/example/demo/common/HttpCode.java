package com.example.demo.common;

public enum HttpCode {

    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求错误"),
    REQUEST_BODY_NULL(400, "请求参数为空"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    NOT_ACCEPTABLE(406, "请求的格式不可得"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "请求冲突"),
    GONE(410, "资源已被删除"),
    LENGTH_REQUIRED(411, "长度必需"),
    PRECONDITION_FAILED(412, "前提条件失败"),
    REQUEST_ENTITY_TOO_LARGE(413, "请求实体过大"),
    REQUEST_URI_TOO_LONG(414, "请求的URI过长"),
    UNSUPPORTED_MEDIA_TYPE(415, "媒体类型不支持"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "请求范围不满足"),
    EXPECTATION_FAILED(417, "期望结果失败"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "服务器不支持该请求"),
    BAD_GATEWAY(502, "错误网关"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不受支持"),

    DATABASE_ERROR(301, "数据丢失或脏数据"),

    REQUESTED_IS_EMPTY(417, "请求为空"),
    REPEAT_SUBMISSION(418, "重复提交"),
    NOT_FOUND_USER(610, "用户不存在");
    private final int code;
    private final String msg;

    HttpCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public static HttpCode getHttpCode(int code) {
        for (HttpCode httpCode : HttpCode.values()) {
            if (httpCode.getCode() == code) {
                return httpCode;
            }
        }
        return null;
    }
    public static HttpCode getHttpCode(String msg) {
        for (HttpCode httpCode : HttpCode.values()) {
            if (httpCode.getMsg().equals(msg)) {
                return httpCode;
            }
        }
        return null;
    }
}
