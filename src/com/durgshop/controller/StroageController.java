package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.Storage;
import com.durgshop.service.StorageService;

/**
 * @author TonyWang
 * @version 创建时间：2020年7月11日 下午2:36:48
 * @ClassName 类名称
 * @Description 类描述
 */

@Controller
@RequestMapping("storage")
public class StroageController {

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<Storage> findList(Storage storage, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		Pager<Storage> pager = new Pager<>(page, size);
		System.out.println("我在库存控制层list");
		pager.setCondition(storage);
		List<Storage> list = storageService.findByPager(pager);
		int total = storageService.findTotalByPager(pager);
		pager.setTotal(total);
		pager.setRows(list);

		return pager;
	}

	@RequestMapping("add")
	@ResponseBody
	public Result add(Storage storage) {
		Result result = new Result(false, "新增失败,该药品存在请直接搜索");

		try {
			boolean ret = storageService.add(storage);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Storage storage) {
		Result result = new Result(false, "删除失败");

		try {
			boolean ret = storageService.delete(storage);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
