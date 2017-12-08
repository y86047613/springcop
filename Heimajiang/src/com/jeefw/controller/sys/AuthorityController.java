package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Authority;
import com.jeefw.model.sys.RoleAuthority;
import com.jeefw.service.sys.AuthorityService;
import com.jeefw.service.sys.RoleAuthorityService;
import com.jeefw.service.sys.RoleService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@RequestMapping({"/sys/authority"})
public class AuthorityController
  extends JavaEEFrameworkBaseController<Authority>
  implements Constant
{
  @Resource
  private AuthorityService authorityService;
  @Resource
  private RoleAuthorityService roleAuthorityService;
  @Resource
  private RoleService roleService;
  
  @RequestMapping(value={"/getAuthority"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getAuthority(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Authority authority = new Authority();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      for (int i = 0; i < jsonArray.size(); i++)
      {
        JSONObject result = (JSONObject)jsonArray.get(i);
        if ((result.getString("field").equals("menuCode")) && (result.getString("op").equals("eq"))) {
          authority.set$eq_menuCode(result.getString("data"));
        }
        if ((result.getString("field").equals("menuName")) && (result.getString("op").equals("cn"))) {
          authority.set$like_menuName(result.getString("data"));
        }
      }
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        authority.setFlag("OR");
      } else {
        authority.setFlag("AND");
      }
    }
    authority.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    authority.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    authority.setSortedConditions(sortedCondition);
    QueryResult<Authority> queryResult = this.authorityService.doPaginationQuery(authority);
    JqGridPageView<Authority> authorityListView = new JqGridPageView();
    authorityListView.setMaxResults(maxResults.intValue());
    authorityListView.setRows(queryResult.getResultList());
    authorityListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, authorityListView);
  }
  
  @RequestMapping(value={"/saveAuthority"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave(Authority entity, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = entity;
    if ("edit".equals(parameter.getCmd())) {
      this.authorityService.merge(entity);
    } else if ("new".equals(parameter.getCmd())) {
      this.authorityService.persist(entity);
    }
    parameter.setSuccess(Boolean.valueOf(true));
    writeJSON(response, parameter);
  }
  
  @RequestMapping(value={"/operateAuthority"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void operateAuthority(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String oper = request.getParameter("oper");
    String id = request.getParameter("id");
    if (oper.equals("del"))
    {
      String[] ids = id.split(",");
      deleteAuthority(request, response, (Long[])ConvertUtils.convert(ids, Long.class));
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
      String menuCode = request.getParameter("menuCode");
      String menuName = request.getParameter("menuName");
      String menuClass = request.getParameter("menuClass");
      String dataUrl = request.getParameter("dataUrl");
      String sequence = request.getParameter("sequence");
      String parentMenuCode = request.getParameter("parentMenuCode");
      if (StringUtils.isBlank(parentMenuCode)) {
        parentMenuCode = "0";
      }
      Authority authority = null;
      if (oper.equals("edit")) {
        authority = (Authority)this.authorityService.get(Long.valueOf(id));
      }
      Authority menuCodeAuthority = (Authority)this.authorityService.getByProerties("menuCode", menuCode);
      Authority parentMenuCodeAuthority = (Authority)this.authorityService.getByProerties("menuCode", parentMenuCode);
      if ((StringUtils.isBlank(menuCode)) || (StringUtils.isBlank(menuName)) || (StringUtils.isBlank(menuClass)) || (StringUtils.isBlank(dataUrl)))
      {
        response.setStatus(411);
        result.put("message", "请填写菜单编码、菜单名称、菜单class属性值和菜单data-url属性值");
        writeJSON(response, result);
      }
      else if ((menuCodeAuthority != null) && (oper.equals("add")))
      {
        response.setStatus(409);
        result.put("message", "此菜单编码已存在，请重新输入");
        writeJSON(response, result);
      }
      else if ((menuCodeAuthority != null) && (!authority.getMenuCode().equalsIgnoreCase(menuCode)) && (oper.equals("edit")))
      {
        response.setStatus(409);
        result.put("message", "此菜单编码已存在，请重新输入");
        writeJSON(response, result);
      }
      else if ((!parentMenuCode.equals("0")) && (parentMenuCodeAuthority == null))
      {
        response.setStatus(409);
        result.put("message", "上级菜单编码输入有误，请重新输入");
        writeJSON(response, result);
      }
      else
      {
        Authority entity = new Authority();
        entity.setMenuCode(menuCode);
        entity.setMenuName(menuName);
        entity.setMenuClass(menuClass);
        entity.setDataUrl(dataUrl);
        entity.setSequence(sequence == "" ? null : Long.valueOf(sequence));
        entity.setParentMenuCode(parentMenuCode);
        if (oper.equals("edit"))
        {
          entity.setId(Long.valueOf(id));
          entity.setCmd("edit");
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
  
  @RequestMapping({"/deleteAuthority"})
  public void deleteAuthority(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids)
    throws IOException
  {
    boolean flag = this.authorityService.deleteByPK(ids);
    if (flag) {
      writeJSON(response, "{success:true}");
    } else {
      writeJSON(response, "{success:false}");
    }
  }
  
  @RequestMapping({"/getAuthorityTreeList"})
  public void getAuthorityTreeList(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String id = request.getParameter("id");
    String roleKey = request.getParameter("roleKey");
    
    List<RoleAuthority> roleAuthorityList = this.roleAuthorityService.queryByProerties("roleKey", roleKey);
    List<String> menuCodeList = new ArrayList();
    for (int j = 0; j < roleAuthorityList.size(); j++) {
      menuCodeList.add(((RoleAuthority)roleAuthorityList.get(j)).getMenuCode());
    }
    List<Authority> allAuthority = this.authorityService.doQueryAll();
    Set<String> parentMenuCodeSet = new HashSet();
    for (int i = 0; i < allAuthority.size(); i++) {
      parentMenuCodeSet.add(((Authority)allAuthority.get(i)).getParentMenuCode());
    }
    List<Authority> subMenuList = this.authorityService.queryByProerties("parentMenuCode", id);
    
    JSONObject allJSONObject = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < subMenuList.size(); i++)
    {
      JSONObject subJsonObject = new JSONObject();
      subJsonObject.element("id", ((Authority)subMenuList.get(i)).getMenuCode());
      subJsonObject.element("name", ((Authority)subMenuList.get(i)).getMenuName());
      if (parentMenuCodeSet.contains(id))
      {
        subJsonObject.element("type", "folder");
        subJsonObject.element("children", true);
      }
      else
      {
        subJsonObject.element("type", "item");
      }
      if (menuCodeList.contains(((Authority)subMenuList.get(i)).getMenuCode())) {
        subJsonObject.element("item-selected", true);
      } else {
        subJsonObject.element("item-selected", false);
      }
      subJsonObject.element("additionalParameters", subJsonObject);
      jsonArray.add(subJsonObject);
    }
    allJSONObject.element("status", "OK");
    allJSONObject.element("data", jsonArray);
    writeJSON(response, allJSONObject);
  }
  
  @RequestMapping({"/getAuthorityButtonList"})
  public void getAuthorityButtonList(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String menuCode = request.getParameter("menuCode");
    JSONObject allJSONObject = new JSONObject();
    String result = this.authorityService.getAuthorityButtonList(menuCode);
    allJSONObject.element("data", result);
    writeJSON(response, allJSONObject);
  }
  
  @RequestMapping({"/saveAuthorityButtonList"})
  public void saveAuthorityButtonList(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String checkboxList = request.getParameter("checkboxList");
    String menuCode = request.getParameter("menuCode");
    this.authorityService.saveAuthorityButtonList(checkboxList, menuCode);
    writeJSON(response, "{success:true}");
  }
}
