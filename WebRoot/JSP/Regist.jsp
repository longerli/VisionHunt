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
       <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
       <script type="text/javascript" src="/JS/bootstrap.min.js"></script>
       <script type="text/javascript" src="/JS/Regist.js"></script>
       <link rel="stylesheet" href="/CSS/Regist.css" />
       <link rel="stylesheet" href="/CSS/bootstrap.min.css" />
       
    </head>
    
    <body>
    	<div id="top">
    		<span id="account" data-toggle="modal" data-target="#myModal">已有账号?登陆</span>
    	</div>
    		<div id="content">
    			<h4>现在就加入VisionHunt,成为大影家</h4>
    			<input type="text" id="userName" required="required" placeholder="请输入用户名" maxlength="10"/><br /><br />
    			<input type="email" id="userEmail" required="required" placeholder="输入登陆邮箱" maxlength="40"/><br /><br />
    			<input type="password" id="userPwd" required="required" pattern="^(\w){6,20}$" placeholder="输入登陆密码" maxlength="20"/><br/><br />
    			<input type="password" id="confirmPwd" required="required" placeholder="确认密码" /><br /><br />
    			<input type="tel" id="userTel" pattern="^1(3|4|5|7|8)\d{9}$" required="required" placeholder="手机号"/><br /><br />
    			<input type="file" id="userIcon" value="选择文件" required="required"><br /><br />
    			<button type="button" class="regist" >注册</button>
    		</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">登陆</h4>
				</div>
				<div class="modal-body">
					<span class="countEmail">邮箱:</span><br>
					<input type="email" class="userEmail" placeholder="请输入邮箱"><br>
					<span class="countPwd">密码:</span><br>
					<input type="password" class="pwd" placeholder="请输入密码">
				</div>
				<div class="modal-footer">
					<button type="button" class="cancel" data-dismiss="modal">关闭</button>
					<button type="button" class="login">登陆</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
