package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.MailDao;
import com.jeefw.model.sys.Mail;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class MailDaoImpl
  extends BaseDao<Mail>
  implements MailDao
{
  public MailDaoImpl()
  {
    super(Mail.class);
  }
}
