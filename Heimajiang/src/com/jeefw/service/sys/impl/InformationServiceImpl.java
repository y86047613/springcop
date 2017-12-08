package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.InformationDao;
import com.jeefw.model.sys.Information;
import com.jeefw.service.sys.InformationService;
import core.service.BaseService;
import core.util.HtmlUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl
  extends BaseService<Information>
  implements InformationService
{
  private InformationDao informationDao;
  
  @Resource
  public void setInformationDao(InformationDao informationDao)
  {
    this.informationDao = informationDao;
    this.dao = informationDao;
  }
  
  public List<Information> queryInformationHTMLList(List<Information> resultList)
  {
    List<Information> informationList = new ArrayList();
    for (Information entity : resultList)
    {
      Information information = new Information();
      information.setId(entity.getId());
      information.setTitle(entity.getTitle());
      information.setAuthor(entity.getAuthor());
      information.setRefreshTime(entity.getRefreshTime());
      information.setContent(entity.getContent());
      information.setContentNoHTML(HtmlUtils.htmltoText(entity.getContent()));
      informationList.add(information);
    }
    return informationList;
  }
  
  public void indexingInformation()
  {
    this.informationDao.indexingInformation();
  }
  
  public List<Information> queryByInformationName(String name)
  {
    return this.informationDao.queryByInformationName(name);
  }
}
