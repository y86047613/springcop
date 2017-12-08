package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.UserInsureScoreDao;
import com.jeefw.model.sys.UserInsureScore;
import com.jeefw.service.sys.UserInsureScoreService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserInsureScoreServiceImpl
  extends BaseService<UserInsureScore>
  implements UserInsureScoreService
{
  private UserInsureScoreDao UserInsureScoreDao;
  
  @Resource
  public void setSysUserDao(UserInsureScoreDao UserInsureScoreDao)
  {
    this.UserInsureScoreDao = UserInsureScoreDao;
    this.dao = UserInsureScoreDao;
  }
}
