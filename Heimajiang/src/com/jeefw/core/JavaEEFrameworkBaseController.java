package com.jeefw.core;

import com.jeefw.model.sys.SysUser;
import core.controller.ExtJSBaseController;
import core.support.ExtJSBaseParameter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class JavaEEFrameworkBaseController<E extends ExtJSBaseParameter>
  extends ExtJSBaseController<E>
  implements Constant
{
  public SysUser getCurrentSysUser()
  {
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    return (SysUser)request.getSession().getAttribute("SESSION_SYS_USER");
  }
}
