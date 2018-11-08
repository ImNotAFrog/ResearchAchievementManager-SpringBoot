
layui.use([ 'layer', 'element'], function(){
	layer = layui.layer //弹层
	,table = layui.table //表格
    var uId=GetQueryString("uId");
    var url="/user/createUser";
    if(uId>0){
        url="/user/updateUser";
        ajax_request({
             type: 'POST',
             url: '/user/getUserById',
             data:{
                uId:uId
             },
             success: function (res) {
                res = JSON.parse(res);
                if (res.state == 'success') {
                     $("#account").val(res.user.account);
                     $("#username").val(res.user.username);
                     $("#roles").val(res.user.roles[0]);
                } else {
                    layer.alert('获取用户信息失败',{icon:5},function(){
                         layer.closeAll();
                         closeIframe();
                    })
                }
             }
        })
    }else{
        $("#account").removeAttr('readonly');
    }

    $("#addUser").submit(function (e) { 
        var userInfo=$("#addUser").serializeObject();
        userInfo.account=userInfo.account.replace(/\s/g,"");
        userInfo.username=userInfo.username.replace(/\s/g,"");
        userInfo.password=userInfo.password.replace(/\s/g,"");
        if(userInfo.account==""){
            layer.msg("账号不能为空");
            return false;
        }
        if(userInfo.username==""){
            layer.msg("用户名不能为空");
            return false;
        }
        //编辑模式
        if(uId>0){
            if(userInfo.password!=userInfo.repawd){
                layer.msg("密码与确认密码不一致");
                return false;
            }
        }else{  //新增模式
            if(userInfo.password==""){
                layer.msg("密码不能为空");
                return false;
            }
            if(userInfo.password!=userInfo.repawd){
                layer.msg("密码与确认密码不一致");
                return false;
            }
        }
        
        delete userInfo.repawd;
        userInfo.roles=[userInfo.roles];
        layer.load();
            ajax_request({
                type: 'POST',
                url: url,
                data: userInfo,
                success: function (res) {
                    res = JSON.parse(res);
                    if (res.state == 'success') {
                        var msg="新增用户成功";
                        if(uId>0){
                            msg="更新用户成功";
                        }
                        layer.alert(msg,{icon:1},function(){
                            layer.closeAll();
                            closeIframe();
                       })
                    } else {
                        layer.alert(res.msg,{icon:5},function(){
                             layer.closeAll();
                        })
                    }
                }
            })

        e.preventDefault();
        
    });

    



   

})