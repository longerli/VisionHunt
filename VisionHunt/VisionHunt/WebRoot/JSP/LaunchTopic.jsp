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
        
        <style type="text/css">
        	body{
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
        		top: 160px;
        		left: 450px;
        		width: 600px;
        		height: 600px;
        		border: 1px solid #999999;
        	}
        	#title{
        		margin-left: 50px;
        	}
        	#toplabel{
        		margin-top: 20px;
        		margin-left: 50px;
        		width: 300px;
        		height: 40px;
        		border: 1px solid #999999;
        	}
        	#topdes{
        		margin-top: 20px;
        		margin-left: 50px;
        		width: 400px;
        		height: 300px;
        		border: 1px solid #999999;
        	}
        	#submit{
        		position: absolute;
        		top: 500px;
        		left: 50px;
        		width: 100px;
        		height: 45px;
        		background-color: #66AFE9;
        		outline: none;
        	}
        	#submit:hover{
        		background-color: #00BFFF;
        	}
        	#reset{
        		position: absolute;
        		top: 500px;
        		left: 350px;
        		width: 100px;
        		height: 45px;
        		background-color: #66AFE9;
        	}
        	#reset:hover{
        		background-color: #00BFFF;
        	}
        </style>
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$("#reset").click(function(){
        			$("#toplabel").val("");
        			$("#topdes").val("");
        			
        		});
        		$("#submit").click(function(){
        			var userId=$('#userId').val();
        			var topLabel=$('#toplabel').val();
        			var topDes=$('#topdes').val();
        			$.ajax({
        				url:"/topicaction?cmd=save",
        				type:"post",
        				data:{"userId":userId,"topLabel":topLabel,"topDes":topDes},
        				success:function(){
        					alert("发布成功");
        					window.location.href='/topicaction?cmd=show';
        				},
        				error:function(){
        					alert("发布失败");
        				},
        				complete:function(XMLHttpRequest,textStstus){
        					
        				}
        			})
        		})
        	});
        </script>
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
