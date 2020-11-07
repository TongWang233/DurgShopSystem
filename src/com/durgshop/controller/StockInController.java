package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.StockIn;
import com.durgshop.entity.StockOut;
import com.durgshop.entity.StockOutDetail;
import com.durgshop.service.StockInDetailService;
import com.durgshop.service.StockInService;
import com.durgshop.service.StockOutDetailService;
import com.durgshop.service.StockOutService;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 14:34:16
* @ClassName 类名称
* @Description 类描述
*/

@RestController
@RequestMapping("sys/stockIn")
public class StockInController {
	
	@Autowired
	private StockInService stockInService;
	
	@Autowired
	private StockInDetailService stockInDetailService;
	
	@RequestMapping("stockInList")
	public Pager<StockIn> testFind(StockIn stockIn,@RequestParam(name =  "page",defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "6") Integer size){
		
		Pager<StockIn> pager= new Pager<StockIn>(page, size);
		pager.setCondition(stockIn);
		List<StockIn> list = stockInService.findByPager(pager);
		int ret=stockInService.findTotalByPager(pager);
		
		pager.setRows(list);
		pager.setTotal(ret);
		
		return pager;
		
	}
	
	@RequestMapping("addStockIn")
	public Result add(StockIn stockIn) {
		Result result = new Result(false, "新增失败");
		try {
			
			boolean ret = stockInService.add(stockIn);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("editStockIn")
	public Result edit(StockIn stockIn) {
		Result result = new Result(false, "修改失败");
		try {
			boolean ret = stockInService.edit(stockIn);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("deleteStockOut")
	public Result delete(StockIn stockIn) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = stockInService.delete(stockIn);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}

