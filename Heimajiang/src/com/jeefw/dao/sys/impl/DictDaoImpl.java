package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class DictDaoImpl
  extends BaseDao<Dict>
  implements DictDao
{
  public DictDaoImpl()
  {
    super(Dict.class);
  }
}
