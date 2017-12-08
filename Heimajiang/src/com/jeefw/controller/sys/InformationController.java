package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Information;
import com.jeefw.service.sys.InformationService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/sys/information"})
public class InformationController
  extends JavaEEFrameworkBaseController<Information>
  implements Constant
{
  @Resource
  private InformationService informationService;
  
  @RequestMapping(value={"/getInformation"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getInformation(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Information information = new Information();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      for (int i = 0; i < jsonArray.size(); i++)
      {
        JSONObject result = (JSONObject)jsonArray.get(i);
        if ((result.getString("field").equals("title")) && (result.getString("op").equals("cn"))) {
          information.set$like_title(result.getString("data"));
        }
      }
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        information.setFlag("OR");
      } else {
        information.setFlag("AND");
      }
    }
    information.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    information.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    information.setSortedConditions(sortedCondition);
    QueryResult<Information> queryResult = this.informationService.doPaginationQuery(information);
    JqGridPageView<Information> informationListView = new JqGridPageView();
    informationListView.setMaxResults(maxResults.intValue());
    List<Information> informationHTMLList = this.informationService.queryInformationHTMLList(queryResult.getResultList());
    informationListView.setRows(informationHTMLList);
    informationListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, informationListView);
  }
  
  @RequestMapping(value={"/getInformationHibernateSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getInformationHibernateSearch(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String luceneName = request.getParameter("luceneName");
    
    Integer firstResult = Integer.valueOf(1);
    Integer maxResults = Integer.valueOf(10);
    Information information = new Information();
    information.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    information.setMaxResults(maxResults);
    JqGridPageView<Information> informationListView = new JqGridPageView();
    informationListView.setMaxResults(maxResults.intValue());
    List<Information> informationLuceneList = this.informationService.queryByInformationName(luceneName);
    informationListView.setRows(informationLuceneList);
    informationListView.setRecords(informationLuceneList.size());
    writeJSON(response, informationListView);
  }
  
  @RequestMapping(value={"/saveInformation"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave(Information entity, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = entity;
    if ("edit".equals(parameter.getCmd())) {
      this.informationService.merge(entity);
    } else if ("new".equals(parameter.getCmd())) {
      this.informationService.persist(entity);
    }
    parameter.setSuccess(Boolean.valueOf(true));
    writeJSON(response, parameter);
  }
  
  @RequestMapping(value={"/operateInformation"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void operateInformation(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String oper = request.getParameter("oper");
    String id = request.getParameter("id");
    if (oper.equals("del"))
    {
      String[] ids = id.split(",");
      deleteInformation(request, response, (Long[])ConvertUtils.convert(ids, Long.class));
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
      String title = request.getParameter("title");
      String author = request.getParameter("author");
      String content = request.getParameter("content");
      if (StringUtils.isBlank(title))
      {
        response.setStatus(411);
        result.put("message", "请填写标题");
        writeJSON(response, result);
      }
      else
      {
        Information entity = new Information();
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setContent(content);
        entity.setRefreshTime(new Date());
        if (StringUtils.isNotBlank(id))
        {
          entity.setId(Long.valueOf(id));
          entity.setCmd("edit");
          doSave(entity, request, response);
        }
        else
        {
          entity.setCmd("new");
          doSave(entity, request, response);
        }
      }
    }
  }
  
  @RequestMapping({"/deleteInformation"})
  public void deleteInformation(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids)
    throws IOException
  {
    boolean flag = this.informationService.deleteByPK(ids);
    if (flag) {
      writeJSON(response, "{success:true}");
    } else {
      writeJSON(response, "{success:false}");
    }
  }
}
