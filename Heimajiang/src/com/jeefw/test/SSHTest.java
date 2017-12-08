package com.jeefw.test;

import com.jeefw.dao.sys.SysUserDao;
import com.jeefw.dao.sys.UserScoreDao;
import com.jeefw.dao.sys.UserinfoDao;
import com.jeefw.model.sys.UserScore;
import com.jeefw.service.sys.SysUserService;
import com.jeefw.service.sys.UserScoreService;
import com.jeefw.service.sys.UserinfoService;
import java.io.PrintStream;
import java.math.BigInteger;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SSHTest
{
  @Resource
  private SysUserDao sysUserDao;
  @Resource
  private SysUserService sysUserService;
  @Resource
  private UserinfoDao userinfoDao;
  @Resource
  private UserinfoService userinfoService;
  @Resource
  private UserScoreDao userScoreDao;
  @Resource
  private UserScoreService userScoreService;
  
  @Before
  public void setUp()
    throws Exception
  {}
  
  @Test
  public final void testSave()
  {
    UserScore user = (UserScore)this.userScoreDao.getByProerties("userId", BigInteger.valueOf(1L));
    System.out.println(user.getLastLogonIP());
  }
}
