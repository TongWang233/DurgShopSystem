package com.durgshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.entity.Type;
import com.durgshop.service.TypeService;

/**
*@author TonyWang
* @version 创建时间：2020年7月11日 上午10:52:47
* @ClassName 类名称
* @Description 类描述
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl extends AbstractServiceImpl<Type> implements TypeService{

}



