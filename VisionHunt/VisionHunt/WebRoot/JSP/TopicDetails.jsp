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
        <style type="text/css">
        	body{
        		margin: 0;
        		background-color: #F1F1F1;
        	}
        	#top{
        		background-color: #333333;
        		width: 100%;
        		height: 120px;
        		font-family: "arial narrow";
        		font-size: 35px;
        		color: white; 
        	}
        	#sitlabel{
        		position: absolute;
        		top: 50px;
        		left: 150px;
        	}
        	#topic{
        		position: absolute;
        		top: 150px;
        		left: 26%;
        		width: 48%;
        		height: 400px;
        		background-color: white;
        	}
        	#topiclabel{
        		position: absolute;
        		top: auto;
        		width: 100%;
        		height: 100px;
        		/* background-color: #00BFFF; */ 
        		border-bottom: 2px solid #999999;
        		
        	}
        	#topicdes{
        		position: absolute;
        		top: 100px;
        		width: 100%;
        		height: 150px;
        		border-bottom: 2px solid #999999;
        		/* background-color: blue; */
        	}
        	#participatein{
        		position: absolute;
        		top: 250px;
        		width: 100%;
        		height: 150px;
        		/* background-color: black; */
        	}
        	.label{
        		position: absolute;
        		top:20px;
        		left:20px;
        		font-size: 30px;
        		color: #333333;
        		
        	}
        	.des{
        		position:absolute;
        		left:20px;
        		color: #333333; 
        		
        	}
        	.join{
        		position:absolute;
        		top:10px;
        		left:85px;
        		width: 100px;
        		height: 30px;
        		background-color:green;
        		outline: none;
        		border:1px solid green;
        		color: white;
        	}
        	#photo{
        		position: relative;
        		top:400px;
        		margin-top: 70px;
        		width: 48%;
        		height:450px;
        		left:26%;
        		background-color: white;
        		border: 2px solid #999999;
        	}
        	.joinTopic{
        		position:absolute;
        		top:45px;
        		left:20px;
        	}
        	.uploadPhoto{
        		width: 100px;
        		height: 50px;
        		background-color:green;
        		outline: none;
        		border:1px solid green;
        		color: white;
        	}
        	.publishArticle{
        		width: 100px;
        		height: 50px;
        		background-color:green;
        		outline: none;
        		border:1px solid green;
        		color: white;
        	}
        	.photoTop{
        		width: 100%;
        		height: 100px;
        		/* background-color: blue; */
        		border-bottom: 2px solid #999999;
        	}
        	.photoCenter{
        		width: 100%;
        		height: 280px;
        		/* background-color: olive; */
        		border-bottom: 2px solid #999999;
        	}
        	.photoFoot{
        		width: 100%;
        		height: 70px;
        		/* background-color: orange; */
        		
        	}
        	.headImg{
        		width: 95px;
        		height: 100px;
        	}
        	.userName{
        		position: absolute;
        		top: 20px;
        		left:100px;
        		cursor: pointer;
        	}
        	.userName:HOVER{
        		color: #1296db;
        	}
        	.attention{
        		position: absolute;
        		top:25px;
        		left:150px;
        		width: 15px;
        		height: 15px;
        		cursor: pointer;
        	}
        	.centerLeft{
        		width: 40%;
        		height: 280px;
        		/* background-color:lime; */
        		border-right: 1px solid #999999;
        	}
        	.centerRight{
        		position:absolute;
        		top:100px;
        		left:40%;
        		width: 60%;
        		height: 280px;
        		/* background-color: blue; */ 
        	}
        	.realPhoto{
        		width: 100%;
        		height: 280px;
        	}
        	.photoDes{
        		position: absolute;
        		top:30px;
        		left:20px;
        	}
        	.photoLabel{
        		position:absolute;
        		top: 400px;
        	}
        	.photoComment{
        		position: absolute;
        		top:400px;
        		left:340px;
        		width: 20px;
        		height: 20px;
        		cursor: pointer;
        	}
        	.photoTransmit{
        		position: absolute;
        		top:400px;
        		left:420px;
        		width: 20px;
        		height: 20px;
        		cursor: pointer;
        	}
        	.photoCollect{
        		position: absolute;
        		top:400px;
        		left:500px;
        		width: 20px;
        		height: 20px;
        		cursor: pointer;
        	}
        	.content{
        		margin-top: 60px;
        		width: 640px;
				height: 35px;
				border: 1px solid #00BFFF;
        	}
        	.submitComment{
        		position:relative;
        		margin-left:20px;
        		width: 60px;
        		height: 40px;
        		background-color: green;
        		border: 1px solid green;
        		color: white;
        	}
        </style>
        <script type="text/javascript" src="/JS/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$('.joinTopic').hide();
        		$('.commContent').hide();
        		$('.join').click(function(){
        			$(this).next('.joinTopic').slideToggle(600);
        		});
        		$('.attention').mouseover(function(){
        			$(this).attr("src","/Images/attentionmouseover.png");
        		});
        		$('.attention').mouseout(function(){
        			$(this).attr("src","/Images/十字.png");
        		});
        		$('.photoComment').mouseover(function(){
        			$(this).attr("src","/Images/评论 (1).png");
        		});
        		$('.photoComment').mouseout(function(){
        			$(this).attr("src","/Images/评论.png");
        		});
        		$('.photoTransmit').mouseover(function(){
        			$(this).attr("src","/Images/转发 (1).png");
        		});
        		$('.photoTransmit').mouseout(function(){
        			$(this).attr("src","/Images/转发.png");
        		});
        		$('.photoCollect').mouseover(function(){
        			$(this).attr("src","/Images/收 藏 (1).png");
        		});
        		$('.photoCollect').mouseout(function(){
        			$(this).attr("src","/Images/收 藏.png");
        		});
        		
        		$('.attention').click(function(){
        			var beAttUserId=$(this).attr("alt");
        			$.ajax({
        				url:"attentionaction",
        				type:"post",
        				data:{"beAttUserId":beAttUserId,"cmd":"addAttention"},
        				success:function(data){
        					alert(data);
        				},
        				error:function(){
        					alert("关注失败");
        				},
        				complete:function(XMLHttpRequest,textStatus){
						}
        			});
        		});
        		
        		$(".photoComment").click(function(){
        			$(this).next('.commContent').slideToggle(600);
        		});
        		
        		$('.submitComment').click(function(){
        			var photoCommContent=$(this).prev().prev().val();
        			var photoId= $(this).prev().val();
        			var userId=$('.currentUserId').val();
        			var topId=$('.topId').val();
        			/* alert(photoCommContent+"--"+photoId+"---"+userId); */
        			$.ajax({
        				url:"commentphoto",
        				type:"post",
        				data:{"commContent":photoCommContent,"photoId":photoId,"userId":userId,"cmd":"commentFromTopic"},
        				success:function(){
        					alert("评论成功");
        					window.location.href="/topicaction?cmd=showDetails&topId="+topId; 
        				},
        				error:function(){
        					alert("评论失败");
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			}); 
        		});
        		
        	});
        </script>
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
