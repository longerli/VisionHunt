$(function() {
	
	$('.regist').click(function(){
		
		var userName=$('#userName').val();
		var userEmail=$('#userEmail').val();
		var pwd=$('#userPwd').val();
		var confirmPwd=$('#confirmPwd').val();
		var userTel=$('#userTel').val();
		
		var formData=new FormData();
		formData.append("file",$('#userIcon')[0].files[0]);
		formData.append("userName",userName);
		formData.append("userPwd",pwd);
		formData.append("userEmail",userEmail);
		formData.append("userTel",userTel);
		formData.append("cmd","save");
		
		if(!(userEmail.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/))){
			alert("请输入正确的邮箱格式");
			
		}
		if(!(pwd.match(/^(\w){6,20}$/))){
			alert("密码请输入6-20位字母数字下划线");
		}
		if(formData==null){
			alert("内容为空");
		}
		
		if(pwd==confirmPwd){
			
			$.ajax({
				url:"/useraction",
				type:"post",
				data:formData,
				processData: false,
				contentType: false,
				success:function(data){
					alert(data);
					if("注册成功"==data){
						window.location.href="/JSP/Login.jsp";
					}
				},
				error:function(){
					
				},
				complete:function(){
					
				}
			})
			
		}else{
			alert("两次输入密码不正确");
			
		}
		
	});
	
	$('#myModal').on('show.bs.modal', function (event){
		var modal=$(this);
		
		var userName=modal.find('.modal-body .userEmail').val();
		var userPwd=modal.find('.modal-body .pwd').val();
		
		modal.find('.modal-footer .login').click(function(){
			$.ajax({
				url:"/useraction",
				type:"post",
				data:{"email":userName,"password":userPwd,"cmd":"login"},
				success:function(data){
					
					if("用户名或密码错误"==data){
						alert(data);
						window.location.href="/JSP/Regist.jsp";
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
	})
});