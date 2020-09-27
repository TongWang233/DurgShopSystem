package com.durgshop.entity;
/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午7:28:54
* @ClassName 类名称
* @Description 类描述
*/
public class Drug {
	private Integer drugNo;
	private String drugName;
	private String drugImage;
	private String drugSpecification;
	private Integer drugValidity;
	
	private Integer typeNo;
	private Type type;
	
	private Integer providerNo;
	private Provider provider;
	
	private String drugRemark;
	private Integer isDelete;
	
	public Drug() {
		super();
	}

	public Drug(Integer drugNo, String drugName, String drugImage, String drugSpecification, Integer drugValidity,
			Type type, Provider provider, String drugRemark, Integer isDelete) {
		super();
		this.drugNo = drugNo;
		this.drugName = drugName;
		this.drugImage = drugImage;
		this.drugSpecification = drugSpecification;
		this.drugValidity = drugValidity;
		this.type = type;
		this.provider = provider;
		this.drugRemark = drugRemark;
		this.isDelete = isDelete;
	}

	public Integer getDrugNo() {
		return drugNo;
	}

	public void setDrugNo(Integer drugNo) {
		this.drugNo = drugNo;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugImage() {
		return drugImage;
	}

	public void setDrugImage(String drugImage) {
		this.drugImage = drugImage;
	}

	public String getDrugSpecification() {
		return drugSpecification;
	}

	public void setDrugSpecification(String drugSpecification) {
		this.drugSpecification = drugSpecification;
	}

	public Integer getDrugValidity() {
		return drugValidity;
	}

	public void setDrugValidity(Integer drugValidity) {
		this.drugValidity = drugValidity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getDrugRemark() {
		return drugRemark;
	}

	public void setDrugRemark(String drugRemark) {
		this.drugRemark = drugRemark;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Drug [drugNo=" + drugNo + ", drugName=" + drugName + ", drugImage=" + drugImage + ", drugSpecification="
				+ drugSpecification + ", drugValidity=" + drugValidity + ", type=" + type + ", provider=" + provider
				+ ", drugRemark=" + drugRemark + ", isDelete=" + isDelete + "]";
	}

}
