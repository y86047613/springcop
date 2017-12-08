package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.MailDao;
import com.jeefw.model.sys.Mail;
import com.jeefw.service.sys.MailService;
import core.service.BaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl
  extends BaseService<Mail>
  implements MailService
{
  private MailDao mailDao;
  
  @Resource
  public void setMailDao(MailDao mailDao)
  {
    this.mailDao = mailDao;
    this.dao = mailDao;
  }
}
