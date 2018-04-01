$(function(){
        		$('#back').click(function(){
        			window.location.href="/homepage"
        		})
        		
        		var userId=$('.userId').val();
        		
        		var extraPrePage;
        		var extraNextPage;
        		var beEditedPhoto;
        		var beEditedArticle;
        		
        		$('.showArticle').hide();
        		$('.showTopic').hide();
        		$('.showAttention').hide();
        		$('.showCollect').hide();
        		
        		$('#myPhoto').click(function(){
        			$('.showPhoto').show();
        			$('.showArticle').hide();
        			$('.showTopic').hide();
        			$('.showAttention').hide();
        			$('.showCollect').hide();
        			$('.photoDetails').hide();
        			$('.deletePhoto').hide();
        			$('.editPhoto').hide();
        			$('.articleDetails').hide();
        			
        			window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
        		})
        		
        		
        		$('#myArticle').click(function(){
        			$('.showPhoto').hide();
        			$('.showArticle').show();
        			$('.showTopic').hide();
        			$('.showAttention').hide();
        			$('.showCollect').hide();
        			$('.photoDetails').hide();
        			$('.deletePhoto').hide();
        			$('.editPhoto').hide();
        			$('.articleDetails').hide();
        			
        			$.ajax({
        				url:"/articleaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectArticle","currentPage":"1"},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var articles=data.apagelist.listData;
        					var showContent=data.showContentList;
        					$('.showArticle').empty();
        					for(var index in articles){
        							$('.showArticle').append("<span class='arTitle'>"+articles[index].arTitle+"</span></br></br>");
        							$('.showArticle').append("<input type='hidden' class='arId' value="+articles[index].arId+">")
        							$('.showArticle').append("<span class='showArContent'>"+showContent[index]+"</span></br></br>");
        							$('.showArticle').append("<hr class='divisionLine'>");
        							
        					}
        					$('.showArticle').append("<span class='extraPrePage'>上页</span>");
        					$('.showArticle').append("<span class='extraNextPage'>下页</span>");
        					$('.showArticle').append("<span class='CurrentPage'>当前第"+data.apagelist.currentPage+"/"+data.apagelist.totalPage+"页</span>");
        					$('.showArticle').append("<span class='TotalPage'>共有"+data.apagelist.totalPage+"页</span>");
        					extraPrePage=data.apagelist.prePage;
        					extraNextPage=data.apagelist.nextPage;
        				},
        				error:function(){
        				
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		
        		$('.showArticle').on('click','.extraPrePage',function(){
        			$.ajax({
        				url:"/articleaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectArticle","currentPage":extraPrePage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var articles=data.apagelist.listData;
        					var showContent=data.showContentList;
        					$('.showArticle').empty();
        					for(var index in articles){
        						$('.showArticle').append("<span class='arTitle'>"+articles[index].arTitle+"</span></br></br>");
        						$('.showArticle').append("<input type='hidden' class='arId' value="+articles[index].arId+">");
        						$('.showArticle').append("<span class='showArContent'>"+showContent[index]+"</span></br></br>");
        						$('.showArticle').append("<hr class='divisionLine'>");
        						
        					}
        					extraPrePage=data.apagelist.prePage;
        					extraNextPage=data.apagelist.nextPage;
        					$('.showArticle').append("<span class='extraPrePage'>上页</span>");
        					$('.showArticle').append("<span class='extraNextPage'>下页</span>");
        					$('.showArticle').append("<span class='CurrentPage'>当前第"+data.apagelist.currentPage+"/"+data.apagelist.totalPage+"页</span>");
        					$('.showArticle').append("<span class='TotalPage'>共有"+data.apagelist.totalPage+"页</span>");
        				},
        				error:function(){
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		
        		$('.showArticle').on('click','.extraNextPage',function(){
        			$.ajax({
        				url:"/articleaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectArticle","currentPage":extraNextPage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var articles=data.apagelist.listData;
        					var showContent=data.showContentList;
        					$('.showArticle').empty();
        					for(var index in articles){
        						$('.showArticle').append("<span class='arTitle'>"+articles[index].arTitle+"</span></br></br>");
        						$('.showArticle').append("<input type='hidden' class='arId' value="+articles[index].arId+">");
        						$('.showArticle').append("<span class='showArContent'>"+showContent[index]+"</span></br></br>");
        						$('.showArticle').append("<hr class='divisionLine'>");
        					
        					}
        					extraPrePage=data.apagelist.prePage;
        					extraNextPage=data.apagelist.nextPage;
        					$('.showArticle').append("<span class='extraPrePage'>上页</span>");
        					$('.showArticle').append("<span class='extraNextPage'>下页</span>");
        					$('.showArticle').append("<span class='CurrentPage'>当前第"+data.apagelist.currentPage+"/"+data.apagelist.totalPage+"页</span>");
        					$('.showArticle').append("<span class='TotalPage'>共有"+data.apagelist.totalPage+"页</span>");
        				},
        				error:function(){
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				
        				}
        			})
        		})
        		
        		$('.showArticle').on('click','.arTitle',function(){
        			var arId=$(this).next().next().next().val();
        			
        			$('.showArticle').hide();
        			$('#right').append("<div class='articleDetails'></div>"); 
        			$.ajax({
        				url:"/articleaction",
        				type:"post",
        				data:{"cmd":"articleDetails","arId":arId},
        				dataType:"json",
        				success:function(data){
        					beEditedArticle=data;
        					console.log(data);
        					$('.articleDetails').append("<h2 class='articleTitle'>"+data.arTitle+"</h2>");
        					$('.articleDetails').append("<span class='articleDate'>"+data.arDate+"</span>");
        					$('.articleDetails').append("<img class='editArticle' title='编辑' src='/Images/编辑.png' data-toggle='modal' data-target='#articleModal' >");
        					$('.articleDetails').append("<img class='deleteArticle' title='删除' src='/Images/删 除.png'>");
        					$('.articleDetails').append("<hr class='arDivisionLine' />");
        					$('.articleDetails').append("<div class='articleContent'>"+data.arContent+"</div>");
        					
        					$('.deleteArticle').click(function(){
        						$.ajax({
        							url:"/articleaction",
        							type:"post",
        							data:{"cmd":"deleteArticle","arId":arId},
        							success:function(data){
        								alert(data);
        								window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
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
        		
        		$('#articleModal').on('show.bs.modal', function (event){
					var modal=$(this);
					console.log(beEditedArticle);
					
					var arId=beEditedArticle.arId;

					modal.find('.modal-body .beEditArticleTitle').attr("value",beEditedArticle.arTitle);
					editor.txt.html(beEditedArticle.arContent);
					modal.find('.modal-body .beEditArticleLabel').attr("value",beEditedArticle.arLabel);
					
					modal.find('.modal-footer .submitEdit').click(function(){

						var title=modal.find('.modal-body .beEditArticleTitle').val();
						var content=editor.txt.html();
						var label=modal.find('.modal-body .beEditArticleLabel').val();

						$.ajax({
							url:'/articleaction',
							type:'post',
							data:{"arTitle":title,"arContent":content,"arLabel":label,"arId":arId,"cmd":"editArticle"},
							success:function(data){
								alert(data);
								window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
							},
							error:function(){
								
							},
							complete:function(){
							}
						}) 
						
					})
				})
        		
        		$('#myTopic').click(function(){
        			$('.showPhoto').hide();
        			$('.showArticle').hide();
        			$('.showTopic').show();
        			$('.showAttention').hide();
        			$('.showCollect').hide();
					$('.photoDetails').hide();
					$('.deletePhoto').hide();
        			$('.editPhoto').hide();
        			$('.articleDetails').hide();
					$.ajax({
						url:"/topicaction",
						type:"post",
						data:{"userId":userId,"cmd":"selectTopic","currentPage":"1"},
						dataType:"json",
						success:function(data){
							
						},
						error:function(){
						},
						complete:function(){
						}
					})        			
        		})
        		$('#myAttention').click(function(){
        			$('.showPhoto').hide();
        			$('.showArticle').hide();
        			$('.showTopic').hide();
        			$('.showAttention').show();
        			$('.showCollect').hide();
        			$('.photoDetails').hide();
        			$('.deletePhoto').hide();
        			$('.editPhoto').hide();
        			$('.articleDetails').hide();
        			
        			$.ajax({
        				url:"/attentionaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectUserAttention","currentPage":"1"},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var attList=data.attPageList.listData;
        					var userList=data.userList;
        					$('.showAttention').empty();
        					//$('.showAttention').append("<div class='attentions'></div>");
        					for(var index in userList){
        						$('.showAttention').append("<div class='attentions'><img class='beAttUserIcon' src="+userList[index].userIcon+">"+
        							"<span class='beAttUserName'>"+userList[index].userName+"</span><img title='取消关注' class='cancleAttention' src='/Images/取消关注.png'>"
        							+"<input type='hidden' class='attId' value="+attList[index].attId+"></div>");
        					}
        					$('.cancleAttention').hide();
        					$('.attentions').mouseover(function(){
        						$(this).children('.cancleAttention').show();
        					})
        					$('.attentions').mouseout(function(){
        						$(this).children('.cancleAttention').hide();
        					})
        					extraPrePage=data.attPageList.prePage;
        					extraNextPage=data.attPageList.nextPage;
        					$('.showAttention').append("<span class='extraPrePage'>上页</span>");
        					$('.showAttention').append("<span class='extraNextPage'>下页</span>");
        					$('.showAttention').append("<span class='CurrentPage'>当前第"+data.attPageList.currentPage+"/"+data.attPageList.totalPage+"页</span>");
        					$('.showAttention').append("<span class='TotalPage'>共有"+data.attPageList.totalPage+"页</span>");
        				},
        				error:function(){
        				
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		$('.showAttention').on('click','.extraNextPage',function(){
        			$.ajax({
        				url:"/attentionaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectUserAttention","currentPage":extraNextPage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var attList=data.attPageList.listData;
        					var userList=data.userList;
        					$('.showAttention').empty();
        					//$('.showAttention').append("<div class='attentions'></div>");
        					for(var index in userList){
        						$('.showAttention').append("<div class='attentions'><img class='beAttUserIcon' src="+userList[index].userIcon+">"+
        							"<span class='beAttUserName'>"+userList[index].userName+"</span><img title='取消关注' class='cancleAttention' src='/Images/取消关注.png'>"
        							+"<input type='hidden' class='attId' value="+attList[index].attId+"></div>");
        					}
        					$('.cancleAttention').hide();
        					$('.attentions').mouseover(function(){
        						$(this).children('.cancleAttention').show();
        					})
        					$('.attentions').mouseout(function(){
        						$(this).children('.cancleAttention').hide();
        					})
        					extraPrePage=data.attPageList.prePage;
        					extraNextPage=data.attPageList.nextPage;
        					$('.showAttention').append("<span class='extraPrePage'>上页</span>");
        					$('.showAttention').append("<span class='extraNextPage'>下页</span>");
        					$('.showAttention').append("<span class='CurrentPage'>当前第"+data.attPageList.currentPage+"/"+data.attPageList.totalPage+"页</span>");
        					$('.showAttention').append("<span class='TotalPage'>共有"+data.attPageList.totalPage+"页</span>");
        				},
        				error:function(){
        				
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		$('.showAttention').on('click','.extraPrePage',function(){
        			$.ajax({
        				url:"/attentionaction",
        				type:"post",
        				data:{"userId":userId,"cmd":"selectUserAttention","currentPage":extraPrePage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var attList=data.attPageList.listData;
        					var userList=data.userList;
        					$('.showAttention').empty();
        					//$('.showAttention').append("<div class='attentions'></div>");
        					for(var index in userList){
        						$('.showAttention').append("<div class='attentions'><img class='beAttUserIcon' src="+userList[index].userIcon+">"+
        							"<span class='beAttUserName'>"+userList[index].userName+"</span><img title='取消关注' class='cancleAttention' src='/Images/取消关注.png'>"+
        							"<input type='hidden' class='attId' value="+attList[index].attId+"></div>");
        					}
        					$('.cancleAttention').hide();
        					$('.attentions').mouseover(function(){
        						$(this).children('.cancleAttention').show();
        					})
        					$('.attentions').mouseout(function(){
        						$(this).children('.cancleAttention').hide();
        					})
        					extraPrePage=data.attPageList.prePage;
        					extraNextPage=data.attPageList.nextPage;
        					$('.showAttention').append("<span class='extraPrePage'>上页</span>");
        					$('.showAttention').append("<span class='extraNextPage'>下页</span>");
        					$('.showAttention').append("<span class='CurrentPage'>当前第"+data.attPageList.currentPage+"/"+data.attPageList.totalPage+"页</span>");
        					$('.showAttention').append("<span class='TotalPage'>共有"+data.attPageList.totalPage+"页</span>");
        				},
        				error:function(){
        				
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		
        		$('.showAttention').on('click','.cancleAttention',function(){

        			var attId=$(this).next().val();
        			$.ajax({
        				url:"/attentionaction",
        				type:"post",
        				data:{"cmd":"cancelAttention","attId":attId},
        				success:function(data){
        					alert(data);
        					window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
        				},
        				error:function(){
        				},
        				complete:function(){
        				}
        			}) 
        		})
        		
        		$('#myCollect').click(function(){
        			$('.showPhoto').hide();
        			$('.showArticle').hide();
        			$('.showTopic').hide();
        			$('.showAttention').hide();
        			$('.showCollect').show();
        			$('.photoDetails').hide();
        			$('.deletePhoto').hide();
        			$('.editPhoto').hide();
        			$('.articleDetails').hide();
        			
        			$.ajax({
        				url:"/collectphoto",
        				type:"post",
        				data:{"cmd":"selectUserCollect","userId":userId,"currentPage":"1"},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var photoList=data.photoList;
        					$('.showCollect').empty();
        					for(var index in photoList){
        						$('.showCollect').append("<img class='collectedPhoto' src="+photoList[index].photoPath+">");
        						$('.showCollect').append("<img class='cancelCollect' src='/Images/关闭.png' title='删除收藏'>");
        						$('.showCollect').append("<input type='hidden' class='colId' value="+data.collectPageList.listData[index].colId+">");
        					}
        					extraPrePage=data.collectPageList.prePage;
        					extraNextPage=data.collectPageList.nextPage;
        					$('.showCollect').append("<span class='extraPrePage'>上页</span>");
        					$('.showCollect').append("<span class='extraNextPage'>下页</span>");
        					$('.showCollect').append("<span class='CurrentPage'>当前第"+data.collectPageList.currentPage+"/"+data.collectPageList.totalPage+"页</span>");
        					$('.showCollect').append("<span class='TotalPage'>共有"+data.collectPageList.totalPage+"页</span>");
        					
        					$('.cancelCollect').hide();
        					$('.collectedPhoto').mouseover(function(){
        						$(this).next().show();
        					});
        					$('.collectedPhoto').mouseout(function(){
        						$(this).next().hide();
        					});
        				},
        				error:function(){
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		
        		$('.showCollect').on('click','.extraNextPage',function(){
        			$.ajax({
        				url:"/collectphoto",
        				type:"post",
        				data:{"cmd":"selectUserCollect","userId":userId,"currentPage":extraNextPage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var photoList=data.photoList;
        					$('.showCollect').empty();
        					for(var index in photoList){
        						$('.showCollect').append("<img class='collectedPhoto' src="+photoList[index].photoPath+">");
        						$('.showCollect').append("<img class='cancelCollect' src='/Images/关闭.png' title='删除收藏'>");
        						$('.showCollect').append("<input type='hidden' class='colId' value="+data.collectPageList.listData[index].colId+">");
        					}
        					extraPrePage=data.collectPageList.prePage;
        					extraNextPage=data.collectPageList.nextPage;
        					$('.showCollect').append("<span class='extraPrePage'>上页</span>");
        					$('.showCollect').append("<span class='extraNextPage'>下页</span>");
        					$('.showCollect').append("<span class='CurrentPage'>当前第"+data.collectPageList.currentPage+"/"+data.collectPageList.totalPage+"页</span>");
        					$('.showCollect').append("<span class='TotalPage'>共有"+data.collectPageList.totalPage+"页</span>");
        					
        					$('.cancelCollect').hide();
        					$('.collectedPhoto').mouseover(function(){
        						$(this).next().show();
        					});
        					$('.collectedPhoto').mouseout(function(){
        						$(this).next().hide();
        					});
        				},
        				error:function(){
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		$('.showCollect').on('click','.extraPrePage',function(){
        			$.ajax({
        				url:"/collectphoto",
        				type:"post",
        				data:{"cmd":"selectUserCollect","userId":userId,"currentPage":extraPrePage},
        				dataType:"json",
        				success:function(data){
        					console.log(data);
        					var photoList=data.photoList;
        					$('.showCollect').empty();
        					for(var index in photoList){
        						$('.showCollect').append("<img class='collectedPhoto' src="+photoList[index].photoPath+">");
        						$('.showCollect').append("<img class='cancelCollect' src='/Images/关闭.png' title='删除收藏'>");
        						$('.showCollect').append("<input type='hidden' class='colId' value="+data.collectPageList.listData[index].colId+">");
        					}
        					extraPrePage=data.collectPageList.prePage;
        					extraNextPage=data.collectPageList.nextPage;
        					$('.showCollect').append("<span class='extraPrePage'>上页</span>");
        					$('.showCollect').append("<span class='extraNextPage'>下页</span>");
        					$('.showCollect').append("<span class='CurrentPage'>当前第"+data.collectPageList.currentPage+"/"+data.collectPageList.totalPage+"页</span>");
        					$('.showCollect').append("<span class='TotalPage'>共有"+data.collectPageList.totalPage+"页</span>");
        					
        					
        					$('.cancelCollect').hide();
        					$('.collectedPhoto').mouseover(function(){
        						$(this).next().show();
        					});
        					$('.collectedPhoto').mouseout(function(){
        						$(this).next().hide();
        					});
        					
        				},
        				error:function(){
        				},
        				complete:function(XMLHttpRequest,textStatus){
        				}
        			})
        		})
        		
        		
        		$('.showCollect').on('click','.cancelCollect',function(){
        			var colId=$(this).next().val();
        			$.ajax({
        				url:"/collectphoto",
        				type:"post",
        				data:{"cmd":"cancelCollect","colId":colId},
        				success:function(data){
        						alert(data);
            					window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;

        				},
        				error:function(){
        				},
        				complete:function(){
        				}
        			})
        		})
        		
        		$('.photoDate').hide();
        		$('.photo').mouseover(function(){
        			$(this).next().next().show();
        		})
        		$('.photo').mouseout(function(){
        			$(this).next().next().hide();
        		})
        		
        		$('.nextPage').click(function(){
        			var photoNextPage=$(this).next().val();
        			window.location.href="/useraction?cmd=userMainPage&userId="+userId+"&currentPage="+photoNextPage;
        		})
        		$('.prePage').click(function(){
        			var photoPrePage=$(this).next().val();
        			window.location.href="/useraction?cmd=userMainPage&userId="+userId+"&currentPage="+photoPrePage;
        		})
        		
        		$('.photo').click(function(){
        			var photoId=$(this).next().val();
        			var photoPath=$(this).attr("src");
        			$('.showPhoto').hide();
        			$('#right').append("<div class='photoDetails'></div>");
        			
        			$.ajax({
        				url:"/photoaction",
        				type:"post",
        				data:{"cmd":"photoDetails","photoId":photoId},
        				dataType:"json",
        				success:function(data){
        					$('#right').append("<img class='editPhoto' title='编辑' src='/Images/编辑.png' data-toggle='modal' data-target='#photoModal' >");
        					$('#right').append("<img class='deletePhoto' title='删除' src='/Images/删 除.png' >");
        					console.log(data);
        					beEditedPhoto=data.photo;
        					var commList=data.commList;
        					var commUserList=data.commUserList;
        					var collectList=data.collectList;
        					var collectUserList=data.collectUserList;
        					$('.photoDetails').append("<img class='selectedPhoto' src="+data.photo.photoPath+">");
        					/* for(var index in commList){
        						$('.photoDetails').append("<div class='photoComment'><img class='commUserIcon' src=''></div>");
        					} */
        					$('.deletePhoto').click(function(){
        						$.ajax({
        							url:"/photoaction",
        							type:"post",
        							data:{"cmd":"deletePhoto","photoId":photoId,"photoPath":photoPath},
        							success:function(data){
        								
        									alert(data);
        									window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
        							
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
        		
        		$('#photoModal').on('show.bs.modal', function (event){
        		 	var originPhotoPath=beEditedPhoto.photoPath;			
        		 	var modal = $(this);
        		 	modal.find('.modal-body .modalPhoto .closePhoto').hide();
        		 	modal.find('.modal-body .modalPhoto .reUploadPhoto').hide();
        		 	modal.find('.modal-body .modalPhoto .editedPhoto').attr("src",beEditedPhoto.photoPath);
        		 	modal.find('.modal-body .modalPhotoDes').val(beEditedPhoto.photoDes);
        		 	modal.find('.modal-body .modalPhotoLabel').val(beEditedPhoto.photoLabel);
        		 	modal.find('.modal-body .modalPhoto .editedPhoto').mouseover(function(){
        		 		modal.find('.modal-body .modalPhoto .closePhoto').show();
        		 	});
        		 	modal.find('.modal-body .modalPhoto .editedPhoto').mouseout(function(){
        		 		modal.find('.modal-body .modalPhoto .closePhoto').hide();
        		 	});
        		 	
        		 	modal.find('.modal-body .modalPhoto .closePhoto').click(function(){
        		 		modal.find('.modal-body .modalPhoto .editedPhoto').attr("src","");
        		 		modal.find('.modal-body .modalPhoto .reUploadPhoto').show();
        		 		modal.find('.modal-body .modalPhoto .reUploadPhoto').change(function(){
        		 			var objUrl = getObjectURL(this.files[0]);
							//console.log("objUrl = " + objUrl);
							if(objUrl) {
								modal.find('.modal-body .modalPhoto .editedPhoto').attr("src",objUrl);
								modal.find('.modal-body .modalPhoto .reUploadPhoto').hide();
							}
        		 		})
        		 	});
        		 	
        		 	modal.find('.modal-footer .submitEdit').click(function(){
        		 		var formData = new FormData();
        		 		var finishedEditPhotoId=beEditedPhoto.photoId;
        		 		var currentPhotoPath=modal.find('.modal-body .modalPhoto .editedPhoto').attr("src");
        		 		var currentPhotoDes=modal.find('.modal-body .modalPhotoDes').val();
        		 		var currentPhotoLabel=modal.find('.modal-body .modalPhotoLabel').val();
        		 		//alert(currentPhotoPath+"--"+beEditedPhoto.photoPath);
        		 		
        		 		if(currentPhotoPath!=beEditedPhoto.photoPath){
        		 			formData.append("file",modal.find('.modal-body .modalPhoto .reUploadPhoto')[0].files[0]);
        		 		}
        		 		
        		 		formData.append("photoId",finishedEditPhotoId);
        		 		formData.append("photoDes",currentPhotoDes);
        		 		formData.append("photoLabel",currentPhotoLabel);
        		 		formData.append("cmd","editPhoto");
        		 		formData.append("originPhotoPath",originPhotoPath);
        		 		formData.append("userId",userId);
        		 		
        		 		if(currentPhotoPath!=null&&!(currentPhotoPath=="")){
        		 			$.ajax({
    							url: '/photoaction',
    							type: 'POST',
    							cache: false,
    							data: formData,
    							processData: false,
    							contentType: false,
    							success:function(data){
    								alert(data);
    								window.location.href="/useraction?cmd=userMainPage&currentPage=1&userId="+userId;
    							},
    							error:function(){
    								
    							},
    							complete:function(XMLHttpRequest,textStatus){
    							}
    							
    						})
							
        		 		}else{
        		 			alert("图片文件不为空");
        		 		}
        		 			
        		 	})
        		 	
        		});
        		
        	})
        	
        	function getObjectURL(file) {
				var url = null;
				if(window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(file);
				} else if(window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				} else if(window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}
			