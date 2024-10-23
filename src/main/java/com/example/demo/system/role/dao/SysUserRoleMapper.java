package com.example.demo.system.role.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.system.role.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15857
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
* @createDate 2024-07-10 16:57:26
* @Entity generator.domain.SysUserRole
*/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}




