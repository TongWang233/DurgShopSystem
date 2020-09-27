package com.durgshop.controller;
/**
* @author ALiyq
* @version 创建时间：2020-7-12 20:49:08
* @ClassName 类名称
* @Description 类描述
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.StockIn;
import com.durgshop.entity.StockInDetail;
import com.durgshop.entity.StockOut;
import com.durgshop.entity.StockOutDetail;
import com.durgshop.service.StockInDetailService;
import com.durgshop.service.StockInService;
import com.durgshop.service.StockOutDetailService;
import com.durgshop.service.StockOutService;

@RestController
@RequestMapping("sys/stockOutDetail")
public class StockOutDetailController {
	
	@Autowired
	private StockOutDetailService stockOutDetailService;	
	

	@RequestMapping("stockOutDetailList")
	public Pager<StockOutDetail> testFind(StockOutDetail stockOutDetail,@RequestParam(name =  "page",defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "6") Integer size){
		
		Pager<StockOutDetail> pager= new Pager<StockOutDetail>(page, size);
		pager.setCondition(stockOutDetail);
		List<StockOutDetail> list = stockOutDetailService.findByPager(pager);
		int ret=stockOutDetailService.findTotalByPager(pager);
		
		pager.setRows(list);
		pager.setTotal(ret);
		
		return pager;
		
	}
	
//	@RequestMapping("goodstypelist")
//	public List<GoodsType> goodsTypeList() {
//		
//		List<GoodsType> list = goodsTypeService.findByPager(null);
//		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
//		return list;
//	}
	
	
	@Autowired
	private StockOutService stockOutService;	
	

	@RequestMapping("stockOutList")
	public List<StockOut> getStockInList(){
		
		List<StockOut> list = stockOutService.findByPager(null);
		
		return list;
	}
	
	
	@RequestMapping("addStockOutDetail")
	public Result add(StockOutDetail stockOutDetail) {
		Result result = new Result(false, "新增失败");
		System.out.println(stockOutDetail);
		try {
			boolean ret = stockOutDetailService.add(stockOutDetail);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("editStockOutDetail")
	public Result edit(StockOutDetail stockOutDetail) {
		Result result = new Result(false, "修改失败");
		System.out.println(stockOutDetail);
		try {
			boolean ret = stockOutDetailService.edit(stockOutDetail);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("deleteStockOutDetail")
	public Result delete(StockOutDetail stockOutDetail) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = stockOutDetailService.delete(stockOutDetail);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("stockOutDetailandDrugList")
	public List<StockOutDetail> stockOutDetailandDrugList(StockOut stockOut) {
//		id=10002;
		System.out.println("------------"+stockOut+"------");
		int id=stockOut.getStockOutNo();
		System.out.println("-----------"+id+"---------");
		List<StockOutDetail> list = stockOutDetailService.findAllById(id);
		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return list;
		
	}
}

