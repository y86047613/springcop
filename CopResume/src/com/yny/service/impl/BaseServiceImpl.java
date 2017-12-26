package com.yny.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yny.dao.BaseDao;
import com.yny.entity.Pagination;
import com.yny.service.BaseService;
//@Transactional
@Service
public class BaseServiceImpl<T> implements BaseService<T> {

	@Resource
	private BaseDao<T> baseDao;
	
	@Override
	public Integer save(Object entity) {
		Integer id = (Integer) baseDao.save(entity);
		return id;
	}

	@Override
	public void delete(Object entity) {
		baseDao.delete(entity);
	}

	@Override
	public void update(Object entity) {
		baseDao.update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveAll(Collection<?> entities) {
		baseDao.saveAll(entities);
	}

	@Override
	public void deleteAll(Collection<?> entities) {
		baseDao.deleteAll(entities);
	}

	@Override
	public void updateAll(Collection<?> entities) {
		baseDao.updateAll(entities);
		
	}

	@Override
	public void saveOrUpdateAll(Collection<?> entities) {
		baseDao.saveOrUpdateAll(entities);
	}

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		T entity = baseDao.get(entityClass, id);
		return entity;
	}

	@Override
	public <T> T get(CharSequence queryString, Map<String, Object> params) {
		T entity = baseDao.get(queryString, params);
		return entity;
	}

	@Override
	public <T> T get(CharSequence queryString, Object... params) {
		T entity = baseDao.get(queryString, params);
		return entity;
	}

	@Override
	public <T> List<T> findList(CharSequence queryString, Object... params) {
		List<T> list = baseDao.findList(queryString, params);
		return list;
	}

	@Override
	public <T> List<T> findList(CharSequence queryString,
			Map<String, Object> params) {
		List<T> list = baseDao.findList(queryString, params);
		return list;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString,
			int pageIndex, int pageSize, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString,
			Map<String, Object> params, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString,
			CharSequence countString, int pageIndex, int pageSize,
			Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString,
			CharSequence countString, Map<String, Object> params,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findSqlPagination(CharSequence queryString,
			CharSequence countString, Map<String, Object> params,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(String queryString) {
		List<T> list = baseDao.findAll(queryString);
		return list;
	}

}
