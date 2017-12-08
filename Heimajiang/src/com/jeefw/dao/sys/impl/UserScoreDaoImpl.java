package com.jeefw.dao.sys.impl;

import com.jeefw.core.BaseDao3;
import com.jeefw.dao.sys.UserScoreDao;
import com.jeefw.model.sys.UserScore;
import org.springframework.stereotype.Repository;

@Repository
public class UserScoreDaoImpl
  extends BaseDao3<UserScore>
  implements UserScoreDao
{
  public UserScoreDaoImpl()
  {
    super(UserScore.class);
  }
}
