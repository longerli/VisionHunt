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
        <title>修改密码</title>
        <style type="text/css">
        	body{
        		margin: 0;
        		text-align: center;
        		background-color: #FFEFD5;
        	}
        	#top{
        		background: linear-gradient(to right, cornflowerblue, mediumspringgreen);
				width: 100%;
				height: 50px;
        	}
        	#editPwd{
        		position: absolute;
        		top:200px;
        		left: 450px;
        		width: 600px;
        		height: 400px;
/*        		background-color: #66AFE9;*/
				font-size: 20px;
        	}
        	#foot{
        		position: absolute;
        		width: 100%;
        		height: 150px;
        		background-color: #000000;
        		bottom: 0;
        	}
        	input{
        		width: 200px;
        		height: 30px;
        		border: 1 solid #00BFFF;
        	}
        	#edit{
        		width: 90px;
        		border: 1 solid;
        		border-radius: calc(5px);
        	}
        	#cancel{
        		width: 90px;
        		border: 1 solid;
        		border-radius: calc(5px);
        	}
        </style>
    </head>
    <body>
    	<div id="top"></div>
    	<form action="/useraction?cmd=editPwd" method="post">
    		<div id="editPwd">
    			<input type="hidden" value='${USER_IN_SESSION.userId}' name="userId"/>
    			<span style="color: red;">${errormessage}</span>
    			输入新密码:<input type="password" name="newPwd" /><br /><br/>
    			确认新密码:<input type="password" name="confirmPwd"/><br /><br />
    			<input type="submit" value="修改" id="edit"/>
    			<input type="reset" value="取消" id="cancel"/>
    		</div>
    	</form>
    	<div id="foot"></div>
 	</body>
</html>
