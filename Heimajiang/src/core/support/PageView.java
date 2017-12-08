package core.support;

import java.util.List;

public class PageView<E>
{
  private List<E> records;
  private long totalPage = 1L;
  private int maxResult = 12;
  private int currentPage = 0;
  private long totalRecord;
  private int pageCode = 10;
  
  public PageView(int maxResult, int currentPage)
  {
    this.maxResult = maxResult;
    this.currentPage = currentPage;
  }
  
  public int getPageCode()
  {
    return this.pageCode;
  }
  
  public void setPagecode(int pageCode)
  {
    this.pageCode = pageCode;
  }
  
  public void setQueryResult(QueryResult<E> qr)
  {
    setRecords(qr.getResultList());
    setTotalRecord(qr.getTotalCount().longValue());
  }
  
  public long getTotalRecord()
  {
    return this.totalRecord;
  }
  
  public void setTotalRecord(long totalRecord)
  {
    this.totalRecord = totalRecord;
    setTotalPage(this.totalRecord % this.maxResult == 0L ? this.totalRecord / this.maxResult : this.totalRecord / this.maxResult + 1L);
  }
  
  public List<E> getRecords()
  {
    return this.records;
  }
  
  public void setRecords(List<E> records)
  {
    this.records = records;
  }
  
  public long getTotalPage()
  {
    return this.totalPage;
  }
  
  public void setTotalPage(long totalpage)
  {
    this.totalPage = totalpage;
  }
  
  public int getMaxResult()
  {
    return this.maxResult;
  }
  
  public int getCurrentPage()
  {
    return this.currentPage;
  }
  
  public int getFirstResult()
  {
    return (this.currentPage - 1) * this.maxResult;
  }
}
