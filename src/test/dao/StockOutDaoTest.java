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

import com.durgshop.dao.StockOutDao;
import com.durgshop.dao.StockOutDetailDao;
import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.StockIn;
import com.durgshop.entity.StockOut;
import com.durgshop.entity.StockOutDetail;

public class StockOutDaoTest {

	private SqlSession session;
	private StockOutDao stockOutdao;
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
				 stockOutdao = session.getMapper(StockOutDao.class);
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
		session.commit();
//		session.rollback();
	}
	
	
//	@Test
//	public void testFind() {
//			//3.调用接口执行数据库操作
//			Pager<StockOut> pager = new Pager<>(1, 10);
//			List<StockOut> list =  stockOutdao.findByPager(pager);
//			//4.处理数据返回结果
//			for(StockOut stockOut : list) {
//				System.out.println(stockOut);
//				
//				
//				List<StockOutDetail> stockOutDetailList = stockOut.getStockOutDetail();
//				System.out.println(stockOutDetailList);
//				if (stockOutDetailList != null && stockOutDetailList.size() > 0) {
//					for (StockOutDetail stockOutDetail : stockOutDetailList) {
//						System.out.println(stockOutDetail);
//					}
//				}
//				 
//			}	
//			//测试统计查询，统计查询的查询必须跟数据查询一致
//			
//			 Integer countInteger = stockOutdao.findTotalByPager(pager);
//			 if(countInteger!=null)
//			 { 
//				 System.out.println("查询到:"+countInteger+" 条记录");
//				 
//			 }
//			 
//	
//	}
	
	
	
//	@Test
//	public void testAdd() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//		StockOut stockIn=new StockOut("刘志文", 10002, "出库单11", new Date());
//		Drug drugOne=new Drug();
//		drugOne.setDrugNo(10002);
//			boolean ret = stockOutdao.add(stockIn);
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("新增1成功");
//				
//				StockOut stockOut = stockOutdao.findNoMax();
//				StockOutDetail stockOutDetail=new StockOutDetail(stockOut, drugOne, 100);
//				boolean flag = stockOutDetailDao.add(stockOutDetail);
//				if(flag) {
//					System.out.println("新增2成功");
//				}
//				else {
//					System.out.println("新增2失败！");
//				}
//			}else {
//				System.out.println("新增1失败！");
//			}
//			
//			
//	}
//	
//	
//	@Test
//	public void findMax() {
//		
//		StockOut stockOut = stockOutdao.findNoMax();
//		
//		System.out.println(stockOut);
//	}
//	
	
//	@Test
//	public void testEdit() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
////		StockIn stockIn = new StockIn(10003,new Date(), "12138", "入库就", null);
//		StockOut stockIn=new StockOut(10010,"梁啟辉", 10002, "出库单11", new Date());
//		
//			boolean ret = stockOutdao.edit(stockIn);
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("修改成功");
//			}else {
//				System.out.println("修改失败！");
//			}
//	}
	
}
