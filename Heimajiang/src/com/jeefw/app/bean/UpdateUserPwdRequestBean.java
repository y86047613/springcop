package com.jeefw.app.bean;

public class UpdateUserPwdRequestBean
  extends BaseRequestBean
{
  private String username;
  private String password;
  
  public UpdateUserPwdRequestBean()
  {
    setActionCode("1302");
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
}
