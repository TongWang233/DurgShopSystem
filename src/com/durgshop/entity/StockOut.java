package com.durgshop.entity;
/**
* @author ALiyq
* @version 创建时间：2020-7-11 19:55:37
* @ClassName 类名称
* @Description 类描述
*/

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockOut {
	
	private Integer stockOutNo;
	

		
	private String handler;
	
	private Integer saleNo;
	
	private String stockOutName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date stockOutTime;
	
	private List<StockOutDetail> stockOutDetail;
	
	public StockOut() {
		super();
	}

	public StockOut(String handler, Integer saleNo, String stockOutName, Date stockOutTime) {
		super();
		this.handler = handler;
		this.saleNo = saleNo;
		this.stockOutName = stockOutName;
		this.stockOutTime = stockOutTime;
	}

	public StockOut(Integer stockOutNo, String handler, Integer saleNo, String stockOutName, Date stockOutTime) {
		super();
		this.stockOutNo = stockOutNo;
		this.handler = handler;
		this.saleNo = saleNo;
		this.stockOutName = stockOutName;
		this.stockOutTime = stockOutTime;
	}
	
	public Integer getStockOutNo() {
		return stockOutNo;
	}
	public void setStockOutNo(Integer stockOutNo) {
		this.stockOutNo = stockOutNo;
	}
	public String getHandler() {
		return handler;
	}
	
	public List<StockOutDetail> getStockOutDetail() {
		return stockOutDetail;
	}

	public void setStockOutDetail(List<StockOutDetail> stockOutDetail) {
		this.stockOutDetail = stockOutDetail;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}
	public Integer getSaleNo() {
		return saleNo;
	}
	public void setSaleNo(Integer saleNo) {
		this.saleNo = saleNo;
	}
	public String getStockOutName() {
		return stockOutName;
	}
	public void setStockOutName(String stockOutName) {

		this.stockOutName = stockOutName;
	}
	public Date getStockOutTime() {
		return stockOutTime;
	}
	public void setStockOutTime(Date stockOutTime) {
		this.stockOutTime = stockOutTime;
	}

	@Override
	public String toString() {
		return "StockOut [stockOutNo=" + stockOutNo + ", handler=" + handler + ", saleNo=" + saleNo + ", stockOutName="
				+ stockOutName + ", stockOutTime=" + stockOutTime + "]";
	}
	
	
}

