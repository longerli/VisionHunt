<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发起话题</title>
        <link rel="stylesheet" href="/CSS/launchTopic.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/JS/LaunchTopic.js"></script>
    </head>
    <body>
    	<div id="top">
    		<span id="sitlabel">VisionHunt</span>
    	</div>
    	<div id="content">
    		<h2 id="title">发起话题</h2>
    		<input type="hidden" id="userId" value="${USER_IN_SESSION.userId}">
    		<hr />
    		<input type="text" id="toplabel" placeholder="输入话题标签,最多15字" maxlength="15" required="required"/><br /><br />
    		<textarea id="topdes" placeholder="输入话题描述" maxlength="200"/></textarea>
    		<button type="button" id="submit">提交</button>
    		<button type="button" id="reset">取消</button>
    	</div>
 	</body>
</html>
