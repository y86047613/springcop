package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.AttachmentDao;
import com.jeefw.model.sys.Attachment;
import core.dao.BaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AttachmentDaoImpl
  extends BaseDao<Attachment>
  implements AttachmentDao
{
  public AttachmentDaoImpl()
  {
    super(Attachment.class);
  }
  
  public List<Object[]> queryFlowerList(String epcId)
  {
    Query query = getSession().createSQLQuery(
      "select ft.name,group_concat(a.file_path),ft.description,f.epc_id from um_type ft inner join jeefw f on ft.id=f.type_id left join attachment a on a.umtype_id=ft.id where f.epc_id=? group by a.file_name");
    query.setParameter(0, epcId);
    return query.list();
  }
  
  public void deleteAttachmentByForestryTypeId(Long umTypeId)
  {
    Query query = getSession().createSQLQuery("delete from attachment where umtype_id = :umTypeId");
    query.setParameter("umTypeId", umTypeId);
    query.executeUpdate();
  }
  
  public List<Object[]> querySensorList()
  {
    SQLQuery query = 
      getSession()
      .createSQLQuery(
      "select s.sensor_id,s.xcoordinate,s.ycoordinate,f.name,ft.description from sensor s,jeefw f,um_type ft where s.epc_id = f.epc_id and f.type_id = ft.id and s.type = 1 and s.xcoordinate is not null and s.ycoordinate is not null");
    return query.list();
  }
}
