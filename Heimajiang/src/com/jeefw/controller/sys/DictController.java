package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Dict;
import com.jeefw.service.sys.DictService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
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
@RequestMapping({"/sys/dict"})
public class DictController
  extends JavaEEFrameworkBaseController<Dict>
  implements Constant
{
  @Resource
  private DictService dictService;
  
  @RequestMapping(value={"/getDict"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void getDict(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    Integer firstResult = Integer.valueOf(request.getParameter("page"));
    Integer maxResults = Integer.valueOf(request.getParameter("rows"));
    String sortedObject = request.getParameter("sidx");
    String sortedValue = request.getParameter("sord");
    String filters = request.getParameter("filters");
    Dict dict = new Dict();
    if (StringUtils.isNotBlank(filters))
    {
      JSONObject jsonObject = JSONObject.fromObject(filters);
      JSONArray jsonArray = (JSONArray)jsonObject.get("rules");
      for (int i = 0; i < jsonArray.size(); i++)
      {
        JSONObject result = (JSONObject)jsonArray.get(i);
        if ((result.getString("field").equals("dictKey")) && (result.getString("op").equals("eq"))) {
          dict.set$eq_dictKey(result.getString("data"));
        }
        if ((result.getString("field").equals("dictValue")) && (result.getString("op").equals("cn"))) {
          dict.set$like_dictValue(result.getString("data"));
        }
      }
      if (((String)jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
        dict.setFlag("OR");
      } else {
        dict.setFlag("AND");
      }
    }
    dict.setFirstResult(Integer.valueOf((firstResult.intValue() - 1) * maxResults.intValue()));
    dict.setMaxResults(maxResults);
    Map<String, String> sortedCondition = new HashMap();
    sortedCondition.put(sortedObject, sortedValue);
    dict.setSortedConditions(sortedCondition);
    QueryResult<Dict> queryResult = this.dictService.doPaginationQuery(dict);
    JqGridPageView<Dict> dictListView = new JqGridPageView();
    dictListView.setMaxResults(maxResults.intValue());
    List<Dict> dictWithSubList = this.dictService.queryDictWithSubList(queryResult.getResultList());
    dictListView.setRows(dictWithSubList);
    dictListView.setRecords(queryResult.getTotalCount().longValue());
    writeJSON(response, dictListView);
  }
  
  @RequestMapping(value={"/saveDict"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doSave(Dict entity, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    ExtJSBaseParameter parameter = entity;
    if ("edit".equals(parameter.getCmd())) {
      this.dictService.merge(entity);
    } else if ("new".equals(parameter.getCmd())) {
      this.dictService.persist(entity);
    }
    parameter.setSuccess(Boolean.valueOf(true));
    writeJSON(response, parameter);
  }
  
  @RequestMapping(value={"/operateDict"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void operateDict(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String oper = request.getParameter("oper");
    String id = request.getParameter("id");
    if (oper.equals("del"))
    {
      String[] ids = id.split(",");
      deleteDict(request, response, (Long[])ConvertUtils.convert(ids, Long.class));
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
      String dictKey = request.getParameter("dictKey");
      String dictValue = request.getParameter("dictValue");
      String sequence = request.getParameter("sequence");
      String parentDictkey = request.getParameter("parentDictkey");
      Dict dict = null;
      if (oper.equals("edit")) {
        dict = (Dict)this.dictService.get(Long.valueOf(id));
      }
      Dict dictKeyDict = (Dict)this.dictService.getByProerties("dictKey", dictKey);
      Dict parentDictkeyDict = (Dict)this.dictService.getByProerties("dictKey", parentDictkey);
      if ((StringUtils.isBlank(dictKey)) || (StringUtils.isBlank(dictValue)))
      {
        response.setStatus(411);
        result.put("message", "请填写字典编码和字典名称");
        writeJSON(response, result);
      }
      else if ((dictKeyDict != null) && (oper.equals("add")))
      {
        response.setStatus(409);
        result.put("message", "此字典编码已存在，请重新输入");
        writeJSON(response, result);
      }
      else if ((dictKeyDict != null) && (!dict.getDictKey().equalsIgnoreCase(dictKey)) && (oper.equals("edit")))
      {
        response.setStatus(409);
        result.put("message", "此字典编码已存在，请重新输入");
        writeJSON(response, result);
      }
      else if ((StringUtils.isNotBlank(parentDictkey)) && (parentDictkeyDict == null))
      {
        response.setStatus(409);
        result.put("message", "上级字典编码输入有误，请重新输入");
        writeJSON(response, result);
      }
      else
      {
        Dict entity = new Dict();
        entity.setDictKey(dictKey);
        entity.setDictValue(dictValue);
        entity.setSequence(sequence == "" ? null : Long.valueOf(sequence));
        entity.setParentDictkey(parentDictkey);
        if (oper.equals("edit"))
        {
          entity.setId(Long.valueOf(id));
          entity.setCmd("edit");
          doSave(entity, request, response);
        }
        else if (oper.equals("add"))
        {
          entity.setCmd("new");
          doSave(entity, request, response);
        }
      }
    }
  }
  
  @RequestMapping({"/deleteDict"})
  public void deleteDict(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids)
    throws IOException
  {
    boolean flag = this.dictService.deleteByPK(ids);
    if (flag) {
      writeJSON(response, "{success:true}");
    } else {
      writeJSON(response, "{success:false}");
    }
  }
}
