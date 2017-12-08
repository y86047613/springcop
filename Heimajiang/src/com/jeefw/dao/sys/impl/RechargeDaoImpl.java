package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.RechargeDao;
import com.jeefw.model.sys.Recharge;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class RechargeDaoImpl
  extends BaseDao<Recharge>
  implements RechargeDao
{
  public RechargeDaoImpl()
  {
    super(Recharge.class);
  }
}
