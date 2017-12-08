package com.jeefw.app.logic;

import org.springframework.web.context.WebApplicationContext;

public abstract interface ILogicFace<BaseRequestBean, BaseResponseBean>
{
  public abstract BaseResponseBean logic(WebApplicationContext paramWebApplicationContext, BaseRequestBean paramBaseRequestBean);
}
