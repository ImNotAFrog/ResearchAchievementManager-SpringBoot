
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
                    dataList = res.data[0].achievementList;
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
    ,{field: 'uploaderName', title: '姓名',fixed: 'left'} 
    ,{field: 'name', title: '成果名称' ,sort: true}
    ,{field: 'type', title: '类型',templet: function(d){
        return checkType(d.type);
      }}
    ,{field: 'score', title: '得分'} 
    ,{field: 'department', title: '所属部门',templet: function(d){
        var obj=checkDep(departmentList,d.department,d.subDepartment);
        return obj.dep
      }}
    ,{field: 'subDepartment', title: '所属科室',templet: function(d){
        var obj=checkDep(departmentList,d.department,d.subDepartment);
        return obj.subDeps
      }}
    ,{field: 'uploadDate', title: '提交时间',sort: true,templet: function(d){
        var t=d.uploadDate.time+"";
        return timestampToTime(t.substring(0,10))
      }}
    ,{field: 'validDate', title: '发布时间',sort: true,templet: function(d){
        var t=d.validDate.time+"";
        return timestampToTime(t.substring(0,10))
      }}
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


$(".print").click(function (e) { 
    
    
});
    //监听工具条
    table.on('tool(tableEvent)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
    if(layEvent === 'detail'){ //查看
            console.log(data.state)
            openIframe(data.type,data.aId,data.state,data.uploader,'see')
    }
    });


   

})