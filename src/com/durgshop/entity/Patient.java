package com.durgshop.entity;
/**
 * 患者实体类
 * @author TonyWang
 *
 */
public class Patient {
	private Integer patientNo;
	private String patientName;
	private String patientSex;
	private String patientPhone;
	private String patientAddress;
	private String patientAllergicDrugs;
	private Integer patientAge;
	private String patientEmail;
	private Integer isDelete;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(Integer patientNo, String patientName, String patientSex, String patientPhone, String patientAddress,
			String patientAllergicDrugs, Integer patientAge, String patientEmail, Integer isDelete) {
		super();
		this.patientNo = patientNo;
		this.patientName = patientName;
		this.patientSex = patientSex;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.patientAllergicDrugs = patientAllergicDrugs;
		this.patientAge = patientAge;
		this.patientEmail = patientEmail;
		this.isDelete = isDelete;
	}
	public Integer getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(Integer patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getPatientAllergicDrugs() {
		return patientAllergicDrugs;
	}
	public void setPatientAllergicDrugs(String patientAllergicDrugs) {
		this.patientAllergicDrugs = patientAllergicDrugs;
	}
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "Patient [patientNo=" + patientNo + ", patientName=" + patientName + ", patientSex=" + patientSex
				+ ", patientPhone=" + patientPhone + ", patientAddress=" + patientAddress + ", patientAllergicDrugs="
				+ patientAllergicDrugs + ", patientAge=" + patientAge + ", patientEmail=" + patientEmail + ", isDelete="
				+ isDelete + "]";
	}
    
	
}
