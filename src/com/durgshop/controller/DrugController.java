package com.durgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Provider;
import com.durgshop.entity.Result;
import com.durgshop.entity.Type;
import com.durgshop.service.DrugService;
import com.durgshop.service.ProviderService;
import com.durgshop.service.TypeService;
import com.durgshop.util.FileResult;
import com.durgshop.util.Fileuploadutil;

/**
 * @author TonyWang
 * @version 创建时间：2020年7月11日 下午8:13:24
 * @ClassName 类名称
 * @Description 类描述
 */

@Controller
@RequestMapping("sys/drug")
public class DrugController {

	@Autowired
	private DrugService drugService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private TypeService typeService;
	
	

	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<Drug> findList(Drug drug,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		Pager<Drug> pager = new Pager<>(page, size);
		pager.setCondition(drug);
		List<Drug> list = drugService.findByPager(pager);
		int total = drugService.findTotalByPager(pager);

		pager.setTotal(total);
		pager.setRows(list);

		return pager;
	}

	@RequestMapping("add")
	@ResponseBody
	public Result add(Drug drug, MultipartFile imageFile, HttpServletRequest request) {
		Result result = new Result(false, "新增失败");

		if (imageFile != null && !imageFile.isEmpty()) {
			FileResult fileResult = Fileuploadutil.uploadFile(request.getServletContext(), imageFile);
			if (!fileResult.getSuccess()) {
				return new Result(false, fileResult.getMsg());
			}
			String serverFileString = fileResult.getServerPath();
			System.out.println(fileResult.getServerPath());
			drug.setDrugImage(serverFileString);
		}

		try {
			boolean ret = drugService.add(drug);
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
	public Result edit(Drug drug, MultipartFile imageFile, HttpServletRequest request) {
		Result result = new Result(false, "修改失败");
		
		if (imageFile != null && !imageFile.isEmpty()) {
			FileResult fileResult = Fileuploadutil.uploadFile(request.getServletContext(), imageFile);
			if (!fileResult.getSuccess()) {
				return new Result(false, fileResult.getMsg());
			}
			String serverFileString = fileResult.getServerPath();
			System.out.println(fileResult.getServerPath());
			drug.setDrugImage(serverFileString);
		}


		try {
			boolean ret = drugService.edit(drug);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Drug drug) {
		Result result = new Result(false, "删除失败");

		try {
			boolean ret = drugService.delete(drug);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("providerlist")
	@ResponseBody
	public List<Provider> providerList() {
		List<Provider> list = providerService.findByPager(null);
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("typelist")
	@ResponseBody
	public List<Type> typeList() {
		List<Type> list = typeService.findByPager(null);
		System.out.println(list);
		return list;
	}

}
