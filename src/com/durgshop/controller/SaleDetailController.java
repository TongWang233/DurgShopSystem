package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.entity.Sale;
import com.durgshop.entity.SaleDetail;
import com.durgshop.entity.Storage;
import com.durgshop.service.DrugService;
import com.durgshop.service.SaleDetailService;
import com.durgshop.service.SaleService;
import com.durgshop.service.StorageService;

/**
 * @RestController 可以不配置@ResponseBody
 * @author TonyWang
 *
 */
@RestController
@RequestMapping("sys/saledetail")
public class SaleDetailController {

	@Autowired
	private SaleDetailService saleDetailService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private StorageService storageService;	


	/**
	 * 
	 * @param goods 搜索参数
	 * @param page  页面
	 * @param size 分页大小
	 * @return
	 */
	@RequestMapping(value = "salelist")
	@ResponseBody
	public List<Sale> salelist(){
		List<Sale> list = saleService.findByPager(null);
		return list;
	}
	@RequestMapping(value = "druglist")
	@ResponseBody
	public List<Drug> druglist(){
		List<Drug> list = drugService.findByPager(null);
			return list;
	}
	@RequestMapping("list")
	public Pager<SaleDetail> findList(SaleDetail saleDetail,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "10") Integer size) {
		Pager<SaleDetail> pager = new Pager<>(page, size);
		pager.setCondition(saleDetail);
		List<SaleDetail> list = saleDetailService.findByPager(pager);
		int total = saleDetailService.findTotalByPager(pager);
		pager.setTotal(total);
		pager.setRows(list);

		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return pager;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(SaleDetail saleDetail) {
		Result result = new Result(false, "库存不足新增失败");
		boolean ret1 = false;
		boolean ret  = false;
		try {
			int drugNo = saleDetail.getDrugNo();	
			Storage storage2 = storageService.findById(drugNo);	
			//对药品对用的库存信息进行修改，然后再进行跟新操作
				int number1= storage2.getStorageNumber();
				int number2 = saleDetail.getSaleQuantity();
				int number3 = number1-number2;
				if (number3>=0) {
					storage2.setStorageNumber(number3);
				    ret1 = storageService.edit(storage2);
				    ret = saleDetailService.add(saleDetail);
				    if (ret1&ret) {
						result = new Result(true, "新增成功");
					}	
				   
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("edit")
	@ResponseBody
	public Result edit(SaleDetail saleDetail) {
		Result result = new Result(false, "修改失败");
		boolean ret1 = false;
		boolean ret  = false;
		boolean ret3  = false;
		
		int number1;
		int number2;
		int number3;
		int number4;
		int number5;
		int number7;
			try {
				//我输入的药品编号
				int number6 = saleDetail.getDrugNo();
				int saleDetailNo = saleDetail.getSaleDetailNo();
				//获取出售药品编号
				//查找药品编号对应的库存信息
				SaleDetail saleDetail1 = saleDetailService.findById(saleDetailNo);
				int number11 = saleDetail1.getDrugNo();
				Storage storage = storageService.findById(number11);
				//数据库的药品数量
				number1=saleDetail1.getSaleQuantity();
				//已经修改了的药品库存量
				number3= storage.getStorageNumber();
				//我输入的药品数量
				number2 = saleDetail.getSaleQuantity();	
				//数据库的药品编号
				number5 = saleDetail1.getDrugNo();

				
					//数据库存了采购了的量的
				   //如果没有修改药品数量
					if (number5==number6&&number1!=number2) {
						if (number1>number2) {
						//库存总量
						number3= storage.getStorageNumber();
						//修改后的库存
						number4= number3 +(number1-number2);
						storage.setStorageNumber(number4);
						ret1 = storageService.edit(storage);
						ret = saleDetailService.edit(saleDetail);
						ret3=true;
						}
						else if (number1<number2) {
						//库存总量
						number3= storage.getStorageNumber();
						//修改后的库存
						number4= number3-(number2-number1);
						storage.setStorageNumber(number4);
						ret1 = storageService.edit(storage);
						ret = saleDetailService.edit(saleDetail);
						}
						else {
						ret = saleDetailService.edit(saleDetail);
						System.out.println("我没有改库存信息");
						ret1=ret3 = true;
						}
				}	
					//如果修改了药瓶编号，数量没有改。
				else if (number5!=number6&&number1==number2) {
				ret = saleDetailService.edit(saleDetail);
				number7 = number1+number3;
				storage.setStorageNumber(number7);
				ret1 = storageService.edit(storage);
				Storage storage1 = storageService.findById(number6);
				int number9 = storage1.getStorageNumber();
				int number10 = number9-number2;
				storage1.setStorageNumber(number10);
				ret3 = storageService.edit(storage1);
				System.out.println("我在改两个库存信息");
				
				}
		
			else{
				Storage storage1 = storageService.findById(number11);
				int number9 = storage1.getStorageNumber();
				int number10 = number9+number1;
				storage1.setStorageNumber(number10);
				ret1 = storageService.edit(storage1);
				
				Storage storage3 = storageService.findById(number6);
				int number12 = storage3.getStorageNumber();
				int number13 = number12 - number2;
				storage3.setStorageNumber(number13);
				ret3 = storageService.edit(storage3);
				ret = saleDetailService.edit(saleDetail);
		}	
			if (ret&ret1&ret3) {
					result = new Result(true, "修改成功");
				}
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(SaleDetail saleDetail) {
		Result result = new Result(false, "删除失败");
		try {
			System.out.println("================================");
			
			int SaleDetailNo = saleDetail.getSaleDetailNo();
			System.out.println(SaleDetailNo);
			SaleDetail saleDetail1 = saleDetailService.findById(SaleDetailNo);
			int drugNo = saleDetail1.getDrugNo();
			Storage storage2 = storageService.findById(drugNo);	
			//对药品对用的库存信息进行修改，然后再进行跟新操作
				int number1= storage2.getStorageNumber();
				int number2 = saleDetail1.getSaleQuantity();
				int number3 = number1+number2;
				storage2.setStorageNumber(number3);
				boolean ret1 = storageService.edit(storage2);
				boolean ret = saleDetailService.delete(saleDetail);
			if (ret&&ret1) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
