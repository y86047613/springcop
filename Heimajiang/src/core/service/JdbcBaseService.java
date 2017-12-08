package core.service;

import core.dao.JdbcBaseDao;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JdbcBaseService
{
  @Resource
  protected JdbcBaseDao jdbcBaseDao;
}
