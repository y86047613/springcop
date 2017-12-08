package com.jeefw.service.sys;

import com.jeefw.model.sys.Authority;
import core.service.Service;
import java.util.List;

public abstract interface AuthorityService
  extends Service<Authority>
{
  public abstract List<Authority> queryAllMenuList(String paramString);
  
  public abstract String getAuthorityButtonList(String paramString);
  
  public abstract void saveAuthorityButtonList(String paramString1, String paramString2);
}
