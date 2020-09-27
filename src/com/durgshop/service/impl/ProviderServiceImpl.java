package com.durgshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.entity.Provider;
import com.durgshop.service.ProviderService;

/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午3:33:46
* @ClassName 类名称
* @Description 类描述
*/

@Service
@Transactional(rollbackFor = Exception.class)
public class ProviderServiceImpl extends AbstractServiceImpl<Provider> implements ProviderService {

}

