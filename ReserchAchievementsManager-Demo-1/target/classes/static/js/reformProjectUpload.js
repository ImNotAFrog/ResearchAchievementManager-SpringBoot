layui.use(['layer', 'element','laydate','upload'], function(){
	var layer = layui.layer;
	var element = layui.element;
	var laydate = layui.laydate;
    var upload = layui.upload;
    var rpId=null;
    var flist=[];
    //日期时间选择器
    laydate.render({
    elem: '#projectDate'
        ,type: 'date'
    });

		 //多文件列表示例
    var demoListView = $('#fileList')
        , uploadListIns = upload.render({
            elem: '#select'
            , url: "/attachment/upload"
            , accept: 'file'
            , multiple: true
            , auto: false
            , data: {
                id: null,
                type: 'reformProject'
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
            ,before:function(obj){
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
                    '<button type="button" filename="'+res.name+'" class="layui-btn layui-btn-xs layui-btn-danger delfile">删除</button>';
                    tds.eq(4).html(optBtn);
                    //重新声明函数 因为doc节点发生变化
                        (function($) {
                            $(".filename").click(function (e) { 
                            var filename=$(this).attr('filename');
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
                                        aId:parseInt(rpId),
                                        type:"reformProject"
                                    },
                                    success: function (res) {
                                        res = JSON.parse(res);
                                        if (res.state == 'success') {
                                            layer.closeAll();
                                            layer.msg(res.msg,{time:1000});
                                            removeEl.remove();
                                        } else {
                                            layer.closeAll();
                                            layer.msg(res.msg,{time:6000});
                                        }
                                    }
                                }) 
                            }, function () {
                            });
                        });


                    })(jQuery);
                    return delete this.files[index]; //删除文件队列已经上传成功的文件    
                }
                this.error(index, upload);
            }
            ,allDone: function(obj){ //当文件全部被提交后，才触发
                layer.closeAll();
              }
            , error: function (index, upload) {
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(4).find('.demo-reload').val('重新上传'); //显示重传
            }
        });



//获取成果ID
    ajax_request({
        url: "/reformProject/create", 
        success: function (res){
            res=JSON.parse(res);
            if (res.state == "success") {
                uploadListIns.config.data.id =res.rpId;
                rpId=res.rpId;
            } else {
                layer.msg('获取成果ID失败');
            }
        },
        error:function(){
            layer.msg('获取成果ID失败');
        }
    })

    
	
    $('#fileupload').submit(function (e) {
        $("#btnSubmit").attr({ "disabled": "true" });
        if (rpId == null) {
            layer.alert("获取成果ID失败,无法保存。", { icon: 5 }, function () {
                $("#btnSubmit").attr({ "disabled": "false" });
                layer.closeAll();
            })
            return false;
        }
        var dataList=$('#fileupload').serializeObject();
        dataList.rpId = rpId;
        dataList.uploader = account;
        $.ajax({
            type: "POST",
            url: "/reformProject/upload",
            headers: { Authorization: 'Bearer ' + token },
            data: JSON.stringify(dataList),
            contentType: 'application/json',
            success: function (res) {
                res = JSON.parse(res);
                if (res.state == "success") {
                    layer.alert(res.msg, { icon: 1 }, function () {
                        layer.closeAll();
                        setItem('active','item3');
                        window.location.href = "/teacher.do";
                    });
                } else {
                    layer.alert(res.msg, { icon: 5 }, function () {
                        layer.closeAll();
                        $("#btnSubmit").attr({ "disabled": "true" });
                    });
                }
            }
        });
        return false;
    });
	});