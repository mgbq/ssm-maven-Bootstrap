package com.java.dao;

import java.util.List;

import com.java.model.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    
    /** 
     * ���ݲ�ѯ������ѯ�����еļ�¼,���÷�ҳ,����excel��������   
     * @param userDeviceVo 
     * @return 
     */  
    List<User> getUserForExcel(User user);  
    //�������ѧ����Ϣ
    int insertStudentlist(List<User>userlist);
}