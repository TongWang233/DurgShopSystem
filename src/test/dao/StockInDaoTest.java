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
import com.durgshop.entity.Pager;
import com.durgshop.entity.Purchasing;
import com.durgshop.entity.StockIn;
import com.durgshop.entity.StockInDetail;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 10:37:03
* @ClassName 类名称
* @Description 类描述
*/
public class StockInDaoTest {

	private SqlSession session;
	private StockInDao stockIndao;
	
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
				 stockIndao = session.getMapper(StockInDao.class);
		
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
	
	
	@Test
	public void testFind() {
			//3.调用接口执行数据库操作
			Pager<StockIn> pager = new Pager<>(1, 5);
			List<StockIn> list =  stockIndao.findByPager(pager);
			//4.处理数据返回结果
			for(StockIn stockIn : list) {
				System.out.println(stockIn);
				
				List<StockInDetail> detail = stockIn.getStockInDetail();
				for(StockInDetail stockInDetail:detail) {
					System.out.println(stockInDetail);
				}
			}	
			//测试统计查询，统计查询的查询必须跟数据查询一致
			
			 Integer countInteger = stockIndao.findTotalByPager(pager);
			 if(countInteger!=null)
			 { 
				 System.out.println("查询到:"+countInteger+" 条记录");
				 
			 }
			 
	
	}
//	@Test
//	public void testEdit() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//			Purchasing purchasing=new Purchasing(10002,new Date(), null, null, null);
//			StockIn stockIn = new StockIn(10018, new Date(), "鸺鹠", "出库", purchasing);
//			boolean ret = stockIndao.edit(stockIn);
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
//			Purchasing purchasing=new Purchasing(10003, null, null, null, null);
//			StockIn stockIn = new StockIn(new Date(), "效力", "小婊砸", purchasing);
//			boolean ret = stockIndao.add(stockIn);
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("新增成功");
//			}else {
//				System.out.println("新增失败！");
//			}
//	}
//	
//	@Test
//	public void testDelete() {
//			//3.调用接口执行数据库操作
//			//new关键词是java语言分配内存用的操作，称为实例化对象;实例化就是给类赋值特定的数据值，并保存到内存中；
//			Purchasing purchasing=new Purchasing(10003,new Date(), null, null, null);
//			StockIn stockIn = new StockIn(10017, null, null, null, purchasing);
//			stockIn.setStockInNo(10017);
//			boolean ret = stockIndao.delete(stockIn);	
//			//4.处理数据返回结果
//			if(ret) {
//				System.out.println("删除成功");
//			}else {
//				System.out.println("删除失败！");
//			}	
//	}
//	
	
}

