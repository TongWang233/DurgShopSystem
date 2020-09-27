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

import com.durgshop.dao.StockInDao;
import com.durgshop.dao.StockOutDetailDao;
import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.StockInDetail;
import com.durgshop.entity.StockOut;
import com.durgshop.entity.StockOutDetail;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 10:37:03
* @ClassName 类名称
* @Description 类描述
*/
public class StockOutDetailDaoTest {

	private SqlSession session;
	private StockOutDetailDao stockOutDetailDao;
	
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
				 stockOutDetailDao = session.getMapper(StockOutDetailDao.class);
		
		}catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	@After
	public void  close() {
		System.out.println("============@After====================");
		//5.更新操作需要编码提交事务！
		//更新操作代码验证成功后，需要关闭事务！避免发生数据异常，导致测试失败！  - mock测试
//		session.commit();
		session.rollback();
	}
	
	
	@Test
	public void testFind() {
			//3.调用接口执行数据库操作
			Pager<StockOutDetail> pager = new Pager<>(1, 5);
			List<StockOutDetail> list =  stockOutDetailDao.findByPager(pager);
			//4.处理数据返回结果
			for(StockOutDetail stockOutDetail : list) {
				System.out.println(stockOutDetail);
			}	
			//测试统计查询，统计查询的查询必须跟数据查询一致
			
			 Integer countInteger = stockOutDetailDao.findTotalByPager(pager);
			 if(countInteger!=null)
			 { 
				 System.out.println("查询到:"+countInteger+" 条记录");
				 
			 }
			 
	
	}
//	
	
//	@Test
//	public void testByIdFind() { // 3.调用接口执行数据库操作 StockOutDetail
//		
//		List<StockOutDetail> list = stockOutDetailDao.findAllById(10002); // 4.处理数据返回结果
//		for (StockOutDetail stockOutDetail : list) {
//			System.out.println(stockOutDetail);
//		} // 测试统计查询，统计查询的查询必须跟数据查询一致
//
//	}
	
//	@Test
//	public void testEdit() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//		StockOut stockIn=new StockOut();
//		stockIn.setStockOutNo(10001);
//		Drug drug=new Drug(10002, null, null, null, null, null, null, null, null);
//		
//		StockOutDetail stockOutDetal = new StockOutDetail(10001, stockIn, drug, 100);
//		System.out.println(stockIn);
//		System.out.println(stockOutDetal);
//			boolean ret = stockOutDetailDao.edit(stockOutDetal);
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("修改成功");
//			}else {
//				System.out.println("修改失败！");
//			}
//	}
//	
	
	
//	@Test
//	public void testAdd() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
////		StockIn stockIn = new StockIn(10003,new Date(), "12138", "入库就", null);
//		StockOut stockIn=new StockOut();
//		stockIn.setStockOutNo(10001);
//		Drug drug=new Drug(10002, "舒服", null, "12345", null, 10002, null, null, null);
//		
//		StockOutDetail stockOutDetal = new StockOutDetail(stockIn, drug, 1000);
//		System.out.println(stockIn);
//		System.out.println(stockOutDetal);
//			boolean ret = stockOutDetailDao.add(stockOutDetal);
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("新增成功");
//			}else {
//				System.out.println("新增失败！");
//			}
//	}
	
//	@Test
//	public void testDelete() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//			StockOutDetail stockInDetail = new StockOutDetail();
//			stockInDetail.setStockOutDetailNo(10001);
//			boolean ret = stockOutDetailDao.delete(stockInDetail);	
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("删除成功");
//			}else {
//				System.out.println("删除失败！");
//			}	
//	}
	
	
}

