package com.durgshop.dao;

import com.durgshop.entity.StockOut;

public interface StockOutDao extends BaseDao<StockOut>{

	StockOut findNoMax();
	
}
