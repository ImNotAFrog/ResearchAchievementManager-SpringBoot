$(function() {
	$('#account').val(account)
})
var savePassword = function() {
	if(checkPassword()){
		data={
			'account':account,
			'oldPassword':$('#oldPassword').val(),
			'newPassword':$('#newPassword').val()
		}
		$.ajax({
			url:'/user/changePassword',
			type:'POST',
			data:JSON.stringify(data),
			contentType : 'application/json',
			headers:{Authorization:'Bearer '+token},
			success:function(result){
				if(result==1){
					alert('密码修改成功，请重新登陆','提示',function(){
						Header.logout()
					})
				}
			},
			error:function(result){
				alert('原密码错误','提示')
			}
			
		})
	}

}
var checkPassword = function() {

	var error = document.getElementById("error");

	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	var confirmPassword = document.getElementById("confirmPassword").value;
	if (newPassword == oldPassword) {
		error.innerText = "您输入的新密码和旧密码相同";
		return false;
	} else if (newPassword != confirmPassword) {
		error.innerText = "您输入的新密码不一致";
		return false;
	}
	return true;
}