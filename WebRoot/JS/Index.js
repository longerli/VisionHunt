$(function() {
	$('.searchResult').hide();
	var userId = $('.userId').val();
	
	$('.speTitle').hide();
	$('.speImgUrl').mouseover(function(){
		$('.speTitle').show();
	})
	
	$('.speImgUrl').mouseout(function(){
		$('.speTitle').hide();
	})
	
	$('.reviewMore').click(function(){
		window.location.href="/photospecialaction?cmd=browser";
	})
	
	$('.reviewMoreTopic').click(function(){
		window.location.href="/topicaction?cmd=show";
	})
	
	$('.collect').click(function() {
		
		var photoId = $(this).prev().val();
		$.ajax({
			url : "/collectphoto",
			type : "post",
			data : {
				"userId" : userId,
				"photoId" : photoId,
				"cmd" : "collect"
			},
			success : function(data) {
				alert(data);
			},
			error : function() {
			},
			complete : function(XMLHttpRequest, textStatus) {}
		})
	});
	$('.commBody').hide();
	$('.comment').click(function() {
		$(this).next('.commBody').slideToggle(600);
	});

	$('.attentionIcon').click(function() {
		var beAttUserId = $(this).attr("alt");
		$.ajax({
			url : "/attentionaction",
			type : "post",
			data : {
				"beAttUserId" : beAttUserId,
				"cmd" : "addAttention"
			},
			success : function(data) {
				alert(data);
			},
			error : function() {
				
			},
			complete : function(XMLHttpRequest, textStatus) {}
		})
	});
	
	$('.subPhotoComment').click(function(){
		var photoId=$(this).parent().parent().children(".collect").prev().val();
		var commContent=$(this).parent().children('.commContent').val();
		
		$.ajax({
			url:"/commentphoto",
			type:"post",
			data:{"cmd":"comment","userId":userId,"photoId":photoId,"commContent":commContent},
			success:function(data){
				if("评论成功"==data){
					alert(data);
					window.location.href="/homepage";
				}
				if("评论失败"==data){
					alert(data);
				}
			},
			error:function(){
				
			},
			complete:function(XMLHttpRequest, textStatus){
				
			}
		})
	})
	$('.photo').mouseover(function(){
		
		$(this).parent().next().hide();
		$(this).parent().next().next().hide();
	})
	$('.photo').mouseout(function(){
		
		$(this).parent().next().show();
		$(this).parent().next().next().show();
	})
	$('#subsearch').click(function(){
		var label=$('#search').val();
		if(label!=null&&!(label=="")){
			$.ajax({
				url:"/photoaction",
				type:"post",
				data:{"cmd":"searchPhoto","photoLabel":label},
				dataType:"json",
				success:function(data){
					console.log(data);
					$('.center').empty();
					$('#myself').hide();
					$('#topic').hide();
					$('#special').hide();
					$('.center').append("<div class='searchResult'><div>");
					if(!(data.length==0)){
						for(var index in data){
							$('.searchResult').append("<img class='resultPhoto' src="+data[index].photoPath+">");
							
						}
					}else{
						alert("没有找到具有此标签的照片");
					}
					$('.resultPhoto').click(function(){
						
						$(this).toggleClass("photostyle");
					})
				},
				error:function(){
					
				},
				complete:function(XMLHttpRequest, textStatus){}
			})
		}else{
			alert("输入的搜索标签为空");
		}
	});
});