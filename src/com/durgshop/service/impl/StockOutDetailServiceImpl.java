package com.durgshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.StockOutDao;
import com.durgshop.dao.StockOutDetailDao;
import com.durgshop.entity.StockOutDetail;
import com.durgshop.service.StockOutDetailService;

/**
* @author ALiyq
* @version 创建时间：2020-7-12 20:47:18
* @ClassName 类名称
* @Description 类描述
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class StockOutDetailServiceImpl extends AbstractServiceImpl<StockOutDetail> implements StockOutDetailService{
	
	@Autowired
	private StockOutDetailDao stockOutDetailDao;

	@Override
	public List<StockOutDetail> findAllById(Integer id) {
		return stockOutDetailDao.findAllById(id);
	}
	
	
}

