package com.jeefw.test;

import com.jeefw.app.bean.UpdateUserPwdRequestBean;
import core.util.AppSendUtils;
import java.io.PrintStream;

public class TestApp
{
  public static void main(String[] args)
  {
    UpdateUserPwdRequestBean plrb = new UpdateUserPwdRequestBean();
    plrb.setUsername("杨添");
    plrb.setPassword("skynet168");
    String result = AppSendUtils.SendToUrlByBean(plrb);
    System.out.println(result);
  }
}
