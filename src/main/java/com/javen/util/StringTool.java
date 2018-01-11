package com.javen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTool {

	public String replace(String s){
		
		s = s.replace("\n", "<br/>");
		s = s.replace(" ", "");
		
		return s;
	}

	//返回当前时间字符串
	public String currentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
