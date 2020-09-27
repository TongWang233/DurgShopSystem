package com.durgshop.dao;

import java.util.List;

import com.durgshop.entity.Pager;

/**
 * <pre>
 *    1.接口是一种特殊的类，定义接口使用interface关键词
 *    2.接口的方法没有方法体；接口是一种规范，用于约束接口的实现类必须实现的操作
 *    3.接口的方法都是public abstract的,公有的，抽象的
 *    4.抽象方法没有方法体，
 *    5.接口没有属性，接口可以有接口常量，接口常量(默认)都是：public static  final 
 *    6.修饰符号可以颠倒顺序，数据类型后面必须是变量或常量
 *    
 *    7.在定义接口时，经常会遇到类型不确定的情况，可以使用钻石符号声明一个泛型，在类中可以使用该泛型进行占位
 * 
 * </pre>
 * 
 * @author netboy
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
    public static  final   boolean   SUCCESS = true;
	
	boolean FAIL = false;
	
	
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
