$(function() {
	$('.showDetails').hide();
	$('.center').show();
	$('.speTitle').hide();
	
	$('.conImg').mouseover(function() {
		$(this).parent().children('.speTitle').show();
	})
	$('.conImg').mouseout(function() {
		$(this).parent().children('.speTitle').hide();
	})
	
	$('.conImg').click(function(){
		var speId=$(this).next().val();
		$('.center').hide();
		$('#top').after("<div class='showDetails'></div>");
		$('.showDetails').append("<button class='back'>返回</button>");
		
		$.ajax({
			url:"/photospecialaction",
			type:"post",
			data:{"cmd":"showDetails","speId":speId},
			dataType:"json",
			success:function(data){
				console.log(data);
				$('.showDetails').append("<h2 class='showSpeTitle'>"+data.speTitle+"</h2>");
				$('.showDetails').append("<span class='showSpeDate'>"+data.speDate+"</span>");
				$('.showSpeDate').after("<div class='showContent'></div>");
				$('.showContent').append(data.speContent);
			},
			error:function(){
				
			},
			complete:function(){
				
			}
		})
		
		$('.back').click(function(){
			$('.center').show();
			$('.showDetails').hide();
		})
	})
	
})