package com.gzhd.dao.itf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description:基础dao类，基本上已经实现了所有有可能使用到的操作数据库的方法，所有service类都可以从整个类中获取方法操作数据库，并不需要各自实现
 * @time:2015年7月24日 下午2:45:29
 */
public interface BaseDao<T> {
	/**
	 * 使用静态常量作为bean名称，以防调用时出错和同名
	 */
	public static final String BEAN_NAME = "com.gzhd.dao.itf.BaseDao";
	
	/**
	 * @Description:保存一个对象，并返回该对象在数据库中的id
	 * @author: 黄威生
	 * @param o
	 * @return
	 * @return:Serializable
	 * @exception:
	 * @time:2015年7月24日 下午2:46:45
	 */
	public Serializable save(T o);
	
	
	/**
	 * @Description:根据hql返回唯一的对象
	 * @author: 黄威生
	 * @param hql
	 * @return
	 * @return:T
	 * @exception:
	 * @time:2015年7月24日 下午2:47:17
	 */
	public T get(String hql);
	
	
	/**
	 * @Description:根据id获得一个对象
	 * @author: 黄威生
	 * @param c
	 * @param id
	 * @return
	 * @return:T
	 * @exception:
	 * @time:2015年7月24日 下午2:47:57
	 */
	public T get(Class<T> c, Serializable id);
	
	
	/**
	 * @Description:根据hql及给定的参数获得一个对象
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @return
	 * @return:T
	 * @exception:
	 * @time:2015年7月24日 下午2:48:14
	 */
	public T get(String hql, Map<String, Object> params);   //推荐使用
	
	
	/**
	 * @Description: 删除一个对象
	 * @author: 黄威生
	 * @param o
	 * @return:void
	 * @exception:
	 * @time:2015年7月24日 下午2:48:42
	 */
	public void delete(T o);
	
	
	/**
	 * @Description:更新一个对象
	 * @author: 黄威生
	 * @param o
	 * @return:void
	 * @exception:
	 * @time:2015年7月24日 下午2:49:00
	 */
	public void update(T o);
	
	
	/**
	 * @Description:保存或更新一个对象
	 * @author: 黄威生
	 * @param o
	 * @return:void
	 * @exception:
	 * @time:2015年7月24日 下午2:49:16
	 */
	public void saveOrUpdate(T o);
	
	
	/**
	 * @Description:根据hql查询返回一系列对象
	 * @author: 黄威生
	 * @param hql
	 * @return
	 * @return:List<T>
	 * @exception:
	 * @time:2015年7月24日 下午2:49:39
	 */
	public List<T> find(String hql);
	
	
	/**
	 * @Description:根据hql及给定的参数查询返回一系列对象
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @return
	 * @return:List<T>
	 * @exception:
	 * @time:2015年7月24日 下午2:50:21
	 */
	public List<T> find(String hql, Map<String, Object> params);
	
	/**
	 * @Description:根据hql及给定的参数查询返回一系列对象,使用了查询缓存，慎用，不同的场景会带来不同的后果，有可能给服务器带来更加大的压力
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @return
	 * @return:List<T>
	 * @exception:
	 * @time:2015年7月24日 下午2:50:21
	 */
	public List<?> findWithCacheable(String hql, Map<String, Object> params);
	
	/**
	 * @Description:带参数的分页查询
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @return:List<T>
	 * @exception:
	 * @time:2015年7月24日 下午2:51:26
	 */
	public List<T> find(String hql, Map<String, Object> params, int pageNum, int pageSize);   //有参数的分页查询
	
	
	/**
	 * @Description:不带参数的分页查询
	 * @author: 黄威生
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @return:List<T>
	 * @exception:
	 * @time:2015年7月24日 下午2:52:38
	 */
	public List<T> find(String hql, int pageNum, int pageSize);   //无参数的分页
	
	
	/**
	 * @Description:不带参数的统计总记录数
	 * @author: 黄威生
	 * @param hql
	 * @return
	 * @return:Long
	 * @exception:
	 * @time:2015年7月24日 下午2:53:11
	 */
	public Long count(String hql);  //无参数的统计总记录数
	
	
	/**
	 * @Description:带参数的统计总记录数
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @return
	 * @return:Long
	 * @exception:
	 * @time:2015年7月24日 下午2:53:29
	 */
	public Long count(String hql, Map<String, Object> params);  //有参数时统计总记录数
	

	/**
	 * @Description: 根据hql查询返回List类型的参数
	 * @author: 黄威生
	 * @param hql
	 * @return
	 * @return:List<String>
	 * @exception:
	 * @time:2015年8月4日 上午10:28:10
	 */
	public List<?> createQuery(String hql);
	
	/**
	 * @Description:dai
	 * @author: 黄威生
	 * @param hql
	 * @param params
	 * @return
	 * @return:List<String>
	 * @exception:
	 * @time:2015年8月4日 上午10:28:41
	 */
	public List<?> createQuery(String hql, Map<String, Object> params);
	
	
	/**
	 * @Description:执行hql
	 * @author: 黄威生
	 * @param sql
	 * @param params
	 * @return
	 * @return:int
	 * @exception:
	 * @time:2015年8月21日 上午11:50:17
	 */
	public int executeHql(String hql, Map<String, Object> params);


}















