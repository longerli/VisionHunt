<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<title>上传图片</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="/JS/jquery-3.2.1.min.js"></script>
		<link rel="stylesheet" href="/CSS/UploadPhoto.css">
		
		<script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var photoLabel=$('#joinlabel').val();
				var topId=$('#joinTopId').val(); 
				if(photoLabel!=null){
					$('#photolabel').attr("value",photoLabel);
				} 

			})
		</script>
	</head>

	<body>
		<div id="top">
			<span class="sitName">VisionHunt</span>
		</div>
		<span style="color: red">${errorMsg}</span>
		<form  action="/photoaction?cmd=upload" method="post" enctype="multipart/form-data">
		<input type="hidden" name="userId" value='${USER_IN_SESSION.userId}'>
			<div id="center">
				<div id="left">
					<input type="file" name="photoUp" id="file0" multiple="multiple" required="required"/><br /><br />
					<img src="" id="img0" ><br />
				</div>
				<div id="right">
					<span id="pdes">描述:</span><textarea id="photodes" name="photoDes"  required="required" maxlength="200"></textarea><br /><br />
					<span id="plabel">标签:</span>
					<input type="hidden" value="${map.label}" id="joinlabel">
					<input type="hidden" value="${map.topId}" id="joinTopId" name="topId">
					<input type="text" name="photoLabel" id="photolabel" placeholder="多个标签之间已空格隔开" required="required" maxlength="15"/><br /><br />
					<input type="submit" value="提交" id="sub"/>
				</div>
					
			</div>
			
		</form>
		<script type="text/javascript">
			$("#file0").change(function() {
				var objUrl = getObjectURL(this.files[0]);
				console.log("objUrl = " + objUrl);
				if(objUrl) {
					$("#img0").attr("src", objUrl);
					//alert(objUrl);
				}else{
					alert("照片文件为空");
				}
			});
			//建立一個可存取到該file的url
			function getObjectURL(file) {
				var url = null;
				if(window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(file);
				} else if(window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				} else if(window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}
		</script>
	</body>

</html>
