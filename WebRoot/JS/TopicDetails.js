$(function() {
	$('.joinTopic').hide();
	$('.commContent').hide();
	$('.join').click(function() {
		$(this).next('.joinTopic').slideToggle(600);
	});
	$('.attention').mouseover(function() {
		$(this).attr("src", "/Images/attentionmouseover.png");
	});
	$('.attention').mouseout(function() {
		$(this).attr("src", "/Images/十字.png");
	});
	$('.photoComment').mouseover(function() {
		$(this).attr("src", "/Images/评论 (1).png");
	});
	$('.photoComment').mouseout(function() {
		$(this).attr("src", "/Images/评论.png");
	});
	$('.photoTransmit').mouseover(function() {
		$(this).attr("src", "/Images/转发 (1).png");
	});
	$('.photoTransmit').mouseout(function() {
		$(this).attr("src", "/Images/转发.png");
	});
	$('.photoCollect').mouseover(function() {
		$(this).attr("src", "/Images/收 藏 (1).png");
	});
	$('.photoCollect').mouseout(function() {
		$(this).attr("src", "/Images/收 藏.png");
	});

	$('.attention').click(function() {
		var beAttUserId = $(this).attr("alt");
		$.ajax({
			url : "attentionaction",
			type : "post",
			data : {
				"beAttUserId" : beAttUserId,
				"cmd" : "addAttention"
			},
			success : function(data) {
				alert(data);
			},
			error : function() {
				alert("关注失败");
			},
			complete : function(XMLHttpRequest, textStatus) {}
		});
	});

	$(".photoComment").click(function() {
		$(this).next('.commContent').slideToggle(600);
	});

	$('.submitComment').click(function() {
		var photoCommContent = $(this).prev().prev().val();
		var photoId = $(this).prev().val();
		var userId = $('.currentUserId').val();
		var topId = $('.topId').val();
		alert(photoCommContent + "--" + photoId + "---" + userId);
		$.ajax({
			url : "commentphoto",
			type : "post",
			data : {
				"commContent" : photoCommContent,
				"photoId" : photoId,
				"userId" : userId,
				"cmd" : "commentFromTopic"
			},
			success : function() {
				alert("评论成功");
				window.location.href = "/topicaction?cmd=showDetails&topId=" + topId;
			},
			error : function() {
				alert("评论失败");
			},
			complete : function(XMLHttpRequest, textStatus) {}
		});
	});

	$('.photoTransmit').click(function() {
		var photoId = $(this).prev().children("input.photoId").val();
		var beTransedUserName = $(this).parent().prev().prev().children("span.userName").html();
		window.location.href = "/transmitphoto?cmd=transmit&photoId=" + photoId + "&beTransedUserName=" + beTransedUserName;
	});

	$('.photoCollect').click(function() {
		var userId = $('.currentUserId').val();
		var photoId = $(this).prev().prev().children("input.photoId").val();
		$.ajax({
			url : "collectphoto",
			type : "post",
			data : {
				"cmd" : "collect",
				"userId" : userId,
				"photoId" : photoId,
				"source" : "TopicDetails"
			},
			success : function(data) {
				alert(data);
			},
			error : function() {
				alert("收藏失败");
			},
			complete : function() {}
		})
	});

	$('.uploadPhoto').click(function() {
		var topLabel = $('.label').html();
		var topId = $('.topId').val();
		window.location.href = "/photoaction?cmd=participateinByPhoto&topLabel=" + topLabel + "&topId=" + topId;
	});
	$('.publishArticle').click(function() {

		var topId = $('.topId').val();
		var topLabel=$('.label').html();
		window.location.href = "/articleaction?cmd=joinedByArticle&topId=" + topId+"&topLabel="+topLabel;
	})
});