package com.durgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Provider;
import com.durgshop.entity.Result;
import com.durgshop.service.ProviderService;
import com.durgshop.util.FileResult;
import com.durgshop.util.Fileuploadutil;

/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午4:38:34
* @ClassName 类名称
* @Description 类描述
*/

@Controller
@RequestMapping("sys/provider")
public class ProviderController {
	
	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "list")
	@ResponseBody
	public Pager<Provider> findList(Provider provider,@RequestParam(name = "page" ,defaultValue= "1") Integer page, @RequestParam(name = "size" ,defaultValue = "10") Integer size){

		Pager<Provider> pager = new Pager<>(page,size);
		pager.setCondition(provider);
		System.out.println(provider);
		
		List<Provider> list = providerService.findByPager(pager);
		int total = providerService.findTotalByPager(pager);
		
		pager.setTotal(total);
		pager.setRows(list);
		
		return pager;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(Provider provider, MultipartFile imageFile, HttpServletRequest request) {
		Result result = new Result(false, "新增失败");
		
		if (imageFile != null && !imageFile.isEmpty()) {
			FileResult fileResult = Fileuploadutil.uploadFile(request.getServletContext(), imageFile);
			if (!fileResult.getSuccess()) {
				return new Result(false, fileResult.getMsg());
			}
			String serverFileString = fileResult.getServerPath();
			System.out.println(fileResult.getServerPath());
			provider.setProviderLicenseImg(serverFileString);
		}
		
		try {
			boolean ret = providerService.add(provider);
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
	public Result edit(Provider provider, MultipartFile imageFile, HttpServletRequest request) {
		Result result = new Result(false, "修改失败");
		
		if (imageFile != null && !imageFile.isEmpty()) {
			FileResult fileResult = Fileuploadutil.uploadFile(request.getServletContext(), imageFile);
			if (!fileResult.getSuccess()) {
				return new Result(false, fileResult.getMsg());
			}
			String serverFileString = fileResult.getServerPath();
			System.out.println(fileResult.getServerPath());
			provider.setProviderLicenseImg(serverFileString);
		}
		
		try {
			boolean ret = providerService.edit(provider);
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
	public Result delete(Provider provider) {
		Result result = new Result(false, "删除失败");
		
		try {
			boolean ret = providerService.delete(provider);
			if(ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
