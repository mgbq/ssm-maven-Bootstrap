package com.java.service;

import java.util.List;

import com.java.model.User;

public interface UserService {

	/**
	 * ���ݲ�ѯ������ѯ�����еļ�¼,���÷�ҳ,����excel��������
	 * 
	 * @param userDeviceVo
	 * @return
	 */
	public List<User> getUserForExcel(User user);

	// �������ѧ����Ϣ
	int insertStudentlist(List<User> userlist);

}
