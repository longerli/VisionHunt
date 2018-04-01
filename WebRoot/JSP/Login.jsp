<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>


<!DOCTYPE html>
<html>
    <head>
        <title>登陆</title>
        <link rel="stylesheet" href="/CSS/Login.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/JS/Login.js"></script>
    </head>
    
    <body>
    <%
    	session.invalidate();

     %>
    		<div id="content">
    			<input type="email" id="count" required="required" placeholder="输入登陆邮箱" pattern="\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}"/><br/><br />
    			<input type="password" id="pwd" required="required" placeholder="输入密码"/><br /><br />
    			<button type="button" class="login">登陆</button><br /><br />
    			<span id="forgetPwd" >忘记密码</span>
    			<a id="foreg" href="/JSP/Regist.jsp" id=reg>注册</a>
    		</div>
 	</body>
 	
</html>