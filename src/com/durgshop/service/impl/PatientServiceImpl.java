package com.durgshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgshop.dao.PatientDao;
import com.durgshop.entity.Patient;
import com.durgshop.service.PatientService;
/**
 * 
 * @author LQH
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PatientServiceImpl extends AbstractServiceImpl<Patient> implements PatientService {
	@Autowired
	private PatientDao patientDao;
}
