package com.jeefw.dao.sys;

import com.jeefw.model.sys.Role;
import core.dao.Dao;

public abstract interface RoleDao
  extends Dao<Role>
{
  public abstract void deleteSysUserAndRoleByRoleId(Long paramLong);
  
  public abstract void deleteRolePermissionByMenuCode(String paramString);
  
  public abstract void saveRolePermission(Long paramLong, String paramString);
}
