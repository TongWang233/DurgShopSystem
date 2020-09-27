package test.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.durgshop.dao.DrugDao;
import com.durgshop.dao.ProviderDao;
import com.durgshop.dao.StockInDao;
import com.durgshop.dao.StockInDetailDao;
import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Provider;
import com.durgshop.entity.Purchasing;
import com.durgshop.entity.StockIn;
import com.durgshop.entity.StockInDetail;
import com.durgshop.entity.StockOutDetail;
import com.durgshop.entity.Type;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 10:37:03
* @ClassName 类名称
* @Description 类描述
*/
public class StockInDetailDaoTest {

	private SqlSession session;
	private StockInDetailDao stockInDetailDao;
	private StockInDao stockInDao;
	private DrugDao drugDao;
	private ProviderDao providerDao;
	@Before
	public void init() {
		System.out.println("============@Before====================");
		try {
				//1.实例化mybaits运行环境
				String resource = "config/mybatis-config.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				//2.通过mybatis的bean 工厂获取接口实例
				//SqlSession是jdbc连接的connection，进行数据库连接
				 session = sqlSessionFactory.openSession();
				//使用反射机制实例化数据操作接口,这里使用了工厂设计模式
				 stockInDetailDao = session.getMapper(StockInDetailDao.class);
				 stockInDao = session.getMapper(StockInDao.class);
				 drugDao = session.getMapper(DrugDao.class);
				 providerDao = session.getMapper(ProviderDao.class);
		
		}catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	@After
	public void  close() {
		System.out.println("============@After====================");
		//5.更新操作需要编码提交事务！
		//更新操作代码验证成功后，需要关闭事务！避免发生数据异常，导致测试失败！  - mock测试
		session.commit();
//		session.rollback();
	}
	
//	@Test
//	public void testByIdFind() { // 3.调用接口执行数据库操作 StockOutDetail
//		
//		List<StockInDetail> list = stockInDetailDao.findAllById(10004); // 4.处理数据返回结果
//		for (StockInDetail stockInDetail : list) {
//			System.out.println(stockInDetail);
//		} // 测试统计查询，统计查询的查询必须跟数据查询一致	
//
//	}
	
	
	@Test
	public void testFind() {
			//3.调用接口执行数据库操作
			Pager<StockInDetail> pager = new Pager<>(1, 5);
			List<StockInDetail> list =  stockInDetailDao.findByPager(pager);
			//4.处理数据返回结果
			for(StockInDetail stockInDetail : list) {
				System.out.println(stockInDetail);
			}	
			//测试统计查询，统计查询的查询必须跟数据查询一致
			
			 Integer countInteger = stockInDetailDao.findTotalByPager(pager);
			 
			 if(countInteger!=null)
			 { 
				 System.out.println("查询到:"+countInteger+" 条记录");
				 
			 }
			 
	
	}
//	@Test
//	public void testEdit() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//		StockIn stockIn = new StockIn(10004, new Date(),"ojbak",null, null);
//		Type type = new Type(10002, null, null);
//		
//		Provider provider=new Provider(10001, null, null, null, null, null, null, null, null, null);
//		Drug drug=new Drug(10003, "小伙子", null, "巫妖可就", null, type, provider, null, null);
//		StockInDetail stockDetal = new StockInDetail(10032,drug, 400, stockIn);
//		
//		System.out.println(stockDetal);
//			boolean ret2 = stockInDao.edit(stockIn);
//			boolean ret3=drugDao.edit(drug);
//			boolean ret = stockInDetailDao.edit(stockDetal);
//			System.out.println(stockIn);
//			
//			      ret = ret && ret2 && ret3;
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("修改成功");
//			}else {
//				System.out.println("修改失败！");
//			}
//	}
	
	
//	
	@Test
	public void testAdd() {
			//3.调用接口执行数据库操作
			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//		StockIn stockIn = new StockIn(10003,new Date(), "12138", "入库就", null);
		StockIn stockIn=new StockIn();
		stockIn.setStockInNo(10018);
		stockIn.setHandler("韦雷");
		Drug drug=new Drug(10002, null, null, null, null, null, null, null, null);
		
		StockInDetail stockDetal = new StockInDetail( drug, 3003, stockIn);
		System.out.println(stockIn);
		System.out.println(stockDetal);
			boolean ret = stockInDetailDao.add(stockDetal);
			//4.处理数据返回结果
			if(ret) {
				System.out.println("新增成功");
			}else {
				System.out.println("新增失败！");
			}
	}
//	
//	@Test
//	public void testDelete() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//			StockInDetail stockInDetail = new StockInDetail();
//			stockInDetail.setStockInDetailNo(10005);
//			boolean ret = stockInDetailDao.delete(stockInDetail);	
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("删除成功");
//			}else {
//				System.out.println("删除失败！");
//			}	
//	}
	
	
}

