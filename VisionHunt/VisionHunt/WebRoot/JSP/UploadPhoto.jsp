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
		<style type="text/css">
			body{
				margin: 0;
				background-color: #F5F5DC;
			}
			#top{
				background: linear-gradient(to right, cornflowerblue, mediumspringgreen); 
				width: 100%;
				height: 120px;
			}
			#center{
				width: 100%;
				height: 450px;
				position: absolute;
				top: 150px;
			}
			#file0{
				display: inline-block;
    			background: #00BFFF;
    			border: 1px solid #99D3F5;
			    border-radius: 4px;
			    padding: 4px 12px;
			    overflow: inherit;
			    color: #1E88C7;
			    text-decoration: none;
			    text-indent: 0;
			    line-height: 20px;
   				opacity: 100%;
			}
			#left{
				position: absolute;
				width: 40%;
				height: 100%;
				text-align: center;
				
			}
			#right{
				position: relative;
				left: 40%;
				width: 600px;
				height: 100%;
				text-align: left;
			}
			#pdes{
				position: absolute;
				top: 40px;
				
			}
			#photodes{
				position: absolute;
				width: 400px;
				height: 250px;
				top: 40px;
				left: 45px;
			}
			#plabel{
				position: absolute;
				top: 300px;
			}
			#photolabel{
				position: absolute;
				width: 400px;
				height: 30px;
				top: 300px;
				left: 45px;
			}
			#sub{
				position: absolute;
				width: 100px;
				height: 30px;
				top: 350px;
				left: 45px;
			}
			#foot{
				position: absolute;
				bottom: 0;
				width: 100%;
				height: 120px;
				background-color: #000000;
				color: white;
			}
		</style>
	</head>

	<body>
		<div id="top"></div>
		<span style="color: red">${errorMsg}</span>
		<form  action="/photoaction?cmd=upload" method="post" enctype="multipart/form-data">
		<input type="hidden" name="userId" value='${USER_IN_SESSION.userId}'>
			<div id="center">
				<div id="left">
					<input type="file" name="photoUp" id="file0" multiple="multiple" /><br /><br />
					<img src="" id="img0" width="450px" height="320px"><br />
				</div>
				<div id="right">
					<span id="pdes">描述:</span><textarea id="photodes" name="photoDes" required="required" maxlength="500"></textarea><br /><br />
					<span id="plabel">标签:</span><input type="text" name="photoLabel" id="photolabel" placeholder="多个标签关键字之间以空格隔开"/><br /><br />
					<input type="submit" value="提交" id="sub"/>
				</div>
					
			</div>
			
		</form>
		<!-- <div id="foot"></div> -->
		<script type="text/javascript">
			$("#file0").change(function() {
				var objUrl = getObjectURL(this.files[0]);
				console.log("objUrl = " + objUrl);
				if(objUrl) {
					$("#img0").attr("src", objUrl);
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
