package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.PurchasingDetailDao;
import com.durgshop.entity.Pager;
import com.durgshop.entity.PurchasingDetail;
import com.durgshop.service.PurchasingDetailService;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:18:08
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchasingDetailServiceImple extends AbstractServiceImpl<PurchasingDetail> implements PurchasingDetailService {

	   
	@Autowired
	private PurchasingDetailDao purchasingDetailDao;
	@Override
	public PurchasingDetail findById(int purchasingDetailNo) {
		// TODO Auto-generated method stub
		return purchasingDetailDao.findById(purchasingDetailNo);
	}

	

	
	
}
