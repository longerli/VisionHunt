<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>照片专题浏览</title>
       <link rel="stylesheet" href="/CSS/PhotoSpecialBrowser.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/JS/PhotoSpecialBrowser.js"></script>
    </head>
    <body>
    	<div id="top">
    		<span id="sitLabel">Visionhunt</span>
    	</div>
    	<div class="center">
    		<c:forEach items="${map.list}" var="photoSpecial" varStatus="vs">
    			<div class="spe">
    				<img alt="" src="${map.imgurl[vs.index]}" class="conImg">
    				<input type="hidden" class="speId" value="${photoSpecial.speId}">
    				<span class="speTitle">${photoSpecial.speTitle}</span>
    			</div>
    			<c:if test="${vs.count%5==0}"><br><br></c:if>
    		</c:forEach>
    	</div>
    	
 	</body>
</html>