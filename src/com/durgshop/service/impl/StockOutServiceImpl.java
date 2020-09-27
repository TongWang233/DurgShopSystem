package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.StockOutDao;
import com.durgshop.entity.StockOut;
import com.durgshop.service.StockOutService;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 11:42:54
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class StockOutServiceImpl extends AbstractServiceImpl<StockOut> implements StockOutService{
	
	@Autowired
	private StockOutDao stockOutDao;
	
	@Override
	public StockOut findNoMax() {
		return stockOutDao.findNoMax();
	}
	

}

