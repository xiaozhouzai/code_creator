package com.example.demo.system.user.service;


import com.example.demo.system.user.domain.SysUsers;
import com.example.demo.system.user.domain.request.RegisterParam;
import com.example.demo.system.user.domain.request.UpdateParam;
import com.example.demo.system.user.domain.vo.LoginParam;

public interface SysUsersService {

    String login(LoginParam loginParam);

    SysUsers getUserInfoById(Long loginId);

    void register(RegisterParam registerParam);

    void update(UpdateParam updateParam);

    void delete(Long id);
}
