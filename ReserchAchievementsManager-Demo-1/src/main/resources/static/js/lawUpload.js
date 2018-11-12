layui.use(['layer', 'element','upload'], function(){
	var layer = layui.layer;
	var element = layui.element;
    var upload = layui.upload;
    var tId=null;
    var flist=[];

//判断文件类型
		function getIcon(filename) {
            var result = filename.substring(filename.lastIndexOf('.') + 1, filename.length);
            result=result.toLowerCase();
			if (result == "jpg" || result == "png" || result == "gif" || result == "jpg") {
				result = 'img.png';
			} else if (result == "pdf") {
				result = "pdf.jpg"
			} else if (result == "doc" || result == "docx") {
				result = 'word.jpg';
			} else if (result == "ppt" || result == "pptx") {
				result = 'ppt.jpg';
			} else if (result == "xls" || result == "xlsx") {
				result = 'excel.jpg';
			} else if (result == "7z" || result == "rar" || result == "zip") {
				result = 'rar.jpg';
			} else {
				result = 'file.jpg';
			}
			return result;
		}

		 //多文件列表示例
    var demoListView = $('#fileList')
        , uploadListIns = upload.render({
            elem: '#select'
            //,url: 'http://www.demo.com/admin/file/upFile'   
            , url: "/attachment/upload"
            , accept: 'file'
            , multiple: true
            , auto: false
            , data: {
                id: null,
                type: 'thesis'
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
                    tds.eq(4).html('<button type="button" filename="'+res.name+'" class="layui-btn layui-btn-xs layui-btn-danger filename">下载</button>');
                   
                    //重新声明函数 因为doc节点发生变化
                    (function($) {
                        $(".filename").click(function (e) { 
                           var filename=$(this).attr('filename');
                           getFile(filename);
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


        /**下载文件
         * filename 文件名字
         *  */ 
        
     function getFile(filename) {
         var a = document.createElement('a');
         a.href ="/attachment/get/file?filename="+filename+"&account="+account;
         a.click();
         $(a).remove();
    }

	

         //获取成果ID
		/* 	$.ajax({
				type: "GET",
				url: "/thesis/create", 
				headers: { Authorization: 'Bearer ' + token },
                contentType : 'application/json',
				success: function (res) {
                    res=JSON.parse(res);
					if (res.state == "success") {
                        uploadListIns.config.data.id =res.tId;
                        tId=res.tId;
					} else {
						layer.msg('获取成果ID失败');
					}
				}
			}); */


        $('#fileupload').submit(function (e) { 
          var dataList={
                tName:getValById('tName'),
                journalLevel:getValById('journalLevel'),
                journalName:getValById('journalName'),
                journalNum:getValById('journalNum'),
                publishDate:getValById('publishDate'),
                uploader:account,
                tId:tId
            }
            if(tId==null){
                layer.alert("获取论文ID失败",{icon:5},function(){
                    layer.closeAll();
                })
            }
            $.ajax({
                type: "POST",
                url: "/thesis/upload",
                headers: { Authorization: 'Bearer ' + token },
                data: JSON.stringify(dataList),
                contentType : 'application/json',
                success: function (res) {
                    res=JSON.parse(res);
                    if(res.state=="success"){
                        layer.alert(res.msg,{icon:1},function(){
                            layer.closeAll();
                            window.location.href = "/teacher.do";
                        });
                    }else{
                        layer.alert(res.msg,{icon:5},function(){
                            layer.closeAll();
                        });
                    }
                }
            });
            return false;
            
        });




	});