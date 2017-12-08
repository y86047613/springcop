package com.jeefw.test;

import com.jeefw.model.sys.SysUser;
import com.jeefw.service.sys.SysUserService;
import java.io.PrintStream;
import javax.annotation.Resource;

public class Test
  implements Runnable
{
  @Resource
  private SysUserService sysUserService;
  private long id;
  
  public Test(long id, SysUserService sysUserService)
  {
    this.id = id;
    this.sysUserService = sysUserService;
  }
  
  public void run()
  {
    System.err.println("----------------------------------------");
    if (this.sysUserService != null) {
      System.out.println(this.sysUserService);
    }
    SysUser user = (SysUser)this.sysUserService.getByProerties("id", Long.valueOf(this.id));
    System.out.println(user.getUserName());
  }
  
  public SysUserService getSysUserService()
  {
    return this.sysUserService;
  }
  
  public void setSysUserService(SysUserService sysUserService)
  {
    this.sysUserService = sysUserService;
  }
}
