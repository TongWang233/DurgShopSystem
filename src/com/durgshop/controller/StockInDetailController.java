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
import com.durgshop.service.DrugService;
import com.durgshop.service.StockInDetailService;
import com.durgshop.service.StockInService;
import com.durgshop.service.TypeService;

@RestController
@RequestMapping("sys/stockInDetail")
public class StockInDetailController {
	
	@Autowired
	private StockInDetailService stockInDetailService;	
	
	@Autowired
	private TypeService typeService;

	@Autowired
	private StockInService stockInService;	
	
	@Autowired
	private DrugService drugServicer;
	
	@RequestMapping("stockInDetailList")
	public Pager<StockInDetail> testFind(StockInDetail stockInDetail,@RequestParam(name =  "page",defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "6") Integer size){
		
		Pager<StockInDetail> pager= new Pager<StockInDetail>(page, size);
		pager.setCondition(stockInDetail);
		List<StockInDetail> list = stockInDetailService.findByPager(pager);
		int ret=stockInDetailService.findTotalByPager(pager);
		
		pager.setRows(list);
		pager.setTotal(ret);
		
		return pager;
		
	}
	
	@RequestMapping("drugList")
	public List<Drug> goodsTypeList() {
		
		List<Drug> list = drugServicer.findByPager(null);
		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return list;
	}
	
	
	
	
	

	@RequestMapping("stockInList")
	public List<StockIn> getStockInList(){
		
		List<StockIn> list = stockInService.findByPager(null);
		
		return list;
	}
	
	
	@RequestMapping("addStockInDetail")
	public Result add(StockInDetail stockInDetail) {
		Result result = new Result(false, "新增失败");
		System.out.println(stockInDetail);
		try {
			boolean ret = stockInDetailService.add(stockInDetail);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("editStockInDetail")
	public Result edit(StockInDetail stockInDetail) {
		Result result = new Result(false, "修改失败");
		System.out.println(stockInDetail);
		try {
			
			/* boolean ret4 = typeService.edit(stockInDetail.getDrug().getType()); */
//			boolean ret2 = stockInService.edit(stockInDetail.getStockIn());
//			boolean ret3 = drugServicer.edit(stockInDetail.getDrug());
			boolean ret = stockInDetailService.edit(stockInDetail);
			
			
			if (ret ) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("deleteStockInDetail")
	public Result delete(StockInDetail stockInDetail) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = stockInDetailService.delete(stockInDetail);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("stockInDetailandDrugList")
	public List<StockInDetail> stockInDetailandDrugList(StockIn stockIn) {
//		id=10002;
		System.out.println("------------"+stockIn+"------");
		int id=stockIn.getStockInNo();
		System.out.println("-----------"+id+"---------");
		List<StockInDetail> list = stockInDetailService.findAllById(id);
		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		if(list != null) {
			return list;
		}
		
		return null;
	}
	
	
	
}

