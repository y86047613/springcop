package com.jeefw.service.sys.impl;import com.jeefw.dao.sys.DepartmentDao;import com.jeefw.model.sys.Department;import com.jeefw.service.sys.DepartmentService;import core.service.BaseService;import java.util.ArrayList;import java.util.List;import javax.annotation.Resource;import org.apache.commons.lang.StringUtils;import org.springframework.stereotype.Service;@Servicepublic class DepartmentServiceImpl  extends BaseService<Department>  implements DepartmentService{  private DepartmentDao departmentDao;    @Resource  public void setDepartmentDao(DepartmentDao departmentDao)  {    this.departmentDao = departmentDao;    this.dao = departmentDao;  }    public List<Department> queryDepartmentCnList(List<Department> resultList)  {    List<Department> departmentList = new ArrayList();    for (Department entity : resultList)    {      Department department = new Department();      department.setId(entity.getId());      department.setDepartmentKey(entity.getDepartmentKey());      department.setDepartmentValue(entity.getDepartmentValue());      department.setParentDepartmentkey(entity.getParentDepartmentkey());      if (StringUtils.isNotBlank(department.getParentDepartmentkey()))      {        Department d = (Department)this.departmentDao.getByProerties("departmentKey", department.getParentDepartmentkey());        if (d != null) {          department.setParentDepartmentValue(d.getDepartmentValue());        }      }      department.setDescription(entity.getDescription());      departmentList.add(department);    }    return departmentList;  }}