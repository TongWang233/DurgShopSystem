package test.dao;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午3:41:32
* @ClassName 类名称
* @Description 类描述
*/

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.durgshop.dao.ProviderDao;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Provider;

public class ProviderDaoTest {

	@Test
	public void testFind() {

		try {
			// 1、实例化mybatis运行环境
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			// 2、通过mybatis的bean工厂获取接口实例 //SqlSession是觉得不错连接connection，进行数据库连接
			SqlSession session = sqlSessionFactory.openSession(); // 使用反射机制实例化数据操作接口
			ProviderDao providerDao = session.getMapper(ProviderDao.class);

			// 3、调用接口执行数据库操作
			Pager<Provider> pager = new Pager<>(1, 10);
			pager.setCondition(new Provider(null, null, null, null, null, null, null, null, null, null));
			List<Provider> list = providerDao.findByPager(pager);

			// 4、处理数据返回结果 
			for(Provider Provider : list) { System.out.println(Provider); }

			Integer countInteger = providerDao.findTotalByPager(pager);
			if (countInteger != null) {
				System.out.println("查询到:" + countInteger + " 条记录");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {

		try { // 1、实例化mybatis运行环境
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			// 2、通过mybatis的bean工厂获取接口实例
			SqlSession session = sqlSessionFactory.openSession(); // 使用反射机制实例化数据操作接口
			ProviderDao providerDao = session.getMapper(ProviderDao.class);

			// 3、调用接口执行数据库操作
			Provider object = new Provider(null, null, null, null, null, null, "供应商法人000", null, null, 0);
			boolean ret = providerDao.add(object);

			// 4、处理数据返回结果
			if (ret) {
				System.out.println("新增成功！");
			} else {
				System.out.println("新增失败！");
			}

			// 5、更新操作需要编码提交事务 //操作代码验证成功后，需要关闭事务，避免发生数据异常，导致测试失败！ -mock测试
			// session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testedit() {

		try { // 1、实例化mybatis运行环境
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			// 2、通过mybatis的bean工厂获取接口实例 //SqlSession是觉得不错连接connection，进行数据库连接
			SqlSession session = sqlSessionFactory.openSession(); // 使用反射机制实例化数据操作接口
			ProviderDao providerDao = session.getMapper(ProviderDao.class);

			// 3、调用接口执行数据库操作 //new关键词是java语言分配内存的操作，称为实例化对象；实例化就是给赋值特定的数值，并保存到内存中
			Provider object = new Provider(10006, null, null, null, null, null, "供应商法人12", null, null, 0);
			boolean ret = providerDao.edit(object);

			// 4、处理数据返回结果
			if (ret) {
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！");
			}

			// 5、更新操作需要编码提交事务 //操作代码验证成功后，需要关闭事务，避免发生数据异常，导致测试失败！ -mock测试
			// session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {

		try {
			// 1、实例化mybatis运行环境
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			// 2、通过mybatis的bean工厂获取接口实例
			SqlSession session = sqlSessionFactory.openSession();
			ProviderDao providerDao = session.getMapper(ProviderDao.class);

			// 3、调用接口执行数据库操作
			Provider object = new Provider(10005, null, null, null, null, null, null, null, null, null);
			boolean ret = providerDao.delete(object);

			// 4、处理数据返回结果
			if (ret) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}

			// 5、更新操作需要编码提交事务
			// 操作代码验证成功后，需要关闭事务，避免发生数据异常，导致测试失败！ -mock测试
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
