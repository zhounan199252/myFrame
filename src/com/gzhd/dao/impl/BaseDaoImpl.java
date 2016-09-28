
package com.gzhd.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.gzhd.dao.itf.BaseDao;

/**
 * @Description:基础dao类，基本上已经实现了所有有可能使用到的操作数据库的方法
 * @time:2015年7月24日 下午2:42:22
 */
@SuppressWarnings("unchecked")
@Repository(BaseDao.BEAN_NAME)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	private Session getMyCurrentSession() {

		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {

		return getMyCurrentSession().save(o);
	}

	@Override
	public T get(String hql) {

		Query query = getMyCurrentSession().createQuery(hql);

		List<T> list = (List<T>) query.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public T get(Class<T> c, Serializable id) {

		return (T) getMyCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}

			/*
			 * for (Entry<String, Object> entry : params.entrySet()) {
			 * query.setParameter(entry.getKey(), entry.getValue()); }
			 */
		}

		List<T> list = (List<T>) query.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public void delete(T o) {

		getMyCurrentSession().delete(o);

	}

	@Override
	public void update(T o) {

		getMyCurrentSession().update(o);

	}

	@Override
	public void saveOrUpdate(T o) {

		getMyCurrentSession().saveOrUpdate(o);

	}

	@Override
	public List<T> find(String hql) {

		Query query = getMyCurrentSession().createQuery(hql);

		return query.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int pageNum, int pageSize) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public List<T> find(String hql, int pageNum, int pageSize) {

		Query query = getMyCurrentSession().createQuery(hql);

		return query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long count(String hql) {

		Query query = getMyCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return (Long) query.uniqueResult();

	}

	@Override
	public List<?> createQuery(String hql) {

		Query query = getMyCurrentSession().createQuery(hql);

		return query.list();
	}

	@Override
	public List<?> createQuery(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.list();
	}

	@Override
	public List<?> findWithCacheable(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		
		query.setCacheable(true);    //使用查询缓存
		
		return query.list();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {

		Query query = getMyCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		
		int executeUpdateCount = query.executeUpdate();
		
		return executeUpdateCount;
	}


}
