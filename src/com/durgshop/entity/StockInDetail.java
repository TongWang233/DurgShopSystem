package com.durgshop.entity;
/**
* @author ALiyq
* @version 创建时间：2020-7-11 10:21:43
* @ClassName 类名称
* @Description 类描述
*/
public class StockInDetail {
	
	private Integer stockInDetailNo;
	
//	private Integer drugNo;
	private Drug drug;
	
	private Integer stockInDetailQuantity;
	
	private StockIn stockIn;
	
	public StockInDetail() {
		super();
	}

	

	public StockInDetail(Drug drug, Integer stockInDetailQuantity, StockIn stockIn) {
		super();
		this.drug = drug;
		this.stockInDetailQuantity = stockInDetailQuantity;
		this.stockIn = stockIn;
	}



	public StockInDetail(Integer stockInDetailNo, Drug drug, Integer stockInDetailQuantity, StockIn stockIn) {
		super();
		this.stockInDetailNo = stockInDetailNo;
		this.drug = drug;
		this.stockInDetailQuantity = stockInDetailQuantity;
		this.stockIn = stockIn;
	}



	public StockIn getStockIn() {
		return stockIn;
	}


	public void setStockIn(StockIn stockIn) {
		this.stockIn = stockIn;
	}


	public Integer getStockInDetailNo() {
		return stockInDetailNo;
	}
	public void setStockInDetailNo(Integer stockInDetailNo) {
		this.stockInDetailNo = stockInDetailNo;
	}



	public Drug getDrug() {
		return drug;
	}



	public void setDrug(Drug drug) {
		this.drug = drug;
	}



	public Integer getStockInDetailQuantity() {
		return stockInDetailQuantity;
	}


	public void setStockInDetailQuantity(Integer stockInDetailQuantity) {
		this.stockInDetailQuantity = stockInDetailQuantity;
	}

	@Override
	public String toString() {
		return "StockInDetail [stockInDetailNo=" + stockInDetailNo + ", drug=" + drug + ", stockInDetailQuantity="
				+ stockInDetailQuantity + ", stockIn=" + stockIn + "]";
	}
}

