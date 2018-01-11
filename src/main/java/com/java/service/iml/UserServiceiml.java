package com.java.service.iml;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UserMapper;
import com.java.model.User;
import com.java.service.UserService;
@Service("userService")
public class UserServiceiml implements UserService {

	@Resource
	private UserMapper Userdao;
	
	
	/** 
     * ���ݲ�ѯ������ѯ�����еļ�¼,���÷�ҳ,����excel��������   
     * @param userDeviceVo 
     * @return 
     */  
    @Override  
    public List<User> getUserForExcel(User user) {  
        List<User> list = Userdao.getUserForExcel(user);  
        Integer order;  
        for (int i = 0; i < list.size(); i++) {  
            order = i + 1;  
            list.get(i).setOrder(order.toString());  
            if (list.get(i).getStudentsex().equals("1")) {  
                list.get(i).setStudentsex("��");  
            } else {  
                list.get(i).setStudentsex("Ů"); 
            }  
        }  
        return list;  
    }


	@Override
	public int insertStudentlist(List<User> userlist) {
		// TODO Auto-generated method stub
		int count=Userdao.insertStudentlist(userlist);
		return count;
	}  
    
    

}
