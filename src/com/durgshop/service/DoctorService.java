package com.durgshop.service;

import com.durgshop.entity.Doctor;

public interface DoctorService extends BaseService<Doctor> {
	public Doctor login(Doctor doctor);

}
