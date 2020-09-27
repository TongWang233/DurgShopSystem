package com.durgshop.dao;

import java.util.List;

import com.durgshop.entity.StockInDetail;
import com.durgshop.entity.StockOutDetail;

/**
* @author ALiyq
* @version 创建时间：2020-7-12 15:35:18
* @ClassName 类名称
* @Description 类描述
*/
public interface StockInDetailDao extends BaseDao<StockInDetail>{
	
	public List<StockInDetail> findAllById(Integer id);
}

