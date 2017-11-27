<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
       <!-- <link rel="stylesheet" href="/VisionHunt/WebRoot/CSS/Regist.css" type="text/css" /> -->
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
        	#account{
        		position: absolute;
        		font-size: 15px;
        		left: 80%;
        		top: 25px;
        	}
        	input{
        		width: 300px;
        		height: 30px;
        		border-radius: calc(3px);
        		border: 2px solid #66AFE9;
        	}
        	#content{
        		width: 600px;
				height: 400px;
        		position: absolute;
        		top: 110px;
        		left: 42%;
        		text-align: left;
        	}
        	#regist{
        		background-color: deepskyblue;
        		height: 40px;
        	}
        	#foot{
        		position: absolute;
        		width: 100%;
        		height: 150px;
        		background-color: #66AFE9;
        		bottom: 0;
        	}
       </style>
       <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
       <script type="text/javascript" src="/JS/jquery.validate.min.js"></script>
    </head>
    <body>
    	<div id="top">
    		<span id="account"><a href="/JSP/Login.jsp"><u>已有账号?登陆</u></a></span>
    	</div>
    	<form action="/useraction?cmd=save" method="post" id="fo" enctype="multipart/form-data">
    	<span style="color: red">${errorMsg}</span>
    		<div id="content">
    			<h4>现在就加入VisionHunt,成为大影家</h4>
    			<input type="text" id="userName" name="userName" required="required" placeholder="请输入用户名" maxlength="20"/><br /><br />
    			<input type="email" id="userEmail" name="userEmail" required="required" placeholder="输入登陆邮箱" maxlength="40"/><br /><br />
    			<input type="password" id="userPwd" name="userPwd" required="required" pattern="^(\w){6,20}$" placeholder="输入登陆密码" maxlength="20"/><br/><br />
    			<input type="password" id="confirmPwd" name="confirm" required="required" placeholder="确认密码" /><br /><br />
    			<input type="tel" id="userTel" name="userTel" pattern="^1(3|4|5|7|8)\d{9}$" required="required" placeholder="手机号"/><br /><br />
    			<input type="file" name="userIcon" id="userIcon" value="选择文件"><br /><br />
    			<input type="submit" id="regist" value="注册" />
    		</div>
    		<div id="foot"></div>
    	    <script type="text/javascript">
    			$(function(){
    				$("#userTel").click(function(){
    					var pwd=$("#userPwd").val();
    					var confirm=$("#confirmPwd").val()
    					if(pwd!=confirm){
    						var mtag=$("<font color='red'></font>");
    						mtag.text("两次输入不一致");
    						$("#confirmPwd").after(mtag);
    						$("#regist").event.preventDefault();
    					}
    				});
    				
    			});
    		</script>
    	</form>
 	</body>
</html>
