<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>文章浏览</title>
		<link rel="stylesheet" href="/CSS/ArticleBrowser.css" />
		<script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="/JS/ArticleBrowser.js"></script>
    </head>
    
    <body>
    	<div class="top">
    		<span class="webSit">VisionHunt</span>
    		<input type="hidden" class="userId" value="${USER_IN_SESSION.userId}">
    		<input type="hidden" class="userIcon" value="${USER_IN_SESSION.userIcon}">
    	</div>
    	<div class="center">
    		<div class="articleList">
    			<button class="backHomePage">回到首页</button>
    			<button class="publishArticle">发表文章</button>
    				<c:forEach items="${map.articlePageList.listData}" var="article" varStatus="vs">
    				<h2 class="arTitle">${article.arTitle}</h2>
    				<input type="hidden" class="arId" value="${article.arId}">
    				<span class="arDate">${article.arDate}</span>
    				<span class="authorName">${map.userList[vs.index].userName}</span>
					<hr class="divisionLine">
    				</c:forEach>
    			<div class="separetePage">
    				<span class="prePage">上页</span>&nbsp;
    				<input type="hidden" class="hiddenPrePage" value="${map.articlePageList.prePage}">
    				<span class="nextPage">下页</span>&nbsp;
    				<input type="hidden" class="hiddenNextPage" value="${map.articlePageList.nextPage}">
    				<span class="totalPage">总共${map.articlePageList.totalPage}页</span>&nbsp;
    				<span class="current">当前第${map.articlePageList.currentPage}/${map.articlePageList.totalPage}</span>
    			</div>
    		</div>
    	</div>
 	</body>
 	
</html>
