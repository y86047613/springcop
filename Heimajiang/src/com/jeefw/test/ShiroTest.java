package com.jeefw.test;

import com.jeefw.service.sys.AuthorityService;
import com.jeefw.service.sys.SysUserService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:applicationContext-shiro.xml"})
public class ShiroTest
{
  @Resource
  private AuthorityService roleAuthorityService;
  @Resource
  private SysUserService sysUserService;
  private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
  
  @org.junit.Test
  public void testAuthority()
  {
    this.fixedThreadPool.execute(new Test(Long.valueOf(1L).longValue(), this.sysUserService));
  }
}
