layui.use(['layer', 'element', 'laytpl','laydate', 'upload'], function () {
    var layer = layui.layer;
    var element = layui.element;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var laytpl = layui.laytpl;
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
    $('.delresearchActivity').click(function (e) { 
        $(".delresearchActivity").attr({"disabled":"true"});
        layer.confirm('确定要删除此调研活动吗？', {
            btn: ['确定', '点错了'] //按钮
        }, function () {
              layer.load();
                ajax_request({
                    type: 'POST',
                    url: '/researchActivity/delete',
                    data: {
                        actId:actId
                    },
                    success: function (res) {
                        res=JSON.parse(res);
                    if (res.state == "success") {
                        layer.alert(res.msg, { icon: 1 }, function () {
                            closeIframe();
                            layer.closeAll();
                        });
                    } else {
                        layer.alert(res.msg, { icon: 5 }, function () {
                            layer.closeAll();
                        });
                        $("#btnSubmit").removeAttr("disabled");
                    }
                    }
            })
        }, function () {
        });

    });
                $('#state').val(state==-1?"驳回":state==1?"未提交":state==2?"待审核":"已通过");
                if((state==1 ||state==-1) && action!="see"){
                    if(state!=-1){
                        $('.submit').removeClass('hidden');
                        $('.submit').click(function (e) { 
                            layer.load();
                            ajax_request({
                                type: 'POST',
                                url: '/researchActivity/submit',  
                                data: {
                                    actId:parseInt(actId)
                                },
                                success: function (res) {
                                    res = JSON.parse(res);
                                    if (res.state == 'success') {
                                    layer.alert('送审成功',{icon:1},function(){
                                        closeIframe();
                                    })
                                    } else {
                                        layer.alert('送审失败',{icon:5},function(){
                                            layer.closeAll();
                                        })
                                    
                                    }
                                }
                            })
                        });
                    }
                }else if(state==2 && action!="see"){
                    $('.submitedWithdraw').removeClass('hidden');
                    $('form input').attr('readonly','readonly');
                    $('.disable-eidt').hide();
                    $('.submitedWithdraw').click(function (e) { 
                        layer.load();
                        ajax_request({
                            type: 'POST',
                            url: '/researchActivity/submitedWithdraw',
                            data: {
                                actId:parseInt(actId)
                            },
                            success: function (res) {
                                res = JSON.parse(res);
                                if (res.state == 'success') {
                                  layer.alert('撤回活动成功',{icon:1},function(){
                                    location.href='/researchActivity/edit.do?actId='+actId+"&state=1";
                                  })
                                } else {
                                    layer.alert(res.msg,{icon:5},function(){
                                        layer.closeAll();
                                      })
                                   
                                }
                            }
                        })
                    });

                }else{
                    if(action=="see"){
                        $('form input').attr('readonly','readonly');
                        $('.see').hide();
                    }else{
                        layer.alert('参数不合法',{icon:5},function () { 
                            closeIframe();
                         })
                    }
                   
                }


    //多文件列表示例
    var demoListView = $('#fileList')
        , uploadListIns = upload.render({
            elem: '#select'
            , url: "/attachment/upload"
            , accept: 'file'
            , multiple: true
            , auto: false
            , data: {
                id: parseInt(actId),
                type: 'researchActivity'
            }  //上传的额外数据
            , headers: { Authorization: 'Bearer ' + token }
            , bindAction: '#stratAction'
            , choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var showem = "";
                    showem = '<div class="tableImg"><div style="background:url(/img/' + getIcon(file.name) + ');background-repeat:no-repeat;background-size:cover"></div></div>'
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + showem + '</td>'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-xs demo-reload" type="button">开始上传</button>'
                        , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete" type="button">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));
                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            , before: function (obj) {
                layer.load(); //上传loading
            }
            , done: function (res, index, upload) {
                if (res.state == "success") { //上传成功
                    flist.push(res.path)
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    var tr = demoListView.find('tr#upload-' + index)
                    tds.eq(4).find('.demo-reload').addClass('layui-hide'); //隐藏单个上传
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
                    //console.log(res.name)
                    var optBtn='<button type="button" filename="'+res.name+'" class="layui-btn layui-btn-xs layui-btn-warm filename">下载</button>'+
                    '<button type="button" filename="'+res.name+'" class="layui-btn layui-btn-xs disable-eidt layui-btn-danger delfile">删除</button>';
                    tds.eq(4).html(optBtn);
                    //重新声明函数 因为doc节点发生变化
                    (function ($) {
                        $(".filename").click(function (e) {
                            var filename = $(this).attr('filename');
                            getFile(filenamee,account);
                        });
                    })(jQuery);

                    
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
                                        url: '/attachment/delete',
                                        data: {
                                            filename:filename,
                                            aId:parseInt(actId),
                                            type:"researchActivity"
                                        },
                                        success: function (res) {
                                            res = JSON.parse(res);
                                            if (res.state == 'success') {
                                                layer.closeAll();
                                                layer.msg(res.msg,{time:1000});
                                                removeEl.remove();
                                            } else {
                                                layer.closeAll();
                                                layer.msg("删除失败",{time:6000});
                                            }
                                        }
                                    }) 
                                }, function () {
                                });
                            });


                    return delete this.files[index]; //删除文件队列已经上传成功的文件    
                }
                this.error(index, upload);
            }
            , allDone: function (obj) { //当文件全部被提交后，才触发
                layer.closeAll();
            }
            , error: function (index, upload) {
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(4).find('.demo-reload').val('重新上传'); //显示重传
            }
        });
