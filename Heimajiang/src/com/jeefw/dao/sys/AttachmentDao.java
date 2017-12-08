package com.jeefw.dao.sys;

import com.jeefw.model.sys.Attachment;
import core.dao.Dao;
import java.util.List;

public abstract interface AttachmentDao
  extends Dao<Attachment>
{
  public abstract List<Object[]> queryFlowerList(String paramString);
  
  public abstract void deleteAttachmentByForestryTypeId(Long paramLong);
  
  public abstract List<Object[]> querySensorList();
}
