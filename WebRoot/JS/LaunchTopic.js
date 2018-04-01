$(function() {
	$("#reset").click(function() {
		$("#toplabel").val("");
		$("#topdes").val("");

	});
	$("#submit").click(function() {
		var userId = $('#userId').val();
		var topLabel = $('#toplabel').val();
		var topDes = $('#topdes').val();
		$.ajax({
			url : "/topicaction?cmd=save",
			type : "post",
			data : {
				"userId" : userId,
				"topLabel" : topLabel,
				"topDes" : topDes
			},
			success : function(data) {
				if("发布成功"==data){
					alert(data);
					window.location.href = '/topicaction?cmd=show';
				}
				if("发布失败"==data){
					alert(data);
				}
			},
			error : function() {
				alert("发布失败");
			},
			complete : function(XMLHttpRequest, textStstus) {}
		})
	})
});