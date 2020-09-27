package com.durgshop.dao;

import java.util.List;

import com.durgshop.entity.StockOutDetail;

/**
* @author ALiyq
* @version 创建时间：2020-7-14 8:47:07
* @ClassName 类名称
* @Description 类描述
*/
public interface StockOutDetailDao extends BaseDao<StockOutDetail> {
	
	public List<StockOutDetail> findAllById(Integer id);
	

}

