package com.jeefw.dao.sys;import com.jeefw.model.sys.SysUser;import core.dao.Dao;public abstract interface SysUserDao  extends Dao<SysUser>{  public abstract String getRoleValueBySysUserId(Long paramLong);}