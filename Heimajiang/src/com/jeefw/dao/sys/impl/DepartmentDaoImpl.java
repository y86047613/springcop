package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.DepartmentDao;
import com.jeefw.model.sys.Department;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl
  extends BaseDao<Department>
  implements DepartmentDao
{
  public DepartmentDaoImpl()
  {
    super(Department.class);
  }
}
