class TokenHelper{
	static getNewToken(username,password){
		var data={
				"username":username,
				"password":password
			}
		var result=null;
		$.ajax({
			type:"POST",
			url:"/auth/login",
			data:JSON.stringify(data),
			contentType:'application/json',
			success:function(data){
				result = data
			},
			error:function(data){
				result = data		
			}
		})
		console.log(result)
		return result
	}
	
	static deleteLocalToken(value){
		var exp = new Date();
		document.cookie = "my_token =" + escape(value) + ";expires=" + exp.toGMTString();

	}
	
	static saveLocalToken(value){
		var Days = 7; //此 cookie 将被保存 7 天
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = "my_token =" + escape(value) + ";expires=" + exp.toGMTString();

	}
	
	static getLocalToken(){
		var arr,reg=new RegExp("(^| )"+"my_token"+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	
	static getRoleFromToken(token){
		if(token!=null){
			var arr = token.split('.')			
			var claims = $.parseJSON($.base64.decode(arr[1]))
			var role = claims['role'][0]['authority']
			return role
		}else{
			return null
		}		
	}
	static getAccountFromToken(token){
		if(token!=null){
			var arr = token.split('.')			
			var claims = $.parseJSON($.base64.decode(arr[1]))
			var account = claims['sub']
			return account
		}else{
			return null
		}		
	}
	static getExpirationDateFromToken(token){
		if(token!=null){
			var arr = token.split('.')			
			var claims = $.parseJSON($.base64.decode(arr[1]))
			var exp = new Date(claims['exp']*1000)
			return exp
		}else{
			return null
		}		
	}
}


