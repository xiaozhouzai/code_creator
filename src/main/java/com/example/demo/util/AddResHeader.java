package com.example.demo.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AddResHeader {

    public static void addResHeader(HttpServletRequest request) {
        Field connectorRequestField = ReflectionUtils.findField(request.getClass(), "request", org.apache.catalina.connector.Request.class);
        connectorRequestField.setAccessible(true); // 允许反射访问
        org.apache.catalina.connector.Request connectorRequest = (org.apache.catalina.connector.Request) ReflectionUtils.getField(connectorRequestField, request);

        // 使用反射获取 org.apache.catalina.connector.Request 中的 org.apache.coyote.Request 对象
        Field coyoteRequestField = ReflectionUtils.findField(org.apache.catalina.connector.Request.class, "coyoteRequest", org.apache.coyote.Request.class);
        coyoteRequestField.setAccessible(true); // 允许反射访问
        org.apache.coyote.Request coyoteRequest = (org.apache.coyote.Request) ReflectionUtils.getField(coyoteRequestField, connectorRequest);

        // 从 coyoteRequest 对象获取到 MimeHeaders
        MimeHeaders headers = coyoteRequest.getMimeHeaders();

        // 添加新的 Header
        headers.addValue("name").setString("我是你爸爸");
    }
}
