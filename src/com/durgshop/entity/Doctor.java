package com.durgshop.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 医生信息实体类
 * @author TonyWang
 *
 */
public class Doctor {
	private Integer doctorNo;
	private String doctorName;
	private String password;
	private String doctorPhone;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	private Date doctorEntryTime;
	private String doctorSex;
	private Integer doctorAge;
	private String doctorEmail;
	private Integer isDelete;
	
	private String salt;
	
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(Integer doctorNo, String doctorName, String password, String doctorPhone, Date doctorEntryTime,
			String doctorSex, Integer doctorAge, String doctorEmail, Integer isDelete) {
		super();
		this.doctorNo = doctorNo;
		this.doctorName = doctorName;
		this.password = password;
		this.doctorPhone = doctorPhone;
		this.doctorEntryTime = doctorEntryTime;
		this.doctorSex = doctorSex;
		this.doctorAge = doctorAge;
		this.doctorEmail = doctorEmail;
		this.isDelete = isDelete;
	}

	public Integer getDoctorNo() {
		return doctorNo;
	}

	public void setDoctorNo(Integer doctorNo) {
		this.doctorNo = doctorNo;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public Date getDoctorEntryTime() {
		return doctorEntryTime;
	}

	public void setDoctorEntryTime(Date doctorEntryTime) {
		this.doctorEntryTime = doctorEntryTime;
	}

	public String getDoctorSex() {
		return doctorSex;
	}

	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}

	public Integer getDoctorAge() {
		return doctorAge;
	}

	public void setDoctorAge(Integer doctorAge) {
		this.doctorAge = doctorAge;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Doctor [doctorNo=" + doctorNo + ", doctorName=" + doctorName + ", password=" + password
				+ ", doctorPhone=" + doctorPhone + ", doctorEntryTime=" + doctorEntryTime + ", doctorSex=" + doctorSex
				+ ", doctorAge=" + doctorAge + ", doctorEmail=" + doctorEmail + ", isDelete=" + isDelete + "]";
	}
	
	
}
