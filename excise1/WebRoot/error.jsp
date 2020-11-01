<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		#left{
			float: left; 
			height: 400px;
			width: 400px;
			margin-left: 25%;
			border-right: 1px gray solid;
		}
		#right{
			float: right;
			margin-right: 22%;
			margin-top: 130px;
		}
		h2{
			color: red;
		}
	</style>
	<script type="text/javascript">
	var ct = 10;
	function startTime()
	{
	
	document.getElementById('txt').innerHTML=ct;
	if(ct===0)
	{
	    window.location="login.html";
	}
	else{
	ct--;
	setTimeout('startTime()',1000);
	}
	}
	
	</script>
  </head>
  
  <body>
    <body onload="startTime()">
    	<div id="left">
    		<img src="images/error.png"/>
    		
    	</div>
    	<div id="right">
    	    <p id="errorInfo" style="color:red;font-size: 30px; font-weight: bold;">${info}</p>
    		<h2>
				${error_1}
			</h2>
    		<h1><span id="txt"></span>秒后自动返回登录页面</h2>
    		<h1><span>不能跳转，请</span><a style="color: red; " href="login.html">点击这里</a></h1>
    	</div>
    </body>
  </body>
</html>
