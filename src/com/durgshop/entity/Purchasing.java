package com.durgshop.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午7:54:52
* @ClassName 类名称
* @Description 类描述
*/
public class Purchasing {
	
	private Integer purchasingNo;
	/* private Integer saleDetailNo; */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	private Date purchasingDate;
	private String handler;
	private String purchasingRemark;
	private String purchasingName;
	@Override
	public String toString() {
		return "Purchasing [purchasingNo=" + purchasingNo + ", purchasingDate=" + purchasingDate + ", handler="
				+ handler + ", purchasingRemark=" + purchasingRemark + ", purchasingName=" + purchasingName + "]";
	}
	/**
	 * @param purchasingNo
	 * @param purchasingDate
	 * @param handler
	 * @param purchasingRemark
	 * @param purchasingName
	 */
	public Purchasing(Integer purchasingNo, Date purchasingDate, String handler, String purchasingRemark,
			String purchasingName) {
		this.purchasingNo = purchasingNo;
		this.purchasingDate = purchasingDate;
		this.handler = handler;
		this.purchasingRemark = purchasingRemark;
		this.purchasingName = purchasingName;
	}
	public Purchasing() {
		
	}
	public Integer getPurchasingNo() {
		return purchasingNo;
	}
	public void setPurchasingNo(Integer purchasingNo) {
		this.purchasingNo = purchasingNo;
	}
	public Date getPurchasingDate() {
		return purchasingDate;
	}
	public void setPurchasingDate(Date purchasingDate) {
		this.purchasingDate = purchasingDate;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getPurchasingRemark() {
		return purchasingRemark;
	}
	public void setPurchasingRemark(String purchasingRemark) {
		this.purchasingRemark = purchasingRemark;
	}
	public String getPurchasingName() {
		return purchasingName;
	}
	public void setPurchasingName(String purchasingName) {
		this.purchasingName = purchasingName;
	}
	
	
	
	
	
} 
