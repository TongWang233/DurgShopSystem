package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Purchasing;
import com.durgshop.entity.Result;
import com.durgshop.service.PurchasingService;

/**
 * @RestController 可以不配置@ResponseBody
 * @author TonyWang
 *
 */
@RestController
@RequestMapping("/sys/purchasing")
public class PurchasingController {

	@Autowired
	private PurchasingService purchasingService;


	/**
	 * 
	 * @param goods 搜索参数
	 * @param page  页面
	 * @param size 分页大小
	 * @return
	 */
	@RequestMapping("list")
	public Pager<Purchasing> findList(Purchasing purchasing,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "10") Integer size) {
		System.out.println("====================================  ");

		Pager<Purchasing> pager = new Pager<>(page, size);
		pager.setCondition(purchasing);
		System.out.println(purchasing);
		List<Purchasing> list = purchasingService.findByPager(pager);
		int total = purchasingService.findTotalByPager(pager);

		pager.setTotal(total);
		pager.setRows(list);

		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return pager;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(Purchasing purchasing) {
		Result result = new Result(false, "新增失败");
		try {
			
			boolean ret = purchasingService.add(purchasing);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("edit")
	@ResponseBody
	public Result edit(Purchasing purchasing) {
		Result result = new Result(false, "修改失败");
			boolean ret = purchasingService.edit(purchasing);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		
		return result;
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Purchasing purchasing) {
		Result result = new Result(false, "删除失败");
		try {
			System.out.println("================================");
			boolean ret = purchasingService.delete(purchasing);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
