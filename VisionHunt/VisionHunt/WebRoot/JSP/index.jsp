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
    </head>
    <style type="text/css">
    	body{
    		margin: 0;
    	}
    	#top{
    		background-color: #333333;
    		width: 100%;
    		height: 110px;
    		color: white;
    	}
    	#sitename{
    		position: absolute;
    		font-family: charcoal;
    		font-size: 30px;
    		top: 45px;
    		left: 20px;
    	}
    	#search{
    		position: absolute;
    		left: 55%;
    		top: 60px;
    		border-radius: calc(3px);
    		border-width: 0;
    		width: 300px;
    		height: 30px;
    	}
    	#subsearch{
    		position: absolute;
    		right: 330px;
    		top: 60px;
    		width: 50px;
    		height: 30px;
    		border-width: 0;
    		background-color: #FFFFFF;
    		border-radius: calc(3px);
    	}
    	a{
    		color: white;
    		margin: 10px;
    		text-decoration:none;
    		color: white;
    	}
    	#nav{
    		position: absolute;
    		top: 70px;
    		left: 320px;
    		color: white;
    	}
    	#user{
    		position: absolute;
    		right: 300px;
    	}
    	#accountset{
    		position: absolute;
    		right: 135px;
    	}
    	#content{
    		position: relative;
    		top: 100px;
    		left: 20%;
    		background-color: /* #00BFFF */ white;
    		width: 600px;
    		height: 500px;
    		border: 1px solid #66AFE9;
    	}
    	.conTop{
    		width: 100%;
    		height: 12%;
    	    background-color: /* antiquewhite */ lightskyblue;
    	}
    	.conLeft{
    		width: 50%;
    		height: 76%;
    		background-color: #00FA9A;
    	}
    	.conRight{
    		position: absolute;
    		width: 50%;
    		top: 12%;
    		left: 50%;
    		height: 76%;
    	}
    	.conFoot{
    		width: 100%;
    		height: 12%;
    		background-color: lightskyblue;
    	}
    	.comment{
    		position: absolute; 
    		top: 460px; 
    		left: 380px;
    		color: blue; 
    		cursor: pointer;
    	}
    	.commContent{
    		margin-top:30px; 
			width: 530px;
			height: 35px;
			border: 1px solid #00BFFF;
		}
		.share{
			 position: absolute;
			 top: 460px; 
			 left: 320px;
			 color: blue;
		}
		.attention{
			position:absolute;
			top:10px;
			left:150px;
			width: 20px;
			height: 20px
		}
		#myself{
			position:absolute;
			top:209px;
			left:63%;
			width: 250px;
			height: 200px;
			background-color: #F1F1F1;
			font-size: 15px;
			border: 1px solid #F1F1F1;
		}
		#myself1{
			position:absolute;
			top:0;
			width: 100%;
			height: 60px;
			border-bottom: 2px solid lightskyblue;
			
		}
		#myself div:HOVER{
			background-color: white;
		}
		#myself2{
			position:absolute;
			top:65px;
			width: 100%;
			height: 30px;
			border-bottom: 2px solid lightskyblue;
		}
		#myself3{
			position:absolute;
			top:100px;
			width: 100%;
			height: 30px;
			border-bottom: 2px solid lightskyblue;
		}
		#myself4{
			position:absolute;
			top:135px;
			width: 100%;
			height: 30px;
			border-bottom: 2px solid lightskyblue;
		}
		#topic{
			position: absolute;
			top: 450px;
			left:63%;
			width: 250px;
			height: 200px;
			background-color: #F1F1F1;
			font-size: 15px;
			border: 1px solid #F1F1F1; 
		}
		#special{
			position: absolute;
			top:695px;
			left:63%;
			width: 250px;
			height: 300px;
			background-color: #F1F1F1;
			font-size: 15px;
			border: 1px solid #F1F1F1; 
		}
    </style>
    <script type="text/javascript" src="/JS/jquery-3.2.1.min.js" ></script>
    <script type="text/javascript">
    	$(function(){
    		$("#collect").click(function(){
    			 alert("收藏成功");
    			
    		});
    		$('.commBody').hide();
    		$('.comment').click(function(){
    			$(this).next('.commBody').slideToggle(600);
    		});
    		
    		$('.attention').click(function(){
    			var beAttUserId=$(this).attr("alt");
    			 $.ajax({
    				url:"/attentionaction",
    				type: "post",
    				data:{"beAttUserId":beAttUserId,"cmd":"addAttention"},
    				success:function(data){
    					alert(data);
    				},
    				error:function(){
    					alert("添加关注失败");
    				},
    				complete:function(XMLHttpRequest,textStatus){
					}
    			}) 
    		});
    	});
    </script>   
    <body>
    	<div id="top">
    		<span id="sitename">VisionHunt</span>
			<nav id="nav">
				<a href="/homepage">首页</a>
				<a href="/JSP/UploadPhoto.jsp">上传照片</a>
				<a href="/JSP/PublishArticle.jsp">发表文章</a>
				<a href="/topicaction?cmd=show">话题</a>
				<a href="">专题</a>
				<a href="">摄影家</a>
				<a href="">我的主页</a>
			</nav>
    		<input type="search" id="search" placeholder="输入标签" name="label" maxlength="50"/>
    		<a href="/searchphoto"><input type="submit" id="subsearch" value="搜索"/></a>
    		<span id="user">${sessionScope.USER_IN_SESSION.userName}</span>;
    		<span id="accountset"><a href="/JSP/AccountSet.jsp">账号设置</a>|<a href="/JSP/Login.jsp">注销</a></span>
    	</div>
    	<c:forEach items="${photoList}" var="photo" varStatus="vs">
    		<div id="content">
    			<div class="conTop"><img src='${userList[vs.index].userIcon}' width="60px" height="60px">&nbsp;&nbsp;
    							<span style="position: absolute; top: 10px;color:blue">${userList[vs.index].userName}</span>&nbsp;
    							<img alt="${userList[vs.index].userId}" src="/Images/关注.png" class="attention" title="关注${userList[vs.index].userName}"></div>
    			<div class="conLeft"><img src='${photo.photoPath}' style="width: 300px" height="380px"> </div>
    			<div class="conRight"><span style="position: absolute; left: 10px">${photo.photoDes}</span></div>
    			<div class="conFoot">
    				<span style="position: relative; top: 20px; color:blue">标签:${photo.photoLabel}</span>
    				<form action="/commentphoto?cmd=comment&userId=${USER_IN_SESSION.userId}&photoId=${photo.photoId}" method="post">
    					<span class="comment">评论</span>
    					<p class="commBody"><input type="text" class="commContent" name="commContent"></input>&nbsp;
    						<input type="submit" value="提交" style="border:1px solid #00BFFF;background-color:#00BFFF;width: 55px;height: 40px"></p>
					</form>
    				<span style="position: absolute; top: 460px; left: 440px">
    					<a href="/transmitphoto?cmd=transmit&photoId=${photo.photoId}&beTransedUserName=${userList[vs.index].userName}" style="color: blue">转发</a></span>
    				<span style="position: absolute; top: 460px; left: 500px" >
    					<a href="/collectphoto?cmd=collect&userId=${USER_IN_SESSION.userId}&photoId=${photo.photoId}" style="color: blue" id="collect">收藏</a></span></div>
    		</div><br><br><br>
    	</c:forEach>
    	<div id="myself">
			<div id="myself1"><a style="color: black" href="">${USER_IN_SESSION.userName}<br>${USER_IN_SESSION.userEmail}</a></div>
			<div id="myself2"><a style="color: black" href="">我的文章</a></div>
			<div id="myself3"><a style="color: black" href="">我关注的</a></div>
			<div id="myself4"><a style="color: black" href="">我收藏的</a></div>
    	</div>
    	<div id="topic">
    		<div id="example"></div>
    		<div id="moretopic"></div>
    	</div>
    	<div id="special">
    		<div id="example"></div>
    		<div id="morespecial"></div>
    	</div>
    	
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
