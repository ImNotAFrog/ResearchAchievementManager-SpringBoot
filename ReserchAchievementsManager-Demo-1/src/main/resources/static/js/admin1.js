
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
				rankingDep(departmentList);
				depAndSub();//拿回部门信息后执行
				$(".export_level").hide();
				getUserProfileByAccount(account, token)
			}

		}
	});
}

//向下拉框中追加部门
function rankingDep(DepArr){
	var obj=$('#_department,#export_department');
	if(DepArr){
		var opt=null;
		$.each(DepArr, function (index, item) { 
			opt=$("<option>").val(item.index).text(item.name);
			obj.append(opt);
		});
		
	}
}



function getLevelSubject(type){
	var level = $("#export_level");
	$(".export_subject").addClass("hidden")
	level.empty();
	level.append("<option value=''>全部</option>");
	var obj=[];
	if(type=="project"){
		var subject = $(".export_subject").removeClass("hidden")
		obj=[
			{key:1,value:"1、国家级"},
			{key:2,value:"2、省部级"},
			{key:3,value:"3、市局级"},
			{key:3,value:"4、校级"},
		]
	}else if(type=="thesis"){
		obj=[
			{key:1,value:"《Science》、《Nature》及其子刊《Cell》"},
			{key:2,value:"SCI收录"},
			{key:3,value:"EI（JA）、SSCI、A&HCI收录"},
			{key:4,value:"EI（CA）、CPCI"},
			{key:5,value:"核心刊物正刊(含北大核心期刊、CSCD、CSSCI、RCCSE)"},
			{key:6,value:"《武警学院学报》、《消防技术与产品信息》、全国性或行业性学会论文集（一等奖）、国际会议论文集"},
			{key:7,value:"专业期刊（正刊）、全国性或行业性学会论文集（二等奖）、《消防科学与技术》增刊、《武警学院学报》增刊、《消防技术与产品信息》增刊"},
			{key:8,value:"一般期刊、其它增刊、其它论文集、校刊"},
		]
	}else if(type=="textbook"){
		obj=[
			{key:1,value:"1、公安消防部队高等专科学校正式出版"},
			{key:2,value:"2、公安消防部队高等专科学校内部印刷教辅"},
			{key:3,value:"3、外单位正式出版"},
		]
	}else if(type=="patent"){
	
	}else if(type=="reform_project"){
		
	}else if(type=="laws"){
		obj=[
			{key:1,value:"1、法律、行政法规、国家标准"},
			{key:2,value:"2、部门规章、行业标准规范"},
			{key:3,value:"3、地方性法规、政府规章、地方性标准规范"},
		]
	}
		if(obj.length>0){
			$(".export_level").show(500);
			$.each(obj, function (index, items) {
				level.append('<option value="' + items.key + '">' + items.value + '</option>');
			});
		}else{
			$(".export_level").hide(200);
		}
}

//部门二级联动
function depAndSub() {
	$('#export_department').change(function () {
		var val = $(this).val();
		var obj = $("#export_subDepartments");
		var arr = departmentList.filter(item => {
			if (item.index.includes(val)) {
				return item
			}
		})
		obj.empty();
		obj.append("<option value=''>全部</option>");
		if (typeof (arr[0].subDeps) != "undefined") {
			$.each(arr[0].subDeps, function (index, items) {
				obj.append('<option value="' + items.index + '">' + items.name + '</option>');
			});
		}
	})
}


$("#export_type").change(function (e) { 
	var val=$(this).val();
	getLevelSubject(val);

});


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


	var con = document.getElementsByClassName("content-item");


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






//筛选符合状态的成果
function stateSiftArray(arr,state){
	return arr.filter(item=>{
		if(item.state==state){
			return item
		}
	})
}




