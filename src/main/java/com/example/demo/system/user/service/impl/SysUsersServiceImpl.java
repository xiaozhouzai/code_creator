package com.example.demo.system.user.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.interceptor.DemoException;
import com.example.demo.system.user.dao.SysUsersMapper;
import com.example.demo.system.user.domain.SysUsers;
import com.example.demo.system.user.domain.request.RegisterParam;
import com.example.demo.system.user.domain.request.UpdateParam;
import com.example.demo.system.user.domain.vo.LoginParam;
import com.example.demo.system.user.service.SysUsersService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Resource
    private SysUsersMapper userDao;

    @Override
    public String login(LoginParam loginParam) {
        SysUsers user = userDao.findByUsername(loginParam.getUsername());
        if (Objects.isNull(user)) {
            throw new DemoException("用户不存在");
        }
        if (!user.getPassword().equals(loginParam.getPassword())) {
            throw new DemoException("密码错误");
        }
        if (Objects.isNull(user.getId())) {
            throw new DemoException("信息异常");
        }
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo.getTokenValue();
    }

    @Override
    public SysUsers getUserInfoById(Long loginId) {
        return userDao.selectById(loginId);
    }

    @Override
    public void register(RegisterParam registerParam) {
        SysUsers user = new SysUsers();
        BeanUtils.copyProperties(registerParam, user);
        userDao.insert(user);
    }

    @Override
    public void update(UpdateParam updateParam) {
        SysUsers users = userDao.selectById(updateParam.getId());
        BeanUtils.copyProperties(updateParam, users);
        userDao.updateById(users);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}




