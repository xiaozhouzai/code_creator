package com.example.demo.interceptor;

import cn.dev33.satoken.exception.SaTokenException;
import com.example.demo.common.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DemoException.class)
    public Result<String> demoExceptionHandler(DemoException e) {
        if (e.getHttpCode() != null) {
            return Result.fail(e.getHttpCode());
        } else if (e.getCode() != null && e.getMsg() != null) {
            return Result.fail(e.getCode(), e.getMsg());
        } else if (e.getMsg() != null && e.getCode() == null) {
            return Result.fail(e.getMsg());
        } else {
            return Result.fail(e.getMsg());
        }

    }
    @ExceptionHandler(BindException.class)
    public Result<String> methodArgumentNotValidExceptionHandler(BindException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String err = String.join(",", errors.values());
        return Result.fail(err);
    }

    @ExceptionHandler(SaTokenException.class)
    public Result<String> saTokenExceptionHandler(SaTokenException e) {
        return Result.fail(e.getMessage());
    }



}
