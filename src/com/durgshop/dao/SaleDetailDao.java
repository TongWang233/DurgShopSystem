package com.durgshop.dao;
/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:52:52
* @ClassName 类名称
* @Description 类描述
*/

import com.durgshop.entity.SaleDetail;

public interface SaleDetailDao extends BaseDao<SaleDetail> {
	
	public SaleDetail findById(int saleDetailNo);
}
