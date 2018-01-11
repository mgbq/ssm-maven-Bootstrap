package com.java.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.runners.model.EachTestNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.java.model.User;
import com.java.service.UserService;
import com.javen.util.ExcelTool;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/userinfolist", method = RequestMethod.GET)
	public @ResponseBody
	List<User> userinfolist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "queryJson") String queryJson)
			throws UnsupportedEncodingException {
		User user = JSON.parseObject((queryJson), User.class);
		List<User> userlList = userService.getUserForExcel(user);

		return userlList;
	}


	
	@RequestMapping(value="filesUpload" , method = RequestMethod.POST)
	public @ResponseBody Object filesUpload(@RequestParam("files") MultipartFile[] files) {
		//�ж�file���鲻��Ϊ�ղ��ҳ��ȴ���0
		if(files!=null&&files.length>0){
			//ѭ����ȡfile�����е��ļ�
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				//�����ļ�
				saveFile(file);
			}
		}
		return 1;
	}

	/***
	 * �����ļ�
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file) {
		// �ж��ļ��Ƿ�Ϊ��
		if (!file.isEmpty()) {
			try {
				// �ļ�����·��
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// ת���ļ�
				file.transferTo(new File(filePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@RequestMapping("fileUpload")
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		// �ж��ļ��Ƿ�Ϊ��
		if (!file.isEmpty()) {
			try {
				// �ļ�����·��
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// ת���ļ�
				file.transferTo(new File(filePath));
				File f1 = new File(filePath.replace('\\', '/'));	
				String jsonstr=ExcelTool.readExcel(f1).toString();
				System.out.println(jsonstr);
		        List<User> userlist=JSON.parseArray(jsonstr,User.class);
		      Integer order;  
		        for (int i = 0; i < userlist.size(); i++) {  
		            order = i + 1;  
		            userlist.get(i).setOrder(order.toString());  
		            if (userlist.get(i).getStudentsex().equals("��")) {  
		            	userlist.get(i).setStudentsex("1");  
		            } else {  
		            	userlist.get(i).setStudentsex("2"); 
		            }  
		        }  
		        userService.insertStudentlist(userlist);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// �ض���
		return "redirect:/index.jsp";

	
	}
}