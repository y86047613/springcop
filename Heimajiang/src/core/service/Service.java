package core.service;

import core.support.BaseParameter;
import core.support.QueryResult;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface Service<E>
{
  public abstract void persist(E paramE);
  
  public abstract boolean deleteByPK(Serializable... paramVarArgs);
  
  public abstract void delete(E paramE);
  
  public abstract void deleteByProperties(String paramString, Object paramObject);
  
  public abstract void deleteByProperties(String[] paramArrayOfString, Object[] paramArrayOfObject);
  
  public abstract void update(E paramE);
  
  public abstract void updateByProperties(String[] paramArrayOfString1, Object[] paramArrayOfObject1, String[] paramArrayOfString2, Object[] paramArrayOfObject2);
  
  public abstract void updateByProperties(String[] paramArrayOfString, Object[] paramArrayOfObject, String paramString, Object paramObject);
  
  public abstract void updateByProperties(String paramString, Object paramObject, String[] paramArrayOfString, Object[] paramArrayOfObject);
  
  public abstract void updateByProperties(String paramString1, Object paramObject1, String paramString2, Object paramObject2);
  
  public abstract void update(E paramE, Serializable paramSerializable);
  
  public abstract E merge(E paramE);
  
  public abstract E get(Serializable paramSerializable);
  
  public abstract E load(Serializable paramSerializable);
  
  public abstract E getByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject);
  
  public abstract E getByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject, Map<String, String> paramMap);
  
  public abstract E getByProerties(String paramString, Object paramObject);
  
  public abstract E getByProerties(String paramString, Object paramObject, Map<String, String> paramMap);
  
  public abstract List<E> queryByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject, Map<String, String> paramMap, Integer paramInteger);
  
  public abstract List<E> queryByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject, Map<String, String> paramMap);
  
  public abstract List<E> queryByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject, Integer paramInteger);
  
  public abstract List<E> queryByProerties(String[] paramArrayOfString, Object[] paramArrayOfObject);
  
  public abstract List<E> queryByProerties(String paramString, Object paramObject, Map<String, String> paramMap, Integer paramInteger);
  
  public abstract List<E> queryByProerties(String paramString, Object paramObject, Map<String, String> paramMap);
  
  public abstract List<E> queryByProerties(String paramString, Object paramObject, Integer paramInteger);
  
  public abstract List<E> queryByProerties(String paramString, Object paramObject);
  
  public abstract void clear();
  
  public abstract void evict(E paramE);
  
  public abstract Long countAll();
  
  public abstract List<E> doQueryAll();
  
  public abstract List<E> doQueryAll(Map<String, String> paramMap, Integer paramInteger);
  
  public abstract List<E> doQueryAll(Integer paramInteger);
  
  public abstract Long doCount(BaseParameter paramBaseParameter);
  
  public abstract List<E> doQuery(BaseParameter paramBaseParameter);
  
  public abstract QueryResult<E> doPaginationQuery(BaseParameter paramBaseParameter);
  
  public abstract QueryResult<E> doPaginationQuery(BaseParameter paramBaseParameter, boolean paramBoolean);
}
