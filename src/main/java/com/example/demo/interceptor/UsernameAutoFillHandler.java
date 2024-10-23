package com.example.demo.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.system.user.dao.SysUsersMapper;
import com.example.demo.system.user.domain.SysUsers;
import com.tangzc.mpe.annotation.handler.IOptionByAutoFillHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class UsernameAutoFillHandler implements IOptionByAutoFillHandler<String> {

    private final SysUsersMapper userDao;

    public UsernameAutoFillHandler(SysUsersMapper userDao) {
        this.userDao = userDao;
    }


    @Override
    public String getVal(Object object, Class<?> clazz, Field field) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        Cookie[] cookies = request.getCookies();
        String header = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                header = cookie.getValue();
            }
        }
        if (StringUtils.isBlank(header)) {
            return null;
        }
        // 从请求头中获取token
        Long loginId =(Long) StpUtil.getLoginIdByToken(header);
        if (Objects.isNull(loginId)) {
            return null;
        }
        SysUsers user = userDao.selectById(loginId);
        if (Objects.isNull(user)) {
            return null;
        }
        return user.getUsername();
    }
}
