package com.jeefw.controller.sys;

import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.RoleAuthority;
import com.jeefw.service.sys.RoleAuthorityService;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sys/roleauthority"})
public class RoleAuthorityController
  extends JavaEEFrameworkBaseController<RoleAuthority>
{
  @Resource
  private RoleAuthorityService roleAuthorityService;
  
  @RequestMapping({"/saveRoleAuthority"})
  public void saveRoleAuthority(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String roleKey = request.getParameter("roleKey");
    String menuCode = request.getParameter("menuCode");
    this.roleAuthorityService.deleteByProperties("roleKey", roleKey);
    String[] menuCodesValue = menuCode.split(",");
    for (int i = 0; i < menuCodesValue.length; i++)
    {
      RoleAuthority roleAuthority = new RoleAuthority();
      roleAuthority.setRoleKey(roleKey);
      roleAuthority.setMenuCode(menuCodesValue[i]);
      this.roleAuthorityService.persist(roleAuthority);
    }
    writeJSON(response, "{success:true}");
  }
}
