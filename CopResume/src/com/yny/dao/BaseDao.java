package com.yny.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yny.entity.Pagination;
import com.yny.util.HibernateHandler;

public interface BaseDao<T>  {
	/**  
     * 淇濆瓨瀹炰綋  
     *   
     * @param entity  
     *            瀹炰綋瀵硅薄  
     * @return 瀹炰綋涓婚敭  
     */  
    Object save(Object entity);  
  
    /**  
     *   
     * 鍒犻櫎瀹炰綋  
     *   
     * @param entity  
     *            瀹炰綋瀵硅薄  
     *   
     */  
    void delete(Object entity);  
  
    /**  
     *   
     * 鏇存柊瀹炰綋  
     *   
     * @param entity  
     *            瀹炰綋瀵硅薄  
     *   
     */  
    void update(Object entity);  
  
    /**  
     *   
     * 淇濆瓨鎴栨洿鏂板疄浣�, 瀹炰綋娌℃湁涓婚敭鏃朵繚瀛橈紝鍚﹀垯鏇存柊  
     *   
     * @param entity  
     *            瀹炰綋瀵硅薄  
     *   
     */  
    void saveOrUpdate(Object entity);  
  
    /**  
     *   
     * 鎵归噺淇濆瓨瀹炰綋  
     *   
     * @param entities  
     *            瀹炰綋闆嗗悎  
     */  
    void saveAll(Collection<?> entities);  
  
    /**  
     *   
     * 鎵归噺鍒犻櫎瀹炰綋  
     *   
     * @param entities  
     *            瀹炰綋闆嗗悎  
     *   
     */  
    void deleteAll(Collection<?> entities);  
  
    /**  
     *   
     * 鎵归噺鏇存柊瀹炰綋  
     *   
     * @param entities  
     *            瀹炰綋闆嗗悎  
     *   
     */  
    void updateAll(Collection<?> entity);  
  
    /**  
     *   
     * 鎵归噺淇濆瓨鎴栨洿鏂板疄浣�, 瀹炰綋娌℃湁涓婚敭鏃朵繚瀛橈紝鍚﹀垯鏇存柊  
     *   
     * @param entity  
     *            瀹炰綋闆嗗悎  
     *   
     */  
    void saveOrUpdateAll(Collection<?> entities);  
  
    /**  
     *   
     * 鑾峰彇鍗曚釜瀹炰綋锛屾牴鎹疄浣撶被鍙婂疄浣撶殑涓婚敭鑾峰彇銆�  
     *   
     * @param entityClass  
     *            瀹炰綋绫�  
     * @param id  
     *            瀹炰綋涓婚敭  
     * @return 瀹炰綋瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    <T> T get(Class<T> entityClass, Serializable id);  
  
    /**  
     * 鑾峰彇鍗曚釜瀹炰綋锛屾牴鎹煡璇㈣鍙ュ強鍙傛暟鑾峰彇銆�  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 鍗曚釜瀹炰綋锛屽鏋滄煡璇㈢粨鏋滄湁澶氫釜锛屽垯杩斿洖绗竴涓疄浣�  
     */  
    @SuppressWarnings("hiding")  
    <T> T get(CharSequence queryString, Map<String, Object> params);  
  
    /**  
     * 鑾峰彇鍗曚釜瀹炰綋锛屾牴鎹煡璇㈣鍙ュ強鍙傛暟鑾峰彇銆�  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 鍗曚釜瀹炰綋锛屽鏋滄煡璇㈢粨鏋滄湁澶氫釜锛屽垯杩斿洖绗竴涓疄浣�  
     */  
    @SuppressWarnings("hiding")  
    <T> T get(CharSequence queryString, Object... params);  
  
    /**  
     *   
     * 鏌ヨ瀹炰綋鍒楄〃  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒楄〃  
     */  
    @SuppressWarnings("hiding")  
    <T> List<T> findList(CharSequence queryString, Object... params);  
  
    /**  
     *   
     * 鏌ヨ瀹炰綋鍒楄〃  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒楄〃  
     */  
    @SuppressWarnings("hiding")  
    <T> List<T> findList(CharSequence queryString, Map<String, Object> params);  
  
