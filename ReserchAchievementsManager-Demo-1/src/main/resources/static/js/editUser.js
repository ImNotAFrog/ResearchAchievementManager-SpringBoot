var Department;
window.onload=function(){ 
	getDepartmentList();
}
var save=function(){
	if(checkform()){
		var data={
				'account':account,
				'name':$('#name').val(),
				'position':$('#position').val(),
				'title':$('#title').val(),
				'positionLevel':$('#positionLevel').val(),
				'department':$('#department').val(),
				'subDepartment':$('#subDepartment').val(),
			}
		console.log(data)
		console.log(JSON.stringify(data))
		$.ajax({
			url:'/user/updateUserProfile',
			type:'POST',
			data:JSON.stringify(data),
			contentType : 'application/json',
			headers:{Authorization:'Bearer '+token},
			success:function(result){
				alert('个人信息修改成功','提示',function(){
					Header.home()
				})
			},
			error:function(result){
				alert(result,'提示')
			}
		})
	}	
}
var getUserProfileByAccount=function(account,token){
		$.ajax({
			url:'/user/getUserProfileByAccount',
			type:'GET',
			contentType : 'application/json',
			headers:{Authorization:'Bearer '+token},
			success:function(data){
				var resData=JSON.parse(data)
				if(resData.state=="success"){
					var result = resData.profile
					$('#account').val(result.account)
					$('#name').val(result.name)
					$('#position').val(result.position)
					$('#title').val(result.title)
					$('#positionLevel').val(result.positionLevel)
					$('#department').val(result.department)			
					refreshSubDepartmentSelect()
					$('#subDepartment').val(result.subDepartment)
				}
				
			},
			error:function(result){
			
			}
		})
}

var getDepartmentList=function(){
  	$.ajax({ 
    type : "GET", 
    contentType : "application/json", 
    headers:{Authorization:'Bearer '+token},
    url : "/user/getDepartmentList", 
    success : function(data){ 
    		var result = JSON.parse(data)
	    	if(result.state="success"){
	    		Department=result.dep
		    	refreshDepartmentSelect()
		    	getUserProfileByAccount(account,token)
	    	}
	    	
	    } 
	}); 
}
var refreshDepartmentSelect=function(){
	
	$('#department').append("<option value=''> </option>");
	for (var i = 0; i < Department.length; i++) {
		var text = Department[i].name
		var value = Department[i].index
		$('#department').append("<option value='"+value+"'>"+text+"</option>");
	}
	
}
var refreshSubDepartmentSelect=function(){
	$('#subDepartment option').remove()
	
	$('#subDepartment').append("<option value=''> </option>");
	for (var i = 0; i < Department.length; i++) {
		if(Department[i].index==$('#department').val()){
			var subDeps = Department[i].subDeps;
			if(subDeps!=null){
				for (var i = 0; i < subDeps.length; i++) {
					var value=subDeps[i].index
					var text=subDeps[i].name
					$('#subDepartment').append("<option value='"+value+"'>"+text+"</option>");
				}
			}
			break
		}
	}
	
}
var checkform=function(){
  	var error = document.getElementById("error");
	var username = document.getElementById("username");
	var name = document.getElementById("name");
	var position = document.getElementById("position");
	var title = document.getElementById("title");
	var positionLevel = document.getElementById("positionLevel");
	if(username.value ==""){
		error.innerText = "用户名不能为空";
		return false;
	}else if(name.value == ""){
		error.innerText = "姓名不能为空";
		return false;
	}else if($('#department').val()==''){
		error.innerText = "所属部门不能为空";
		return false;
	}else if($('#subDepartment').val()==''){
	error.innerText = "所属科室不能为空";
		return false;
	}
	return true;
}