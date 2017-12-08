package com.jeefw.app.logic;

import com.jeefw.app.bean.BaseRequestBean;
import com.jeefw.app.bean.BaseResponseBean;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ClientServerController
  implements Servlet
{
  private static final Logger log = Logger.getLogger(ClientServerController.class);
  private WebApplicationContext wac;
  private ILogicExecuteWorkerEngine logicExecuteWorkerEngin;
  private ITransmission transmission;
  
  public void destroy() {}
  
  public ServletConfig getServletConfig()
  {
    return null;
  }
  
  public String getServletInfo()
  {
    return null;
  }
  
  public void init(ServletConfig servletConfig)
    throws ServletException
  {
    this.wac = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());
    this.logicExecuteWorkerEngin = ((ILogicExecuteWorkerEngine)this.wac.getBean("logicExecuteWorkerEnginBean"));
    log.debug("logicExecuteWorkerEngin is:[" + this.logicExecuteWorkerEngin + "]");
    this.transmission = ((ITransmission)this.wac.getBean("transmissionBean"));
    log.debug("transmission is:[" + this.transmission + "]");
  }
  
  public void service(ServletRequest req, ServletResponse resp)
    throws ServletException, IOException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;
    if ((this.transmission != null) && (this.logicExecuteWorkerEngin != null))
    {
      String json = this.transmission.resv(request);
      BaseRequestBean brb = this.logicExecuteWorkerEngin.trans(json);
      if (brb != null)
      {
        ILogicFace logic = this.logicExecuteWorkerEngin.getILogicFaceByActionCode(brb.getActionCode());
        if (logic != null)
        {
          BaseResponseBean brespon = (BaseResponseBean)logic.logic(this.wac, brb);
          if (brespon != null) {
            this.transmission.resp(response, brespon);
          }
        }
      }
    }
  }
}
