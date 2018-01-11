<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<title>用户信息分页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fenye.css" type="text/css"></link>
	<script src="${pageContext.request.contextPath}/js/jquery.table2excel.js"></script>
<script type="text/javascript">
			//总记录数
			var count = 0;
			//总页数
			var totalPage = 0;
			//页面大小
			var pageSize = 1;
			//当前页码
			var currPage = 1;
			//当前页起始记录编号
			var currStart = 0;
			//页码页数
			var pageNum = 1;
			//页码显示个数
			var pageShowNum = 6;
			//总页数页码
			var totalPageNum;
			
			//页面跳转
			function jump(num){
				if(num == "add"){
					currPage = (currPage+1 > totalPage) ? totalPage : currPage+1;
					fenye();
				}else if(num == "subtract"){
					currPage = (currPage-1 <= 0) ? 1 : currPage-1;
					fenye();
				}else if(num == "last"){
					currPage = totalPage;
					fenye();
				}else if(num == "go"){
					var goNum = $("#goNum").val();
					if(!isNaN(goNum)){
						goNum = (goNum < 1)? 1 : goNum;
						goNum = (goNum > totalPage)? totalPage : goNum;
						currPage = goNum;
						$("#goNum").val("");
						fenye();
					}
				}else if(!isNaN(num)){
					currPage = num;
					fenye();
				}
			}
			
			
		function fenye() {
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/user/fenye',
				data : {
					sex : '男',currPage : currPage,pageSize:pageSize
				},
				success : function(date) {
			        	//总记录数
						count = date.count;
						//总页数
						totalPage = date.totalPage;
						//页面大小
						pageSize = date.pageSize;
						//当前页码
						currPage = date.currPage;
						//当前页起始记录编号
						currStart = date.currStart;
						//当前页数页码
						pageNum = Math.ceil(currPage / pageShowNum);
						//总页数页码
						totalPageNum = Math.ceil(totalPage / pageShowNum);
						//显示查询数据总数
						$("#num").html(count);
						//输出页码
						$("#middle").empty();
						//显示总页数
						$("#count").html(totalPage);
						//输出的页码个数
						var size = (pageNum == totalPageNum)? totalPage % pageShowNum : pageShowNum;
						size = totalPageNum == 0 ? 0 : size;
						//输出页码
						for(var i = 1; i <= size; i++){
						
							var num = ((pageNum - 1) * pageShowNum + i);
							if(num == currPage){
								$("#middle").append("<input type='button' onclick='jump(" + num + ")' style='border: 1px solid #428bca' id='" + num + "' value='" + num + "'/>");
							}else{	
								$("#middle").append("<input type='button' onclick='jump(" + num + ")' id='" + num + "' value='" + num + "'/>");
							}
						}
							
						if(size == 0){$("#paging").hide()}else{$("#paging").show()}
				
				
					$(".UserInfo").empty();
					for ( var i = 0; i < date.items.length; i++) {
						$(".UserInfo").append(
								"<tr><td>" + date.items[i].username
										+ "</td>&nbsp;&nbsp;&nbsp;<td>"
										+ date.items[i].userpassword
										+ "</td>&nbsp;&nbsp;&nbsp;<td>"
										+ date.items[i].sex
										+ "</td>&nbsp;&nbsp;&nbsp;</tr>");
					}
				}
			});
		}
	$(function() {
		fenye();
		
		$("#export").click(function(){
		alert("dd");
       $(".UserInfo").table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: "myFileName",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true
        });
    });
		
		
	});
</script>
</head>
<style type="text/css">
 .userinfoxs ul li{list-style:none;float:left;}
</style>
<body>
	<br />
		<p id="export">导出</p>
<div class="userinfoxs">
	<p>用户名     密码     性别      共<span id="num"></span> 个人</p><br/>
	<table class="UserInfo"></table>
	</div>
	<div class="tcdPageCode-weik" id="paging">
		<input type="button" class="tcdPageCode-weik-input" onclick="jump(1)"
			value="首页" /> <input type="button" class="tcdPageCode-weik-input"
			onclick="jump('subtract')" value="上一页" />
		<div id="middle"></div>
		<input type="button" class="tcdPageCode-weik-input"
			onclick="jump('add')" value="下一页" /> <input type="button"
			class="tcdPageCode-weik-input" onclick="jump('last')" value="尾页" /> 共<span
			id="count"></span>页,到第<input
			style="width: 30px;height:23px;float: none;text-align: center;"
			class="tcdPageCode-weik-input" id="goNum" />页 <input type="button"
			class="tcdPageCode-weik-input" onclick="jump('go')" value="跳转"
			style="float: none;" />
	</div>
</body>
</html>
