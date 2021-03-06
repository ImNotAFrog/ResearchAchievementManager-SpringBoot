/*
 * @Author: MaiBenBen
 * @Date:   2018-01-27 23:11:28
 * @Last Modified by:   MaiBenBen
 * @Last Modified time: 2018-02-13 18:09:29
 */
class Header{
	static home() {
		var token = TokenHelper.getLocalToken()
		var role = TokenHelper.getRoleFromToken(token)
		switch (role) {
		case 'ROLE_ADMIN_01':
			window.location.href = "/admin01.do"
			break;
		case 'ROLE_ADMIN_02':
			window.location.href = "/admin02.do"
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
	static logout(){
		$.get({
			url : '/auth/logout',
			success : function() {
				TokenHelper.deleteLocalToken(token)
				window.location.href = '/'
			},
			error : function() {
				console.log('logout error')
			}
		})
	}
}
var token = TokenHelper.getLocalToken()
var role = TokenHelper.getRoleFromToken(token)
var exp = TokenHelper.getExpirationDateFromToken(token)
var account=TokenHelper.getAccountFromToken(token)

if(exp!=null&&exp<new Date()){
	alert("距离上次登陆时间过长，请重新登陆","提示",function(){
		Header.logout()
	})
}
window.onload = function() {
	var oSlider = document.getElementsByClassName("slider")[0], navWrap = document
			.getElementsByClassName("nav-wrap")[0], oLogin = document
			.getElementsByClassName("login")[0], oLogined = document
			.getElementsByClassName("logined")[0], unLogin = document
			.getElementsByClassName("unlogin")[0], userCenter = oLogined
			.getElementsByClassName("user-center")[0], userIcon = oLogined
			.getElementsByClassName("portrait")[0], oImg = document
			.getElementById("show"), IndexPage = document
			.getElementById("index"), LoginPage = document
			.getElementById("loginpage"), NewsPage = document
			.getElementById("newspage"),
	// prev = document.getElementById("prev"),
	// next = document.getElementById("next"),
	// navLi = navWrap.getElementsByTagName("li"),
	// navLia1 = navLi[0].getElementsByTagName("a")[0],
	// navLia2 = navLi[1].getElementsByTagName("a")[0]
	// aLi = oSlider.getElementsByTagName("li"),
	// oSubmit = LoginPage.getElementsByTagName("input")[2],
	// oBtn = userCenter.getElementsByTagName("button")[0],
	// arrImg =
	// ["../img/index/demo.png","../img/index/demo1.png","../img/index/demo2.png","../img/index/demo3.png"],
	// arrSelect = ["../img/index/unselect.png","../img/index/select.png"],
	timer = null, wait = null, a = 0;
	// Length = aLi.length;

	// 鏉烆喗鎸�
	// function unSlider() {
	// timer = setInterval(function(){
	// if (a < 3) {
	// a++;
	// oImg.src = arrImg[a];
	//			 		
	// }
	// else{
	// a = 0;
	// oImg.src = arrImg[a];
	// }
	// showSelect();
	// },3000);
	// }
	// unSlider();

	// prev.onclick = function(){
	// if (a == 0) {
	// a = Length - 1;
	// oImg.src = arrImg[a];
	// }
	// else{
	// a--;
	// oImg.src = arrImg[a];
	// }
	// showSelect();
	// }
	//
	// next.onclick = function(){
	// if (a == Length - 1) {
	// a = 0;
	// oImg.src = arrImg[a];
	// }
	// else{
	// a++;
	// oImg.src = arrImg[a];
	// }
	// showSelect();
	// }

	// // 鐏忎浇顥栭惃鍕娑擃亪娈㈤崶鍓у閺�懓褰夐惃鍕櫏閺嬫粌娴�
	// function showSelect(){
	// for (var i = 0; i < Length; i++) {
	// var aImg = aLi[i].getElementsByTagName("img")[0];
	// if (i == a) {
	// aImg.src = arrSelect[1];
	// }
	// else{
	// aImg.src = arrSelect[0];
	// }
	// }
	// }

	// 閻ц缍嶉柈銊ュ瀻
	if (oLogin) {
		oLogin.onclick = function() {
			IndexPage.style.display = "none";
			NewsPage.style.display = "none";
			LoginPage.style.display = "block";
			return false;
		}
	}

	// 閸ョ偛鍩屾＃鏍�
	// navLi[0].onclick = function(){
	// clearInterval(timer);
	// }

	// 閺備即妞堢挧鍕唵妞わ拷
	// navLi[1].onclick = function(){
	// LoginPage.style.display = "none";
	// IndexPage.style.display = "none";
	// NewsPage.style.display = "block";
	// navLia1.className = "";
	// navLia2.className = "live";
	// return false;
	// }

	userIcon.onmouseover = function() {
		userCenter.style.display = "block";
	}
	userIcon.onmouseout = function() {
		wait = setTimeout(function() {
			userCenter.style.display = "none";
		}, 2000);
	}

	userCenter.onmouseover = function() {
		clearTimeout(wait);
		userCenter.style.display = "block";
	}
	userCenter.onmouseout = function() {
		userCenter.style.display = "none";
	}

	/* 闁拷鍤惂璇茬秿 */
	// oBtn.onclick = function(){
	// unLogin.style.display = "block";
	// oLogined.style.display = "none";
	// }
	if (token == null) {
		oLogined.style.display = "none";
		unLogin.style.display = "block";
	} else {
		oLogined.style.display = "block";
		unLogin.style.display = "none";
	}
};


function confirmLogout() {
	if (confirm("确认退出?")) {
		Header.logout()
	}
}
jQuery(function($) {
	$.datepicker.regional['zh-CN'] = {
		clearText : '清除',
		clearStatus : '清除已选日期',
		closeText : '关闭',
		closeStatus : '不改变当前选择',
		prevText : '< 上月',
		prevStatus : '显示上月',
		prevBigText : '<<',
		prevBigStatus : '显示上一年',
		nextText : '下月>',
		nextStatus : '显示下月',
		nextBigText : '>>',
		nextBigStatus : '显示下一年',
		currentText : '今天',
		currentStatus : '显示本月',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
				'九月', '十月', '十一月', '十二月' ],
		monthStatus : '选择月份',
		yearStatus : '选择年份',
		weekHeader : '周',
		weekStatus : '年内周次',
		dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		dayStatus : '设置 DD 为一周起始',
		dateStatus : '选择 m月 d日, DD',
		dateFormat : 'yy-mm-dd',
		firstDay : 1,
		initStatus : '请选择日期',
		isRTL : false
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});