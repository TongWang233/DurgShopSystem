package test.service;
/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午2:40:53
* @ClassName 类名称
* @Description 类描述
*/

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.durgshop.entity.Type;
import com.durgshop.service.TypeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-core.xml")
public class TypeServiceTest {

	@Autowired
	private TypeService typeService;
	
	@Test
	public void testFind() {
		
		List<Type> list = typeService.findByPager(null);
		
		for(Type type : list) {
			System.out.println(type);
		}
	}

}

