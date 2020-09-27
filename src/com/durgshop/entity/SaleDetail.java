package com.durgshop.entity;
/**
* @author TonyWang
* @version 创建时间：2020年7月11日 下午4:47:50
* @ClassName 类名称
* @Description 类描述
*/
public class SaleDetail {
	
	private Integer saleDetailNo;
	private Integer saleNo;
	private Integer drugNo;
	private Integer saleQuantity;
	private Integer isDelete;
	private Float price;
	private Drug drug;
	private Sale sale;
	public Integer getSaleDetailNo() {
		return saleDetailNo;
	}
	public void setSaleDetailNo(Integer saleDetailNo) {
		this.saleDetailNo = saleDetailNo;
	}
	public Integer getSaleNo() {
		return saleNo;
	}
	public void setSaleNo(Integer saleNo) {
		this.saleNo = saleNo;
	}
	public Integer getDrugNo() {
		return drugNo;
	}
	public void setDrugNo(Integer drugNo) {
		this.drugNo = drugNo;
	}
	public Integer getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	/**
	 * @param saleDetailNo
	 * @param saleNo
	 * @param drugNo
	 * @param saleQuantity
	 * @param isDelete
	 * @param price
	 * @param drug
	 * @param sale
	 */
	public SaleDetail(Integer saleDetailNo, Integer saleNo, Integer drugNo, Integer saleQuantity, Integer isDelete,
			Float price, Drug drug, Sale sale) {
		this.saleDetailNo = saleDetailNo;
		this.saleNo = saleNo;
		this.drugNo = drugNo;
		this.saleQuantity = saleQuantity;
		this.isDelete = isDelete;
		this.price = price;
		this.drug = drug;
		this.sale = sale;
	}
	public SaleDetail() {
		
	}
	@Override
	public String toString() {
		return "SaleDetail [saleDetailNo=" + saleDetailNo + ", saleNo=" + saleNo + ", drugNo=" + drugNo
				+ ", saleQuantity=" + saleQuantity + ", isDelete=" + isDelete + ", price=" + price + ", drug=" + drug
				+ ", sale=" + sale + "]";
	}
	
}
