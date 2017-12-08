package com.jeefw.service.sys;

import com.jeefw.model.sys.Department;
import core.service.Service;
import java.util.List;

public abstract interface DepartmentService
  extends Service<Department>
{
  public abstract List<Department> queryDepartmentCnList(List<Department> paramList);
}
