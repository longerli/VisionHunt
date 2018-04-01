<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>个人主页</title>
        <link rel="stylesheet" href="/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="/CSS/UserMainPage.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/JS/bootstrap.min.js"></script>
        <script type="text/javascript" src="/JS/UserMainPage.js"></script>
    </head>
    <body>
    	<input type="hidden" value="${USER_IN_SESSION.userId}" class="userId">
    	<div id="left">
    		<button id="back">回到首页</button>
    		<img src="${USER_IN_SESSION.userIcon}" id="headImg"/>
    		<span id="userName">${USER_IN_SESSION.userName}</span>
    		<button id="myPhoto">我的照片</button>
    		<button id="myArticle">我的文章</button>
    		<!-- <button id="myTopic">我的话题</button> -->
    		<button id="myAttention">我的关注</button>
    		<button id="myCollect">我的收藏</button>
    	</div>
    	<div id="right">
    		<div class="showPhoto">
    			<c:forEach items="${map.plist.listData}" var="photo" varStatus="vs">
    				<img alt="" src="${photo.photoPath}" class="photo">
    				<input type="hidden" class="photoId" value="${photo.photoId}">
    				<span class="photoDate">${photo.photoDate}</span>
    			</c:forEach>
    			<div class="separatePage">
    				<span class="prePage">上页</span>
    				<input type="hidden" class="photoPrePage" value="${map.plist.prePage}">
    				<span class="nextPage">下页</span>
    				<input type="hidden" class="photoNextPage" value="${map.plist.nextPage}">
    				<span class="current">当前第${map.plist.currentPage}/${map.plist.totalPage}页</span>
    				<span class="total">共${map.plist.totalPage}页</span>
    			</div>
    		</div>
    		<div class="showArticle"></div>
    		<div class="showTopic"></div>
    		<div class="showAttention"></div>
    		<div class="showCollect"></div>
    	</div>
    	
    	<div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">编辑照片</h4>
					</div>
					<div class="modal-body">
						<div class="modalPhoto">
							<img alt="" src="" class="editedPhoto"><br>
							<img alt="" src="/Images/关闭.png" class="closePhoto" title="重新上传">
							<input type="file" class="reUploadPhoto" >
						</div><br />
						
							<textarea class="modalPhotoDes" name="beEditedPhotoDes" required="required" maxlength="200"></textarea><br />
							<input type="text" class="modalPhotoLabel" name="beEditedPhotoLabel" required="required" maxlength="30"/>

					</div>
					<div class="modal-footer">
						<button type="button" class="cancelEdit" data-dismiss="modal">取消</button>
						<button type="button" class="submitEdit">提交</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="articleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">编辑文章</h4>
					</div>
					<div class="modal-body">
						<input type="text" class='beEditArticleTitle'><br>
						<div id="div1" class="toolbar"></div>
						<div id="div2" class="text"></div><br>
						<input type="text" class="beEditArticleLabel">
					</div>
					<div class="modal-footer">
						<button type="button" class="cancelEdit" data-dismiss="modal">取消</button>
						<button type="button" class="submitEdit">提交</button>
					</div>
				</div>
			</div>
		</div>
    	
 	</body>
 	
 	<script type="text/javascript" src="/JS/wangEditor.js"></script>
 	<script type="text/javascript">
 		  var E = window.wangEditor;
 		  var editor = new E('#div1', '#div2');
 		  editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0;
          editor.customConfig.uploadImgServer = '/articleimgaction';
          editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
          editor.customConfig.uploadImgMaxLength = 5;
          editor.customConfig.uploadImgTimeout = 10000;
          editor.create();
          
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
</html>
