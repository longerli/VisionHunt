<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登陆</title>
        <style type="text/css">
        	body{
        		background-image: url(/Images/缆车.jpg);
        	}
        	#content{
        		width: 400px;
        		height: 300px;
        		position: absolute;
        		margin-top: 250px;
        		margin-left: 550px;
        		font-size: 20px;
        		text-align: center;
        	}
        	#count{
        		width: 300px;
        		height: 40px;
        		border-radius: calc(3px);
        		border: 2px solid #66AFE9;
        		box-shadow: none;
        		transition: all 0.3s ease 0s;
        	}
        	#pwd{
        		width: 300px;
        		height: 40px;
        		border-radius: calc(5px);
        		border: 2px solid #66AFE9;
        	}
        	#login{
        		width: 300px;
        		height: 40px;
        		background-color: #66AFE9;
        		border-radius: calc(10px);
        		border: 2px solid #66AFE9;
        	}
        	#checkbox{
        		position: absolute;
        		left: 70px;
        		opacity:2;
        	}
        	.text{
        		position: absolute;
        		left: 90px;
        		font-size: 15px;
        	}
        	#foreg{
        		position: absolute;
        		font-size: 15px;
        		left: 230px;
        		bottom: 68px;
        	}
        </style>
       
    </head>
    <body>
    <%
    	session.invalidate();

     %>
		<form action="/useraction?cmd=login" method="post">
    		<div id="content">
    			<span style="color: red"">${errorMessage}</span>
    			<input type="email" id="count" name="email" required="required" maxlength="40" placeholder="输入登陆邮箱"/><br/><br />
    			<input type="password" id="pwd" name="password" required="required" pattern="^(\w){6,20}$" maxlength="20" placeholder="输入密码"/><br /><br />
    			<input type="submit" value="登陆" id="login"/><br /><br />
    			<input type="checkbox" value="null" id="checkbox" name="check"/>
    			<span class="text"><font color="#66AFE9"><u>记住我</u></font></span>
    			<span id="foreg"><font color="#66AFE9"><u><a href="" id="forgetpwd">忘记密码</a>|<a href="/JSP/Regist.jsp" id=reg>注册</a></u><font/></span>
    		</div>
    	</form>
 	</body>
</html>