package com.durgshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.DrugDao;
import com.durgshop.dao.StockInDao;
import com.durgshop.dao.StockInDetailDao;
import com.durgshop.dao.TypeDao;
import com.durgshop.entity.StockInDetail;
import com.durgshop.service.StockInDetailService;

/**
 * @author ALiyq
 * @version 创建时间：2020-7-12 20:47:18
 * @ClassName 类名称
 * @Description 类描述
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockInDetailServiceImpl extends AbstractServiceImpl<StockInDetail> implements StockInDetailService {

	@Autowired private StockInDetailDao stockInDetailDao;
	
	@Override
	public List<StockInDetail> findAllById(Integer id) {
		return stockInDetailDao.findAllById(id);
	}

	/*
	 * 
	 * 
	 * @Autowired private DrugDao drugDao;
	 * 
	 * @Autowired private TypeDao typeDao;
	 * 
	 * @Override public boolean edit(StockInDetail object) {
	 * 
	 * 
	 * boolean ret2=stockInDao.edit(object.getStockIn()); // boolean
	 * ret3=drugDao.edit(object.getDrug()); boolean
	 * ret4=typeDao.edit(object.getDrug().getType()); boolean ret =
	 * super.edit(object);
	 * 
	 * //业务逻辑 ret = ret&&ret2;
	 * 
	 * return ret; }
	 */

}
