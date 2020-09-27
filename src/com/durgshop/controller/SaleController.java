package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Doctor;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Patient;
import com.durgshop.entity.Result;
import com.durgshop.entity.Sale;
import com.durgshop.service.DoctorService;
import com.durgshop.service.PatientService;
import com.durgshop.service.SaleService;


/**
 * @RestController 可以不配置@ResponseBody
 * @author netboy
 *
 */
@RestController
@RequestMapping("sys/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	
	


	/**
	 * 
	 * @param goods 搜索参数
	 * @param page  页面
	 * @param size 分页大小
	 * @return
	 */
	

	@RequestMapping(value = "patientlist")
	@ResponseBody
	public List<Patient> patientlist(){
		List<Patient> list = patientService.findByPager(null);
	
		return list;
	}
	@RequestMapping(value = "doctorlist")
	@ResponseBody
	public List<Doctor> doctorlist(){
		List<Doctor> list = doctorService.findByPager(null);
	
		return list;
	}
	
	
	@RequestMapping("list")
	public Pager<Sale> findList(Sale sale,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "10") Integer size) {
		Pager<Sale> pager = new Pager<>(page, size);
		pager.setCondition(sale);
		List<Sale> list = saleService.findByPager(pager);
		int total = saleService.findTotalByPager(pager);

		pager.setTotal(total);
		pager.setRows(list);

		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return pager;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(Sale sale) {
		Result result = new Result(false, "新增失败");
		try {
			
			boolean ret = saleService.add(sale);
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
	public Result edit(Sale sale) {
		Result result = new Result(false, "修改失败");
			boolean ret = saleService.edit(sale);
			if (ret) {
				result = new Result(true, "修改成功");
			}
		
		return result;
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Sale sale) {
		Result result = new Result(false, "删除失败");
		try {
			System.out.println("================================");
			boolean ret = saleService.delete(sale);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
