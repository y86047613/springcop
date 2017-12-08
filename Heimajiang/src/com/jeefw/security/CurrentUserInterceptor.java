package com.jeefw.security;

import com.jeefw.model.sys.SysUser;
import com.jeefw.service.sys.SysUserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CurrentUserInterceptor
  extends HandlerInterceptorAdapter
{
  @Resource
  private SysUserService sysUserService;
  
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
    throws Exception
  {
    Long currentUserId = (Long)SecurityUtils.getSubject().getPrincipal();
    SysUser currentUser = (SysUser)this.sysUserService.get(currentUserId);
    if (currentUser != null) {
      httpServletRequest.setAttribute("currentUser", currentUser);
    }
  }
}
