<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>账号设置</title>
        <style type="text/css">
        	body{
        		margin: 0;
        		text-align: center;
        	}
        	#top{
        		background: linear-gradient(to right, cornflowerblue, mediumspringgreen);
				width: 100%;
				height: 50px;
        	}
        	#editcontent{
        		position: absolute;
        		top: 200px;
        		left: 40%;
        		width: 600px;
        		height: 400px;
        		text-align: left;
/*        		background-color: #00BFFF;*/
        		font-size: 20px;
        		color: #00BFFF;
        	}
        	input{
        		width: 300px;
				height: 30px;
				border-radius: calc(3px);
				border: 2px solid #66AFE9;
        	}
        	#sub{
        		margin-left: 77px;
        		height: 40px;
        		background-color: #FF0000;
        		border: 2px solid #FF0000;
        		
        	}
        	#foot{
        		position: absolute;
        		width: 100%;
        		height: 150px;
        		background-color: #000000;
        		bottom: 0;
        	}
        </style>
    </head>
    <body>
    <div id="top"></div>
    	<form action="/useraction?cmd=edit" method="post">
    		<div id="editcontent">
				<input type="hidden" value='${sessionScope.USER_IN_SESSION.userId}' name="userId"/>
				<input type="hidden" value="${USER_IN_SESSION.userPwd}" name="userPwd">
				用户名:&nbsp;<input type="text" value='${sessionScope.USER_IN_SESSION.userName}' name="userName"/><br /><br />
				&nbsp;&nbsp;&nbsp;密码:&nbsp;&nbsp;<span id="editPwd" style="color: green;"><a href="EditPwd.jsp">*修改密码</a></span><br /><br />
				&nbsp;&nbsp;&nbsp;邮箱:&nbsp;&nbsp;<input type="email" value='${USER_IN_SESSION.userEmail}' name="userEmail"/><br /><br />
				&nbsp;&nbsp;&nbsp;电话:&nbsp;&nbsp;<input type="tel" value='${USER_IN_SESSION.userTel}' name="userTel" /><br /><br />
				<input type="submit" value="修改学生信息" id="sub"/>
			</div>
    	</form>
    	<div id="foot"></div>
	</body>
</html>
