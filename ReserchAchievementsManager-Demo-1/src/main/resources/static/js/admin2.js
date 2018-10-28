
var departmentList
var department



$(function () {
	if (token == null) {
		window.location.href = '/index'
	}
	if (role != 'ROLE_ADMIN_02') {
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
		console.log(e)
		console.log(row)
		console.log(value)
		console.log(index)
	//location.href="/"+row.type+"/exam.do?aId="+row.aId+"&state="+row.state+"&action=see";
	},
	'click .RoleOfB': function (e, value, row, index) {
		console.log(e)
		console.log(row)
		console.log(value)
		console.log(index)

		location.href="/"+row.type+"/exam.do?aId="+row.aId+"&state="+row.state;
	}
}



function operateFormatter(value, row, index) {
	return [
		'<button type="button" class="RoleOfA btn btn-primary  btn-sm _see" style="margin-right:15px;">查看</button>',
		'<button type="button" class="RoleOfB btn btn-success  btn-sm _edit" style="margin-right:15px;">审核</button>',
	].join('');
}

//筛选符合状态的成果
function stateSiftArray(arr,state){
	return arr.filter(item=>{
		if(item.state==state){
			return item
		}
	})
}


	layui.use([ 'layer', 'table', 'element'], function(){
		layer = layui.layer //弹层
		,table = layui.table //表格
		,element = layui.element //元素操作
		//执行一个 table 实例



		function openIframe(type,aid,state,action=null){
			var height=$(window).height()-50;
			console.log("/"+type+"/exam.do?aId="+aid+"&state="+state+"&action="+action);
			//return false;
			layer.open({
			//title:'查看'+data.user+'的信息',
			type: 2,
			area: ['800px', height+'px'],
			content: "/"+type+"/exam.do?aId="+aid+"&state="+state+"&action="+action,
			scrollbar:true,
			end: function () {
				$(".layui-laypage-btn").click();  //重新点击分页页面
			  }  
		  });
		}

			var table4=table.render({
				elem: '#table4'
				,height: 312
				,url: '/achievement/getListBySubDepartment' //数据接口
				,method:"POST"
				,where:{
					state:4,
				}
				,page: true //开启分页
				,headers: { Authorization: 'Bearer ' + token }
				,contentType: 'application/json'
				,cols: [[ //表头
				{field: 'name', title: '成果名称' ,sort: true, fixed: 'left'}
				,{field: 'type', title: '类型',templet: function(d){
					return checkType(d.type);
				  }}
				,{field: 'uploaderName', title: '提交者'} 
				,{field: 'score', title: '成果得分'}
				,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar4'}
				]]
			});
			//监听工具条
			table.on('tool(t4)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent === 'detail'){ //查看
					console.log(data.state)
					openIframe(data.type,data.aId,data.state,'see')
			}
			});
			


			//执行一个 table 实例
			var table2=table.render({
				elem: '#table2'
				,height: 312
				,url: '/achievement/getListBySubDepartment' //数据接口
				,method:"POST"
				,where:{
					state:2,
				}
				,page: true //开启分页
				,headers: { Authorization: 'Bearer ' + token }
				,contentType: 'application/json'
				,cols: [[ //表头
				{field: 'name', title: '成果名称' ,sort: true, fixed: 'left'}
				,{field: 'type', title: '类型',templet: function(d){
					return checkType(d.type);
				  }}
				,{field: 'uploaderName', title: '提交者'} 
				,{field: 'score', title: '成果得分'}
				,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar2'}
				]]
			});

			table.on('tool(t2)', function(obj){
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event;
			var active=null;
			if(layEvent == 'detail'){ //审核模式
				active="see";
			}
			console.log(active)
			openIframe(data.type,data.aId,data.state,active);
			});

			//执行一个 table 实例
			var table3=table.render({
				elem: '#table3'
				,height: 312
				,url: '/achievement/getListBySubDepartment' //数据接口
				,page: true //开启分页
				,method:"POST"
				,where:{
					state:3,
				}
				,headers: { Authorization: 'Bearer ' + token }
				,contentType: 'application/json'
				,cols: [[ //表头
				{field: 'name', title: '成果名称' ,sort: true, fixed: 'left'}
				,{field: 'type', title: '类型',templet: function(d){
					return checkType(d.type);
				  }}
				,{field: 'uploaderName', title: '提交者'} 
				,{field: 'score', title: '成果得分'}
				,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar3'}
				]]
			});

			//监听工具条
			table.on('tool(t3)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象
			if(layEvent === 'detail'){ //查看
					openIframe(data.type,data.aId,data.state,'see')
			}
			})


			//执行一个 table 实例
			var table_1=table.render({
				elem: '#table-1'
				,height: 312
				,url: '/achievement/getListBySubDepartment' //数据接口
				,page: true //开启分页
				,method:"POST"
				,where:{
					state:-1,
				}
				,headers: { Authorization: 'Bearer ' + token }
				,contentType: 'application/json'
				,cols: [[ //表头
				{field: 'name', title: '成果名称' ,sort: true, fixed: 'left'}
				,{field: 'type', title: '类型',templet: function(d){
					return checkType(d.type);
				  }}
				,{field: 'uploaderName', title: '提交者'} 
				,{field: 'score', title: '成果得分'}
				,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar-1'}
				]]
			});

				//监听工具条
				table.on('tool(t-1)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var tr = obj.tr; //获得当前行 tr 的DOM对象
				if(layEvent === 'detail'){ //查看
						openIframe(data.type,data.aId,data.state,'see')
				}
				})
	
	


		$('.searchBtn').click(function (e) { 
			var state=$(this).attr('state');
			var type=$(".table"+state+" .optType").val();
			var keyword=$(".table"+state+" .keyword").val();
			console.log("查询的类型:"+type);
			console.log("查询的关键字:"+keyword);
			var conditions={
				where: {
					type: type,
					state: parseInt(state),
					keyword:keyword
				}
				,page: {
				  curr: 1 
				}
			  }

			if(state==4){
				table4.reload(conditions);
			}else if(state==2){
				table2.reload(conditions);
			}else if(state==3){
				table3.reload(conditions);
			}else if(state==-1){
				table_1.reload(conditions);
			}
		});


		$('.refresh').click(function (e) { 
			console.log(state);
			var state=$(this).attr('state');
			console.log(state);
			$(".table"+state+" .optType").val("");
			$(".table"+state+" .keyword").val('');
			var conditions={
				where: {
					state: parseInt(state),
				}
				,page: {
				  curr: 1 
				}
			  }
			if(state==4){
				table4.reload(conditions);
			}else if(state==2){
				table2.reload(conditions);
			}else if(state==3){
				table3.reload(conditions);
			}else if(state==-1){
				table_1.reload(conditions);
			}
		});



	})	

$(document).ready(function () {
	if (getItem('active')) {
		$('#' + getItem('active')).click();
	}
	$('.left a').click(function (e) {
		var obj = $(this);
		setItem('active', obj.attr('id'))
	});
});