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
		//判断file数组不能为空并且长度大于0
		if(files!=null&&files.length>0){
			//循环获取file数组中得文件
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				//保存文件
				saveFile(file);
			}
		}
		return 1;
	}

	/***
	 * 保存文件
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
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
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				File f1 = new File(filePath.replace('\\', '/'));	
				String jsonstr=ExcelTool.readExcel(f1).toString();
				System.out.println(jsonstr);
		        List<User> userlist=JSON.parseArray(jsonstr,User.class);
		      Integer order;  
		        for (int i = 0; i < userlist.size(); i++) {  
		            order = i + 1;  
		            userlist.get(i).setOrder(order.toString());  
		            if (userlist.get(i).getStudentsex().equals("男")) {  
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
		
		// 重定向
		return "redirect:/index.jsp";

	
	}
}