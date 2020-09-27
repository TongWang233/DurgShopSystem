package com.durgshop.dao;

import java.util.List;

import com.durgshop.entity.Pager;
import com.durgshop.entity.PurchasingDetail;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午2:43:39
* @ClassName 类名称
* @Description 类描述
*/
public interface PurchasingDetailDao extends BaseDao<PurchasingDetail> {

	
	public PurchasingDetail findById(int purchasingDetailNo);
}
