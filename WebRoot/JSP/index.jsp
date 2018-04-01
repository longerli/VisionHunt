<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
        <link rel="stylesheet" href="/CSS/Index.css"> 
    </head>
    
    <script type="text/javascript" src="/JS/jquery-3.2.1.min.js" ></script>
    <script type="text/javascript" src="/JS/Index.js"></script>   
    <body>
    	<div id="top">
    		<span id="sitename">VisionHunt</span>
			<nav id="nav">
				<a href="/homepage">首页</a>
				<a href="/JSP/UploadPhoto.jsp">上传照片</a>
				<a href="/articleaction?cmd=reviewArticle&currentPage=1">文章</a>
				<a href="/topicaction?cmd=show">话题</a>
				<a href="/photospecialaction?cmd=browser">专题</a>
				<a href="/useraction?cmd=userMainPage&currentPage=1&userId=${USER_IN_SESSION.userId}">我的主页</a>
			</nav>
			
    		<input type="text" id="search" placeholder="输入搜索标签" maxlength="15"/>
    		<button type="button" id="subsearch">搜索</button>
    		
    		<span id="user">${sessionScope.USER_IN_SESSION.userName}</span>
    		<input type="hidden" class="userId" value="${USER_IN_SESSION.userId}">
    		<span id="accountset"><a href="/JSP/AccountSet.jsp">账号设置</a>|<a href="/JSP/Login.jsp">注销</a></span>
    	</div>
    	
    	<div class="center">
    	<c:forEach items="${map.photoList}" var="photo" varStatus="vs">
    		<div id="content">
    			<div class="conTop">
    				<img src='${map.userList[vs.index].userIcon}' class="userIcon">&nbsp;&nbsp;
    				<span class="userName">${map.userList[vs.index].userName}</span>&nbsp;
    				<img alt="${map.userList[vs.index].userId}" src="/Images/十字.png" title="关注${map.userList[vs.index].userName}" class="attentionIcon">
    			</div>
    			<div class="conLeft">
    				<img src='${photo.photoPath}' class="photo">
    			</div>
    			<div class="conRight">
    				<span class="photoDes">${photo.photoDes}</span>
    			</div>
    			<div class="conFoot">
    				<span class="photoLabel">标签:${photo.photoLabel}</span>
    				<img class="comment" src="/Images/评论.png" title="评论">
    				<p class="commBody">
    					<input type="text" class="commContent" name="commContent" maxlength="200"></input>&nbsp;
    					<button type="button" class="subPhotoComment" style="border:1px solid #00BFFF;background-color:#00BFFF;width: 55px;height: 40px" >提交</button>
    				</p>
    				<a href="/transmitphoto?cmd=transmit&photoId=${photo.photoId}&beTransedUserName=${map.userList[vs.index].userName}">
    					<img alt="" src="/Images/转发.png" class="transmitPhoto" title="转发">
    				</a>
    				<input type="hidden" class="photoId" value="${photo.photoId}" >
    				<img class="collect" src="/Images/收 藏.png" title="收藏">
    			</div>
    		</div><br><br><br>
    	</c:forEach>
    	</div>
    	
    	<div id="special">
    		<div id="speExample">
    			<img alt="" src="${map.speImgUrl}" class="speImgUrl">
    			<span class="speTitle">${map.photoSpecial.speTitle}</span>
    		</div>
    		<div id="moreSpe">
    			<span class="reviewMore">查看专题</span>
    		</div>
    	</div>

	<script>
	window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},
	"slide":{"type":"slide","bdImg":"0","bdPos":"left","bdTop":"180"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
	</script>
    	
 	</body>
</html>
