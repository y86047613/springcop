package com.jeefw.app.logic;

import com.jeefw.app.bean.UpdateUserPwdRequestBean;
import com.jeefw.app.bean.UpdateUserPwdResponseBean;
import com.jeefw.service.sys.SysUserService;
import org.springframework.web.context.WebApplicationContext;

public class UpdateUserPwdLogicServer
  implements ILogicFace<UpdateUserPwdRequestBean, UpdateUserPwdResponseBean>
{
  public UpdateUserPwdResponseBean logic(WebApplicationContext wac, UpdateUserPwdRequestBean brb)
  {
    SysUserService userService = (SysUserService)wac.getBean("sysUserServiceImpl");
    UpdateUserPwdResponseBean updateUserResponseBean = new UpdateUserPwdResponseBean();
    updateUserResponseBean.setResult(userService.updateSysUser(brb));
    return updateUserResponseBean;
  }
}
