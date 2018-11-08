
var departmentList
var department

// 检查成果状态
function checkState(value) {
	switch (value) {
		case -1:
			return '驳回';
			break;
		case 1:
			return '未提交';
			break;
		case 2:
			return '待初审';
			break;
		case 3:
			return '待复审';
			break;
		case 4:
			return '已通过';
			break;
		default:
			return '参数错误'
			break;
	}
}

//打开iframe
function openIframe(type,aid,state,action=null){
	var height=$(window).height()-50;
	console.log("/"+type+"/edit.do?aId="+aid+"&state="+state+"&action="+action);
	layer.open({
	type: 2,
	area: ['800px', height+'px'],
	content: "/"+type+"/edit.do?aId="+aid+"&state="+state+"&action="+action,
	scrollbar:true,
	end: function () {
		$(".layui-laypage-btn").click();  //重新点击分页页面
	  }  
  });
}

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

layui.use([ 'layer', 'table', 'element'], function(){
	layer = layui.layer //弹层
	,table = layui.table //表格
	,element = layui.element //元素操作
	var table_=table.render({
		elem: '#table_'
		,url: '/achievement/getListByAccount' //数据接口
		,method:"POST" 
		,initSort: {
			field: 'aId' //排序字段，对应 cols 设定的各字段名
			,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
		  }
		,page: true //开启分页
		,headers: { Authorization: 'Bearer ' + token }
		,contentType: 'application/json'
		,cols: [[ //表头
		{field: 'name',width:200, title: '成果名称' ,sort: true, fixed: 'left'}
		,{field: 'aId',width:170, title: '成果编号' ,sort: true, fixed: 'left'}
		,{field: 'type', title: '类型',templet: function(d){
			return checkType(d.type);
			}}
		,{field: 'state', title: '审核状态',templet: function(d){
			return checkState(d.state)
			}}
		,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar'}
		]]
	});

	table.on('tool(t)', function(obj){ 
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail'){ //查看
				openIframe(data.type,data.aId,data.state,'see')		
		}else{
			if(data.state==4){
				layer.msg("通过的成果不能编辑");
				return false;
			}
			openIframe(data.type,data.aId,data.state);		
		}
	});


	$('.searchBtn').click(function (e) { 
		var type=$(".optType").val();
		var keyword=$(".keyword").val();
		console.log("查询的类型:"+type);
		console.log("查询的关键字:"+keyword);
		var conditions={
			where: {
				type: type,
				keyword:keyword
			}
			,page: {
			  curr: 1 
			}
		  }
			table_.reload(conditions);
	});

	$('.refresh').click(function (e) { 
		$(".optType").val("");
		$(".keyword").val('');
		var conditions={
			where: {
				type: "",
				keyword:""
			}
			,page: {
			  curr: 1 
			}
		  }
		  table_.reload(conditions);
		
	});


	var activitiesTable=table.render({
		elem: '#activitiesTable'
		,url: '/researchActivity/getListByAccount' //数据接口
		,method:"POST" 
		,page: true //开启分页
		,headers: { Authorization: 'Bearer ' + token }
		,contentType: 'application/json'
		,cols: [[ //表头
		{field: 'groupName', title: '调研组', fixed: 'left'}
		,{field: 'state', title: '状态',templet:function(d){
			var state=parseInt(d.state);
			if(state==-1){
				return "驳回";
			}else if(state==1){
				return "未提交";
			}else if(state==2){
				return "待审核";
			}else if(state==3){
				return "已发布"
			}
		
		}}
		,{field: 'startDate', title: '起始时间',templet:function (d) { 
			if(d.startDate==null){
				return "空"
			}
			var startDate=d.startDate.time+"";
			return timestampToTime(startDate.substring(0,10));
			
		 }}
		,{field: 'endDate', title: '结束时间',templet:function (d) { 
			if(d.endDate==null){
				return "空"
			}
			var endDate=d.endDate.time+"";
			return timestampToTime(endDate.substring(0,10));

		}}
		,{field: 'location', title: '地点'}
		,{fixed: 'right',title:'操作', align:'center',width:150, toolbar: '#activitiesToolBar'}
		]]
	});

	table.on('tool(activities)', function(obj){ 
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		var action="";
		if(layEvent === 'detail'){ //查看
			action="see";
		}
			if(data.state==3 && action!="see"){
				layer.msg("发布的活动不能编辑");
				return false;
			}
			var height=$(window).height()-50;
			layer.open({
				type: 2,
				area: ['800px', height+'px'],
				content: "/researchActivity/edit.do?actId="+data.actId+"&state="+data.state+"&action="+action,
				scrollbar:true,
				end: function () {
					$(".layui-laypage-btn").click();  //重新点击分页页面
				  }  
			  });		
		
	});



	var otherActivityTable=table.render({
		elem: '#otherActivityTable'
		,url: '/otherActivity/getListByAccount' //数据接口
		,method:"POST" 
		,page: true //开启分页
		,headers: { Authorization: 'Bearer ' + token }
		,contentType: 'application/json'
		,cols: [[ //表头
		{field: 'name', title: '活动名', fixed: 'left'}
		,{field: 'state', title: '状态',templet:function(d){
			var state=parseInt(d.state);
			if(state==-1){
				return "驳回";
			}else if(state==1){
				return "未提交";
			}else if(state==2){
				return "待审核";
			}else if(state==3){
				return "已发布"
			}
		
		}}
		,{field: 'startDate', title: '起始时间',templet:function (d) { 
			if(d.startDate==null){
				return "空"
			}
			var startDate=d.startDate.time+"";
			return timestampToTime(startDate.substring(0,10));
			
		 }}
		,{field: 'endDate', title: '结束时间',templet:function (d) { 
			if(d.endDate==null){
				return "空"
			}
			var endDate=d.endDate.time+"";
			return timestampToTime(endDate.substring(0,10));

		}}
		,{field: 'location', title: '地点'}
		,{fixed: 'right',title:'操作', align:'center',width:150, toolbar: '#otherActivityToolBar'}
		]]
	});

	table.on('tool(otherActivity)', function(obj){ 
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		var action="";
		if(layEvent === 'detail'){ //查看
			action="see";
		}
			if(data.state==3 && action!="see"){
				layer.msg("发布的活动不能编辑");
				return false;
			}
			var height=$(window).height()-50;
			layer.open({
				type: 2,
				area: ['800px', height+'px'],
				content: "/otherActivity/edit.do?actId="+data.actId+"&state="+data.state+"&action="+action,
				scrollbar:true,
				end: function () {
					$(".layui-laypage-btn").click();  //重新点击分页页面
				  }  
			  });		
		
	});


	$('.otherActivityBtn').click(function (e) { 
		var keyword=$(".otherActivityKeyword").val();
		var conditions={
			where: {
				keyword:keyword
			}
			,page: {
			  curr: 1 
			}
		  }
		  otherActivityTable.reload(conditions);
	});
	$(".otherActivityRefresh").click(function (e) { 
		$(".ActivitiesKeyword").val('');
		var conditions={
			where: {
				type: "",
				keyword:""
			}
			,page: {
			  curr: 1 
			}
		  }
		otherActivityTable.reload(conditions);
		e.preventDefault();
		
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