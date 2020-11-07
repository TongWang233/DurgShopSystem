package com.durgshop.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.durgshop.entity.Pager;
import com.durgshop.entity.Patient;
import com.durgshop.entity.Result;
import com.durgshop.service.PatientService;
/**
 * 创建时间2020/7/12
 * @author TonyWang
 *
 */
@Controller
@RequestMapping("sys/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public Pager<Patient> findList(Patient patient,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		System.out.println("====================================  ");

		Pager<Patient> pager = new Pager<>(page, size);
		pager.setCondition(patient);
		System.out.println(patient);

		List<Patient> list = patientService.findByPager(pager);
		int total = patientService.findTotalByPager(pager);

		pager.setTotal(total);
		pager.setRows(list);

		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return pager;
	}

	/**
	 * <pre>
	 *   springmvc接收参数的方法： 
	 *   1.可以使用方法的参数进行接收用户传递的数据：url参数或表单参数，或json  (方法的参数名与URL参数名对应，如果不对应的可以使用@RequestParam进行指定)
	 *   2.可以使用对象进行参数接收：对象的属性名与参数名对应
	 *   3.可以使用数组或集合进行参数接收：
	 * 
	 * </pre>
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(Patient patient) {
		Result result = new Result(false, "新增失败");

		try {
			boolean ret = patientService.add(patient);
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
	public Result edit(Patient patient) {
		Result result = new Result(false, "修改失败");
		try {
			boolean ret = patientService.edit(patient);
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
	public Result delete(Patient patient) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = patientService.delete(patient);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
