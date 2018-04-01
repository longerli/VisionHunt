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
        <link rel="stylesheet" href="/CSS/AccountSet.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
        	$(function(){
        		var userId=$('.userId').val();
        		var userPwd=$('.userPwd').val();
        		var userName=$('.userName').val();
        		var userEmail=$('.userEmail').val();
        		var userTel=$('.userTel').val();
        		
        		$('#sub').click(function(){
        			$.ajax({
        				url:"/useraction",
        				type:"post",
        				data:{"cmd":"edit","userName":userName,"userId":userId,"userPwd":userPwd,"userEmail":userEmail,"userTel":userTel},
        				success:function(data){
        					if("修改成功"==data){
        						alert(data);
        						window.location.href="/homepage";
        					}
        					if("修改失败"==data){
        						alert(data);
        					}
        				},
        				error:function(){},
        				complete:function(XMLHttpRequest,textStatus){}
        			})
        		})
        	})
        </script>
    </head>
    <body>
    <div id="top">
    	<span class="sitName">VisionHunt</span>
    </div>
    		<div id="editcontent">
				<input type="hidden" value='${sessionScope.USER_IN_SESSION.userId}' name="userId" class="userId"/>
				<input type="hidden" value="${USER_IN_SESSION.userPwd}" name="userPwd" class="userPwd">
				用户名:&nbsp;<input type="text" value='${sessionScope.USER_IN_SESSION.userName}' name="userName" class="userName"/><br /><br />
				&nbsp;&nbsp;&nbsp;密码:&nbsp;&nbsp;<span id="editPwd" style="color: green;"><a href="EditPwd.jsp">*修改密码</a></span><br /><br />
				&nbsp;&nbsp;&nbsp;邮箱:&nbsp;&nbsp;<input type="email" value='${USER_IN_SESSION.userEmail}' name="userEmail" class="userEmail"/><br /><br />
				&nbsp;&nbsp;&nbsp;电话:&nbsp;&nbsp;<input type="tel" value='${USER_IN_SESSION.userTel}' name="userTel" class="userTel"/><br /><br />
				<button type="button" id="sub">修改信息</button>
			</div>
	</body>
</html>
