package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Role;
import com.jeefw.service.sys.RoleService;
import com.jeefw.service.sys.SysUserService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/sys/role"})
public class RoleController
  extends JavaEEFrameworkBaseController<Role>
  implements Constant
{
  @Resource
  private RoleService roleService;
  @Resource
  private SysUserService sysUserService;
  
  @RequestMapping(value={"/getRole"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getRole(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Role role = new Role();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      for (int i = 0; i < jsonArray.size(); i++)
      {
        JSONObject result = (JSONObject)jsonArray.get(i);
        if ((result.getString("field").equals("roleKey")) && (result.getString("op").equals("eq"))) {
          role.set$eq_roleKey(result.getString("data"));
        }
        if ((result.getString("field").equals("roleValue")) && (result.getString("op").equals("cn"))) {
          role.set$like_roleValue(result.getString("data"));
        }
      }
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        role.setFlag("OR");
      } else {
        role.setFlag("AND");
      }
    }
    role.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    role.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    role.setSortedConditions(sortedCondition);
    QueryResult<Role> queryResult = this.roleService.doPaginationQuery(role);
    JqGridPageView<Role> roleListView = new JqGridPageView();
    roleListView.setMaxResults(maxResults.intValue());
    roleListView.setRows(queryResult.getResultList());
    roleListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, roleListView);
  }
  
  @RequestMapping(value={"/saveRole"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave(Role entity, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = entity;
    if ("edit".equals(parameter.getCmd())) {
      this.roleService.merge(entity);
    } else if ("new".equals(parameter.getCmd())) {
      this.roleService.persist(entity);
    }
    parameter.setSuccess(Boolean.valueOf(true));
    writeJSON(response, parameter);
  }
  
  @RequestMapping(value={"/operateRole"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void operateRole(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String oper = request.getParameter("oper");
    String id = request.getParameter("id");
    if (oper.equals("del"))
    {
      String[] ids = id.split(",");
      deleteRole(request, response, (Long[])ConvertUtils.convert(ids, Long.class));
    }
    else if (oper.equals("excel"))
    {
      response.setContentType("application/msexcel;charset=UTF-8");
      try
      {
        response.addHeader("Content-Disposition", "attachment;filename=file.xls");
        OutputStream out = response.getOutputStream();
        out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
        out.flush();
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      Map<String, Object> result = new HashMap();
      String roleKey = request.getParameter("roleKey");
      String roleValue = request.getParameter("roleValue");
      String description = request.getParameter("description");
      Role role = null;
      if (oper.equals("edit")) {
        role = (Role)this.roleService.get(Long.valueOf(id));
      }
      Role roleKeyRole = (Role)this.roleService.getByProerties("roleKey", roleKey);
      if ((StringUtils.isBlank(roleKey)) || (StringUtils.isBlank(roleValue)))
      {
        response.setStatus(411);
        result.put("message", "请填写角色编码和角色名称");
        writeJSON(response, result);
      }
      else if ((roleKeyRole != null) && (oper.equals("add")))
      {
        response.setStatus(409);
        result.put("message", "此角色编码已存，请重新输入");
        writeJSON(response, result);
      }
      else if ((roleKeyRole != null) && (!role.getRoleKey().equalsIgnoreCase(roleKey)) && (oper.equals("edit")))
      {
        response.setStatus(409);
        result.put("message", "此角色编码已存在，请重新输入");
        writeJSON(response, result);
      }
      else
      {
        Role entity = new Role();
        entity.setRoleKey(roleKey);
        entity.setRoleValue(roleValue);
        entity.setDescription(description);
        if (oper.equals("edit"))
        {
          entity.setId(Long.valueOf(id));
          entity.setCmd("edit");
          entity.setPermissions(role.getPermissions());
          doSave(entity, request, response);
        }
        else if (oper.equals("add"))
        {
          entity.setCmd("new");
          
          doSave(entity, request, response);
        }
      }
    }
  }
  
  @RequestMapping({"/deleteRole"})
  public void deleteRole(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids)
    throws IOException
  {
    boolean flag = false;
    for (int i = 0; i < ids.length; i++)
    {
      Long id = ids[i];
      this.roleService.deleteSysUserAndRoleByRoleId(id);
      flag = this.roleService.deleteByPK(new Serializable[] { id });
    }
    if (flag) {
      writeJSON(response, "{success:true}");
    } else {
      writeJSON(response, "{success:false}");
    }
  }
  
  @RequestMapping(value={"/getRoleSelectList"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getRoleSelectList(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    List<Role> roleList = this.roleService.doQueryAll();
    StringBuilder builder = new StringBuilder();
    builder.append("<select>");
    for (int i = 0; i < roleList.size(); i++) {
      builder.append("<option value='" + ((Role)roleList.get(i)).getRoleKey() + "'>" + ((Role)roleList.get(i)).getRoleValue() + "</option>");
    }
    builder.append("</select>");
    writeJSON(response, builder.toString());
  }
}
