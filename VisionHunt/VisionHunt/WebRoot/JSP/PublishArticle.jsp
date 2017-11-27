<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发表文章</title>
    <style type="text/css">
    	body{
    		margin: 0;
    	}
    	#left{
    		position: absolute;
    		left: 0;
    		width: 20%;
    		height: 100%;
    		background-color: #333333;
    	}
    	#back{
    		position: absolute;
    		top: 65px;
    		left: 20%;
    		width: 150px;
    		height: 35px;
    		background-color: #333333;
    		border: 1px solid orangered;
    		border-radius:10px;
    		color: orangered;
    		outline: none;
    	}
    	#right{
    		position: absolute;
    		left: 20%;
    		height: 100%;
    		width: 80%;
    	}
    	#top{
    		position: absolute;
    		top: 0;
    		height: 10%;
    		width: 100%;
    	}
    	#arTitle{
    		position: absolute;
    		top: 5px;
    		width: 100%;
    		height: 100%;
    		border: none;
    		font-size: 20px;
    		outline: none;
    	}
        .toolbar {
        	position: absolute;
        	top:60px;
        	width: 100%;
            border: 1px solid #CCCCCC;
            background-color: #CCCCCC;
        }
        .publishArticle{
        	position: absolute;
        	left: 660px;
        	top: 2px;
        	width: 25px;
        	height: 25px;
        }
        .text {
        	position: absolute;
        	top: 90px;
            border: 1px solid #ccc;
            height: 80%;
        }
        .label{
        	position:absolute;
        	top:92%;
        	bottom:0px;
        	background-color: #CCCCCC;
        	width: 100%;
        	height: auto;
        }
      #arLabel{
        	margin:0;
        	border: 1px solid #CCCCCC;
        	width: 100%;
        	height:58px;
        	outline: none;
        } 
        /* .w-e-text img{
          height: 300px;
          width: 400px; 
    	} */
    </style>
    <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$('#arTitle').attr("value","无标题文本");
    		$('#back').click(function(){
    			window.location.href='/homepage';
    		});
    		
    		$('.publishArticle').click(function(){
        		var arContent=editor1.txt.html();
        		var arTitle=$("#arTitle").val();
        		var arLabel=$("#arLabel").val();
        		var userId=$("#userId").val(); 
        		$.ajax({
        			url:"/articleaction",
        			type:"post",
        			data:{"userId":userId,"arTitle":arTitle,"arContent":arContent,"arLabel":arLabel,"cmd":"saveArticle"},
        			success:function(){
    					alert("发表成功");
    				},
    				error:function(){
    					alert("发表失败");
    				},
    				complete:function(XMLHttpRequest,textStatus){
    					
					}
        		})
        	});
    		
    	});
    </script>
</head>
<body>
	
	<div id="left">
		<button type="button" id="back">回到首页</button>
		<input type="hidden" name="userId" id="userId" value="${USER_IN_SESSION.userId}">
	</div>
	<div id="right">
	<div id="top">
		<input type="text" id="arTitle" />
	</div>
    <div id="div1" class="toolbar"><img src="/Images/发表.png" class="publishArticle" title="发表"/></div>
    <div id="div2" class="text"></div>
    <div id="div3" class="label"><input type="text" id="arLabel" placeholder="输入文章标签，多个标签之间空格隔开" required="required"></div>
    </div>
    <script type="text/javascript" src="/JS/wangEditor.js"></script>
     <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor;
        var editor1 = new E('#div1', '#div2');
        
        editor1.customConfig.menus = [
        	'head',  // 标题
    		'bold',  // 粗体
    		'italic',  // 斜体
		    'underline',  // 下划线
		    'strikeThrough',  // 删除线
		    'foreColor',  // 文字颜色
		    'backColor',  // 背景颜色
		    'link',  // 插入链接
		    'list',  // 列表
		    'justify',  // 对齐方式
		    'quote',  // 引用
		    'emoticon',  // 表情
		    'image',  // 插入图片
		    'table',  // 表格
		    'video',  // 插入视频
		    'code',  // 插入代码
		    'undo',  // 撤销
		    'redo'  // 重复
    	]
        editor1.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0;
        editor1.customConfig.uploadImgServer = '/articleimgaction';
        editor1.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
        editor1.customConfig.uploadImgMaxLength = 5;
        editor1.customConfig.uploadImgTimeout = 10000;
        editor1.create();
        
        editor1.customConfig.uploadImgHooks = {
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
		    },
		    fail: function (xhr, editor, result) {
		        // 图片上传并返回结果，但图片插入错误时触发
		        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
		        alert("插入图片失败");
		    },
		    error: function (xhr, editor) {
		        // 图片上传出错时触发
		        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
		        alert("图片上传失败");
		    },
		    timeout: function (xhr, editor) {
		        // 图片上传超时时触发
		        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
		        alert("图片上传超时");
		    },
		
		    // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
		    // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
		    customInsert: function (insertImg, result, editor) {
		        // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
		        // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
		
		        // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
		        var url = result.imgUrl;
		        insertImg(url);
		
		        // result 必须是一个 JSON 格式字符串！！！否则报错
		    }
		}
        
    </script>
</body>
</html>
