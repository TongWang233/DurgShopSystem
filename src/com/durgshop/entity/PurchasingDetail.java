package com.durgshop.entity;
/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午8:21:13
* @ClassName 类名称
* @Description 类描述
*/

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchasingDetail {
	private Integer purchasingDetailNo;
	private Integer drugNo;
	private Integer purchasingNo;
	private Integer purchasingDetailQuantity;
	private Float purchasingDetailPrice;
	/* private Integer saleDetailNo; */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	private Date productDate;
	private Drug drug;
	private Purchasing purchasing;
	public Integer getPurchasingDetailNo() {
		return purchasingDetailNo;
	}
	public void setPurchasingDetailNo(Integer purchasingDetailNo) {
		this.purchasingDetailNo = purchasingDetailNo;
	}
	public Integer getDrugNo() {
		return drugNo;
	}
	public void setDrugNo(Integer drugNo) {
		this.drugNo = drugNo;
	}
	public Integer getPurchasingNo() {
		return purchasingNo;
	}
	public void setPurchasingNo(Integer purchasingNo) {
		this.purchasingNo = purchasingNo;
	}
	public Integer getPurchasingDetailQuantity() {
		return purchasingDetailQuantity;
	}
	public void setPurchasingDetailQuantity(Integer purchasingDetailQuantity) {
		this.purchasingDetailQuantity = purchasingDetailQuantity;
	}
	public Float getPurchasingDetailPrice() {
		return purchasingDetailPrice;
	}
	public void setPurchasingDetailPrice(Float purchasingDetailPrice) {
		this.purchasingDetailPrice = purchasingDetailPrice;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Purchasing getPurchasing() {
		return purchasing;
	}
	public void setPurchasing(Purchasing purchasing) {
		this.purchasing = purchasing;
	}
	@Override
	public String toString() {
		return "PurchasingDetail [purchasingDetailNo=" + purchasingDetailNo + ", drugNo=" + drugNo + ", purchasingNo="
				+ purchasingNo + ", purchasingDetailQuantity=" + purchasingDetailQuantity + ", purchasingDetailPrice="
				+ purchasingDetailPrice + ", productDate=" + productDate + ", drug=" + drug + ", purchasing="
				+ purchasing + "]";
	}
	/**
	 * @param purchasingDetailNo
	 * @param drugNo
	 * @param purchasingNo
	 * @param purchasingDetailQuantity
	 * @param purchasingDetailPrice
	 * @param productDate
	 * @param drug
	 * @param purchasing
	 */
	public PurchasingDetail(Integer purchasingDetailNo, Integer drugNo, Integer purchasingNo,
			Integer purchasingDetailQuantity, Float purchasingDetailPrice, Date productDate, Drug drug,
			Purchasing purchasing) {
		this.purchasingDetailNo = purchasingDetailNo;
		this.drugNo = drugNo;
		this.purchasingNo = purchasingNo;
		this.purchasingDetailQuantity = purchasingDetailQuantity;
		this.purchasingDetailPrice = purchasingDetailPrice;
		this.productDate = productDate;
		this.drug = drug;
		this.purchasing = purchasing;
	}
	
	public PurchasingDetail() {
		
	}
}
