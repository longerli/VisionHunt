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
        <title>话题浏览</title>
        <link rel="stylesheet" href="/CSS/TopicBrowser.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$('.photo').click(function(){
        			$(this).toggleClass("photostyle");
        		});
        		
        		
        	});
        	
        </script>
        
    </head>
    <body>
    	<div id="top">
    		<span id="sitlabel">VisionHunt</span>
    	</div>
    	<div id="content">
    		<a id="launchtopic" href="/JSP/LaunchTopic.jsp"><img src="/Images/十字.png" style="width: 12px;height: 12px;"/>发起话题</a>
    		<c:forEach items="${map.topicList}" var="topic" varStatus="vs">
    			<div id="topiccontent">
    				<div class="contop">
    				
    					<a href="/topicaction?cmd=showDetails&topId=${topic.topId}" class="topicLabel">${topic.topLabel}</a>
    					
    					<div class="showPhoto">
    						<c:forEach items="${map.list[vs.index]}" var="photo" varStatus="pvs">
    							<img alt="" src="${photo.photoPath}" class="photo">
    						</c:forEach>
    					</div>
    				</div>
    			</div>
    		</c:forEach>
    		
    	</div>
 	</body>
</html>
