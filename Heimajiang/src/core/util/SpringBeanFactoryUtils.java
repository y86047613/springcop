package core.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringBeanFactoryUtils
  implements BeanFactoryAware
{
  private static BeanFactory beanFactory = null;
  private static SpringBeanFactoryUtils factoryUtils = null;
  
  public void setBeanFactory(BeanFactory beanFactory)
    throws BeansException
  {
    beanFactory = beanFactory;
  }
  
  public static BeanFactory getBeanFactory()
  {
    return beanFactory;
  }
  
  public static SpringBeanFactoryUtils getInstance()
  {
    if (factoryUtils == null) {
      factoryUtils = new SpringBeanFactoryUtils();
    }
    return factoryUtils;
  }
  
  public static Object getBean(String name)
  {
    return beanFactory.getBean(name);
  }
  
  public static Connection getScsConnection()
    throws Exception
  {
    DataSource dataSource = (DataSource)getBean("jeefwDataSource");
    Connection con = null;
    try
    {
      con = dataSource.getConnection();
    }
    catch (SQLException e)
    {
      throw e;
    }
    return con;
  }
}
