package com.java.dao;

import java.util.List;

import com.java.model.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    
    /** 
     * 根据查询条件查询出所有的记录,不用分页,用于excel导出功能   
     * @param userDeviceVo 
     * @return 
     */  
    List<User> getUserForExcel(User user);  
    //批量添加学生信息
    int insertStudentlist(List<User>userlist);
}