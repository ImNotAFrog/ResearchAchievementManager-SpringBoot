layui.use(['layer',], function () {
	var layer = layui.layer;
})
var departmentList
var department

// var active=GetQueryString('active');
// if(active!=null){
// 	console.log();
// 	setTimeout(() => {
// 		$('#'+active).click();	
// 	}, 1000);
// }
// console.log(active)

$(function () {
	if (token == null) {
		window.location.href = '/index'
	}
	if (role != 'ROLE_TEACHER') {
		window.location.href = '/index'
	}
	$('#account').html(account)
	getDepartmentList()

})
var getDepartmentList = function () {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		headers: {Authorization: 'Bearer ' + token },
		url: "/user/getDepartmentList",
		success: function (data) {
			var result = JSON.parse(data)
			if (result.state == "success") {
				departmentList = result.dep
				console.log(departmentList)
				getUserProfileByAccount(account, token)
			}

		}
	});
}
function getUserProfileByAccount(account, token) {
	$.ajax({
		url: '/user/getUserProfileByAccount',
		type: 'GET',
		contentType: 'application/json',
		headers: { Authorization: 'Bearer ' + token },
		success: function (data) {
			var resData = JSON.parse(data)
			if (resData.state = "success") {
				var result = resData.profile
				$('#name').html(result.name)
				if (result.position != null) {
					$('#position').html(result.position)
					$('#positionBlock').show();
				} else {
					$('#positionBlock').hide();
				}
				if (result.title != null) {
					$('#title').html(result.title)
					$('#titleBlock').show();
				} else {
					$('#titleBlock').hide();
				}
				if (result.positionLevel != null) {
					$('#positionLevel').html(result.positionLevel)
					$('#positionLevelBlock').show();
				} else {
					$('#positionLevelBlock').hide();
				}
				if (result.department != null) {
					for (var i = 0; i < departmentList.length; i++) {
						if (departmentList[i].index == result.department) {
							$('#department').html(departmentList[i].name)
							department = departmentList[i]
						}
					}
					$('#departmentBlock').show();
				} else {
					$('#departmentBlock').hide();
				}
				if (result.subDepartment != null) {
					for (var i = 0; i < department.subDeps.length; i++) {
						if (department.subDeps[i].index == result.subDepartment) {
							$('#subDepartment').html(department.subDeps[i].name)
						}
					}
					$('#subDepartmentBlock').show();
				} else {
					$('#subDepartmentBlock').hide();
				}
			}

		},
		error: function (result) {
			return result
		}
	}).done
}

$(function () {
	// alert("hello");
	var nav = document.getElementsByClassName("left")[0];
	var navItem = nav.getElementsByTagName("li");

	// 鍙宠竟鐨勫唴瀹瑰尯
	var con = document.getElementsByClassName("content-item");

	// 鐐瑰嚮宸﹁竟鐨勯�椤癸紝鍙宠竟鍐呭鍒囨崲

	for (var i = 0; i < con.length; i++) {
		con[i].style.display = "none";
	}
	con[0].style.display = "block";
	for (var i = 0; i < navItem.length; i++) {
		navItem[i].index = i;
		navItem[i].onclick = function () {

			for (var j = 0; j < navItem.length; j++)
				con[j].style.display = "none";
			con[this.index].style.display = "block";
			for (var j = 0; j < navItem.length; j++)
				navItem[j].firstElementChild.className = "";
			navItem[this.index].firstElementChild.className = "active";
		}
	}
})

var goToUpload = function (page) {
	console.log($('#department').html())
	if ($('#department').html() == null || $('#department').html() == '') {
		layer.alert("请先完善个人资料", '提示', function () {
			window.location.href = "/editUser.do";
		});
	} else {
		window.location.href = page;
	}
}



window.operateEvents = {
	'click .RoleOfA': function (e, value, row, index) {
		location.href="/"+row.type+"/edit.do?aId="+row.aId+"&state="+row.state+"&action=see";
	},
	'click .RoleOfB': function (e, value, row, index) {
		if(row.state==4){
			layer.alert('通过的成果只能查看',{icon:5},function(){
				 layer.closeAll();
			})
			return false;
		}
		location.href="/"+row.type+"/edit.do?aId="+row.aId+"&state="+row.state;
	}
}



function operateFormatter(value, row, index) {
	return [
		'<button type="button" class="RoleOfA btn btn-primary  btn-sm" style="margin-right:15px;">查看</button>',
		'<button type="button" class="RoleOfB btn btn-success  btn-sm" style="margin-right:15px;">编辑</button>',
	].join('');
}


$(function () {
	$.ajax({
		type: "GET",
		url: "/achievement/getListByAccount",
		contentType: 'application/json',
		headers: { Authorization: 'Bearer ' + token },
		success: function (res) {
			res = JSON.parse(res);
			console.log(res.achievement);
			if (res.state == "success") {
				$('#wating').bootstrapTable('load', res.achievement);
			} else {
				layer.alert("获取成果列表失败", { icon: 5 }, function () {
					layer.closeAll();
				});
			}
		},
		error: function (pa) {
			layer.msg(132132)
		}
	});
})





$('#wating').bootstrapTable({
	columns: [
		{
			field: 'name',
			title: '成果名称'
		}, {
			field: 'aId',
			title: '成果编号',
			sortable:true,
			order:'asc'
		}, {
			field: 'type',
			title: '类型',
			formatter: function (value, row, index) {
				return checkType(value)

			}
		}, {
			field: 'state',
			title: '审核状态',
			formatter: function (value, row, index) {
				return checkState(value);
			}
		}, {
			field: 'operate',
			title: '操作',
			align: 'center',
			events: operateEvents,
			formatter: operateFormatter
		}
	],
	data: []
});
