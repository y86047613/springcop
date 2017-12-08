package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.GameStatus;
import com.jeefw.model.sys.Recharge;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.UserInsureScore;
import com.jeefw.model.sys.Userinfo;
import com.jeefw.service.sys.GameStatusService;
import com.jeefw.service.sys.RechargeService;
import com.jeefw.service.sys.SysUserService;
import com.jeefw.service.sys.UserInsureScoreService;
import com.jeefw.service.sys.UserinfoService;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.HighPreciseComputor;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sys/sell"})
public class SellController
  extends JavaEEFrameworkBaseController<SysUser>
  implements Constant
{
  private static final Log log = LogFactory.getLog(SellController.class);
  @Resource
  private UserInsureScoreService userInsureScoreService;
  @Resource
  private SysUserService sysUserService;
  @Resource
  private RechargeService rechargeService;
  @Resource
  private UserinfoService userinfoService;
  @Resource
  private GameStatusService gameStatusService;
  private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  @RequestMapping({"/chongzhi"})
  public void chongzhi(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String userId = request.getParameter("userId");
    String score = request.getParameter("score");
    UserInsureScore insureScor = (UserInsureScore)this.userInsureScoreService.getByProerties("userId", Integer.valueOf(userId));
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    String ROLE = String.valueOf(session.getAttribute("ROLE_KEY"));
    SysUser user = (SysUser)session.getAttribute("SESSION_SYS_USER");
    SysUser sysUser = (SysUser)this.sysUserService.get(user.getId());
    
    Map<String, Object> result = new HashMap();
    if (insureScor == null)
    {
      result.put("success", "充值账户不存在.");
    }
    else if (("ROLE_USER".equals(ROLE)) && (sysUser.getScore().compareTo(Double.valueOf(score)) < 0))
    {
      result.put("success", "余额不足.");
    }
    else
    {
      result.put("score", Integer.valueOf(0));
      if ("ROLE_USER".equals(ROLE))
      {
        double s = HighPreciseComputor.sub(sysUser.getScore().doubleValue(), Double.valueOf(score).doubleValue());
        sysUser.setScore(Double.valueOf(s));
        this.sysUserService.merge(sysUser);
        result.put("score", Double.valueOf(s));
      }
      insureScor.setInsureScore(insureScor.getInsureScore().add(BigInteger.valueOf(Long.valueOf(score).longValue())));
      this.userInsureScoreService.merge(insureScor);
      
      Recharge recharge = new Recharge();
      recharge.setA_id(BigInteger.valueOf(user.getId().longValue()));
      recharge.setA_name(user.getEmail());
      recharge.setB_id(BigInteger.valueOf(Long.valueOf(userId).longValue()));
      Userinfo userinfo = (Userinfo)this.userinfoService.get(Integer.valueOf(userId));
      
      recharge.setB_name(userinfo.getAccounts());
      recharge.setCreateTime(this.dateFormater.format(new Date()));
      recharge.setScore(BigInteger.valueOf(Long.valueOf(score).longValue()));
      recharge.setType(2);
      recharge.setFunc("玩家充值{管理员给游戏玩家充值}");
      this.rechargeService.persist(recharge);
      result.put("success", "0");
      double socred = Double.valueOf(String.valueOf(score)).doubleValue();
      double ji = 100.0D;
      if (sysUser.getParent().intValue() != 0)
      {
        SysUser one = (SysUser)this.sysUserService.getByProerties("id", Long.valueOf(sysUser.getParent().intValue()));
        if (one != null)
        {
          GameStatus oneStatus = (GameStatus)this.gameStatusService.getByProerties("statusName", "oneCash");
          double statusValue1 = Double.valueOf(String.valueOf(oneStatus.getStatusValue())).doubleValue();
          double mul1 = HighPreciseComputor.mul(socred, statusValue1);
          double div1 = HighPreciseComputor.div(mul1, ji);
          one.setTiceng(Double.valueOf(HighPreciseComputor.add(one.getTiceng().doubleValue(), div1)));
          one.setScore(Double.valueOf(HighPreciseComputor.add(one.getTiceng().doubleValue(), div1)));
          this.sysUserService.merge(one);
          if (one.getParent().intValue() != 0)
          {
            SysUser two = (SysUser)this.sysUserService.getByProerties("id", Long.valueOf(one.getParent().intValue()));
            if (two != null)
            {
              GameStatus twoStatus = (GameStatus)this.gameStatusService.getByProerties("statusName", "twoCash");
              double statusValue2 = Double.valueOf(String.valueOf(twoStatus.getStatusValue())).doubleValue();
              double mul2 = HighPreciseComputor.mul(socred, statusValue2);
              double div2 = HighPreciseComputor.div(mul2, ji);
              two.setTiceng(Double.valueOf(HighPreciseComputor.add(two.getTiceng().doubleValue(), div2)));
              two.setScore(Double.valueOf(HighPreciseComputor.add(two.getTiceng().doubleValue(), div2)));
              this.sysUserService.merge(two);
              if (two.getParent().intValue() != 0)
              {
                SysUser three = (SysUser)this.sysUserService.getByProerties("id", Long.valueOf(two.getParent().intValue()));
                if (three != null)
                {
                  GameStatus threeStatus = (GameStatus)this.gameStatusService.getByProerties("statusName", 
                    "threeCash");
                  double statusValue3 = Double.valueOf(String.valueOf(threeStatus.getStatusValue())).doubleValue();
                  double mul3 = HighPreciseComputor.mul(socred, statusValue3);
                  double div3 = HighPreciseComputor.div(mul3, ji);
                  three.setTiceng(Double.valueOf(HighPreciseComputor.add(three.getTiceng().doubleValue(), div3)));
                  three.setScore(Double.valueOf(HighPreciseComputor.add(three.getTiceng().doubleValue(), div3)));
                  this.sysUserService.merge(three);
                }
              }
            }
          }
        }
      }
    }
    writeJSON(response, result);
  }
  
  @RequestMapping({"/information"})
  public void information(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    Map<String, Object> result = new HashMap();
    String userId = request.getParameter("userId");
    Userinfo userinfo = (Userinfo)this.userinfoService.get(Integer.valueOf(userId));
    UserInsureScore insureScor = (UserInsureScore)this.userInsureScoreService.getByProerties("userId", Integer.valueOf(userId));
    if ((userinfo != null) && (insureScor != null))
    {
      result.put("userId", userinfo.getUserId());
      result.put("nickName", userinfo.getNickName());
      result.put("insureScore", insureScor.getInsureScore());
    }
    else
    {
      result.put("userId", Integer.valueOf(0));
      result.put("nickName", Integer.valueOf(0));
      result.put("insureScore", Integer.valueOf(0));
    }
    writeJSON(response, result);
  }
  
  @RequestMapping({"/emailInformation"})
  public void emailInformation(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    Map<String, Object> result = new HashMap();
    String email = request.getParameter("email");
    SysUser sysUser = (SysUser)this.sysUserService.getByProerties("email", email);
    if ((sysUser != null) && (sysUser != null))
    {
      result.put("id", sysUser.getId());
      result.put("email", sysUser.getEmail());
      result.put("score", sysUser.getScore());
      result.put("state", Integer.valueOf(1));
    }
    else
    {
      result.put("state", Integer.valueOf(0));
    }
    writeJSON(response, result);
  }
  
  @RequestMapping(value={"/zhuanzhang"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void zhuanzhang(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String emailId = request.getParameter("emailId");
    String emailScore = request.getParameter("emailScore");
    SysUser user = (SysUser)this.sysUserService.getByProerties("id", Long.valueOf(emailId));
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    String ROLE = String.valueOf(session.getAttribute("ROLE_KEY"));
    SysUser u = (SysUser)session.getAttribute("SESSION_SYS_USER");
    SysUser sysUser = (SysUser)this.sysUserService.get(u.getId());
    
    Map<String, Object> result = new HashMap();
    if (user == null)
    {
      result.put("success", "账户不存在.");
    }
    else if (("ROLE_USER".equals(ROLE)) && (sysUser.getScore().compareTo(Double.valueOf(emailScore)) < 0))
    {
      result.put("success", "余额不足.");
    }
    else
    {
      result.put("score", Integer.valueOf(0));
      if ("ROLE_USER".equals(ROLE))
      {
        double s = HighPreciseComputor.sub(sysUser.getScore().doubleValue(), Double.valueOf(emailScore).doubleValue());
        sysUser.setScore(Double.valueOf(s));
        this.sysUserService.merge(sysUser);
        result.put("score", Double.valueOf(s));
      }
      user.setScore(Double.valueOf(HighPreciseComputor.add(user.getScore().doubleValue(), Double.valueOf(emailScore).doubleValue())));
      this.sysUserService.merge(user);
      
      Recharge recharge = new Recharge();
      recharge.setA_id(BigInteger.valueOf(sysUser.getId().longValue()));
      recharge.setA_name(sysUser.getEmail());
      recharge.setB_id(BigInteger.valueOf(user.getId().longValue()));
      recharge.setB_name(user.getEmail());
      recharge.setCreateTime(this.dateFormater.format(new Date()));
      recharge.setScore(BigInteger.valueOf(Long.valueOf(emailScore).longValue()));
      recharge.setType(3);
      recharge.setFunc("转账{管理员之间的转账}");
      this.rechargeService.persist(recharge);
      
      result.put("success", "0");
    }
    writeJSON(response, result);
  }
  
  @RequestMapping(value={"/sellLog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void sellLog(HttpServletRequest request, HttpServletResponse response)
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
  
  @RequestMapping(value={"/buyLog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void buyLog(HttpServletRequest request, HttpServletResponse response)
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
    recharge.set$eq_type(1);
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
  
  @RequestMapping(value={"/zhuanzhangLog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void zhuanzhangLog(HttpServletRequest request, HttpServletResponse response)
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
    recharge.set$eq_type(3);
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
  
  @RequestMapping(value={"/parent"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void parent(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    SysUser sysUser = new SysUser();
    
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    SysUser user = (SysUser)session.getAttribute("SESSION_SYS_USER");
    
    sysUser.set$eq_parent(Integer.valueOf(String.valueOf(user.getId())));
    sysUser.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    sysUser.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    sysUser.setSortedConditions(sortedCondition);
    QueryResult<SysUser> queryResult = this.sysUserService.doPaginationQuery(sysUser);
    JqGridPageView<SysUser> roleListView = new JqGridPageView();
    roleListView.setMaxResults(maxResults.intValue());
    roleListView.setRows(queryResult.getResultList());
    roleListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, roleListView);
  }
  
  public static void main(String[] args)
  {
    double s = 1.0D;
    double s1 = 1.0D;
    double s2 = 100.0D;
    double s3 = HighPreciseComputor.mul(s, s1);
    double s5 = HighPreciseComputor.div(s3, s2);
    System.out.println(s5);
  }
}
