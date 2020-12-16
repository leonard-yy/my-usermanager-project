package com.lgsoftware.supportbuild.myusermanagerproject.login.dao;

import com.lgsoftware.supportbuild.myusermanagerproject.login.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);

    SysUser selectByUserCode(String userCode);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}