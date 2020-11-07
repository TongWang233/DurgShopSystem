package com.durgshop.service;

import com.durgshop.entity.PurchasingDetail;
import com.durgshop.entity.Storage;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 上午10:46:37
* @ClassName 类名称
* @Description 类描述
*/
public interface StorageService extends BaseService<Storage>{

	public Storage findById(int storageNo);
}
