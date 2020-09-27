package com.durgshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.entity.Sale;
import com.durgshop.service.SaleService;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:18:08
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleServiceImple extends AbstractServiceImpl<Sale> implements SaleService {

	
}
