$(function() {
	
	var userId=$('.userId').val();
	var userIcon=$('.userIcon').val();
	var nextPage=$('.hiddenNextPage').val();
	var prePage=$('.hiddenPrePage').val();
	
	$('.publishArticle').click(function(){
		window.location.href="/JSP/PublishArticle.jsp";
	})
	$('.backHomePage').click(function(){
		window.location.href="/homepage";
	})
	$('.nextPage').click(function(){
		window.location.href="/articleaction?cmd=reviewArticle&currentPage="+nextPage;
		//alert(nextPage);
	})
	$('.prePage').click(function(){
		window.location.href="/articleaction?cmd=reviewArticle&currentPage="+prePage;
	})
	$('.arTitle').click(function() {
		var arId=$(this).next().val();
		var arDate=$(this).next().next().html();
		var authorName=$(this).next().next().next().html();
		
		$.ajax({
			url:"/articleaction",
			type:"post",
			dataType:"json",
			data:{"cmd":"articleDetails","arId":arId},
			success:function(data){
				console.log(data);
				var arContent=data.arContent;
				$('.articleList').hide();
				$('.center').append("<div class='articleDetails'></div>");
				$('.articleDetails').append("<span class='back'>返回</span>");
				$('.articleDetails').append("<h2 class='reviewedArTitle'>"+data.arTitle+"</h2");
				$('.articleDetails').append("<span class='reviewedArDate'>"+arDate+"</span>");
				$('.articleDetails').append("<span class='reviewedArAuthor'>"+authorName+"</span>");
				$('.articleDetails').append("<div class='reviewedArContent'></div>");
				$('.reviewedArContent').append(arContent);
				
				$('.articleDetails').append("<textarea type='text' class='arComment' placeholder='写下你想说的' maxlength='200'></textarea>");
				$('.articleDetails').append("<button type='button' class='submitArComment'>发送</button>");
				
				$('.back').click(function(){
					$('.articleDetails').hide();
					$('.articleList').show();
				});
				
				$('.submitArComment').click(function(){
					var articleComment=$('.arComment').val();
					
					$.ajax({
						url:"/articlecomment",
						type:"post",
						data:{"cmd":"saveArticleComment","arCommContent":articleComment,"userId":userId,"arId":arId},
						success:function(data){
							if(!("评论文章失败"==data)){
								alert("评论成功");
								$('.submitArComment').after("<div class='articelComment'></div>");
								$('.articelComment').append("<span class='arCommDate'>"+data+"</span>");
								$('.articelComment').append("<img class='userIcon' src="+userIcon+">");
								$('.articelComment .userIcon').after("<span class='content'>"+articleComment+"</span>");
							}
							if("评论文章失败"==data){
								alert(data);
							}
						},
						error:function(){
							 
						},
						complete:function(){
							
						}
					})
				})
			},
			error:function(){
				
			},
			complete:function(){
				
			}
		})
	})
	
	
})