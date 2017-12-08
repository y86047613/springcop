package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.UserScoreDao;
import com.jeefw.model.sys.UserScore;
import com.jeefw.service.sys.UserScoreService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserScoreServiceImpl
  extends BaseService<UserScore>
  implements UserScoreService
{
  private UserScoreDao userScoreDao;
  
  @Resource
  public void setSysUserDao(UserScoreDao userScoreDao)
  {
    this.userScoreDao = userScoreDao;
    this.dao = userScoreDao;
  }
}
