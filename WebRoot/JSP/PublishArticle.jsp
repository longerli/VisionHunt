<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发表文章</title>
    <link rel="stylesheet" href="/CSS/PublishArticle.css">
    <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/JS/PublishArticle.js"></script>
</head>
<body>
	
	<div id="left">
		<button type="button" id="back">回去看看</button>
		<input type="hidden" name="userId" id="userId" value="${USER_IN_SESSION.userId}">
	</div>
	<div id="right">
	
	<div id="top">
		<input type="text" id="arTitle" required="required" maxlength="20"/>
	</div>
    <div id="div1" class="toolbar"><img src="/Images/发表.png" class="publishArticle" title="提交"/></div>
    <div id="div2" class="text"></div>
    <input type="hidden" class="articleContent" value="${article.arContent}">
    <div id="div3" class="label">
		<input type="hidden" id="topId" value="${map.topId}">
		<input type="hidden" id="topLabel" value="${map.topLabel}">
    	<input type="text" id="arLabel" placeholder="输入文章标签，多个标签之间空格隔开" required="required"></div>
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
