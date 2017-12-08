package com.jeefw.dao.sys.impl;

import com.jeefw.core.BaseDao4;
import com.jeefw.dao.sys.UserInsureScoreDao;
import com.jeefw.model.sys.UserInsureScore;
import org.springframework.stereotype.Repository;

@Repository
public class UserInsureScoreDaoImpl
  extends BaseDao4<UserInsureScore>
  implements UserInsureScoreDao
{
  public UserInsureScoreDaoImpl()
  {
    super(UserInsureScore.class);
  }
}
