package com.jeefw.app.logic;

import com.jeefw.app.bean.BaseResponseBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface ITransmission
{
  public abstract String resv(HttpServletRequest paramHttpServletRequest);
  
  public abstract void resp(HttpServletResponse paramHttpServletResponse, BaseResponseBean paramBaseResponseBean);
}
