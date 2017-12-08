package core.support;

import java.util.Map;

public class Group
{
  private String name;
  private Map<String, Item> items;
  
  public Group(String name, Map<String, Item> items)
  {
    this.name = name;
    this.items = items;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Map<String, Item> getItems()
  {
    return this.items;
  }
  
  public void setItems(Map<String, Item> items)
  {
    this.items = items;
  }
}
