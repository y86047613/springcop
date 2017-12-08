package com.jeefw.app.logic;

import com.google.gson.Gson;
import com.jeefw.app.bean.BaseResponseBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class Transmission
  implements ITransmission
{
  private static final Logger log = Logger.getLogger(Transmission.class);
  private String encode = "UTF-8";
  
  public String getEncode()
  {
    return this.encode;
  }
  
  public void setEncode(String encode)
  {
    this.encode = encode;
  }
  
  public String resv(HttpServletRequest request)
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      InputStream is = request.getInputStream();
      Reader reader = new InputStreamReader(is, this.encode);
      BufferedReader br = new BufferedReader(reader);
      String r_line = "";
      while ((r_line = br.readLine()) != null) {
        sb.append(r_line);
      }
    }
    catch (IOException e)
    {
      log.debug(e, e);
    }
    log.debug("recv client request json is:[" + sb.toString() + "]");
    return sb.toString();
  }
  
  public void resp(HttpServletResponse response, BaseResponseBean brb)
  {
    response.setContentType("text/html;charset=UTF-8");
    Gson gson = new Gson();
    String result = gson.toJson(brb);
    log.debug("server response client json is:[" + result + "]");
    try
    {
      response.getWriter().write(result);
      response.flushBuffer();
    }
    catch (IOException e)
    {
      log.debug(e, e);
    }
  }
}
