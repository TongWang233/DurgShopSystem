package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.StockOut;
import com.durgshop.entity.StockOutDetail;
import com.durgshop.service.StockOutDetailService;
import com.durgshop.service.StockOutService;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 14:34:16
* @ClassName 类名称
* @Description 类描述
*/

@RestController
@RequestMapping("sys/stockOut")
public class StockOutController {
	
	@Autowired
	private StockOutService stockOutService;
	
	@Autowired
	private StockOutDetailService stockOutDetailService;
	
	@RequestMapping("stockOutList")
	public Pager<StockOut> testFind(StockOut stockOut,@RequestParam(name =  "page",defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "6") Integer size){
		
		Pager<StockOut> pager= new Pager<StockOut>(page, size);
		pager.setCondition(stockOut);
		List<StockOut> list = stockOutService.findByPager(pager);
		int ret=stockOutService.findTotalByPager(pager);
		
		pager.setRows(list);
		pager.setTotal(ret);
		
		return pager;
		
	}
	
	@RequestMapping("addStockOut")
	public Result add(StockOut stockOut) {
		Result result = new Result(false, "新增失败");
		System.out.println(stockOut);
		try {
			
			boolean ret = stockOutService.add(stockOut);
			if (ret) {
				result = new Result(true, "新增成功");
				StockOut max = stockOutService.findNoMax();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("editStockOut")
	public Result edit(StockOut stockOut) {
		Result result = new Result(false, "修改失败");
		try {
			boolean ret = stockOutService.edit(stockOut);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("deleteStockOut")
	public Result delete(StockOut stockOut) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = stockOutService.delete(stockOut);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}

