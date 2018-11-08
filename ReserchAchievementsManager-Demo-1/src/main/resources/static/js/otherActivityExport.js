
layui.use([ 'layer', 'table', 'element'], function(){
	layer = layui.layer //弹层
	,table = layui.table //表格
//渲染表格
    function renderTable() {
        ajax_request({
            type: 'POST',
            url: '/otherActivity/getPublished',
            success: function (res) {
                res = JSON.parse(res);
                if (res.code == 0) {
                    dataList=formatData(res.data);
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

    renderTable();

  




 var mytable=table.render({
    elem: '#dataTable'
    ,page: true //开启分页
    ,cols: [[ //表头
    ,{field: 'name', title: '活动名',fixed: 'left'} 
    ,{field: 'results', title: '主要成果' ,sort: true}
    ,{field: 'members', title: '参与人员'}
    ,{field: 'host', title: '主办方',sort: true}
    ,{field: 'startDate', title: '开始时间'}
    ,{field: 'endDate', title: '结束时间'}
    ,{field: 'location', title: '地点'}
  
    ]]
});



//刷新表格
$('.refresh').click(function (e) { 
    renderTable();
}); 



//格式化数据
function formatData(_data) { 
    var temp=null;
    var t1;
    var t2; 
    var data=_data
    $.each(data, function (index, items) {
        if (data[index].startDate != null) {
            t1 = data[index].startDate.time + "";
            t2 = data[index].endDate.time + "";
            data[index].startDate = timestampToTime(t1.substring(0, 10));
            data[index].endDate = timestampToTime(t2.substring(0, 10));
        }
    });
    return data;
}

  


// 导出成果
    $(".print").click(function (e) {
        if(dataList.length==0){
            return false;
          }
        table.exportFile(mytable.config.id,dataList, "xlsx");
        return false;
    });

    //监听工具条
    table.on('tool(tableEvent)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
    if(layEvent === 'detail'){ //查看
    

    }
    });

})