package com.durgshop.service;

import java.util.List;

import com.durgshop.entity.Pager;

/**
 * 
 * @author netboy
 *
 * @param <T>
 */
public interface BaseService<T> {
	/**
	 * 通用的新增方法
	 * 
	 * @param object
	 * @return
	 */
	public abstract boolean add(T object);

	/**
	 * 通用的修改方法
	 * 
	 * @param object
	 * @return
	 */
	public boolean edit(T object);

	/**
	 * 通用的删除方法
	 * 
	 * @param object
	 * @return
	 */
	public boolean delete(T object);

	/**
	 * 带条件查询的分页类
	 * 
	 * @param pager
	 * @return
	 */
	public List<T> findByPager(Pager<T> pager);
	
	/**
	 * 统计查询条件下的记录总数
	 * @param pager
	 * @return
	 */
	public Integer findTotalByPager(Pager<T> pager);
}
