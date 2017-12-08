package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Dict;
import com.jeefw.model.sys.GameStatus;
import com.jeefw.model.sys.Recharge;
import com.jeefw.model.sys.Role;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.UserInsureScore;
import com.jeefw.model.sys.UserScore;
import com.jeefw.model.sys.Userinfo;
import com.jeefw.service.sys.GameStatusService;
import com.jeefw.service.sys.RechargeService;
import com.jeefw.service.sys.SysUserService;
import com.jeefw.service.sys.UserInsureScoreService;
import com.jeefw.service.sys.UserScoreService;
import com.jeefw.service.sys.UserinfoService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.HighPreciseComputor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sys/game"})
public class GameController
  extends JavaEEFrameworkBaseController<Role>
  implements Constant
{
  @Resource
  private UserScoreService userScoreService;
  @Resource
  private UserinfoService userinfoService;
  @Resource
  private UserInsureScoreService userInsureScoreService;
  @Resource
  private GameStatusService gameStatusService;
  @Resource
  private RechargeService rechargeService;
  @Resource
  private SysUserService sysUserService;
  private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  @RequestMapping(value={"/getuser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getRole(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Userinfo userinfo = new Userinfo();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      for (int i = 0; i < jsonArray.size(); i++)
      {
        JSONObject result = (JSONObject)jsonArray.get(i);
        if ((result.getString("field").equals("accounts")) && (result.getString("op").equals("eq"))) {
          userinfo.set$eq_accounts(result.getString("data"));
        } else if ((result.getString("field").equals("nickName")) && (result.getString("op").equals("eq"))) {
          userinfo.set$eq_nickName(result.getString("data"));
        } else if ((result.getString("field").equals("spreaderID")) && (result.getString("op").equals("eq"))) {
          userinfo.set$eq_spreaderID(Integer.valueOf(result.getString("data")));
        }
      }
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        userinfo.setFlag("OR");
      } else {
        userinfo.setFlag("AND");
      }
    }
    String accounts = request.getParameter("accounts");
    String nickName = request.getParameter("nickName");
    String spreaderID = request.getParameter("spreaderID");
    String userId = request.getParameter("userId");
    if ((accounts != null) && (!"".equals(accounts))) {
      userinfo.set$eq_accounts(accounts);
    }
    if ((nickName != null) && (!"".equals(nickName))) {
      userinfo.set$eq_nickName(nickName);
    }
    if (isBigInteger(userId)) {
      userinfo.set$eq_userId(Integer.valueOf(userId));
    }
    if ((spreaderID != null) && (!"".equals(spreaderID)))
    {
      Userinfo user = (Userinfo)this.userinfoService.getByProerties("accounts", spreaderID);
      if (user != null) {
        userinfo.set$eq_userId(user.getUserId());
      } else {
        userinfo.set$eq_userId(Integer.valueOf(0));
      }
    }
    userinfo.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    userinfo.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    userinfo.setSortedConditions(sortedCondition);
    QueryResult<Userinfo> queryResult = this.userinfoService.doPaginationQuery(userinfo);
    for (Userinfo user : queryResult.getResultList())
    {
      UserInsureScore score = (UserInsureScore)this.userInsureScoreService.getByProerties("userId", user.getUserId());
      if (score != null)
      {
        user.setWinCount(score.getWinCount());
        user.setLostCount(score.getLostCount());
        user.setScore(score.getScore());
        user.setDrawCount(score.getDrawCount());
        user.setFleeCount(score.getFleeCount());
        user.setInsureScore(score.getInsureScore());
      }
      else
      {
        user.setWinCount(Integer.valueOf(0));
        user.setLostCount(Integer.valueOf(0));
        user.setScore(BigInteger.valueOf(0L));
        user.setDrawCount(Integer.valueOf(0));
        user.setFleeCount(Integer.valueOf(0));
        user.setInsureScore(BigInteger.valueOf(0L));
      }
      user.setId(user.getUserId());
    }
    JqGridPageView<Userinfo> roleListView = new JqGridPageView();
    roleListView.setMaxResults(maxResults.intValue());
    roleListView.setRows(queryResult.getResultList());
    roleListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, roleListView);
  }
  
  @RequestMapping(value={"/saveUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave(Userinfo userinfo, UserScore userScore, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = userinfo;
    Userinfo user2 = (Userinfo)this.userinfoService.getByProerties("accounts", userinfo.getAccounts());
    if (user2 == null)
    {
      this.userinfoService.merge(userinfo);
      if (userScore != null) {
        this.userScoreService.merge(userScore);
      }
      parameter.setSuccess(Boolean.valueOf(true));
      writeJSON(response, parameter);
    }
    else
    {
      parameter.setSuccess(Boolean.valueOf(false));
      writeJSON(response, parameter);
    }
  }
  
  @RequestMapping(value={"/saveUser2"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave2(Userinfo userinfo, UserInsureScore s, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = userinfo;
    Userinfo user2 = (Userinfo)this.userinfoService.getByProerties("accounts", userinfo.getAccounts());
    if (user2 == null)
    {
      this.userinfoService.merge(userinfo);
      
      parameter.setSuccess(Boolean.valueOf(true));
      writeJSON(response, parameter);
    }
    else
    {
      userinfo.setAccounts(user2.getAccounts());
      this.userinfoService.merge(userinfo);
      
      parameter.setSuccess(Boolean.valueOf(true));
      writeJSON(response, parameter);
    }
  }
  
  @RequestMapping(value={"/operateGameUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void operateDict(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String oper = request.getParameter("oper");
    String id = request.getParameter("id");
    if (oper.equals("del"))
    {
      String[] ids = id.split(",");
      Map<String, Object> result = new HashMap();
      result.put("message", "暂不提供删除功能");
      writeJSON(response, result);
    }
    else if (oper.equals("excel"))
    {
      response.setContentType("application/msexcel;charset=UTF-8");
      try
      {
        response.addHeader("Content-Disposition", "attachment;filename=file.xls");
        OutputStream out = response.getOutputStream();
        out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
        out.flush();
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      Map<String, Object> result = new HashMap();
      String userId = id;
      String accounts = request.getParameter("accounts");
      String nickName = request.getParameter("nickName");
      String logonPass = request.getParameter("logonPass");
      String score = request.getParameter("score");
      String insureScore = request.getParameter("insureScore");
      String winCount = request.getParameter("winCount");
      String drawCount = request.getParameter("drawCount");
      String userRight = request.getParameter("userRight");
      String fleeCount = request.getParameter("fleeCount");
      String lostCount = request.getParameter("lostCount");
      String insurePass = request.getParameter("insurePass");
      Dict dict = null;
      if (oper.equals("edit"))
      {
        UserInsureScore s = (UserInsureScore)this.userInsureScoreService.getByProerties("userId", Integer.valueOf(userId));
        if ((isBigInteger(insureScore)) && (isBigInteger(score)) && (s != null))
        {
          s.setScore(BigInteger.valueOf(Long.valueOf(score).longValue()));
          s.setInsureScore(BigInteger.valueOf(Long.valueOf(insureScore).longValue()));
          this.userInsureScoreService.merge(s);
        }
        Userinfo userinfo = (Userinfo)this.userinfoService.getByProerties("userId", Integer.valueOf(userId));
        userinfo.setAccounts(accounts);
        userinfo.setNickName(nickName);
        userinfo.setLogonPass(logonPass);
        if (isBigInteger(userRight)) {
          userinfo.setUserRight(Integer.valueOf(userRight));
        }
        doSave2(userinfo, s, request, response);
      }
    }
  }
  
  @RequestMapping(value={"/chongzhi"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void chongZhi(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Map<String, Object> result = new HashMap();
    String chongZhiId = request.getParameter("chongZhiId");
    String chongZhiScore = request.getParameter("chongZhiScore");
    Subject subject = SecurityUtils.getSubject();
    
    Session session = subject.getSession();
    String ROLE = String.valueOf(session.getAttribute("ROLE_KEY"));
    SysUser user = (SysUser)session.getAttribute("SESSION_SYS_USER");
    SysUser sysUser = (SysUser)this.sysUserService.get(user.getId());
    if (("ROLE_ADMIN".equals(ROLE)) || (("ROLE_USER".equals(ROLE)) && (Integer.valueOf(chongZhiScore).intValue() > 0))) {
      if (sysUser.getScore().compareTo(Double.valueOf(chongZhiScore)) >= 0)
      {
        double s = HighPreciseComputor.sub(sysUser.getScore().doubleValue(), Double.valueOf(chongZhiScore).doubleValue());
        sysUser.setScore(Double.valueOf(s));
        this.sysUserService.merge(sysUser);
        
        UserInsureScore u = (UserInsureScore)this.userInsureScoreService.get(Integer.valueOf(chongZhiId));
        u.setScore(u.getScore().add(BigInteger.valueOf(Long.valueOf(chongZhiScore).longValue())));
        this.userInsureScoreService.merge(u);
        
        Recharge recharge = new Recharge();
        recharge.setA_id(BigInteger.valueOf(user.getId().longValue()));
        recharge.setA_name(user.getUserName());
        recharge.setB_id(BigInteger.valueOf(Long.valueOf(chongZhiId).longValue()));
        Userinfo userinfo = (Userinfo)this.userinfoService.get(Integer.valueOf(chongZhiId));
        recharge.setB_name(userinfo.getAccounts());
        recharge.setCreateTime(this.dateFormater.format(new Date()));
        recharge.setScore(BigInteger.valueOf(Long.valueOf(chongZhiScore).longValue()));
        recharge.setType(1);
        this.rechargeService.persist(recharge);
      }
      else
      {
        response.setStatus(411);
        result.put("message", "余额不足");
        writeJSON(response, result);
      }
    }
  }
  
  @RequestMapping(value={"/getGameSys"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getGameSys(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    GameStatus gameStatus = new GameStatus();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        gameStatus.setFlag("OR");
      } else {
        gameStatus.setFlag("AND");
      }
    }
    gameStatus.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    gameStatus.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    gameStatus.setSortedConditions(sortedCondition);
    QueryResult<GameStatus> queryResult = this.gameStatusService.doPaginationQuery(gameStatus);
    JqGridPageView<GameStatus> authorityListView = new JqGridPageView();
    authorityListView.setMaxResults(maxResults.intValue());
    authorityListView.setRows(queryResult.getResultList());
    authorityListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, authorityListView);
  }
  
  @RequestMapping(value={"/updateGameStatus"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void updateGameStatus(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String statusName = request.getParameter("statusName");
    String statusValue = request.getParameter("statusValue");
    String statusString = request.getParameter("statusString");
    
    GameStatus status = (GameStatus)this.gameStatusService.getByProerties("statusName", statusName);
    if (status != null)
    {
      status.setStatusString(statusString);
      status.setStatusValue(Integer.valueOf(statusValue));
      this.gameStatusService.merge(status);
      ExtJSBaseParameter parameter = status;
      parameter.setSuccess(Boolean.valueOf(true));
      writeJSON(response, parameter);
    }
  }
  
  @RequestMapping(value={"/getRecharges"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getRecharges(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Recharge recharge = new Recharge();
    
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    String ROLE = String.valueOf(session.getAttribute("ROLE_KEY"));
    SysUser user = (SysUser)session.getAttribute("SESSION_SYS_USER");
    SysUser sysUser = (SysUser)this.sysUserService.get(user.getId());
    if ((!"ROLE_ADMIN".equals(ROLE)) && (!"ROLE_RESTRICTED_ADMIN".equals(ROLE))) {
      if ("ROLE_USER".equals(ROLE)) {
        recharge.set$eq_a_id(BigInteger.valueOf(sysUser.getId().longValue()));
      } else {
        recharge.set$eq_a_id(BigInteger.valueOf(0L));
      }
    }
    String a_name = request.getParameter("a_name");
    String b_id = request.getParameter("b_id");
    if ((a_name != null) && (!"".equals(a_name))) {
      recharge.set$eq_a_name(a_name);
    }
    if ((b_id != null) && (!"".equals(b_id)) && (isInteger(b_id))) {
      recharge.set$eq_b_id(BigInteger.valueOf(Long.valueOf(b_id).longValue()));
    }
    recharge.set$eq_type(2);
    recharge.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    recharge.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    recharge.setSortedConditions(sortedCondition);
    QueryResult<Recharge> queryResult = this.rechargeService.doPaginationQuery(recharge);
    JqGridPageView<Recharge> roleListView = new JqGridPageView();
    roleListView.setMaxResults(maxResults.intValue());
    roleListView.setRows(queryResult.getResultList());
    roleListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, roleListView);
  }
  
  public static boolean isInteger(String value)
  {
    try
    {
      Integer.parseInt(value);
      return true;
    }
    catch (NumberFormatException e) {}
    return false;
  }
  
  public boolean isBigInteger(String num)
  {
    Pattern pattern = Pattern.compile("[0-9]*");
    if ((num == null) || ("".equals(num))) {
      return false;
    }
    Matcher isNum = pattern.matcher(num);
    if (isNum.matches()) {
      return true;
    }
    return false;
  }
  
  public static void main(String[] args)
  {
    BigInteger bi1 = new BigInteger("-4");
    BigInteger bi2 = new BigInteger("3");
    
    int res = bi1.compareTo(bi2);
    System.out.println(res);
  }
}
