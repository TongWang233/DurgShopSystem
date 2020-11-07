package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.Type;
import com.durgshop.service.TypeService;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午2:36:48
* @ClassName 类名称
* @Description 类描述
*/

@Controller
@RequestMapping("sys/type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;

	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<Type> findList(Type type,@RequestParam(name = "page" ,defaultValue= "1") Integer page, @RequestParam(name = "size" ,defaultValue = "10") Integer size){

		Pager<Type> pager = new Pager<>(page,size);
		pager.setCondition(type);
		List<Type> list = typeService.findByPager(pager);
		int total = typeService.findTotalByPager(pager);
		pager.setTotal(total);
		pager.setRows(list);
		
		return pager;
	}
	@RequestMapping("add")
	@ResponseBody
	public Result add(Type type) {
		Result result = new Result(false, "新增失败");
		try {
			boolean ret = typeService.add(type);
			if(ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public Result edit(Type type) {
		Result result = new Result(false, "修改失败");
		try {
			boolean ret = typeService.edit(type);
			if(ret) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Type type) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = typeService.delete(type);
			if(ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
