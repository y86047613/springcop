package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.RechargeDao;
import com.jeefw.model.sys.Recharge;
import com.jeefw.service.sys.RechargeService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RechargeServiceImpl
  extends BaseService<Recharge>
  implements RechargeService
{
  private RechargeDao rechargeDao;
  
  @Resource
  public void setRechargeDao(RechargeDao rechargeDao)
  {
    this.rechargeDao = rechargeDao;
    this.dao = rechargeDao;
  }
}
