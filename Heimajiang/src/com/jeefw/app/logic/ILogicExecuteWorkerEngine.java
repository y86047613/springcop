package com.jeefw.app.logic;

import com.jeefw.app.bean.BaseRequestBean;
import java.util.Map;

public abstract interface ILogicExecuteWorkerEngine
{
  public abstract ILogicFace getILogicFaceByActionCode(String paramString);
  
  public abstract void setLogicPool(Map<String, ILogicFace> paramMap);
  
  public abstract void setRequestBeanTrans(Map<String, String> paramMap);
  
  public abstract BaseRequestBean trans(String paramString);
}
