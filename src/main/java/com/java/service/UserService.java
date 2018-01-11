package com.java.service;

import java.util.List;

import com.java.model.User;

public interface UserService {

	/**
	 * 根据查询条件查询出所有的记录,不用分页,用于excel导出功能
	 * 
	 * @param userDeviceVo
	 * @return
	 */
	public List<User> getUserForExcel(User user);

	// 批量添加学生信息
	int insertStudentlist(List<User> userlist);

}
