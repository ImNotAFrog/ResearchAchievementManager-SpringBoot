/*
 * @Author: MaiBenBen
 * @Date:   2018-01-27 23:11:28
 * @Last Modified by:   MaiBenBen
 * @Last Modified time: 2018-02-13 18:09:29
 */

//设置本地储存
function setItem(key, val) {
	localStorage.setItem(key, val);
}

//获取本地储存
function getItem(key) {
	return localStorage.getItem(key);
}

//删除本地储存
function removeItem(key) {
	localStorage.removeItem(key);
}


class Header {
	static home() {
		var token = TokenHelper.getLocalToken()
		var role = TokenHelper.getRoleFromToken(token)
		switch (role) {
			case 'ROLE_ADMIN_01':
				window.location.href = "/admin1.do"
				break;
			case 'ROLE_ADMIN_02':
				window.location.href = "/admin2.do"
				break;
			case 'ROLE_TEACHER':
				window.location.href = "/teacher.do"
				break;
			default:
				alert('登陆出错!')
				window.location.href = "/index"
				break;
		}
	}
	static logout() {
		$.get({
			url: '/auth/logout',
			success: function () {
				TokenHelper.deleteLocalToken(token)
				window.location.href = '/'
			},
			error: function () {
				console.log('logout error')
			}
		})
		removeItem('active');
	}
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

$(document).ready(function () {
	if (token == null) {
		$(".auth").removeClass("hidden");
	} else {
		$(".logined").removeClass("hidden");
	}
});

function confirmLogout() {

	/* layer.confirm('确认退出', {
		btn: ['确定','点错了'] //按钮
	  }, function(){
		Header.logout()
	  }, function(){
		
	  }); */
	if (confirm("确认退出?")) {
		Header.logout()
	}
}
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




