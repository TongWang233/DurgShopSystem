package com.durgshop.entity;
/**
* @author TonyWang
* @version 创建时间：2020年7月15日 下午2:47:12
* @ClassName 类名称
* @Description 类描述
*/
public class Storage {
	
	private Integer storageNo;
	private Integer drugNo;
	private Integer storageNumber;
	private Drug drug;
	
	

	public Integer getStorageNo() {
		return storageNo;
	}

	public void setStorageNo(Integer storageNo) {
		this.storageNo = storageNo;
	}

	public Integer getDrugNo() {
		return drugNo;
	}

	public void setDrugNo(Integer drugNo) {
		this.drugNo = drugNo;
	}

	public Integer getStorageNumber() {
		return storageNumber;
	}

	public void setStorageNumber(Integer storageNumber) {
		this.storageNumber = storageNumber;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	@Override
	public String toString() {
		return "Storage [storageNo=" + storageNo + ", drugNo=" + drugNo + ", storageNumber=" + storageNumber + ", drug="
				+ drug + "]";
	}

	/**
	 * @param storageNo
	 * @param drugNo
	 * @param storageNumber
	 * @param drug
	 */
	public Storage(Integer storageNo, Integer drugNo, Integer storageNumber, Drug drug) {
		this.storageNo = storageNo;
		this.drugNo = drugNo;
		this.storageNumber = storageNumber;
		this.drug = drug;
	}
	public Storage() {
		
	}
	
	

}
