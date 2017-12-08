package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.AuthorityDao;
import com.jeefw.model.sys.Authority;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl
  extends BaseDao<Authority>
  implements AuthorityDao
{
  public AuthorityDaoImpl()
  {
    super(Authority.class);
  }
}
