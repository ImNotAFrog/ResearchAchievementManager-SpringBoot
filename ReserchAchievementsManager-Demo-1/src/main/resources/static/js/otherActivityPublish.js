layui.use(['layer', 'element','laydate', 'upload'], function () {
    var layer = layui.layer;
    var element = layui.element;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var actId = null;
    var flist = [];
    //日期时间选择器
    laydate.render({
        elem: '#publishDate'
        , type: 'date'
        ,range: true
    });
    var actId = GetQueryString('actId');
    var state=parseInt(GetQueryString('state'));
    var action=GetQueryString('action');

    
    //检查用户级别  
    function checkAuth(){
        if(role=="ROLE_ADMIN_01"){
            return 1;
        }else{
            layer.alert('非法访问',{icon:5},function(){
                 layer.closeAll();
                 location.href='/index.do';
            })
        }
    }



    //添加驳回事件
    function reject(){
        $('.reject').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/otherActivity/reject',
                data: {
                    actId:parseInt(actId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                        layer.alert('驳回成功',{icon:1},function(){
                            layer.closeAll();
                            closeIframe();
                        })
                   
                    } else {
                        layer.alert('驳回成果失败',{icon:5},function(){
                            layer.closeAll();
                        })
                    
                    }
                },
                error:function(){
                    layer.closeAll();
                    layer.msg('请求失败');
                   
                }
            })
        });
    }


     //撤回
     function approvedWithdraw(){
        $('.Withdraw').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/otherActivity/publishedWithdraw',
                data: {
                    actId:parseInt(actId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                    layer.alert('撤回活动成功',{icon:1},function(){
                        layer.closeAll();
                        closeIframe();
                    })
                    } else {
                        layer.alert('撤回活动失败',{icon:5},function(){
                            layer.closeAll();
                        })
                    
                    }
                },
                error:function(){
                    layer.closeAll();
                    layer.msg('请求失败');
                   
                }
            })
        });
    }
//通过
     function approve(){
        $('.approve').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/otherActivity/publish',
                data: {
                    actId:parseInt(actId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                        layer.alert('发布成功',{icon:1},function(){
                           closeIframe();
                        })
                    } else {
                        layer.alert(res.msg,{icon:5},function(){
                            layer.closeAll();
                        })
                    
                    }
                },
                error:function(){
                    layer.closeAll();
                    layer.msg('请求失败');
                   
                }
            })
        });
    }
    $('#state').val(state==-1?"驳回":state==1?"未提交":state==2?"待审核":"已通过");
    if(action=="see"){
            $('.see').hide();
            $('form input').attr('readonly','readonly');
            if(state==3){
                $(".Withdraw").removeClass('hidden');
                approvedWithdraw();
            }  
        }else if(state==2){
                $('.submit,.reject,.approve,.preCheck').removeClass('hidden');  //显示驳回按钮、发布按钮
                reject();
                approve();
        }else{
                layer.alert('请求不合法',{icon:5},function () { 
                    closeIframe();
                })
        }

    
