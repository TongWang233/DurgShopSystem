package com.durgshop.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author ALiyq
* @version 创建时间：2020-7-11 10:16:23
* @ClassName 类名称
* @Description 类描述
*/
public class StockIn {
	
	private Integer stockInNo;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date stockInTime;
	
	private String handler;
	private String stockInName;
	private Integer purchasingNo;
	private Purchasing purchasing;
	
	private List<StockInDetail> stockInDetail;
	
	public StockIn() {
		super();
	}

	public StockIn(Date stockInTime, String handler, String stockInName, Purchasing purchasing) {
		super();
		this.stockInTime = stockInTime;
		this.handler = handler;
		this.stockInName = stockInName;
		this.purchasing = purchasing;
	}


	
	
	public Integer getPurchasingNo() {
		return purchasingNo;
	}

	public void setPurchasingNo(Integer purchasingNo) {
		this.purchasingNo = purchasingNo;
	}

	public StockIn(Integer stockInNo, Date stockInTime, String handler, String stockInName, Purchasing purchasing) {
		super();
		this.stockInNo = stockInNo;
		this.stockInTime = stockInTime;
		this.handler = handler;
		this.stockInName = stockInName;
		this.purchasing = purchasing;
	}

	
	public List<StockInDetail> getStockInDetail() {
		return stockInDetail;
	}

	public void setStockInDetail(List<StockInDetail> stockInDetail) {
		this.stockInDetail = stockInDetail;
	}

	public Purchasing getPurchasing() {
		return purchasing;
	}


	public void setPurchasing(Purchasing purchasing) {
		this.purchasing = purchasing;
	}

	public Integer getStockInNo() {
		return stockInNo;
	}
	public void setStockInNo(Integer stockInNo) {
		this.stockInNo = stockInNo;
	}
	public Date getStockInTime() {
		return stockInTime;
	}
	public void setStockInTime(Date stockInTime) {
		this.stockInTime = stockInTime;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getStockInName() {
		return stockInName;
	}
	public void setStockInName(String stockInName) {
		this.stockInName = stockInName;
	}


	@Override
	public String toString() {
		return "StockIn [stockInNo=" + stockInNo + ", stockInTime=" + stockInTime + ", handler=" + handler
				+ ", stockInName=" + stockInName + ", purchasing=" + purchasing + "]";
	}
	
	
	
}

