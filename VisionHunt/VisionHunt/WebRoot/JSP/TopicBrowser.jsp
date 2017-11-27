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
        <style type="text/css">
        	body{
        		background-color: #CCCCCC;
        		margin: 0;
        	}
        	#top{
        		background-color: #333333;
        		width: 100%;
        		height: 150px;
        		font-family: "arial narrow";
        		font-size: 35px;
        		color: white; 
        	}
        	#sitlabel{
        		position: absolute;
        		top: 50px;
        		left: 150px;
        	}
        	#content{
        		position: absolute;
        		top: 150px;
        		left: 15%;
        		background-color: white;
        		width: 70%;
        		height: auto;
        		
        	}
        	#launchtopic{
        		position: absolute;
        		top: 20px;
        		color: #999999;
        		text-decoration: none;
        		left: 85%;
        	}
        	#launchtopic:hover{
        		color: skyblue;
        	}
        	
        	#topiccontent{
        		position:relative;
        		width: 500px;
        		height: 500px;
        		margin-top: 70px;
        		margin-left: 250px;
        		/* background-color: #F1F1F1; */
        		color: black;
        		border: 2px solid #F1F1F1;
        	}
        	.contop{
        		margin-top: 0;
        		width: 100%;
        		height: 90px;
        	}
        	.topicLabel{
        		position:absolute;
        		top:25px;
        		left:25px;
        		color: #333333;
        	}
        	.topicLabel:HOVER {
        		color: #66AFE9;
        	}
        	.showPhoto{
        		position: absolute;
        		top:100px;
				left:25px; 
        		width: 450px;
        		height: 360px;
        	}
        	.photo{
        		width: 140px;
        		height: 180px;
        		border: 2px solid white;
        		outline: none;
        	}
        	.photo:HOVER {
        		cursor:pointer;
				/* -webkit-filter: opacity(80%); */
			}
			.photostyle{
				transform:scale(3,2);
			}
        </style>
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
    		<c:forEach items="${topicList}" var="topic" varStatus="vs">
    			<div id="topiccontent">
    				<div class="contop">
    					<a href="/topicaction?cmd=showDetails&topId=${topic.topId}"><span class="topicLabel">${topic.topLabel}</span></a>
    					<div class="showPhoto">
    						<img src="${list[vs.index][0].photoPath}" class="photo">
    						<img src="${list[vs.index][1].photoPath}" class="photo">
    						<img src="${list[vs.index][2].photoPath}" class="photo"><br>
    						<img src="${list[vs.index][3].photoPath}" class="photo">
    						<img src="${list[vs.index][4].photoPath}" class="photo">
    						<img src="${list[vs.index][5].photoPath}" class="photo"> 
    					</div>
    				</div>
    			</div>
    		</c:forEach>
    		
    	</div>
 	</body>
</html>
