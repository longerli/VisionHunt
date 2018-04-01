$(function() {

	$('#arTitle').attr("value", "无标题文本");
	var topLabel=$('#topLabel').val();
	if(topLabel!=null){
		$('#arLabel').attr("value",topLabel);
	}

	$('#back').click(function() {
		window.location.href="/articleaction?cmd=reviewArticle&currentPage=1";
	});

	$('.publishArticle').click(function() {
		var arContent = editor1.txt.html();
		var arTitle = $("#arTitle").val();
		var arLabel = $("#arLabel").val();
		var userId = $("#userId").val();
		var topId = $('#topId').val();
		$.ajax({
			url : "/articleaction",
			type : "post",
			data : {
				"userId" : userId,
				"arTitle" : arTitle,
				"arContent" : arContent,
				"arLabel" : arLabel,
				"topId" : topId,
				"cmd" : "saveArticle"
			},
			success : function(data) {
				alert(data);
			},
			error : function() {

			},
			complete : function(XMLHttpRequest, textStatus) {}
		})
	});
});