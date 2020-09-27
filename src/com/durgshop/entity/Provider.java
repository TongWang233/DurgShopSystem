package com.durgshop.entity;
/**
* @author 刘志文
* @version 创建时间：2020年7月11日 下午3:28:27
* @ClassName 类名称
* @Description 类描述
*/
public class Provider {

	private Integer providerNo;
	private String providerAddress;
	private String providerLinkName;
	private String providerPhone;
	private String providerEmail;
	private String providerFactory;
	private String providerName;
	private String providerLicenseNo;
	private String providerLicenseImg;
	private Integer isDelete;
	
	public Provider() {
		super();
	}

	public Provider(Integer providerNo, String providerAddress, String providerLinkName, String providerPhone,
			String providerEmail, String providerFactory, String providerName, String providerLicenseNo,
			String providerLicenseImg, Integer isDelete) {
		super();
		this.providerNo = providerNo;
		this.providerAddress = providerAddress;
		this.providerLinkName = providerLinkName;
		this.providerPhone = providerPhone;
		this.providerEmail = providerEmail;
		this.providerFactory = providerFactory;
		this.providerName = providerName;
		this.providerLicenseNo = providerLicenseNo;
		this.providerLicenseImg = providerLicenseImg;
		this.isDelete = isDelete;
	}

	public Integer getProviderNo() {
		return providerNo;
	}

	public void setProviderNo(Integer providerNo) {
		this.providerNo = providerNo;
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}

	public String getProviderLinkName() {
		return providerLinkName;
	}

	public void setProviderLinkName(String providerLinkName) {
		this.providerLinkName = providerLinkName;
	}

	public String getProviderPhone() {
		return providerPhone;
	}

	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderFactory() {
		return providerFactory;
	}

	public void setProviderFactory(String providerFactory) {
		this.providerFactory = providerFactory;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderLicenseNo() {
		return providerLicenseNo;
	}

	public void setProviderLicenseNo(String providerLicenseNo) {
		this.providerLicenseNo = providerLicenseNo;
	}

	public String getProviderLicenseImg() {
		return providerLicenseImg;
	}

	public void setProviderLicenseImg(String providerLicenseImg) {
		this.providerLicenseImg = providerLicenseImg;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Provider [providerNo=" + providerNo + ", providerAddress=" + providerAddress + ", providerLinkName="
				+ providerLinkName + ", providerPhone=" + providerPhone + ", providerEmail=" + providerEmail
				+ ", providerFactory=" + providerFactory + ", providerName=" + providerName + ", providerLicenseNo="
				+ providerLicenseNo + ", providerLicenseImg=" + providerLicenseImg + ", isDelete=" + isDelete + "]";
	}
	
	
	
	
	
	
}
