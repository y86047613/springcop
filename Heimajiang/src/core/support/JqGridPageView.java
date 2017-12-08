package core.support;

import java.util.List;

public class JqGridPageView<E>
{
  private List<E> rows;
  private long total = 1L;
  private int maxResults = 12;
  private int currentPage = 0;
  private long records;
  
  public JqGridPageView() {}
  
  public JqGridPageView(int maxResults, int currentPage)
  {
    this.maxResults = maxResults;
    this.currentPage = currentPage;
  }
  
  public void setQueryResult(QueryResult<E> qr)
  {
    setRows(qr.getResultList());
    setRecords(qr.getTotalCount().longValue());
  }
  
  public long getRecords()
  {
    return this.records;
  }
  
  public void setRecords(long records)
  {
    this.records = records;
    setTotal(this.records % this.maxResults == 0L ? this.records / this.maxResults : this.records / this.maxResults + 1L);
  }
  
  public List<E> getRows()
  {
    return this.rows;
  }
  
  public void setRows(List<E> rows)
  {
    this.rows = rows;
  }
  
  public long getTotal()
  {
    return this.total;
  }
  
  public void setTotal(long total)
  {
    this.total = total;
  }
  
  public int getMaxResults()
  {
    return this.maxResults;
  }
  
  public int getCurrentPage()
  {
    return this.currentPage;
  }
  
  public int getFirstResult()
  {
    return (this.currentPage - 1) * this.maxResults;
  }
  
  public void setMaxResults(int maxResults)
  {
    this.maxResults = maxResults;
  }
  
  public void setCurrentPage(int currentPage)
  {
    this.currentPage = currentPage;
  }
}
