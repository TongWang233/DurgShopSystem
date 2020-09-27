package com.durgshop.service;

import com.durgshop.entity.StockOut;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 11:41:30
* @ClassName 类名称
* @Description 类描述
*/
public interface StockOutService extends BaseService<StockOut>{
	StockOut findNoMax();

}