//取回信息
    ajax_request({
        type: 'POST',
        url: '/researchActivity/getById',
        data: {
            actId:actId
        },
        success: function (res) {
            res = JSON.parse(res);
            if (res.state == 'success') {
                $('#groupName').val(res.researchActivity.groupName);
                $('#context').val(res.researchActivity.context);
                $('#members').val(res.researchActivity.members);
                $('#personTimes').val(res.researchActivity.personTimes); 
                $('#comments').val(res.researchActivity.comments); 
                $('#location').val(res.researchActivity.location); 
                $('#actId').val(res.researchActivity.actId); 
                if(res.researchActivity.startDate){
                    var startDate=res.researchActivity.startDate.time+"";
                    var endDate=res.researchActivity.endDate.time+"";
                    startDate=timestampToTime(startDate.substring(0,10));
                    endDate=timestampToTime(endDate.substring(0,10));
                    $("#publishDate").val(startDate+"- "+endDate);
                }
                var fileUploaded=$('#fileUploaded');
                if(res.researchActivity.attachment){
                    var fileData=res.researchActivity.attachment+"";
                    fileData=fileData.split("|");
                }else{
                    $('#yc').hide();  //文件为空就隐藏已上传列表
                    return false;
                }
                $.each(fileData, function (index, item) { 
                    if(item==""){
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

                if(state==2 ||state==3){
                    $('.disable-eidt').hide();
                }
                if(action=="see"){
                    $('.see').hide();
                }
    
                (function ($) {
                    //下载文件
                    $(".filename").click(function (e) {
                        var filename = $(this).attr('filename');
                        getFile(filename,account);
                    });
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
                                url: '/attachment/delete',
                                data: {
                                    filename:filename,
                                    aId:parseInt(actId),
                                    type:"researchActivity"
                                },
                                success: function (res) {
                                    res = JSON.parse(res);
                                    if (res.state == 'success') {
                                        layer.closeAll();
                                        layer.msg(res.msg,{time:1000});
                                        removeEl.remove();
                                    } else {
                                        layer.closeAll();
                                        layer.msg("删除失败",{time:6000});
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
        dataList.applicant=account;
        delete dataList.publishDate;
       
        if (actId == "") {
            layer.alert("获取调研活动失败,无法提交", { icon: 5 }, function () {
            })
            return false;
        }
        if(dataList.personTimes!=""){
            if(!checkIsInt(dataList.personTimes)){
                layer.msg("人次请输入正整数");
                $("#btnSubmit").removeAttr("disabled");
                return false;
            }
        }
        layer.load();
        ajax_request({
            type: "POST",
            url: "/researchActivity/upload",
            data:dataList,
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == "success") {
                    layer.alert(res.msg, { icon: 1 }, function () {
                        layer.closeAll();
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