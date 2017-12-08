package core.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AntiSQLRequest
  extends HttpServletRequestWrapper
{
  private HttpServletRequest original;
  private Map safeParameterMap;
  
  public AntiSQLRequest(HttpServletRequest req)
  {
    super(req);
    this.original = req;
  }
  
  public String getParameter(String paramName)
  {
    String[] values = getParameterValues(paramName);
    if ((values != null) && (values.length > 0)) {
      return values[0];
    }
    return null;
  }
  
  public String[] getParameterValues(String paramName)
  {
    return (String[])getParameterMap().get(paramName);
  }
  
  public Map getParameterMap()
  {
    if (this.safeParameterMap == null)
    {
      Map originalParameterMap = this.original.getParameterMap();
      this.safeParameterMap = AntiSQLFilter.getSafeParameterMap(originalParameterMap);
    }
    return this.safeParameterMap;
  }
}
