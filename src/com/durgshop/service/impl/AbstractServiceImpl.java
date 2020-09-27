package com.durgshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.durgshop.dao.BaseDao;
import com.durgshop.entity.Pager;
import com.durgshop.service.BaseService;

/**
 * 
 * @author netboy
 *
 * @param <T>
 */
public abstract class AbstractServiceImpl<T> implements BaseService<T>{
	
	/**
	 * <pre>
	 * 
	 * 使用@Autowired自动织入一个持久层接口
	 * 业务层是通过数据层实现数据的操作；它们间通过接口进行隔离；
	 * 这是面向接口编程，使用接口进行层次间的解耦合,提高系统的可扩展和可维护性 
	 * 
	 * </pre>
	 */
	
	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public boolean add(T object) {
		
		return baseDao.add(object);
	}

	@Override
	public boolean edit(T object) {
		
		return baseDao.edit(object);
	}

	@Override
	public boolean delete(T object) {
		
		return baseDao.delete(object);
	}

	@Override
	public List<T> findByPager(Pager<T> pager) {
		
		return baseDao.findByPager(pager);
	}

	@Override
	public Integer findTotalByPager(Pager<T> pager) {
		
		return baseDao.findTotalByPager(pager);
	}
	
}