//取回信息
    ajax_request({
        type: 'POST',
        url: '/otherActivity/adminGetById',
        data: {
            actId: actId
        },
        success: function (res) {
            res = JSON.parse(res);
            if (res.state == 'success') {
                $('#name').val(res.otherActivity.name);
                $('#results').val(res.otherActivity.results);
                $('#members').val(res.otherActivity.members);
                $('#host').val(res.otherActivity.host); 
                $('#comments').val(res.otherActivity.comments); 
                $('#location').val(res.otherActivity.location); 
                $('#actId').val(res.otherActivity.actId); 
                if(res.otherActivity.startDate){
                    var startDate=res.otherActivity.startDate.time+"";
                    var endDate=res.otherActivity.endDate.time+"";
                    startDate=timestampToTime(startDate.substring(0,10));
                    endDate=timestampToTime(endDate.substring(0,10));
                    $("#publishDate").val(startDate+"- "+endDate);
                }
                var fileUploaded=$('#fileUploaded');

                //此处与下面有重复代码 但不能删--
                if((state==2 ||state==3) && checkAuth()==2){
                    $('.disable-eidt').hide();
                }
                //--此处与下面有重复代码 但不能删

                if(res.otherActivity.attachment){
                    var fileData=res.otherActivity.attachment+"";
                    fileData=fileData.split("|");
                }else{
                    $('#yc').hide();  //文件为空就隐藏已上传列表
                    return false;
                }
                $.each(fileData, function (index, item) { 
                    if(item==""){
                      //  return false;
                      return true;
                    }
                    tr='<tr class="fileRow">'+
                        '<td>'+
                           ' <div class="tableImg">'+
                               '<div style="background:url(/img/'+getIcon(item)+');background-repeat:no-repeat;background-size:cover"></div>'+
                           ' </div>'+
                        '</td>'+
                       ' <td>'+item+'</td>'+
                       '<td>'+
                       '<button type="button" filename="'+item+'" class="layui-btn layui-btn-warm layui-btn-xs filename">下载</button>'+
                       '<button class="layui-btn layui-btn-xs layui-btn-danger see  disable-eidt delfile" filename="'+item+'" type="button">删除</button>'+
                        '</td>'+
                       '</tr>';
                    fileUploaded.append(tr);
                });

                if((state==2 ||state==3) && checkAuth()==2){
                    $('.disable-eidt').hide();
                }
                if(action=="see"){
                    $('.see').hide();
                }
                //console.log(JSON.stringify(fileData))
                //下载文件
                $(".filename").click(function (e) {
                    var filename = $(this).attr('filename');
                    getFile(filename,res.otherActivity.uploader);
                });
                (function ($) {
                    //删除文件  
                    $(".delfile").click(function (e) {
                        var filename = $(this).attr('filename');
                        var removeEl=$(this).parent().parent();
                        layer.confirm('确定要删除吗？', {
                            btn: ['确定', '点错了'] //按钮
                        }, function () {
                        layer.load();
                          ajax_request({
                                type: 'POST',
                                url: '/attachment/adminDelete',
                                data: {
                                    filename:filename,
                                    aId:parseInt(actId),
                                    type:"otherActivity"
                                },
                                success: function (res) {
                                    res = JSON.parse(res);
                                    if (res.state == 'success') {
                                        layer.closeAll();
                                        layer.msg(res.msg,{time:1000});
                                        removeEl.remove();
                                    } else {
                                        layer.closeAll();
                                        layer.msg(res.msg,{time:3000});
                                    }
                                }
                            }) 
                        }, function () {
                        });
                    });

                })(jQuery);
            } else {
                layer.alert(res.msg,{icon:5},function(){
                   closeIframe();
                });
            }
        }
    })




    $('#fileupload').submit(function (e) {
        $("#btnSubmit").attr({"disabled":"true"});
        var dataList=$("#fileupload").serializeObject(); //将表单序列化为JSON对象   
        var rangeTime=$("#publishDate").val();
        dataList.startDate=rangeTime.substring(0,10);
        dataList.endDate=rangeTime.substring(13,23);
        delete dataList.publishDate;
        if (actId == null) {
            layer.alert("获取活动ID失败,不能提交", { icon: 5 }, function () {
                layer.closeAll();
            })
            return false;
        }
        layer.load();
        ajax_request({
            type: "POST",
            url: "/otherActivity/adminModify",
            data:dataList,
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == "success") {
                    layer.alert(res.msg, { icon: 1 }, function () {
                       closeIframe();
                    });
                } else {
                    layer.alert(res.msg, { icon: 5 }, function () {
                        $("#btnSubmit").removeAttr("disabled");
                        layer.closeAll();
                    });
                  
                }
            }
        })


        return false;

    });


});