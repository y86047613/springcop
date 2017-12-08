package com.jeefw.dao.sys.impl;

import com.jeefw.core.BaseDao2;
import com.jeefw.dao.sys.GameStatusDao;
import com.jeefw.model.sys.GameStatus;
import org.springframework.stereotype.Repository;

@Repository
public class GameStatusDaoImpl
  extends BaseDao2<GameStatus>
  implements GameStatusDao
{
  public GameStatusDaoImpl()
  {
    super(GameStatus.class);
  }
}
