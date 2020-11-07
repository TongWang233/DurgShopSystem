package test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.durgshop.entity.Provider;
import com.durgshop.service.ProviderService;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:42:08
* @ClassName 类名称
* @Description 类描述
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-core.xml")
public class ProviderServiceTest {

	@Autowired
	private ProviderService providerService;
	
	@Test
	public void testFind() {
		
		List<Provider> list = providerService.findByPager(null);
		
		for(Provider provider : list) {
			System.out.println(provider);
		}
	}
}

