package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.DoctorDao;
import com.durgshop.entity.Doctor;
import com.durgshop.service.DoctorService;
/**
 * 
 * @author TonyWang
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorServiceImpl extends AbstractServiceImpl<Doctor> implements DoctorService{
	
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public Doctor login(Doctor doctor) {
		
		return doctorDao.login(doctor);
	}

}
