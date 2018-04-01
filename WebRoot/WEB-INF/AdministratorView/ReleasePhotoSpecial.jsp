<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<title>发布照片专题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			body{
				margin: 0;
			}
			#top{
				position: absolute;
				width: 100%;
				height: 15%;
				background-color: #333333;
			}
			#sitLabel{
				position: absolute;
				top: 50px;
				left: 150px;
				font-size: 30px;
				color: white;
			}
			#center{
				position: absolute;
				top: 15%;
				width: 100%;
				height: 10%;
				background-color: darkgray; 
			}
			#speTitle{
				top:10%;
				height: 90%;
				width: 100%;
				
			}
			#content{
				position: absolute;
				top: 25%;
				width: 100%;
				height: 85%;
				/* background-color: #F1F1F1; */
			}
			.toolbar {
				
            	border: 1px solid #ccc;
        	}
        	.text {
        	
            	border: 1px solid #ccc;
            	height: 400px;
            	min-height: auto;
        	}
        	.submitContent{
        		position: absolute;
        		top:7px;
        		left:655px;
        		width: 18px;
        		height: 18px;
        		cursor: pointer;
        	}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JS/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('.submitContent').mouseover(function(){
					$(this).attr("src","<%=request.getContextPath()%>/Images/提交 (1).png")
				})
				$('.submitContent').mouseout(function(){
					$(this).attr("src","<%=request.getContextPath()%>/Images/提交.png")
				})
				$('.submitContent').click(function(){
					var content=editor1.txt.html();
					var speTitle=$('#speTitle').val();
					$.ajax({
						url:"photospecialaction?cmd=savePhotoSpecial",
						type:"post",
						data:{"speContent":content,"speTitle":speTitle},
						success:function(data){
							if("发布成功"==data){
								alert(data);
								window.location.href="/adminhomepage";	
							}
							if("发布失败"==data){
								alert(data);
							}
							
						},
						error:function(){
							alert("提交失败")
						},
						complete:function(XMLHttpRequest,textStatus){
						}
						
					})
				})
			})
		</script>
	</head>

	<body>
		<div id="top">
			<span id="sitLabel">VisionHunt</span>
		</div>
		<div id="center">
			<input type="text" id="speTitle" placeholder="无标题文本" required="required" maxlength="20"/>
		</div>
		<div id="content">
			<div id="div1" class="toolbar"></div>
			<img alt="提交" src="<%=request.getContextPath()%>/Images/提交.png" class="submitContent" title="提交">
    		<div id="div2" class="text"></div>
		</div>

    	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/wangEditor.js"></script>
    		<script type="text/javascript">
        	var E = window.wangEditor
        	var editor1 = new E('#div1', '#div2') 
        	editor1.customConfig.uploadImgServer = '/articleimgaction'
        	editor1.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
        	editor1.customConfig.uploadImgMaxLength = 5
        	editor1.customConfig.uploadImgTimeout = 10000
        	editor1.create()
        	
        	editor.customConfig.uploadImgHooks = {
    			before: function (xhr, editor, files) {
        		// 图片上传之前触发
        		// xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
        
       			 // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
        		// return {
        		//     prevent: true,
        		//     msg: '放弃上传'
        		// }
    			},
    			success: function (xhr, editor, result) {
        			// 图片上传并返回结果，图片插入成功之后触发
        			// xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        			alert("上传成功")
    			},
    			fail: function (xhr, editor, result) {
        			// 图片上传并返回结果，但图片插入错误时触发
        			// xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        			alert("插入图片失败")
    			},
    			error: function (xhr, editor) {
        			// 图片上传出错时触发
        			// xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        			alert("上传出错")
    			},
    			timeout: function (xhr, editor) {
        			// 图片上传超时时触发
        			// xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
    			},

    			// 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
    			// （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
    			customInsert: function (insertImg, result, editor) {
        			// 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
        			// insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

        			// 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
        			var url = result.imgUrl
        			insertImg(url)

        			// result 必须是一个 JSON 格式字符串！！！否则报错
    			}
			}
        	
    	</script>
	</body>

</html>
