package com.example.demo.system.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.system.user.domain.SysUsers;
import org.apache.ibatis.annotations.Param;


public interface SysUsersMapper extends BaseMapper<SysUsers> {

    SysUsers findByUsername(@Param("username") String username);
}




