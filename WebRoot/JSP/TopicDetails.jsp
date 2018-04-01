<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>话题详情</title>
       <link rel="stylesheet" href="/CSS/TopicDetails.css">
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="/JS/TopicDetails.js"></script>
    </head>
    <body>
    	<div id="top">
    		<span id="sitlabel">VisionHunt</span>
    		<input type="hidden" value="${USER_IN_SESSION.userId}" class="currentUserId">
    	</div>
    	<div id="topic">
    		<div id="topiclabel">
    			<span class="label">${map.topic.topLabel}</span>
    			<input type="hidden" value="${map.topic.topId}" class="topId">
    		</div>
    		<div id="topicdes">
    			<pre class="des">${map.topic.topDes}</pre>
    		</div>
    		<div id="participatein">
    			<button type="button" class="join">参与话题</button>
    			<p class="joinTopic"><button type="button" class="uploadPhoto">上传照片</button>&nbsp;&nbsp;
    			<button type="button" class="publishArticle">发表文章</button></p>
    		</div>
    	</div>
    	<c:forEach items="${map.photoList}" var="photo" varStatus="vs">
    		<div id="photo">
    			<div class="photoTop">
    				<img alt="" src="${map.userList[vs.index].userIcon}" class="headImg">
    				<span class="userName">${map.userList[vs.index].userName}</span>
    				<img alt="${map.userList[vs.index].userId}" src="/Images/十字.png" class="attention">
    			</div>
    			<div class="photoCenter">
    				<div class="centerLeft"><img alt="" src="${photo.photoPath}" class="realPhoto"></div>
    				<input type="hidden" value="${photo.photoId}" class="photoId">
    				<div class="centerRight"><span class="photoDes">${photo.photoDes}</span></div>
    				
    			</div>
    			<div class="photoFoot">
    				<span class="photoLabel">标签:&nbsp;${photo.photoLabel}</span>
    				<img alt="评论" src="/Images/评论.png" class="photoComment">
    				<p class="commContent"><input type="text" class="content" >
    				<input type="hidden" value="${photo.photoId}" class="photoId">
    				<button type="button" class="submitComment">提交</button></p>
    				<img alt="转发" src="/Images/转发.png" class="photoTransmit">
    				<img alt="收藏" src="/Images/收 藏.png" class="photoCollect">
    				
    			</div>
    		</div><br /><br />
    	</c:forEach>
    	
    	<script>
    		window._bd_share_config={"common":{"bdSnsKey":{},
    		"bdText":"","bdMini":"2",
    		"bdMiniList":false,
    		"bdPic":"","bdStyle":"0",
    		"bdSize":"16"},
    		"slide":{"type":"slide","bdImg":"0","bdPos":"left","bdTop":"180"},
    		"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],
    		"viewText":"分享到：","viewSize":"16"}};
    		with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
    	</script>
    	
 	</body>
</html>
