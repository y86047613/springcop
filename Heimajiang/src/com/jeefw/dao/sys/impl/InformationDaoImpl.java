package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.InformationDao;
import com.jeefw.model.sys.Information;
import core.dao.BaseDao;
import core.util.HtmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.EntityContext;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.QueryContextBuilder;
import org.hibernate.search.query.dsl.TermContext;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.hibernate.search.query.dsl.TermTermination;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDaoImpl
  extends BaseDao<Information>
  implements InformationDao
{
  public InformationDaoImpl()
  {
    super(Information.class);
  }
  
  public void indexingInformation()
  {
    try
    {
      FullTextSession fullTextSession = Search.getFullTextSession(getSession());
      
      fullTextSession.createIndexer(new Class[] { Information.class }).threadsForSubsequentFetching(1).threadsToLoadObjects(1).startAndWait();
      fullTextSession.flushToIndexes();
      fullTextSession.getSearchFactory().optimize();
      fullTextSession.clear();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public List<Information> queryByInformationName(String name)
  {
    if (StringUtils.isBlank(name)) {
      return null;
    }
    FullTextSession fullTextSession = Search.getFullTextSession(getSession());
    SearchFactory searchFactory = fullTextSession.getSearchFactory();
    QueryBuilder queryBuilder = searchFactory.buildQueryBuilder().forEntity(Information.class).get();
    Query luceneQuery = queryBuilder.bool().should(queryBuilder.keyword().onField("title").matching(name).createQuery())
      .should(queryBuilder.keyword().onField("author").matching(name).createQuery()).should(queryBuilder.keyword().onField("content").matching(name).createQuery()).createQuery();
    FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, new Class[] { Information.class }).setMaxResults(500);
    
    List<Information> originalInformationList = fullTextQuery.list();
    List<Information> informationList = new ArrayList();
    for (Information entity : originalInformationList)
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
}
