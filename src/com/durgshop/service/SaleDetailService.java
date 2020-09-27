package com.durgshop.service;

import com.durgshop.entity.SaleDetail;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午2:37:10
* @ClassName 类名称
* @Description 类描述
*/
public interface SaleDetailService  extends BaseService<SaleDetail> {

	public SaleDetail findById(int SaleDetailNo);
}
