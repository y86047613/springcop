package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.RoleDao;
import com.jeefw.model.sys.Role;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl
  extends BaseDao<Role>
  implements RoleDao
{
  public RoleDaoImpl()
  {
    super(Role.class);
  }
  
  public void deleteSysUserAndRoleByRoleId(Long roleId)
  {
    Query query = getSession().createSQLQuery("delete from sysuser_role where role_id = :roleId");
    query.setParameter("roleId", roleId);
    query.executeUpdate();
  }
  
  public void deleteRolePermissionByMenuCode(String menuCode)
  {
    Query query = getSession().createSQLQuery("delete from role_permission where permissions like '%" + menuCode + "%'");
    query.executeUpdate();
  }
  
  public void saveRolePermission(Long roleId, String permissions)
  {
    Query query = getSession().createSQLQuery("insert into role_permission values (:roleId,:permissions)");
    query.setParameter("roleId", roleId);
    query.setParameter("permissions", permissions);
    query.executeUpdate();
  }
}
