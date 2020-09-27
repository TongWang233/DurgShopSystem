package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.SaleDetailDao;
import com.durgshop.entity.SaleDetail;
import com.durgshop.service.SaleDetailService;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:18:08
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleDetailServiceImple extends AbstractServiceImpl<SaleDetail> implements SaleDetailService {


	@Autowired
	private SaleDetailDao saleDetailDao;
	@Override
	public SaleDetail findById(int SaleDetailNo) {
		// TODO Auto-generated method stub
		return saleDetailDao.findById(SaleDetailNo);
	}


	
}
