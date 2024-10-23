package com.example.demo.util;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.system.user.dao.SysUsersMapper;
import com.example.demo.system.user.domain.SysUsers;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetLoginIdByToken {

    @Resource
    private SysUsersMapper userDao;


    public String getUserName(String tokenName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String header = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(tokenName)) {
                header = cookie.getValue();
            }
        }
        if (StringUtils.isNotBlank(header)) {
            // 从请求头中获取token
            Object loginId = StpUtil.getLoginIdByToken(header);
            Long userId = Long.valueOf(loginId.toString());
            SysUsers users = userDao.selectById(userId);
            if (Objects.isNull(users)) {
                return null;
            } else {
                return users.getUsername();
            }
        } else {
            header = request.getHeader(tokenName);
            if (StringUtils.isNotBlank(header)) {
                Object loginId = StpUtil.getLoginIdByToken(header);
                Long userId = Long.valueOf(loginId.toString());
                SysUsers users = userDao.selectById(userId);
                if (Objects.isNull(users)) {
                    return null;
                } else {
                    return users.getUsername();
                }
            } else {
                return null;
            }
        }
    }
}
