package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.RoleAuthorityDao;
import com.jeefw.model.sys.RoleAuthority;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleAuthorityDaoImpl
  extends BaseDao<RoleAuthority>
  implements RoleAuthorityDao
{
  public RoleAuthorityDaoImpl()
  {
    super(RoleAuthority.class);
  }
}
