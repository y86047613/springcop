package com.jeefw.dao.sys;

import com.jeefw.model.sys.Information;
import core.dao.Dao;
import java.util.List;

public abstract interface InformationDao
  extends Dao<Information>
{
  public abstract void indexingInformation();
  
  public abstract List<Information> queryByInformationName(String paramString);
}
