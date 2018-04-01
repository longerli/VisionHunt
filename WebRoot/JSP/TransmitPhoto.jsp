<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>转发照片</title>
		<link rel="stylesheet" href="/CSS/TransmitPhoto.css">
		<script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#subTrans').click(function(){
					var userId=$('.userId').val();
					var photoId=$('.photoId').val();
					var transDes=$('#transDes').val();
					
					$.ajax({
						url:"/transmitphoto",
						type:"post",
						data:{"userId":userId,"photoId":photoId,"cmd":"save","transDes":transDes},
						success:function(data){
							if("转发成功"==data){
								alert(data);
								window.location.href="/homepage";
							}
							if("转发失败"==data){
								alert(data);
							}
							if("该照片已转发"==data){
								alert(data);
							}
						},
						error:function(){
						},
						complete:function(XMLHttprequest,textStatus){
						}
					})

				})
			})
		</script>
    </head>
    <body>
    	<div id="top">
    		<span class="sitName">VisionHunt</span>
    	</div>
    		<input type="hidden" name="userId" value="${USER_IN_SESSION.userId}" class="userId"/>
    		<input type="hidden" name="photoId" value="${map.photoId}" class="photoId"/>
    		<div id="content">
    			<span id="name">${USER_IN_SESSION.userName}转发自${map.beTransedUserName}</span><br /><br />
    			<textarea id="transDes" maxlength="200" placeholder="说些什么吧" required="required"></textarea>
    			<button type="button" id="subTrans" >提交</button>
    	</div>
    	</body>
</html>
