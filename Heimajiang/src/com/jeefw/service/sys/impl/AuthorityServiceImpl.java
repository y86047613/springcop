package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.AuthorityDao;
import com.jeefw.dao.sys.RoleAuthorityDao;
import com.jeefw.dao.sys.RoleDao;
import com.jeefw.model.sys.Authority;
import com.jeefw.model.sys.Role;
import com.jeefw.model.sys.RoleAuthority;
import com.jeefw.service.sys.AuthorityService;
import core.service.BaseService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl
  extends BaseService<Authority>
  implements AuthorityService
{
  private AuthorityDao authorityDao;
  @Resource
  private RoleAuthorityDao roleAuthorityDao;
  @Resource
  private RoleDao roleDao;
  
  @Resource
  public void setAuthorityDao(AuthorityDao authorityDao)
  {
    this.authorityDao = authorityDao;
    this.dao = authorityDao;
  }
  
  public List<Authority> queryAllMenuList(String globalRoleKey)
  {
    List<RoleAuthority> roleAuthorityList = this.roleAuthorityDao.queryByProerties("roleKey", globalRoleKey);
    List<String> menuCodeList = new ArrayList();
    for (int j = 0; j < roleAuthorityList.size(); j++) {
      menuCodeList.add(((RoleAuthority)roleAuthorityList.get(j)).getMenuCode());
    }
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put("sequence", "ASC");
    List<Authority> mainMenuList = this.authorityDao.queryByProerties("parentMenuCode", "0", sortedCondition);
    
    List<Authority> allAuthority = this.authorityDao.doQueryAll();
    Set<String> parentMenuCodeSet = new HashSet();
    for (int i = 0; i < allAuthority.size(); i++) {
      parentMenuCodeSet.add(((Authority)allAuthority.get(i)).getParentMenuCode());
    }
    List<Authority> authorityList = new ArrayList();
    for (Authority entity : mainMenuList)
    {
      Authority authority = new Authority();
      authority.setId(entity.getId());
      authority.setMenuCode(entity.getMenuCode());
      authority.setMenuName(entity.getMenuName());
      authority.setMenuClass(entity.getMenuClass());
      authority.setDataUrl(entity.getDataUrl());
      authority.setSequence(entity.getSequence());
      authority.setParentMenuCode(entity.getParentMenuCode());
      List<Authority> subAuthorityList = this.authorityDao.queryByProerties("parentMenuCode", entity.getMenuCode());
      List<Authority> resultSubAuthorityList = new ArrayList();
      for (int i = 0; i < subAuthorityList.size(); i++) {
        if (menuCodeList.contains(((Authority)subAuthorityList.get(i)).getMenuCode()))
        {
          resultSubAuthorityList.add((Authority)subAuthorityList.get(i));
          if (parentMenuCodeSet.contains(((Authority)subAuthorityList.get(i)).getMenuCode()))
          {
            List<Authority> subSubAuthorityList = this.authorityDao.queryByProerties("parentMenuCode", ((Authority)subAuthorityList.get(i)).getMenuCode());
            ((Authority)subAuthorityList.get(i)).setSubAuthorityList(subSubAuthorityList);
          }
        }
      }
      authority.setSubAuthorityList(resultSubAuthorityList);
      if (subAuthorityList.size() == 0) {
        authorityList.add(null);
      } else {
        authorityList.add(authority);
      }
    }
    return authorityList;
  }
  
  public String getAuthorityButtonList(String menuCode)
  {
    List<Role> roleList = this.roleDao.doQueryAll();
    StringBuilder sb = new StringBuilder();
    Map<String, String> allRoles = new HashMap();
    int n;
    for (Iterator localIterator1 = roleList.iterator(); localIterator1.hasNext(); n < allRoles.size())
    {
      Role role = (Role)localIterator1.next();
      allRoles.put(role.getRoleKey(), role.getRoleValue());
      String[] sections;
      int i;
      for (Iterator iterator = role.getPermissions().iterator(); iterator.hasNext(); i < sections.length)
      {
        String str = (String)iterator.next();
        sections = str.split(":");
        i = 0; continue;
        if (sections[i].equals(menuCode))
        {
          String roleKey = sections[0];
          String[] buttons = sections[2].split(",");
          sb.append((String)allRoles.get(roleKey) + ":");
          ArrayList<String> allButtons = new ArrayList(Arrays.asList(new String[] { "edit", "add", "delete", "search", "view", "export" }));
          for (int j = 0; j < buttons.length; j++) {
            if (buttons[j].equals("edit"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
            else if (buttons[j].equals("add"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
            else if (buttons[j].equals("view"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
            else if (buttons[j].equals("delete"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
            else if (buttons[j].equals("search"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
            else if (buttons[j].equals("export"))
            {
              sb.append("<label><input id='" + roleKey + "-" + buttons[j] + "' name='form-field-checkbox' type='checkbox' class='ace' checked /><span class='lbl'>����</span></label>");
              allButtons.remove(buttons[j].toString());
            }
          }
          for (int z = 0; z < allButtons.size(); z++) {
            if (((String)allButtons.get(z)).equals("edit")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            } else if (((String)allButtons.get(z)).equals("add")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            } else if (((String)allButtons.get(z)).equals("view")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            } else if (((String)allButtons.get(z)).equals("delete")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            } else if (((String)allButtons.get(z)).equals("search")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            } else if (((String)allButtons.get(z)).equals("export")) {
              sb.append("<label><input id='" + roleKey + "-" + (String)allButtons.get(z) + "' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>����</span></label>");
            }
          }
          sb.append("<input name='form-field-checkbox' type='hidden' />");
          sb.append("<br/>");
          allRoles.remove(roleKey);
        }
        i++;
      }
      n = 0; continue;
      String restRoleKey = (String)((Map.Entry)allRoles.entrySet().iterator().next()).getKey();
      sb.append((String)((Map.Entry)allRoles.entrySet().iterator().next()).getValue() + ":");
      sb.append("<label><input id='" + restRoleKey + "-" + "add' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>添加</span></label>");
      sb.append("<label><input id='" + restRoleKey + "-" + "edit' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>编辑</span></label>");
      sb.append("<label><input id='" + restRoleKey + "-" + "delete' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>删除</span></label>");
      sb.append("<label><input id='" + restRoleKey + "-" + "view' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>查看</span></label>");
      sb.append("<label><input id='" + restRoleKey + "-" + "search' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>查找</span></label>");
      sb.append("<label><input id='" + restRoleKey + "-" + "export' name='form-field-checkbox' type='checkbox' class='ace' /><span class='lbl'>导出</span></label>");
      sb.append("<input name='form-field-checkbox' type='hidden' />");
      sb.append("<br/>");
      allRoles.remove(((Map.Entry)allRoles.entrySet().iterator().next()).getKey());n++;
    }
    return sb.toString();
  }
  
  public void saveAuthorityButtonList(String checkboxList, String menuCode)
  {
    Map<String, String> destinationMap = new HashMap();
    String[] allRoleAuthority = checkboxList.split("AND");
    for (int i = 0; i < allRoleAuthority.length; i++)
    {
      StringBuilder stringBuilder = new StringBuilder();
      String[] roleButtonStatusAuthority = allRoleAuthority[i].split(";");
      String role = allRoleAuthority[i].subSequence(0, allRoleAuthority[i].indexOf("-")).toString();
      stringBuilder.append(role + ":");
      stringBuilder.append(menuCode + ":");
      for (int j = 0; j < roleButtonStatusAuthority.length; j++)
      {
        String[] buttonStatusAuthority = roleButtonStatusAuthority[j].split(",");
        String[] roleButtonAuthority = buttonStatusAuthority[0].split("-");
        if ((roleButtonAuthority[1].equals("edit")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("edit,");
        } else if ((roleButtonAuthority[1].equals("add")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("add,");
        } else if ((roleButtonAuthority[1].equals("view")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("view,");
        } else if ((roleButtonAuthority[1].equals("delete")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("delete,");
        } else if ((roleButtonAuthority[1].equals("search")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("search,");
        } else if ((roleButtonAuthority[1].equals("export")) && (buttonStatusAuthority[1].equals("true"))) {
          stringBuilder.append("export,");
        } else {
          stringBuilder.append("none,");
        }
      }
      destinationMap.put(role, stringBuilder.toString());
    }
    this.roleDao.deleteRolePermissionByMenuCode(menuCode);
    for (Iterator mapIterator = destinationMap.entrySet().iterator(); mapIterator.hasNext();)
    {
      Map.Entry<String, String> map = (Map.Entry)mapIterator.next();
      Role r = (Role)this.roleDao.getByProerties("roleKey", map.getKey());
      this.roleDao.saveRolePermission(r.getId(), (String)map.getValue());
    }
  }
}
