(function($, doc) {
	var home = doc.getElementById("home");
	var message = doc.getElementById("message");
	var center = doc.getElementById("center");

	home.addEventListener("tap", function() {
		$.openWindow({
			url: "/main",
			show: {
				autoShow: true, //页面loaded事件发生后自动显示，默认为true
			},
			waiting: {
				autoShow: true, //自动显示等待框，默认为true
				title: '正在加载...' //等待对话框上显示的提示内容
			}
		});
	});

	message.addEventListener("tap", function() {
		$.openWindow({
			url: "message.html",
			show: {
				autoShow: true, //页面loaded事件发生后自动显示，默认为true
			},
			waiting: {
				autoShow: true, //自动显示等待框，默认为true
				title: '正在加载...' //等待对话框上显示的提示内容
			}
		});
	});

	center.addEventListener("tap", function() {
		$.openWindow({
			url: "center-not-login.html",
			show: {
				autoShow: true, //页面loaded事件发生后自动显示，默认为true
			},
			waiting: {
				autoShow: true, //自动显示等待框，默认为true
				title: '正在加载...' //等待对话框上显示的提示内容
			}
		});
	});

})(mui, document);