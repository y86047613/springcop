package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.UserinfoDao;
import com.jeefw.model.sys.Userinfo;
import com.jeefw.service.sys.UserinfoService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl
  extends BaseService<Userinfo>
  implements UserinfoService
{
  private UserinfoDao userinfoDao;
  
  @Resource
  public void setSysUserDao(UserinfoDao userinfoDao)
  {
    this.userinfoDao = userinfoDao;
    this.dao = userinfoDao;
  }
}
