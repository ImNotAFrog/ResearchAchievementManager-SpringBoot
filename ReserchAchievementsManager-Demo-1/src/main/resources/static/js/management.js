layui.use(['layer', 'element','table', 'upload'], function () {
	var layer = layui.layer;
	var element = layui.element;
	var table = layui.table;
	var userList=[];


	function checkRole(val){
		switch (val) {
			case 'ROLE_ADMIN_01':
				return "高级管理员"
			case 'ROLE_ADMIN_02':
				return "初级管理员"
			case 'ROLE_TEACHER':
				return "教师"
			default:
				return "错误身份";
		}
	}

//获取用用户列表
		ajax_request({
			type: 'GET',
			url: '/user/getUserList',
			success: function (res) {
			   res = JSON.parse(res);
			   if (res.state == 'success') {
						userList=res.users;
						table_user.reload({data:userList});
			   } else {
					layer.alert('获取用户列表失败',{icon:5},function(){
						 layer.closeAll();
					})
			   }
			}
	   })
	   //用户列表
	var table_user=table.render({
		elem: '#table-user'
		,page: true //开启分页
		,data:[]
		,cols: [[ //表头
		{field: 'account', title: '账号' , fixed: 'left'}
		,{field: 'username', title: '用户名'}
		,{field: 'roles', title: '角色',templet: function(d){ 	
				return checkRole(d.roles[0]);		
			  }}
		,{fixed: 'right',title:'操作', align:'center', width:200,toolbar: '#toolBar-user'}
		]]
	});
	table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	var data = obj.data; //获得当前行数据
	var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	var tr = obj.tr; //获得当前行 tr 的DOM对象
	if(layEvent === 'edit'){ //编辑用户信息
		updateUser(data.uId,"编辑"+data.username+"的个人信息");
	}else if(layEvent==="delUser"){
		layer.confirm('确定要删除此用户吗？', {
            btn: ['确定', '点错了'] //按钮
        }, function () {
              layer.load();
                ajax_request({
                    type: 'POST',
                    url: '/user/deleteUser',
                    data: {
                        uId:data.uId
                    },
                    success: function (res) {
                        res=JSON.parse(res);
                    if (res.state == "success") {
                        layer.alert("删除成功", { icon: 1 }, function () {
							layer.closeAll();
                        });
                    } else {
                        layer.alert(res.msg, { icon: 5 }, function () {
                            layer.closeAll();
                        });
                    }
                    }
			})
        }, function () {
        });
	}
	})
	//弹出信息编辑或新增创空
	function updateUser(uId="",msg="新增用户"){
		layer.open({
			title:msg,
			type: 2,
			area: ['800px','450px'],
			content: "/user/edit.do?uId="+uId,
			scrollbar:true,
			end: function () {
				$(".layui-laypage-btn").click();  //重新点击分页页面
			  }  
		  });
	}


	//新增用户
$('.addUser').click(function (e) { 
	updateUser();
});	
});