
layui.use(['layer', 'element','laydate', 'upload'], function () {
    var layer = layui.layer;
    var element = layui.element;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var laytpl = layui.laytpl;
    var flist = [];
    //日期时间选择器
    laydate.render({
        elem: '#projectDate'
        , type: 'date'
    });
    var pId = GetQueryString('aId');
    var state=parseInt(GetQueryString('state'));
    var action=GetQueryString('action');
    //检查用户级别  
    function checkAuth(){
        if(role=="ROLE_ADMIN_01"){
            return 1;
        }else if(role=="ROLE_ADMIN_02"){
            return 2;
        }else{
            layer.alert('非法访问',{icon:5},function(){
                 layer.closeAll();
            })
        }
    }
    //初级管理员添加上报成果事件
    function preCheck(){
        $('.preCheck').click(function (e) { 
            var load=layer.load();
            ajax_request({
                type: 'POST',
                url: '/achievement/preCheck',
                data: {
                    aId:parseInt(pId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                        layer.close(load);
                        successOpt("上报成果成功");
                    } else {
                        layer.alert('上报成果失败',{icon:5},function(){
                            layer.closeAll();
                          })
                       
                    }
                }
            })
        });
    }

    //管理员添加驳回事件
    function reject(){
        $('.reject').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/achievement/reject',
                data: {
                    aId:parseInt(pId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                    layer.alert('驳回成果成功',{icon:1},function(){

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

    //初级管理员撤回成果事件
    function preCheckedWithdraw(){
        $('.Withdraw').click(function (e) { 
            console.log('初级撤回');
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/achievement/preCheckedWithdraw',
                data: {
                    aId:parseInt(pId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                        successOpt('撤回成功')
                    } else {
                        layer.alert('撤回成果失败',{icon:5},function(){
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

     //高级管理员撤回成果事件
     function approvedWithdraw(){
        $('.Withdraw').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/achievement/approvedWithdraw',
                data: {
                    aId:parseInt(pId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                    layer.alert('撤回成果成功',{icon:1},function(){
                        
                    })
                    } else {
                        layer.alert('撤回成果失败',{icon:5},function(){
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

     //高级管理员复审通过
     function approve(){
        $('.approve').click(function (e) { 
            layer.load();
            ajax_request({
                type: 'POST',
                url: '/achievement/approve',
                data: {
                    aId:parseInt(pId)
                },
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                    layer.alert('复审通过',{icon:1},function(){
                        
                    })
                    } else {
                        layer.alert('撤回成果失败',{icon:5},function(){
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
        $('#state').val(checkState(state));
        if(action=="see"){
            //console.log('查看模式')
            $('.see').hide();
            $('form input').attr('readonly','readonly');
            //判断撤回成果
            if(state==3 && checkAuth()==2){
                //初级管理员撤回成果事件
                $(".Withdraw").removeClass('hidden');
                preCheckedWithdraw();
            }else if(state==4 && checkAuth()==1){
                //高级管理员撤回已通过成果事件
                $(".Withdraw").removeClass('hidden');
                approvedWithdraw();
            }
        }else if((state==1 ||state==-1) && action==null){
            if(state!=-1){
                $('.submit').removeClass('hidden');
            }
        }else if(state==2){
            if(checkAuth()==2){
                $('.reject,.preCheck').removeClass('hidden');  //显示驳回按钮、上报按钮
                reject();
                preCheck();
                $('form input').attr('readonly','readonly'); //只读
            }
        }else if(state==3 && checkAuth()==1){
            $('.btnSubmit,.submit,.reject,.approve').removeClass('hidden');  //显示驳提交按钮、保存、驳回、通过、重置
            reject();
            approve();
            console.log('高级管理员');
        }else{
                layer.alert('请求不合法',{icon:5},function () { 
                    layer.closeAll();
                })
        }

    

//取回课题项目信息
    ajax_request({
        type: 'POST',
        url: '/project/adminGetById',
        data: {
            pId: pId
        },
        success: function (res) {
            res = JSON.parse(res);
            if (res.state == 'success') {
                $('#involvement').val(res.project.involvement);
                $('#level').val(res.project.level);
                $('#pName').val(res.project.pName);
                $('#subject').val(res.project.subject); 
                $('#uploader').val(res.project.uploader); 
                if(res.project.projectDate){
                    var projectDate=res.project.projectDate.time+"";
                    $('#projectDate').val(timestampToTime(projectDate.substring(0,10)));
                }
                $('#pId').val(res.project.pId);
                getTm(res.project.pName);//获取同名结果
                var fileUploaded=$('#fileUploaded');

                //此处与下面有重复代码 但不能删--
                if((state==2 ||state==3) && checkAuth()==2){
                    $('.disable-eidt').hide();
                }
                //--此处与下面有重复代码 但不能删

                if(res.project.attachment){
                    var fileData=res.project.attachment+"";
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
                    getFile(filename,res.project.uploader);
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
                                    aId:parseInt(pId),
                                    type:"project"
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
                    layer.closeAll();
                });
            }
        }
    })

    
    //获取同名成果列表
    function getTm(name){
        ajax_request({
            type: 'POST',
            url: '/achievement/getListByName',
            data: {
                name: name,
            },
            success: function (res) {
                res = JSON.parse(res);
                if (res.code == 0) {
                    var tm=$('#tm');
                    $.each(res.data, function (index, item) { 
                        if(item==""){
                          return true;
                        }
                        tr='<tr class="fileRow">'+
                            '<td>'+item.name+'</td>'+
                           ' <td>'+checkType(item.type)+'</td>'+
                           ' <td>'+item.uploaderName+'</td>'+
                           ' <td>'+'未设置'+'</td>'+
                           '<td>'+
                           '<button type="button"  class="layui-btn layui-btn-warm layui-btn-xs filename">详情</button>'+
                            '</td>'+
                           '</tr>';
                        tm.append(tr);
                    });
                }
            }
        })
    }



    $('#fileupload').submit(function (e) {
        layer.load();
        $("#btnSubmit").attr({"disabled":"true"});
        var dataList=$("#fileupload").serializeObject(); //将表单序列化为JSON对象   
        console.log(dataList);
        if (pId == null) {
            layer.alert("获取论文ID失败,不能提交", { icon: 5 }, function () {
                layer.closeAll();
            })
            return false;
        }
        ajax_request({
            type: "POST",
            url: "/project/adminModify",
            data:dataList,
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == "success") {
                    layer.alert(res.msg, { icon: 1 }, function () {
                        layer.closeAll();
                            location.reload();
                    });
                } else {
                    layer.alert(res.msg, { icon: 5 }, function () {
                        $("#btnSubmit").attr({"disabled":"false"});
                        layer.closeAll();
                    });
                  
                }
            }
        })


        return false;

    });

    $(".goback").click(function (e) { 
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
       parent.layer.close(index)
    });

    //跳转到下一页
    window.jumpNext=function(type=null){
        var data=null;
        if(type!=null){
           data={
               type:type
           }
        }
        var load=layer.load();
        ajax_request({
             type: 'POST',
             url: '/achievement/getNextAchievementId',
             data: data,
             success: function (res) {
                res = JSON.parse(res);
                if (res.state == 'success') {
                    layer.close(load);
                    location.href="/type="+res.type+"/exam.do?aId="+res.aId+"&state="+state;
                } else {
                    layer.close(load);
                        var alert=alert=layer.alert(res.msg,{icon:5},function() {
                            layer.close(alert);
                      });
                }
             }
        })

    }



});