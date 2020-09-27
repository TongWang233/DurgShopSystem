package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.StockInDao;
import com.durgshop.entity.StockIn;
import com.durgshop.service.StockInService;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 11:42:54
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class StockInServiceImpl extends AbstractServiceImpl<StockIn> implements StockInService{
	

}