    /**  
     * 鍒嗛〉鏌ヨ瀹炰綋  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param pageIndex  
     *            褰撳墠椤电爜锛屽鏋減ageIndex<1鍒欎笉鍒嗛〉锛屼笖杩斿洖pageSize鏉¤褰曘��  
     * @param pageSize  
     *            姣忛〉璁板綍鏁帮紝濡傛灉pageSize<1鍒欒繑鍥炴墍鏈夎褰曘��  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒嗛〉瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    <T> Pagination<T> findPagination(CharSequence queryString, int pageIndex, int pageSize, Object... params);  
  
    /**  
     * 鍒嗛〉鏌ヨ瀹炰綋  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @param pageIndex  
     *            褰撳墠椤电爜锛屽鏋減ageIndex<2鍒欎笉鍒嗛〉锛屼笖杩斿洖pageSize鏉¤褰曘��  
     * @param pageSize  
     *            姣忛〉璁板綍鏁帮紝濡傛灉pageSize<1鍒欒繑鍥炴墍鏈夎褰曘��  
     *   
     * @return 瀹炰綋鍒嗛〉瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    <T> Pagination<T> findPagination(CharSequence queryString, Map<String, Object> params, int pageIndex, int pageSize);  
  
    /**  
     * 鍒嗛〉鏌ヨ瀹炰綋锛岃嚜瀹氫箟鎬绘潯鏁版煡璇㈣鍙ワ紝閫傚悎澶嶆潅鐨刪ql鍒嗛〉鏌ヨ  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param countString  
     *            鏌ヨ璁板綍鎬绘潯鏁拌鍙�  
     * @param pageIndex  
     *            褰撳墠椤电爜锛屽鏋減ageIndex<1鍒欎笉鍒嗛〉锛屼笖杩斿洖pageSize鏉¤褰曘��  
     * @param pageSize  
     *            姣忛〉璁板綍鏁帮紝濡傛灉pageSize<1鍒欒繑鍥炴墍鏈夎褰曘��  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒嗛〉瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    <T> Pagination<T> findPagination(CharSequence queryString, CharSequence countString, int pageIndex, int pageSize,  
            Object... params);  
  
    /**  
     * 鍒嗛〉鏌ヨ瀹炰綋锛岃嚜瀹氫箟鎬绘潯鏁版煡璇㈣鍙ワ紝閫傚悎澶嶆潅鐨刪ql鍒嗛〉鏌ヨ  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param countString  
     *            鏌ヨ璁板綍鎬绘潯鏁拌鍙�  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @param pageIndex  
     *            褰撳墠椤电爜锛屽鏋減ageIndex<2鍒欎笉鍒嗛〉锛屼笖杩斿洖pageSize鏉¤褰曘��  
     * @param pageSize  
     *            姣忛〉璁板綍鏁帮紝濡傛灉pageSize<1鍒欒繑鍥炴墍鏈夎褰曘��  
     *   
     * @return 瀹炰綋鍒嗛〉瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    <T> Pagination<T> findPagination(CharSequence queryString, CharSequence countString, Map<String, Object> params,  
            int pageIndex, int pageSize);  
  
    /**  
     * 鍒嗛〉鏌ヨ瀹炰綋锛岃嚜瀹氫箟鎬绘潯鏁版煡璇㈣鍙ワ紝閫傚悎澶嶆潅鐨剆ql鍒嗛〉鏌ヨ  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param countString  
     *            鏌ヨ璁板綍鎬绘潯鏁拌鍙�  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @param pageIndex  
     *            褰撳墠椤电爜锛屽鏋減ageIndex<2鍒欎笉鍒嗛〉锛屼笖杩斿洖pageSize鏉¤褰曘��  
     * @param pageSize  
     *            姣忛〉璁板綍鏁帮紝濡傛灉pageSize<1鍒欒繑鍥炴墍鏈夎褰曘��  
     *   
     * @return 瀹炰綋鍒嗛〉瀵硅薄  
     */  
    @SuppressWarnings("hiding")  
    public <T> Pagination<T> findSqlPagination(final CharSequence queryString, final CharSequence countString,  
            final Map<String, Object> params, int pageIndex, int pageSize);  
  
    /**  
     * 鎵ц鏁版嵁搴撴煡璇㈡搷浣�  
     *   
     * @param handler  
     *            澶勭悊鍣�  
     * @return  
     * @throws Exception  
     */  
    Object executeQuery(HibernateHandler handler);  
  
    /**  
     * 鎵ц鏁版嵁搴撴洿鏂版搷浣�  
     *   
     * @param sql  
     * @return 鏇存柊鐨勮褰曟潯鏁�  
     */  
    int executeSqlUpdate(String sql);  
  
    /**  
     * 鎵ц鏁版嵁搴撴洿鏂版搷浣�  
     *   
     * @param hql  
     * @return 鏇存柊鐨勮褰曟潯鏁�  
     */  
    int executeUpdate(String hql);  
  
    public T getById(Class<T> clazz,Serializable id);  
  
    public T saveEntity(T o);  
  
    public T insert(T o);  
  
    public void save(List<T> list);  
  
    public void insert(List<T> list);  
  
    public void delete(List<T> list);  
  
    public void update(List<T> list);  
  
    public List<T> findAll(String queryString);
     
  
    /**  
     *   
     * 鏌ヨ瀹炰綋鍒楄〃  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param maxResults  
     *            鍒楄〃鏈�澶ф暟  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒楄〃  
     */  
    public <V> List<V> findListByMax(CharSequence queryString, int maxResults, Object... params);  
  
    /**  
     *   
     * 鏌ヨ瀹炰綋鍒楄〃  
     *   
     * @param queryString  
     *            鏌ヨ璇彞  
     * @param maxResults  
     *            鍒楄〃鏈�澶ф暟  
     * @param params  
     *            鍙�夌殑鏌ヨ鍙傛暟  
     * @return 瀹炰綋鍒楄〃  
     */  
    public <V> List<V> findListByMax(CharSequence queryString, int maxResults, Map<String, Object> params);  
}
