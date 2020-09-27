package com.durgshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgshop.entity.Drug;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Purchasing;
import com.durgshop.entity.PurchasingDetail;
import com.durgshop.entity.Result;
import com.durgshop.entity.Storage;
import com.durgshop.service.DrugService;
import com.durgshop.service.PurchasingDetailService;
import com.durgshop.service.PurchasingService;
import com.durgshop.service.StorageService;

/**
 * @RestController 可以不配置@ResponseBody
 * @author TonyWang
 *
 */
@RestController
@RequestMapping("sys/purchasingdetail")
public class PurchasingDetailController {

	@Autowired
	private PurchasingDetailService purchasingDetailService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private PurchasingService purchasingService;
	@Autowired
	private StorageService storageService;	
	/**
	 * 
	 * @param goods 搜索参数
	 * @param page  页面
	 * @param size 分页大小
	 * @return
	 */
	
	@RequestMapping(value = "purchasinglist")
	@ResponseBody
	public List<Purchasing> salelist(){
		List<Purchasing> list = purchasingService.findByPager(null);
		return list;
	}
	@RequestMapping(value = "druglist")
	@ResponseBody
	public List<Drug> druglist(){
		List<Drug> list = drugService.findByPager(null);
			return list;
	}
	@RequestMapping("list")
	public Pager<PurchasingDetail> findList(PurchasingDetail purchasingDetail,@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "rows", defaultValue = "10") Integer size) {
				Pager<PurchasingDetail> pager = new Pager<>(page, size);
				pager.setCondition(purchasingDetail);
				List<PurchasingDetail> list = purchasingDetailService.findByPager(pager);
				int total = purchasingDetailService.findTotalByPager(pager);
				pager.setTotal(total);
				pager.setRows(list);
		
				// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
				return pager;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(PurchasingDetail purchasingDetail) {
		Result result = new Result(false, "新增失败");
		boolean ret1 = false;
		boolean ret  = false;
		try {
			int drugNo = purchasingDetail.getDrugNo();	
			//获取出售药品编号
			Storage storage = new Storage();		
			storage.setDrugNo(drugNo);
			Pager<Storage> pager = new Pager<>();
			pager.setCondition(storage);
			//查找药品编号对应的库存信息
			List<Storage> list =storageService.findByPager(pager);
			for (Storage drug : list) {
				//对药品对用的库存信息进行修改，然后再进行跟新操作
					int number1= drug.getStorageNumber();
					int number2 = purchasingDetail.getPurchasingDetailQuantity();
					int number3 = number1+number2;
					drug.setStorageNumber(number3);
					ret1 = storageService.edit(drug);
					ret = purchasingDetailService.add(purchasingDetail);
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
	public Result edit(PurchasingDetail purchasingDetail) {
		Result result = new Result(false, "修改失败");
		boolean ret1 = false;
		boolean ret  = false;
		boolean ret3  = false;
		int number1;
		int number2;
		int number3;
		int number4;
			try {
				int drugNo = purchasingDetail.getDrugNo();	
				int purchasingDetailNo = purchasingDetail.getPurchasingDetailNo();
				PurchasingDetail purchasingDetail1 = purchasingDetailService.findById(purchasingDetailNo);
				int drugNo1 = purchasingDetail1.getDrugNo();	
				//获取出售药品编号		
				Storage storage2 = storageService.findById(drugNo1);
					//数据库存了采购了的量的
					number1=purchasingDetail1.getPurchasingDetailQuantity();
					//库存总量
					number3= storage2.getStorageNumber();
					//用户要改实际出售了的量的
					number2 = purchasingDetail.getPurchasingDetailQuantity();		
					//如果采购多了，就要把库存多发的加回来
					if (drugNo==drugNo1&&number1!=number2) {
					if (number1>number2) {
						//库存总量
						
						//修改后的库存
						number4= number3 -(number1-number2);
						storage2.setStorageNumber(number4);
						ret1 = storageService.edit(storage2);
						ret = purchasingDetailService.edit(purchasingDetail);
						ret3=true;
					}
					else if (number1<number2) {
						//库存总量
						number3= storage2.getStorageNumber();
						//修改后的库存
						number4= number3 +(number2-number1);
						storage2.setStorageNumber(number4);
						ret1 = storageService.edit(storage2);
						ret = purchasingDetailService.edit(purchasingDetail);
						ret3=true;
					}
					else {
						ret = purchasingDetailService.edit(purchasingDetail);
						ret1=ret3 = true;
					}
				}else if (drugNo!=drugNo1&&number1==number2) {
					ret = purchasingDetailService.edit(purchasingDetail);
					
					int number7 =number3-number1;
					storage2.setStorageNumber(number7);
					ret1 = storageService.edit(storage2);
					Storage storage1 = storageService.findById(drugNo);
					int number9 = storage1.getStorageNumber();
					int number10 = number9+number2;
					storage1.setStorageNumber(number10);
					ret3 = storageService.edit(storage1);
					System.out.println("我在改两个库存信息");
					
				}else {
					ret1=ret3=true;
					ret = purchasingDetailService.edit(purchasingDetail);
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
	public Result delete(PurchasingDetail purchasingDetail) {
		Result result = new Result(false, "删除失败");
		try {
		
			boolean ret = purchasingDetailService.delete(purchasingDetail);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
