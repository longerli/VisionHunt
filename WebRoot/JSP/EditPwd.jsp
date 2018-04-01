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
        <link rel="stylesheet" href="/CSS/EditPwd.css">
       <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
       <script type="text/javascript" src="/JS/EditPwd.js"></script>
    </head>
    <body>
    

    	<div id="top">
    		<span class="webSit">VisionHunt</span>
    	</div>
    		<div id="editPwd">
    			<input type="hidden" value='${USER_IN_SESSION.userId}' name="userId" class="userId"/>
    			<span class="inputNewPwd">输入新密码:</span><input type="password" name="newPwd" class="newPwd" required="required"/><br /><br/>
    			<span class="confirmNewPwd">确认新密码:</span><input type="password" name="confirmPwd" class="confirmPwd" required="required"/><br /><br />
    			<button type="button" id="edit">修改</button>
    			<button id="cancel" type="reset">取消</button>
    		</div>
 	</body>

</html>
