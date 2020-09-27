package com.durgshop.entity;
/**
* @author ALiyq
* @version 创建时间：2020-7-12 15:08:11
* @ClassName 类名称
* @Description 类描述
*/
public class StockOutDetail {
	
	private Integer stockOutDetailNo;
	
	private StockOut stockOut;
	
	private Drug drugOne;
	
	private Integer stockOutDetailQuantity;
	
	
	
	public StockOutDetail() {
		super();
	}
	
	

	public StockOutDetail(StockOut stockOut, Drug drugOne, Integer stockOutDetailQuantity) {
		super();
		this.stockOut = stockOut;
		this.drugOne = drugOne;
		this.stockOutDetailQuantity = stockOutDetailQuantity;
	}


	public StockOutDetail(Integer stockOutDetailNo, StockOut stockOut, Drug drugOne, Integer stockOutDetailQuantity) {
		super();
		this.stockOutDetailNo = stockOutDetailNo;
		this.stockOut = stockOut;
		this.drugOne = drugOne;
		this.stockOutDetailQuantity = stockOutDetailQuantity;
	}






	public Integer getStockOutDetailNo() {
		return stockOutDetailNo;
	}
	public void setStockOutDetailNo(Integer stockOutDetailNo) {
		this.stockOutDetailNo = stockOutDetailNo;
	}
	
	public StockOut getStockOut() {
		return stockOut;
	}

	public void setStockOut(StockOut stockOut) {
		this.stockOut = stockOut;
	}


	public Drug getDrugOne() {
		return drugOne;
	}



	public void setDrugOne(Drug drugOne) {
		this.drugOne = drugOne;
	}



	public Integer getStockOutDetailQuantity() {
		return stockOutDetailQuantity;
	}
	public void setStockOutDetailQuantity(Integer stockOutDetailQuantity) {
		this.stockOutDetailQuantity = stockOutDetailQuantity;
	}
	
	@Override
	public String toString() {
		return "StockOutDetail [stockOutDetailNo=" + stockOutDetailNo + ", stockOut=" + stockOut + ", drug=" + drugOne
				+ ", stockOutDetailQuantity=" + stockOutDetailQuantity + "]";
	}
	
	
	
}

