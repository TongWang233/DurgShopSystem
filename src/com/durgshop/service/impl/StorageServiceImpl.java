package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.StockOutDetailDao;
import com.durgshop.dao.StorageDao;
import com.durgshop.entity.Storage;
import com.durgshop.service.StorageService;

/**
* @author 刘志文
* @version 创建时间：2020年7月11日 上午10:52:47
* @ClassName 类名称
* @Description 类描述
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class StorageServiceImpl extends AbstractServiceImpl<Storage> implements StorageService{

	@Autowired
	private StorageDao storageDao;
	@Override
	public Storage findById(int storageNo) {
		// TODO Auto-generated method stub
		return storageDao.findById(storageNo);
	}

}



