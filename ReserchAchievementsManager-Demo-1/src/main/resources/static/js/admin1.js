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
	if (role != 'ROLE_ADMIN_01') {
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
			if (resData.state == "success") {
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
			}else{
				layer.alert('获取个人信息失败',{icon:5},function(){
					layer.closeAll();
					//location.href="/index"
				})
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
		location.href="/"+row.type+"/exam.do?aId="+row.aId+"&state="+row.state+"&action=see";
	},
	'click .RoleOfB': function (e, value, row, index) {
		location.href="/"+row.type+"/exam.do?aId="+row.aId+"&state="+row.state;
	}
}



function operateFormatter(value, row, index) {
	return [
		'<button type="button" class="RoleOfA btn btn-primary  btn-sm _see" style="margin-right:15px;">查看</button>',
		'<button type="button" class="RoleOfB btn btn-success  btn-sm _edit" style="margin-right:15px;">审核</button>',
	].join('');
}


// 查询成果名称
function checkType(value) {
	value = value.toLowerCase();
	switch (value) {
		case "thesis":
			return '论文类';
			break;
		case "poject":
			return '课题项目类';
			break;
		case "textbook":
			return '论著、教材类';
			break;
		case "patent":
			return '专利';
			break;
		case "edu-reform project":
			return '教学改革项目类';
			break;
		default:
			return '法律、法规类';
			break;
	}
}

//筛选符合状态的成果
function stateSiftArray(arr,state){
	return arr.filter(item=>{
		if(item.state==state){
			return item
		}
	})
}

//高级管理员获取成果
$(function () {
	$.ajax({
		type: "GET",
		url: "/achievement/getAll",
		contentType: 'application/json',
		headers: { Authorization: 'Bearer ' + token },
		success: function (res) {
			res = JSON.parse(res);
			console.log(res.achievement);
			if (res.state == "success") {
				var dataList=res.achievement;
				//已通过的表
				$('#wating4').bootstrapTable('load',stateSiftArray(dataList,4));
				//待审核的表
				$('#wating3').bootstrapTable('load',stateSiftArray(dataList,3));
				//已驳回的表
				console.log(stateSiftArray(dataList,-1))
				$('#wating-1').bootstrapTable('load',stateSiftArray(dataList,-1));

			} else {
				layer.alert("获取成果列表失败", { icon: 5 }, function () {
					location.href="/index.do"
					layer.closeAll();
				});
			}
		},
		error: function (pa) {
			layer.alert("请求数据失败", { icon: 5 }, function () {
				location.href="/index.do"
				layer.closeAll();
			});
		}
	});
})



$('#wating4').bootstrapTable({
	columns: [
		{
			field: 'name',
			title: '成果名称'
		}, {
			field: 'type',
			title: '类型',
			formatter: function (value, row, index) {
				return checkType(value)

			}
		},{
			field: 'uploaderName',
			title: '提交人',
			sortable:true,
			order:'asc'
		},  {
			field: 'score',
			title: '成果得分',
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

$('#wating3').bootstrapTable({
	columns: [
		{
			field: 'name',
			title: '成果名称'
		}, {
			field: 'type',
			title: '类型',
			formatter: function (value, row, index) {
				return checkType(value)

			}
		},{
			field: 'uploaderName',
			title: '提交人',
			sortable:true,
			order:'asc'
		},  {
			field: 'score',
			title: '成果得分',
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

$('#wating-1').bootstrapTable({
	columns: [
		{
			field: 'name',
			title: '成果名称'
		}, {
			field: 'type',
			title: '类型',
			formatter: function (value, row, index) {
				return checkType(value)

			}
		},{
			field: 'uploaderName',
			title: '提交人',
			sortable:true,
			order:'asc'
		},  {
			field: 'score',
			title: '成果得分',
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


