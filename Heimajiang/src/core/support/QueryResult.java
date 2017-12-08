package core.support;

import java.util.List;

public class QueryResult<E>
{
  private List<E> resultList;
  private Long totalCount;
  
  public List<E> getResultList()
  {
    return this.resultList;
  }
  
  public void setResultList(List<E> resultList)
  {
    this.resultList = resultList;
  }
  
  public Long getTotalCount()
  {
    return this.totalCount;
  }
  
  public void setTotalCount(Long totalCount)
  {
    this.totalCount = totalCount;
  }
}
