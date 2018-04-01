$(function(){
	
	$('.login').click(function(){
		var userEmail=$('#count').val();
		var pwd=$('#pwd').val();

		$.ajax({
			url:"/useraction",
			type:"post",
			data:{"email":userEmail,"password":pwd,"cmd":"login"},
			success:function(data){
				if("用户名或密码错误"==data){
					alert(data);
					window.location.href="/JSP/Login.jsp";
				}
				if("登陆成功"==data){
					window.location.href="/homepage";
				}
			},
			error:function(){
				
			},
			complete:function(){
				
			}
		})
	})
	
	$('#forgetPwd').click(function(){
		var userEmail=$('#count').val();
		if(userEmail!=null&&!(userEmail=="")){
			
			if(userEmail.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
				window.location.href="/JSP/EditPwd.jsp?resource=forgetPwd&userEmail="+userEmail;
				
			}else{
				alert("请输入正确的邮箱格式");
			}
		}else{
			alert("请输入邮箱账号");
		}
	})
})