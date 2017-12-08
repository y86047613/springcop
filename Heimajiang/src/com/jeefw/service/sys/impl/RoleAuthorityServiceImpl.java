package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.RoleAuthorityDao;
import com.jeefw.model.sys.RoleAuthority;
import com.jeefw.service.sys.RoleAuthorityService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthorityServiceImpl
  extends BaseService<RoleAuthority>
  implements RoleAuthorityService
{
  private RoleAuthorityDao roleAuthorityDao;
  
  @Resource
  public void setRoleAuthorityDao(RoleAuthorityDao roleAuthorityDao)
  {
    this.roleAuthorityDao = roleAuthorityDao;
    this.dao = roleAuthorityDao;
  }
}
