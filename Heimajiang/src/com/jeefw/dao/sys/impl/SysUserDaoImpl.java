package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.SysUserDao;
import com.jeefw.model.sys.SysUser;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserDaoImpl
  extends BaseDao<SysUser>
  implements SysUserDao
{
  public SysUserDaoImpl()
  {
    super(SysUser.class);
  }
  
  public String getRoleValueBySysUserId(Long sysUserId)
  {
    Query query = getSession().createSQLQuery("select role_value from sysuser_role,role where sysuser_role.role_id = role.id and sysuser_id = :sysUserId");
    query.setParameter("sysUserId", sysUserId);
    String roleValue = (String)query.uniqueResult() == null ? "" : (String)query.uniqueResult();
    return roleValue;
  }
}
