package com.durgshop.service;

import java.util.List;

import com.durgshop.entity.Pager;
import com.durgshop.entity.PurchasingDetail;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午2:37:10
* @ClassName 类名称
* @Description 类描述
*/
public interface PurchasingDetailService  extends BaseService<PurchasingDetail> {

	public PurchasingDetail findById(int purchasingDetailNo);
}
