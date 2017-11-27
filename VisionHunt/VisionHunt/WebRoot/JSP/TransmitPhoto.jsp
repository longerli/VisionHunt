<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>转发照片</title>
        <style type="text/css">
        	body{
        		margin: 0;
        	}
        	#top{
        		background: linear-gradient(to right, cornflowerblue, mediumspringgreen);
				width: 100%;
				height: 100px;
        	}
        	#content{
        		position: absolute;
        		width: 600px;
        		height: 500px;
        		top: 130px;
        		background-color: aliceblue;
        		text-align: center;
        		border: 2px solid #00BFFF;
        	}
        	#transDes{
        		position: absolute;
        		width: 400px;
        		height: 300px;
        		left: 20%;
        		border: 1px solid #00BFFF;
        	}
        	#subTrans{
        		position: absolute;
        		width: 407px;
        		height: 40px;
        		background-color: #00BFFF;
        		top: 370px;
        		left: 120px;
        		border: 1px solid #00BFFF;
        	}
        </style>
    </head>
    <body>
    	<div id="top"></div>
    	<form action="/transmitphoto?cmd=save" method="post">
    		<input type="hidden" name="userId" value="${USER_IN_SESSION.userId}"/>
    		<input type="hidden" name="photoId" value="${PHOTOID_IN_SESSION}"/>
    		<div id="content">
    			<span id="name" style="color: blue;">${USER_IN_SESSION.userName}转发自${TRANSEDUSERNAME_IN_SESSION}</span><br /><br />
    			<textarea id="transDes" name="transDes" maxlength="200" placeholder="转发描述"></textarea>
    			<input type="submit" id="subTrans" value="提交"/>
    		</div>
    	</form>
 	</body>
</html>
