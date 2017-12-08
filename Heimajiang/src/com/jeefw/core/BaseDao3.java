package com.jeefw.core;

import core.dao.Dao;
import core.support.BaseParameter;
import core.support.QueryResult;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class BaseDao3<E>
  implements Dao<E>
{
  protected final Logger log = Logger.getLogger(BaseDao3.class);
  private static Map<String, Method> MAP_METHOD = new HashMap();
  private SessionFactory sessionFactory;
  protected Class<E> entityClass;
  
  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }
  
  public void setSessionFactory(SessionFactory sessionFactory)
  {
    this.sessionFactory = sessionFactory;
  }
  
  public Session getSession()
  {
    return this.sessionFactory.getCurrentSession();
  }
  
  @Resource(name="sessionFactory3")
  public void setSF(SessionFactory sessionFactory)
  {
    setSessionFactory(sessionFactory);
  }
  
  public BaseDao3(Class<E> entityClass)
  {
    this.entityClass = entityClass;
  }
  
  public void persist(E entity)
  {
    getSession().save(entity);
  }
  
  public boolean deleteByPK(Serializable... id)
  {
    boolean result = false;
    if ((id != null) && (id.length > 0)) {
      for (int i = 0; i < id.length; i++)
      {
        Object entity = get(id[i]);
        if (entity != null)
        {
          getSession().delete(entity);
          result = true;
        }
      }
    }
    return result;
  }
  
  public void deleteByProperties(String[] propName, Object[] propValue)
  {
    if ((propName != null) && (propName.length > 0) && (propValue != null) && (propValue.length > 0) && 
      (propValue.length == propName.length))
    {
      StringBuffer sb = new StringBuffer("delete from " + this.entityClass.getName() + " o where 1=1 ");
      appendQL(sb, propName, propValue);
      Query query = getSession().createQuery(sb.toString());
      setParameter(query, propName, propValue);
      query.executeUpdate();
    }
  }
  
  public void delete(E entity)
  {
    getSession().delete(entity);
  }
  
  public void deleteByProperties(String propName, Object propValue)
  {
    deleteByProperties(new String[] { propName }, new Object[] { propValue });
  }
  
  public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue)
  {
    if ((propertyName != null) && (propertyName.length > 0) && (propertyValue != null) && (propertyValue.length > 0) && 
      (propertyName.length == propertyValue.length) && (conditionValue != null) && (conditionValue.length > 0))
    {
      StringBuffer sb = new StringBuffer();
      sb.append("update " + this.entityClass.getName() + " o set ");
      for (int query = 0; query < propertyName.length; query++) {
        sb.append(propertyName[query] + " = :p_" + propertyName[query] + ",");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append(" where 1=1 ");
      appendQL(sb, conditionName, conditionValue);
      Query arg7 = getSession().createQuery(sb.toString());
      for (int i = 0; i < propertyName.length; i++) {
        arg7.setParameter("p_" + propertyName[i], propertyValue[i]);
      }
      setParameter(arg7, conditionName, conditionValue);
      arg7.executeUpdate();
    }
    else
    {
      throw new IllegalArgumentException("Method updateByProperties in BaseDao argument is illegal!");
    }
  }
  
  public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue)
  {
    updateByProperties(conditionName, conditionValue, new String[] { propertyName }, 
      new Object[] { propertyValue });
  }
  
  public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue)
  {
    updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, propertyName, 
      propertyValue);
  }
  
  public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue)
  {
    updateByProperties(new String[] { conditionName }, new Object[] { conditionValue }, 
      new String[] { propertyName }, new Object[] { propertyValue });
  }
  
  public void update(E entity)
  {
    getSession().update(entity);
  }
  
  public void update(E entity, Serializable oldId)
  {
    deleteByPK(new Serializable[] { oldId });
    persist(entity);
  }
  
  public E merge(E entity)
  {
    return (E)getSession().merge(entity);
  }
  
  public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition)
  {
    if ((propName != null) && (propName.length > 0) && (propValue != null) && (propValue.length > 0) && 
      (propValue.length == propName.length))
    {
      StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o where 1=1 ");
      appendQL(sb, propName, propValue);
      if ((sortedCondition != null) && (sortedCondition.size() > 0))
      {
        sb.append(" order by ");
        Iterator list = sortedCondition.entrySet().iterator();
        while (list.hasNext())
        {
          Map.Entry query = (Map.Entry)list.next();
          sb.append((String)query.getKey() + " " + (String)query.getValue() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
      }
      Query query1 = getSession().createQuery(sb.toString());
      setParameter(query1, propName, propValue);
      List list1 = query1.list();
      if ((list1 != null) && (list1.size() > 0)) {
        return (E)list1.get(0);
      }
    }
    return null;
  }
  
  public E get(Serializable id)
  {
    return (E)getSession().get(this.entityClass, id);
  }
  
  public E load(Serializable id)
  {
    return (E)getSession().load(this.entityClass, id);
  }
  
  public E getByProerties(String[] propName, Object[] propValue)
  {
    return (E)getByProerties(propName, propValue, null);
  }
  
  public E getByProerties(String propName, Object propValue)
  {
    return (E)getByProerties(new String[] { propName }, new Object[] { propValue });
  }
  
  public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition)
  {
    return (E)getByProerties(new String[] { propName }, new Object[] { propValue }, sortedCondition);
  }
  
  public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top)
  {
    if ((propName != null) && (propValue != null) && (propValue.length == propName.length))
    {
      StringBuffer sb = new StringBuffer("select o from " + this.entityClass.getName() + " o where 1=1 ");
      appendQL(sb, propName, propValue);
      if ((sortedCondition != null) && (sortedCondition.size() > 0))
      {
        sb.append(" order by ");
        Iterator arg6 = sortedCondition.entrySet().iterator();
        while (arg6.hasNext())
        {
          Map.Entry query = (Map.Entry)arg6.next();
          sb.append((String)query.getKey() + " " + (String)query.getValue() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
      }
      Query query1 = getSession().createQuery(sb.toString());
      setParameter(query1, propName, propValue);
      if (top != null)
      {
        query1.setFirstResult(0);
        query1.setMaxResults(top.intValue());
      }
      return query1.list();
    }
    return null;
  }
  
  public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top)
  {
    return queryByProerties(propName, propValue, null, top);
  }
  
  public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition)
  {
    return queryByProerties(propName, propValue, sortedCondition, null);
  }
  
  public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top)
  {
    return queryByProerties(new String[] { propName }, new Object[] { propValue }, sortedCondition, top);
  }
  
  public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition)
  {
    return queryByProerties(new String[] { propName }, new Object[] { propValue }, 
      sortedCondition, null);
  }
  
  public List<E> queryByProerties(String propName, Object propValue, Integer top)
  {
    return queryByProerties(new String[] { propName }, new Object[] { propValue }, 
      null, top);
  }
  
  public List<E> queryByProerties(String[] propName, Object[] propValue)
  {
    return queryByProerties(propName, propValue, null, null);
  }
  
  public List<E> queryByProerties(String propName, Object propValue)
  {
    return queryByProerties(new String[] { propName }, new Object[] { propValue }, 
      null, null);
  }
  
  public Long countAll()
  {
    return (Long)getSession().createQuery("select count(*) from " + this.entityClass.getName())
      .uniqueResult();
  }
  
  public void clear()
  {
    getSession().clear();
  }
  
  public void evict(E entity)
  {
    getSession().evict(entity);
  }
  
  public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top)
  {
    Criteria criteria = getSession().createCriteria(this.entityClass);
    if ((sortedCondition != null) && (sortedCondition.size() > 0))
    {
      Iterator it = sortedCondition.keySet().iterator();
      while (it.hasNext())
      {
        String pm = (String)it.next();
        if ("DESC".equals(sortedCondition.get(pm))) {
          criteria.addOrder(Order.desc(pm));
        } else if ("ASC".equals(sortedCondition.get(pm))) {
          criteria.addOrder(Order.asc(pm));
        }
      }
    }
    if (top != null)
    {
      criteria.setMaxResults(top.intValue());
      criteria.setFirstResult(0);
    }
    return criteria.list();
  }
  
  public List<E> doQueryAll()
  {
    return doQueryAll(null, null);
  }
  
  public List<E> doQueryAll(Integer top)
  {
    return doQueryAll(null, top);
  }
  
  public Long doCount(BaseParameter param)
  {
    if (param == null) {
      return null;
    }
    Criteria criteria = getSession().createCriteria(this.entityClass);
    processQuery(criteria, param);
    try
    {
      criteria.setProjection(Projections.rowCount());
      return Long.valueOf(((Number)criteria.uniqueResult()).longValue());
    }
    catch (Exception arg3)
    {
      arg3.printStackTrace();
    }
    return null;
  }
  
  public List<E> doQuery(BaseParameter param)
  {
    if (param == null) {
      return null;
    }
    Criteria criteria = getSession().createCriteria(this.entityClass);
    processQuery(criteria, param);
    try
    {
      if ((param.getSortedConditions() != null) && (param.getSortedConditions().size() > 0))
      {
        Map e = param.getSortedConditions();
        Iterator it = param.getSortedConditions().keySet().iterator();
        while (it.hasNext())
        {
          String pm = (String)it.next();
          if ("DESC".equals(e.get(pm))) {
            criteria.addOrder(Order.desc(pm));
          } else if ("ASC".equals(e.get(pm))) {
            criteria.addOrder(Order.asc(pm));
          }
        }
      }
      return criteria.list();
    }
    catch (Exception arg5)
    {
      arg5.printStackTrace();
    }
    return null;
  }
  
  public QueryResult<E> doPaginationQuery(BaseParameter param)
  {
    return doPaginationQuery(param, true);
  }
  
  public QueryResult<E> doPaginationQuery(BaseParameter param, boolean bool)
  {
    if (param == null) {
      return null;
    }
    Criteria criteria = getSession().createCriteria(this.entityClass);
    if (bool) {
      processQuery(criteria, param);
    } else {
      extendprocessQuery(criteria, param);
    }
    try
    {
      QueryResult e = new QueryResult();
      criteria.setProjection(Projections.rowCount());
      e.setTotalCount(Long.valueOf(((Number)criteria.uniqueResult()).longValue()));
      if (e.getTotalCount().longValue() > 0L)
      {
        if ((param.getSortedConditions() != null) && (param.getSortedConditions().size() > 0))
        {
          Map map = param.getSortedConditions();
          Iterator it = param.getSortedConditions().keySet().iterator();
          while (it.hasNext())
          {
            String pm = (String)it.next();
            if ("DESC".equalsIgnoreCase((String)map.get(pm))) {
              criteria.addOrder(Order.desc(pm));
            } else if ("ASC".equalsIgnoreCase((String)map.get(pm))) {
              criteria.addOrder(Order.asc(pm));
            }
          }
        }
        criteria.setProjection(null);
        criteria.setMaxResults(param.getMaxResults().intValue());
        criteria.setFirstResult(param.getFirstResult().intValue());
        e.setResultList(criteria.list());
      }
      else
      {
        e.setResultList(new ArrayList());
      }
      return e;
    }
    catch (Exception arg7)
    {
      arg7.printStackTrace();
    }
    return null;
  }
  
  private void appendQL(StringBuffer sb, String[] propName, Object[] propValue)
  {
    for (int i = 0; i < propName.length; i++)
    {
      String name = propName[i];
      Object value = propValue[i];
      if ((!(value instanceof Object[])) && (!(value instanceof Collection)))
      {
        if (value == null) {
          sb.append(" and o." + name + " is null ");
        } else {
          sb.append(" and o." + name + "=:" + name.replace(".", ""));
        }
      }
      else
      {
        Object[] arraySerializable = (Object[])value;
        if ((arraySerializable != null) && (arraySerializable.length > 0)) {
          sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
        }
      }
    }
  }
  
  private void setParameter(Query query, String[] propName, Object[] propValue)
  {
    for (int i = 0; i < propName.length; i++)
    {
      String name = propName[i];
      Object value = propValue[i];
      if (value != null) {
        if ((value instanceof Object[])) {
          query.setParameterList(name.replace(".", ""), (Object[])value);
        } else if ((value instanceof Collection)) {
          query.setParameterList(name.replace(".", ""), (Collection)value);
        } else {
          query.setParameter(name.replace(".", ""), value);
        }
      }
    }
  }
  
  protected void buildSorted(BaseParameter param, StringBuffer hql)
  {
    if ((param.getSortedConditions() != null) && (param.getSortedConditions().size() > 0))
    {
      hql.append(" order by ");
      Map sorted = param.getSortedConditions();
      Iterator it = sorted.keySet().iterator();
      while (it.hasNext())
      {
        String col = (String)it.next();
        hql.append(col + " " + (String)sorted.get(col) + ",");
      }
      hql.deleteCharAt(hql.length() - 1);
    }
  }
  
  private String transferColumn(String queryCondition)
  {
    return queryCondition.substring(queryCondition.indexOf('_', 1) + 1);
  }
  
  protected void setParameter(Map<String, Object> mapParameter, Query query)
  {
    Iterator it = mapParameter.keySet().iterator();
    while (it.hasNext())
    {
      String parameterName = (String)it.next();
      Object value = mapParameter.get(parameterName);
      query.setParameter(parameterName, value);
    }
  }
  
  protected Map handlerConditions(BaseParameter param)
    throws Exception
  {
    Map staticConditions = core.util.BeanUtils.describe(param);
    Map dynamicConditions = param.getQueryDynamicConditions();
    if (dynamicConditions.size() > 0)
    {
      Iterator it = staticConditions.keySet().iterator();
      while (it.hasNext())
      {
        String key = (String)it.next();
        Object value = staticConditions.get(key);
        if ((key.startsWith("$")) && (value != null) && (!"".equals(value))) {
          dynamicConditions.put(key, value);
        }
      }
      staticConditions = dynamicConditions;
    }
    return staticConditions;
  }
  
  private Method getMethod(String name)
  {
    if (!MAP_METHOD.containsKey(name))
    {
      Class clazz = Restrictions.class;
      Class[] paramType = { String.class, Object.class };
      Class[] likeParamType = { String.class, String.class, MatchMode.class };
      Class[] isNullType = { String.class };
      try
      {
        Method e = null;
        if ("like".equals(name)) {
          e = clazz.getMethod(name, likeParamType);
        } else if ("isNull".equals(name)) {
          e = clazz.getMethod(name, isNullType);
        } else {
          e = clazz.getMethod(name, paramType);
        }
        MAP_METHOD.put(name, e);
      }
      catch (Exception arg6)
      {
        arg6.printStackTrace();
      }
    }
    return (Method)MAP_METHOD.get(name);
  }
  
  private Method getExtendMethod(String name)
  {
    if (!MAP_METHOD.containsKey(name))
    {
      Class clazz = Restrictions.class;
      Class[] paramType = { String.class, Object.class };
      Class[] likeParamType = { String.class, String.class, MatchMode.class };
      Class[] isNullType = { String.class };
      try
      {
        Method e = null;
        if ("like".equals(name)) {
          e = clazz.getMethod(name, likeParamType);
        } else if ("isNull".equals(name)) {
          e = clazz.getMethod(name, isNullType);
        } else if (!"IN".equals(name.toUpperCase())) {
          e = clazz.getMethod(name, paramType);
        }
        MAP_METHOD.put(name, e);
      }
      catch (Exception arg6)
      {
        arg6.printStackTrace();
      }
    }
    return (Method)MAP_METHOD.get(name);
  }
  
  private String getOpt(String value)
  {
    return value.substring(0, value.indexOf('_', 1)).substring(1);
  }
  
  private String getPropName(String value)
  {
    return value.substring(value.indexOf('_', 1) + 1);
  }
  
  private void processQuery(Criteria criteria, BaseParameter param)
  {
    try
    {
      Map e = core.util.BeanUtils.describeAvailableParameter(param);
      Map dynamicConditionMap = param.getQueryDynamicConditions();
      Disjunction disjunction = Restrictions.disjunction();
      if ((e != null) && (e.size() > 0))
      {
        Iterator map = e.entrySet().iterator();
        while (map.hasNext())
        {
          Map.Entry bean = (Map.Entry)map.next();
          Object e1 = bean.getValue();
          if ((e1 != null) && (
            (!(e1 instanceof String)) || (!"".equals((String)e1))))
          {
            String prop = getPropName((String)bean.getKey());
            String pn = getOpt((String)bean.getKey());
            Method prop1 = getMethod(pn);
            if ("like".equals(pn))
            {
              if (param.getFlag().equals("OR")) {
                criteria.add(disjunction.add((Criterion)prop1.invoke(Restrictions.class, 
                  new Object[] { prop, e1, MatchMode.ANYWHERE })));
              } else {
                criteria.add((Criterion)prop1.invoke(Restrictions.class, 
                  new Object[] { prop, e1, MatchMode.ANYWHERE }));
              }
            }
            else if (("isNull".equals(pn)) && ((e1 instanceof Boolean)))
            {
              if (((Boolean)e1).booleanValue())
              {
                if (param.getFlag().equals("OR")) {
                  criteria.add(disjunction.add(Restrictions.isNull(prop)));
                } else {
                  criteria.add(Restrictions.isNull(prop));
                }
              }
              else if (param.getFlag().equals("OR")) {
                criteria.add(disjunction.add(Restrictions.isNotNull(prop)));
              } else {
                criteria.add(Restrictions.isNotNull(prop));
              }
            }
            else if (param.getFlag().equals("OR")) {
              criteria.add(disjunction
                .add((Criterion)prop1.invoke(Restrictions.class, new Object[] { prop, e1 })));
            } else {
              criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[] { prop, e1 }));
            }
          }
        }
      }
      if ((dynamicConditionMap != null) && (dynamicConditionMap.size() > 0))
      {
        Object bean1 = this.entityClass.newInstance();
        HashMap map1 = new HashMap();
        Iterator prop2 = dynamicConditionMap.entrySet().iterator();
        while (prop2.hasNext())
        {
          Map.Entry e2 = (Map.Entry)prop2.next();
          map1.put(getPropName((String)e2.getKey()), e2.getValue());
        }
        org.apache.commons.beanutils.BeanUtils.populate(bean1, map1);
        prop2 = dynamicConditionMap.entrySet().iterator();
        for (;;)
        {
          if (!prop2.hasNext()) {
            return;
          }
          Map.Entry e2 = (Map.Entry)prop2.next();
          String pn = (String)e2.getKey();
          String prop3 = getPropName(pn);
          String methodName = getOpt(pn);
          Method m = getMethod(methodName);
          Object value = PropertyUtils.getNestedProperty(bean1, prop3);
          if ((value != null) && (
            (!(value instanceof String)) || (!"".equals((String)value)))) {
            if ("like".equals(methodName))
            {
              if (param.getFlag().equals("OR")) {
                criteria.add(disjunction.add((Criterion)m.invoke(Restrictions.class, 
                  new Object[] { prop3, value, MatchMode.ANYWHERE })));
              } else {
                criteria.add((Criterion)m.invoke(Restrictions.class, 
                  new Object[] { prop3, value, MatchMode.ANYWHERE }));
              }
            }
            else if (("isNull".equals(methodName)) && ((value instanceof Boolean)))
            {
              if (((Boolean)value).booleanValue())
              {
                if (param.getFlag().equals("OR")) {
                  criteria.add(disjunction.add(Restrictions.isNull(prop3)));
                } else {
                  criteria.add(Restrictions.isNull(prop3));
                }
              }
              else if (param.getFlag().equals("OR")) {
                criteria.add(disjunction.add(Restrictions.isNotNull(prop3)));
              } else {
                criteria.add(Restrictions.isNotNull(prop3));
              }
            }
            else if (param.getFlag().equals("OR")) {
              criteria.add(disjunction
                .add((Criterion)m.invoke(Restrictions.class, new Object[] { prop3, value })));
            } else {
              criteria.add((Criterion)m.invoke(Restrictions.class, new Object[] { prop3, value }));
            }
          }
        }
      }
      return;
    }
    catch (Exception arg14)
    {
      arg14.printStackTrace();
    }
  }
  
  private void extendprocessQuery(Criteria criteria, BaseParameter param)
  {
    try
    {
      Map e = core.util.BeanUtils.describeAvailableParameter(param);
      Map dynamicConditionMap = param.getQueryDynamicConditions();
      if ((e != null) && (e.size() > 0))
      {
        Iterator map = e.entrySet().iterator();
        while (map.hasNext())
        {
          Map.Entry bean = (Map.Entry)map.next();
          Object e1 = bean.getValue();
          if ((e1 != null) && (
            (!(e1 instanceof String)) || (!"".equals((String)e1))))
          {
            String prop = getPropName((String)bean.getKey());
            String pn = getOpt((String)bean.getKey());
            Method prop1 = getExtendMethod(pn);
            if ("like".equals(pn))
            {
              criteria.add((Criterion)prop1.invoke(Restrictions.class, 
                new Object[] { prop, e1, MatchMode.ANYWHERE }));
            }
            else if (("isNull".equals(pn)) && ((e1 instanceof Boolean)))
            {
              if (((Boolean)e1).booleanValue()) {
                criteria.add(Restrictions.isNull(prop));
              } else {
                criteria.add(Restrictions.isNotNull(prop));
              }
            }
            else if ((e1 != null) && ((e1 instanceof Object[])) && ("IN".equals(pn.toUpperCase())))
            {
              Object[] methodName = (Object[])e1;
              criteria.add(Restrictions.in(prop, methodName));
            }
            else
            {
              criteria.add((Criterion)prop1.invoke(Restrictions.class, new Object[] { prop, e1 }));
            }
          }
        }
      }
      if ((dynamicConditionMap != null) && (dynamicConditionMap.size() > 0))
      {
        Object bean1 = this.entityClass.newInstance();
        HashMap map1 = new HashMap();
        Iterator prop2 = dynamicConditionMap.entrySet().iterator();
        while (prop2.hasNext())
        {
          Map.Entry e2 = (Map.Entry)prop2.next();
          map1.put(getPropName((String)e2.getKey()), e2.getValue());
        }
        org.apache.commons.beanutils.BeanUtils.populate(bean1, map1);
        prop2 = dynamicConditionMap.entrySet().iterator();
        for (;;)
        {
          if (!prop2.hasNext()) {
            return;
          }
          Map.Entry e2 = (Map.Entry)prop2.next();
          String pn = (String)e2.getKey();
          String prop3 = getPropName(pn);
          String methodName1 = getOpt(pn);
          Method m = getMethod(methodName1);
          Object value = PropertyUtils.getNestedProperty(bean1, prop3);
          if ((value != null) && (
            (!(value instanceof String)) || (!"".equals((String)value)))) {
            if ("like".equals(methodName1)) {
              criteria.add((Criterion)m.invoke(Restrictions.class, 
                new Object[] { prop3, value, MatchMode.ANYWHERE }));
            } else if (("isNull".equals(methodName1)) && ((value instanceof Boolean)))
            {
              if (((Boolean)value).booleanValue()) {
                criteria.add(Restrictions.isNull(prop3));
              } else {
                criteria.add(Restrictions.isNotNull(prop3));
              }
            }
            else {
              criteria.add((Criterion)m.invoke(Restrictions.class, new Object[] { prop3, value }));
            }
          }
        }
      }
      return;
    }
    catch (Exception arg13)
    {
      arg13.printStackTrace();
    }
  }
}
