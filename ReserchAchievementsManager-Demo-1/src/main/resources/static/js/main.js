

 //设置本地储存
function setItem(key,val){
	localStorage.setItem(key,val);
}

//获取本地储存
function getItem(key){
	return localStorage.getItem(key);
}

//删除本地储存
function removeItem(key){
	localStorage.removeItem(key);
}

var token = TokenHelper.getLocalToken()
var role = TokenHelper.getRoleFromToken(token)
var exp = TokenHelper.getExpirationDateFromToken(token)
var account = TokenHelper.getAccountFromToken(token)

if (exp != null && exp < new Date()) {
	alert("距离上次登陆时间过长，请重新登陆", "提示", function () {
		Header.logout()
	})
}
window.onload = function () {

};


jQuery(function ($) {
	$.datepicker.regional['zh-CN'] = {
		clearText: '清除',
		clearStatus: '清除已选日期',
		closeText: '关闭',
		closeStatus: '不改变当前选择',
		prevText: '< 上月',
		prevStatus: '显示上月',
		prevBigText: '<<',
		prevBigStatus: '显示上一年',
		nextText: '下月>',
		nextStatus: '显示下月',
		nextBigText: '>>',
		nextBigStatus: '显示下一年',
		currentText: '今天',
		currentStatus: '显示本月',
		monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
			'十月', '十一月', '十二月'],
		monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
			'九月', '十月', '十一月', '十二月'],
		monthStatus: '选择月份',
		yearStatus: '选择年份',
		weekHeader: '周',
		weekStatus: '年内周次',
		dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
		dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
		dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
		dayStatus: '设置 DD 为一周起始',
		dateStatus: '选择 m月 d日, DD',
		dateFormat: 'yy-mm-dd',
		firstDay: 1,
		initStatus: '请选择日期',
		isRTL: false
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

//通过ID获取元素值
function getValById(idName) {
	return $('#' + idName).val();
}

//验证数据是否为正整数
function checkIsInt(value) {
	if (!(/(^[1-9]\d*$)/.test(value))) {
		return false;
	} else {
		return true;
	}
}


//获取地址栏的参数值
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}

//将时间戳转换为日期
function timestampToTime(timestamp) {
	var date = new Date(timestamp*1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	var D = date.getDate() + ' ';
	return Y + M + D;
}


//重绘的ajax请求
function ajax_request(opt) {
	opt = opt || {};
	opt.data = opt.data || null;
	opt.url = opt.url || '';
	opt.type = opt.type || 'GET';
	opt.success = opt.success || function () { }
	opt.error = opt.error || function () { }
	data=opt.data;
	if(opt.data!=null){
		data = JSON.stringify(opt.data);
	}
	$.ajax({
		type: opt.type,
		url: opt.url,
		data: data,
		headers: { Authorization: 'Bearer ' + token },
		contentType: 'application/json',
		success: function (res) {
			opt.success(res)
		},
		error: function (res) {
			opt.error(res);
		}
	});

}

//判断文件类型
function getIcon(filename) {
	var result = filename.substring(filename.lastIndexOf('.') + 1, filename.length);
	result = result.toLowerCase();
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
//下载文件
function getFile(filename,uploader) {
	var a = document.createElement('a');
	a.href = "/attachment/get/file?filename=" + filename + "&account=" + uploader;
	a.click();
	$(a).remove();
}

//将表单序列化成对象
$.fn.serializeObject = function () {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function () {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
} 

// 检查成果状态
function checkState(value) {
	switch (value) {
		case -1:
			return '驳回';
			break;
		case 1:
			return '未提交';
			break;
		case 2:
			return '待初审';
			break;
		case 3:
			return '待复审';
			break;
		case 4:
			return '已通过';
			break;
		default:
			return '参数错误'
			break;
	}
}


// 查询成果中文名称
function checkType(value) {
	value = value.toLowerCase();
	switch (value) {
		case "thesis":
			return '论文类';
			break;
		case "project":
			return '课题项目类';
			break;
		case "textbook":
			return '论著、教材类';
			break;
		case "patent":
			return '专利';
			break;
		case "reformproject":
			return '教学改革项目类';
			break;
		case "laws":
			return '法律、法规类';
			break;
		default:
			return '不存在的类型';
			break;
	}
}
//显示成功后的选项
function successOpt(msg){
	$('#tips').text(msg)
	layer.open({	
		type: 1,
		closeBtn: 0,
		area: ['400px', '180px'],
		content: $('#result-msg')
	  });
}
//关闭Iframe层
function closeIframe(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index)
}

function checkIsempty(val){
	if(val){
		return val;
	}
	return "空";
}