layui.use([ 'layer','laydate', 'table', 'element'], function(){
	layer = layui.layer //弹层
	,table = layui.table //表格
	,element = layui.element //元素操作
	var laydate = layui.laydate;
	//执行一个 table 实例

	//日期范围
	laydate.render({
		elem: '#rangeTime',
		range:true
	  });

	  laydate.render({
		elem: '#export_rangeTime',
		range:true
	  });


	  


 //打开弹出层
	function openIframe(type,aid,state,name,action=null){
		var height=$(window).height()-50;
		layer.open({
		title:name+'的成果信息',
		type: 2,
		area: ['800px', height+'px'],
		content: "/"+type+"/exam.do?aId="+aid+"&state="+state+"&action="+action,
		scrollbar:true,
		end: function () {
			$(".layui-laypage-btn").click();  //重新点击分页页面
		  }  
	  });
	}

	//已通过
		var table4=table.render({
			elem: '#table4'
			,url: '/achievement/getListBySubDepartment' //数据接口
			,method:"POST"
			,where:{
				state:4,
			}
			,page: true //开启分页
			,headers: { Authorization: 'Bearer ' + token }
			,contentType: 'application/json'
			,cols: [[ //表头
			{field: 'name',width:300, title: '成果名称' ,sort: true, fixed: 'left'}
			,{field: 'type', title: '类型',width:150,templet: function(d){
				return checkType(d.type);
			  }}
			,{field: 'uploaderName', title: '提交者'} 
			,{field: 'uploadDate', title: '提交时间',templet: function(d){
				var t=d.uploadDate.time+"";
					return timestampToTime(t.substring(0,10)) 	
			  }}
			,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar4'}
			]]
		});
		table.on('tool(t4)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail'){ //查看
				openIframe(data.type,data.aId,data.state,data.uploader,'see')		
		}
		});
		

		//待审核
		var table3=table.render({
			elem: '#table3'
			,url: '/achievement/getListBySubDepartment' //数据接口
			,page: true //开启分页
			,method:"POST"
			,where:{
				state:3,
			}
			,headers: { Authorization: 'Bearer ' + token }
			,contentType: 'application/json'
			,cols: [[ //表头
			{field: 'name',width:300, title: '成果名称' ,sort: true, fixed: 'left'}
			,{field: 'type', title: '类型',width:150,templet: function(d){
				return checkType(d.type);
			  }}
			,{field: 'uploaderName', title: '提交者'} 
			,{field: 'uploadDate', title: '提交时间',templet: function(d){ 				
				var t=d.uploadDate.time+"";
				return timestampToTime(t.substring(0,10)) 	  }}
			,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar3'}
			]]
		});

		table.on('tool(t3)', function(obj){
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event;
			var action=null;
			if(layEvent == 'detail'){ //审核模式
				console.log(5313)
				action="see";
			}
			openIframe(data.type,data.aId,data.state,data.uploader,action);
			});


		//执行一个 table 实例
		var table_1=table.render({
			elem: '#table-1'
			,url: '/achievement/getListBySubDepartment' //数据接口
			,page: true //开启分页
			,method:"POST"
			,where:{
				state:-1,
			}
			,headers: { Authorization: 'Bearer ' + token }
			,contentType: 'application/json'
			,cols: [[ //表头
			{field: 'name',width:300, title: '成果名称' ,sort: true, fixed: 'left'}
			,{field: 'type', title: '类型',width:150,templet: function(d){
				return checkType(d.type);
			  }}
			,{field: 'uploaderName', title: '提交者'} 
			,{field: 'uploadDate', title: '提交时间',templet: function(d){ 	
				var t=d.uploadDate.time+"";
					return timestampToTime(t.substring(0,10)) 			
				  }}
			,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar-1'}
			]]
		});
		table.on('tool(t-1)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail'){ //查看
				openIframe(data.type,data.aId,data.state,data.uploader,'see')		
		}
		});

	//搜索表格
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
		}else if(state==3){
			table3.reload(conditions);
		}else if(state==-1){
			table_1.reload(conditions);
		}
	});

	//刷新表格
	$('.refresh').click(function (e) { 
		var state=$(this).attr('state');
		$(".table"+state+" .optType").val("");
		$(".table"+state+" .keyword").val('');
		var conditions={
			where: {
				state: parseInt(state),
				type:"",
				keyword:""
			}
			,page: {
			  curr: 1 
			}
		  }
		if(state==4){
			table4.reload(conditions);
		}else if(state==3){
			table3.reload(conditions);
		}else if(state==-1){
			table_1.reload(conditions);
		}
	});
	//查看成果评比

	$(".lookRankBtn").click(function(e){
		var type=$(this).attr('_type');
		var rangeTime=$('#rangeTime').val();
		if(rangeTime==""){
			layer.msg("评审的时间范围不能为空",{time:1500});
			return false;
		}
		var startDate=rangeTime.substring(0,10);
		var endDate=rangeTime.substring(13,23);
		var department=$('#_department').val();
		if(type==1){
			type="individual";
		}else{
			type="department";
		}
		location.href="/ranking/"+type+".do?startDate="+startDate+"&endDate="+endDate+"&department="+department;
	})



	

	//查看成果列表
	$(".getExportList button").click(function (e) {

		var rangeTime=$("#export_rangeTime").val();
		if(rangeTime==""){
			layer.msg("请选择时间范围");
			return false;
		}
		var type=$("#export_type").val();
		var startDate=rangeTime.substring(0,10);
		var endDate=rangeTime.substring(13,23);
		var department=$('#export_department').val();
		var subDepartment=$('#export_subDepartments').val();
		var level=$('#export_level').val();
		var subject="";
		var export_subject=$(".export_subject").attr("class");
		if(export_subject.indexOf("hidden") == -1 ){
			console.log(45)
			subject=$("#export_subject").val();
		}


		/* console.log("/achievementExport.do?startDate="+startDate+"&endDate="+endDate+"&department="+department+"&subDepartment="+subDepartment+"&type="+type+"&subject="+subject+"&level="+level);*/
		var height=$(window).height()-50;
        var width=$(window).width()*0.9;
        layer.open({
        type: 2,
        title:"导出成果列表",
        area: [width+'px', height+'px'],
        content: "/achievementExport.do?startDate="+startDate+"&endDate="+endDate+"&department="+department+"&subDepartment="+subDepartment+"&type="+type+"&subject="+subject+"&level="+level,
        scrollbar:true,
        end: function () {
           
          }  
	  });


		return false;
		
	  
	});


	//新闻列表表格
		var table_news=table.render({
			elem: '#table-news'
			,url: '/news/getAll' //数据接口
			,page: true //开启分页
			,method:"POST"
			,headers: { Authorization: 'Bearer ' + token }
			,contentType: 'application/json'
			,cols: [[ //表头
			{field: 'title', title: '标题' , fixed: 'left'}
			,{field: 'state', title: '状态',templet: function(d){
				if(d.state==1){
					return "未发布";
				}
				return "已发布"
			  }}
			,{field: 'uploaderName', title: '发布者'}
			,{field: 'uploadDate', title: '发布时间',templet: function(d){ 	
				var t=d.uploadDate.time+"";
					return timestampToTime(t.substring(0,10)) 			
				  }}
			,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar-news'}
			]]
		});
		table.on('tool(news)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'edit'){ //编辑新闻
				layer.msg("跳转编辑页面");
		}
		});


		//搜索新闻
	$('.searchBtn-nwes').click(function (e) { 
		console.log("新闻搜索")
		return false;
		var keyword=$(".keyword-news").val();
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
		table_news.reload(conditions);
	});

	//刷新新闻列表
	$('.refresh-news').click(function (e) { 
		console.log("刷新")
		return 
		$(".keyword-news").val("");
		$(".table"+state+" .optType").val("");
		$(".table"+state+" .keyword").val('');
		var conditions={
			where: {
				keyword:""
			}
			,page: {
			  curr: 1 
			}
		  }
	});


	var activitiesTable=table.render({
		elem: '#activitiesTable'
		,url: '/researchActivity/getAll' //数据接口
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
			if(data.state==-1 && action!="see"){
				layer.msg("驳回的活动不能编辑");
				return false;
			}
			var height=$(window).height()-50;
			layer.open({
				type: 2,
				area: ['800px', height+'px'],
				content: "/researchActivity/publish.do?actId="+data.actId+"&state="+data.state+"&action="+action,
				scrollbar:true,
				end: function () {
					$(".layui-laypage-btn").click();  //重新点击分页页面
				  }  
			  });		
		
	});


	$('.activitiesBtn').click(function (e) { 
		var keyword=$(".ActivitiesKeyword").val();
		var conditions={
			where: {
				keyword:keyword
			}
			,page: {
			  curr: 1 
			}
		  }
		  activitiesTable.reload(conditions);
	});
	$(".ActivitiesRefresh").click(function (e) { 
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
		activitiesTable.reload(conditions);
		e.preventDefault();
		
	});


	$(".researchActivityExport").click(function (e) { 
		var height=$(window).height()-50;
        var width=$(window).width()*0.9;
        layer.open({
        type: 2,
        title:"导出活动",
        area: [width+'px', height+'px'],
        content: "/researchActivity/export.do",
        scrollbar:true,
        end: function () {
           
          }  
      });
		
	});



	
	var otherActivityTable=table.render({
		elem: '#otherActivityTable'
		,url: '/otherActivity/getAll' //数据接口
		,method:"POST" 
		,page: true //开启分页
		,headers: { Authorization: 'Bearer ' + token }
		,contentType: 'application/json'
		,cols: [[ //表头
		{field: 'name', title: '调研活动', fixed: 'left'}
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
			if(data.state==-1 && action!="see"){
				layer.msg("驳回的活动不能编辑");
				return false;
			}
			var height=$(window).height()-50;
			layer.open({
				type: 2,
				area: ['800px', height+'px'],
				content: "/otherActivity/publish.do?actId="+data.actId+"&state="+data.state+"&action="+action,
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
		$(".otherActivityKeyword").val('');
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


	$(".otherActivityExport").click(function (e) { 
		var height=$(window).height()-50;
        var width=$(window).width()*0.9;
        layer.open({
        type: 2,
        title:"导出活动",
        area: [width+'px', height+'px'],
        content: "/otherActivity/export.do",
        scrollbar:true,
        end: function () {
           
          }  
      });
		
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


