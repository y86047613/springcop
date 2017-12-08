package com.jeefw.service.sys;

import com.jeefw.model.sys.Dict;
import core.service.Service;
import java.util.List;

public abstract interface DictService
  extends Service<Dict>
{
  public abstract List<Dict> queryDictWithSubList(List<Dict> paramList);
}
