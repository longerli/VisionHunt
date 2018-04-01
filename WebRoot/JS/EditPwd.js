$(function(){
	$('#edit').click(function(){
		var userEmail=getUrlParam('userEmail');
		var resource=getUrlParam('resource');
		var userId=$('.userId').val();
		var newPwd=$('.newPwd').val();
		var confirmNewPwd=$('.confirmPwd').val();
		
		if(newPwd==confirmNewPwd){
			if(userEmail!=null&&!(userEmail=="")){
				$.ajax({
					url:"/useraction",
					type:"post",
					data:{"cmd":"forgetPwd","userEmail":userEmail,"newPwd":newPwd},
					success:function(data){
						if("密码修改成功"==data){
							alert(data);
							window.location.href="/JSP/Login.jsp";
						}
						if("无此邮箱账号"==data){
							alert(data);
							window.location.href="/JSP/EditPwd.jsp?userEmail="+userEmail;
						}
						
					},
					error:function(){
						
					},
					complete:function(){
						
					}
				})
			}
			
			if(userId!=null&&!(userId=="")){
				$.ajax({
					url:'/useraction',
					type:"post",
					data:{"cmd":"editPwd","userId":userId,"newPwd":newPwd},
					success:function(data){
						if("修改成功"==data){
							alert(data);
							window.location.href="/JSP/Login.jsp";
						}
						
						if("修改失败"==data){
							alert(data);
						}
					},
					error:function(){
						
					},
					complete:function(){
						
					}
				})
			}
		}else{
			alert("两次输入密码不一致");
		}
		
	})
	
	 function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
     }
})