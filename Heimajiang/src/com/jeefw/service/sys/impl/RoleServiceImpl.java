package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.RoleDao;
import com.jeefw.model.sys.Role;
import com.jeefw.service.sys.RoleService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl
  extends BaseService<Role>
  implements RoleService
{
  private RoleDao roleDao;
  
  @Resource
  public void setRoleDao(RoleDao roleDao)
  {
    this.roleDao = roleDao;
    this.dao = roleDao;
  }
  
  public void deleteSysUserAndRoleByRoleId(Long roleId)
  {
    this.roleDao.deleteSysUserAndRoleByRoleId(roleId);
  }
}
