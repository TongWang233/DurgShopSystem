package com.durgshop.entity;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author TonyWang
* @version 创建时间：2020年7月11日 上午11:51:45
* @ClassName 类名称
* @Description 类描述
*/
public class Sale {

	private Integer saleNo;
	private Integer patientNo;
	/* private Integer saleDetailNo; */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	private Date saleDate;
	private Integer doctorNo;
	private Doctor doctor;
	private Integer isDelete;
	private String saleName;
	private SaleDetail saleDetail;
	private Drug drug;
	private Patient patient;
	public Integer getSaleNo() {
		return saleNo;
	}
	public void setSaleNo(Integer saleNo) {
		this.saleNo = saleNo;
	}
	public Integer getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(Integer patientNo) {
		this.patientNo = patientNo;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Integer getDoctorNo() {
		return doctorNo;
	}
	public void setDoctorNo(Integer doctorNo) {
		this.doctorNo = doctorNo;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
	 * @param saleNo
	 * @param patientNo
	 * @param saleDate
	 * @param doctorNo
	 * @param doctor
	 * @param isDelete
	 * @param saleName
	 * @param saleDetail
	 * @param drug
	 * @param patient
	 */
	public Sale(Integer saleNo, Integer patientNo, Date saleDate, Integer doctorNo, Doctor doctor, Integer isDelete,
			String saleName, SaleDetail saleDetail, Drug drug, Patient patient) {
		this.saleNo = saleNo;
		this.patientNo = patientNo;
		this.saleDate = saleDate;
		this.doctorNo = doctorNo;
		this.doctor = doctor;
		this.isDelete = isDelete;
		this.saleName = saleName;
		this.saleDetail = saleDetail;
		this.drug = drug;
		this.patient = patient;
	}
	public Sale() {
		
	}
	@Override
	public String toString() {
		return "Sale [saleNo=" + saleNo + ", patientNo=" + patientNo + ", saleDate=" + saleDate + ", doctorNo="
				+ doctorNo + ", doctor=" + doctor + ", isDelete=" + isDelete + ", saleName=" + saleName
				+ ", saleDetail=" + saleDetail + ", drug=" + drug + ", patient=" + patient + "]";
	}

	
	
}
