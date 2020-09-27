package test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.durgshop.entity.Drug;
import com.durgshop.service.DrugService;

/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午8:15:56
* @ClassName 类名称
* @Description 类描述
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-core.xml")
public class DrugServiceTest {

	@Autowired
	private DrugService drugService;
	
	@Test
	public void testFind() {
		
		List<Drug> list = drugService.findByPager(null);
		
		for(Drug drug : list) {
			System.out.println(drug);
		}
	}
}

