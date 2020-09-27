package com.durgshop.service;

import java.util.List;

import com.durgshop.entity.StockInDetail;
import com.durgshop.entity.StockOutDetail;

/**
* @author ALiyq
* @version 创建时间：2020-7-12 20:46:35
* @ClassName 类名称
* @Description 类描述
*/
public interface StockInDetailService extends BaseService<StockInDetail>{

	public List<StockInDetail> findAllById(Integer id);
}

