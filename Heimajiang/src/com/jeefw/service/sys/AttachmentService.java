package com.jeefw.service.sys;

import com.jeefw.model.sys.Attachment;
import core.service.Service;
import java.util.List;

public abstract interface AttachmentService
  extends Service<Attachment>
{
  public abstract List<Object[]> queryFlowerList(String paramString);
  
  public abstract void deleteAttachmentByForestryTypeId(Long paramLong);
  
  public abstract List<Object[]> querySensorList();
}
