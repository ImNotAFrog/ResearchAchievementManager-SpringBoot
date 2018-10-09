$(function() {
	if (token != null) {
		Header.home()
	}
	$('#submit').click(function() {
		var data = {
			"username" : $('#account').val(),
			"password" : $('#password').val()
		}
		$.ajax({
			type : "POST",
			url : "/auth/login",
			data : JSON.stringify(data),
			contentType : 'application/json',
			success : function(result) {
				var token = result["token"]
				console.log(token)
				if (token != null) {
					console.log(token)
					TokenHelper.saveLocalToken(token)
					var role = TokenHelper.getRoleFromToken(token)
					console.log(role)
					switch(role){
						case 'ROLE_ADMIN_01':
							window.location.href = "/admin01.do"
							break;
						case 'ROLE_ADMIN_02':
							window.location.href = "/admin02.do"
							break;
						case 'ROLE_TEACHER':
							window.location.href = "/teacher.do"
							break;
						default:
							alert('登陆出错!')
							window.location.href = "/index"
							break;
					}
					
				}
			},
			error : function(result) {
				if (result['status'] == 403) {
					alert("账户名或密码错误", "提示", function() {
						$('#password').val('')
					})
				}
			}
		})
	})
	
	$('#loginForm').keydown(function(event){
		 if (event.keyCode==13){
			 $('#submit').click()
		 }
	})
})
