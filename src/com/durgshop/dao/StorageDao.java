package com.durgshop.dao;

import com.durgshop.entity.Drug;
import com.durgshop.entity.PurchasingDetail;
import com.durgshop.entity.Storage;

/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午7:33:53
* @ClassName 类名称
* @Description 类描述
*/
public interface StorageDao extends BaseDao<Storage>{

	public Storage findById(int StorageNo);
}
