package com.jeefw.dao.sys.impl;

import com.jeefw.core.BaseDao2;
import com.jeefw.dao.sys.UserinfoDao;
import com.jeefw.model.sys.Userinfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserinfoDaoImpl
  extends BaseDao2<Userinfo>
  implements UserinfoDao
{
  public UserinfoDaoImpl()
  {
    super(Userinfo.class);
  }
}
