package com.jeefw.service.sys;

import com.jeefw.model.sys.Information;
import core.service.Service;
import java.util.List;

public abstract interface InformationService
  extends Service<Information>
{
  public abstract List<Information> queryInformationHTMLList(List<Information> paramList);
  
  public abstract void indexingInformation();
  
  public abstract List<Information> queryByInformationName(String paramString);
}
