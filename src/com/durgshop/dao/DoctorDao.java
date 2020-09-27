package com.durgshop.dao;

import com.durgshop.entity.Doctor;

/**
 * 创建时间2020/7/11
 * @author LQH
 *
 */
public interface DoctorDao extends BaseDao<Doctor> {
	
	/**
	 * 继承的目的是为了方便功能扩展，同时也继承了父类的功能
	 * 
	 * 系统用户登录，使用账号和密码进行登录
	 * @param doctor
	 * @return
	 */
    public Doctor login(Doctor doctor);

}
