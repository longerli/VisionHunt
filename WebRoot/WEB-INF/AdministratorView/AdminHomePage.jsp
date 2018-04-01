<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员主页</title>
        <style type="text/css">
        	body{
        		margin: 0;
        	}
        	#top{
        		position:absolute;
        		width: 100%;
        		height: 15%;
        		background-color: #333333;
        	}
        	.sitLabel{
        		position: absolute;
        		top: 50px;
        		left: 150px;
        		font-size: 30px;
        		color: white;
        	}
        	#left{
        		position: absolute;
        		top: 15%;
        		width: 20%;
        		height: 85%;
        		background-color: #C24F4A;
        	}
        	
        	button{
        		height: 40px;
        		background-color: brown;
        		border: 1px solid #C24F4A;
        		color: white;
        	}
        	button:hover{
        		background-color: coral;
        	}
        	.photoSpecial{
        		width: 100%;
        	}
        	.phySpecial{
        		width: 100%;
        	}
        	#rightPhotoSpecial{
        		position: absolute;
        		width: 80%;
        		height: 85%;
        		top: 15%;
        		left:20%;
        		
        	}
        	
        	a{
        		position: absolute;
        		top: 20px;
        		left: 80%;
        		color: #999999;
        		text-decoration: none;
        		cursor: pointer;
        	}
        	a:HOVER {
        		color: skyblue;
        	}
        	#righhtPhySpecial{
        		position: absolute;
        		width: 80%;
        		height: 85%;
        		top: 15%;
        		left:20%;
        	}
        	#spe{
        		position:relative; 
        		display:inline-block;
         		top:100px;
        		left:10%;
        		width: 150px;
        		height: 150px;
        		background-color: yellow;
        		border: 4px solid white;
        	
        	}
        	.speTitle{
        		position: absolute;
        		top:30px;
        		left:20px;
        		
        	}
        	.delete{
        		position: absolute;
        		top:120px;
        		left:30px;
        		cursor: pointer;
        	}
        	.edit{
        		position: absolute;
        		top:120px;
        		left:90px;
        		cursor: pointer;
        	}
        
        </style>
        <script type="text/javascript" src="<%=request.getContextPath()%>/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$('#righhtPhySpecial').hide();
        		$('.photoSpecial').click(function(){
        			$('#righhtPhySpecial').hide();
        			$('#rightPhotoSpecial').show();
        		})
        		$('.phySpecial').click(function(){
        			$('#righhtPhySpecial').show();
        			$('#rightPhotoSpecial').hide();
        		})
        		
        	})
        </script>
    </head>
    
    <body>
    	<div id="top">
    		<span class="sitLabel">VisionHunt</span>
    	</div>
    	<div id="left">
    		<button type="button" class="photoSpecial">照片专题</button>
    	</div>
    	<div id="rightPhotoSpecial">
    		<a id="publishSpecial" href="/photospecialaction?cmd=releaseSpecial">发布照片专题</a>
    		<c:forEach items="${speList}" var="photoSpecial" varStatus="vs">
    			<div id="spe">
    				<span class="speTitle">${photoSpecial.speTitle}</span>
    				<label class="delete">删除</label>
    				<label class="edit">编辑</label>
    			</div>
    			<c:if test="${vs.count%5==0}"><br><br></c:if>
    		</c:forEach>
    	</div>
    	
 	</body>
</html>
