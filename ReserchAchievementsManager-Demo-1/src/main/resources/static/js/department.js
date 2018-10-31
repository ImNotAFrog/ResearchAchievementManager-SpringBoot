
layui.use([ 'layer', 'table', 'element'], function(){
	layer = layui.layer //弹层
	,table = layui.table //表格
    var startDate=GetQueryString("startDate");
	var endDate=GetQueryString("endDate");
    var department=GetQueryString("department");
    var dataList=[];
    var departmentList=[];
/*     console.log(startDate)
    console.log(endDate)
    console.log(department) */

//渲染表格
    function renderTable() {
        ajax_request({
            type: 'POST',
            url: '/ranking/individual',
            data: {
                startDate: startDate,
                endDate: endDate,
                department: department
            },
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == 'success') {
                    //dataList = res.data[0].achievementList;
                    dataList = res.data;
                    mytable.reload({
                        data: dataList
                    })
                } else {
                    layer.alert('获取数据失败', { icon: 5 }, function () {
                        layer.closeAll();
                    })
                }
            },
            error:function(){
                layer.alert('获取数据失败', { icon: 5 }, function () {
                    layer.closeAll();
                })
                mytable.reload({
                    data: []
                })
            }
        })
    }
        
    ajax_request({
         type: 'GET',
         url : "/user/getDepartmentList", 
         success: function (res) {
            res = JSON.parse(res);
            if (res.state == 'success') {
                departmentList=res.dep;
                renderTable();
            } else {
                layer.alert('获取数据失败',{icon:5},function(){
                     layer.closeAll();
                })
            }
         }
    })

  




 var mytable=table.render({
    elem: '#dataTable'
    ,page: true //开启分页
    ,cols: [[ //表头
    ,{field: 'rank', title: '排名',fixed: 'left'} 
    ,{field: 'department', title: '部门名称',templet: function(d){
        var obj=checkDep(departmentList,d.department,d.subDepartment);
        return obj.dep
      }}
      ,{field: 'score', title: '成果总数'}
      ,{field: 'score', title: '得分'}
  /*   ,{field: 'subDepartment', title: '所属科室',templet: function(d){
        var obj=checkDep(departmentList,d.department,d.subDepartment);
        return obj.subDeps
      }} */
   /*  ,{field: 'uploadDate', title: '提交时间',sort: true,templet: function(d){
        var t=d.uploadDate.time+"";
        return timestampToTime(t.substring(0,10))
      }}
    ,{field: 'validDate', title: '发布时间',sort: true,templet: function(d){
        var t=d.validDate.time+"";
        return timestampToTime(t.substring(0,10))
      }} */
    ,{fixed: 'right',title:'操作', align:'center', toolbar: '#toolBar'}
    ]]
});



 //搜索表格
 $('.searchBtn').click(function (e) { 
    var keyword=$(".keyword").val();
    var tempData=dataList.filter(item=>{
        if(item.name.includes(keyword)){
            return item
        }
    })
    mytable.reload({
        data:tempData
    })
});

//刷新表格
$('.refresh').click(function (e) { 
    renderTable();
}); 


//导出成果
$(".print").click(function (e) { 

/*     var a = document.createElement('a');
	a.href = "/attachment/get/file?filename=" + filename + "&account=" + uploader;
	a.click();
    $(a).remove(); */
    table.exportFile(mytable.config.id, dataList, "xls")
    return false;

    ajax_request({
         type: 'POST',
         url: '/ranking/download/individualRank',
         data: {
            startDate: startDate,
            endDate: endDate,
            department: department
         },
         success: function (res) {
            res = JSON.parse(res);
            if (res.state == 'success') {
    
            } else {
    
            }
         }
    })
    
});
    //监听工具条
    table.on('tool(tableEvent)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
    if(layEvent === 'detail'){ //查看
        var height=$(window).height()-50;
        var width=$(window).width()*0.9;
        layer.open({
        type: 2,
        title:data.name+"的成果列表",
        area: [width+'px', height+'px'],
        content: "/ranking/individualDetail.do?startDate="+startDate+"&endDate="+endDate+"&department="+department,
        scrollbar:true,
        end: function () {
            $(".layui-laypage-btn").click();  //重新点击分页页面
          }  
      });
    }
    });


   

})