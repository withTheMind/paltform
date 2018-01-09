(function($){
	$("#btn-login")[0].addEventListener("tap", function(){
		$.openWindow({
			url : "/mem/loginPage",
			show : {
				autoShow:true,//页面loaded事件发生后自动显示，默认为true
			},
			waiting:{
				autoShow:true,//自动显示等待框，默认为true
				title:'正在加载...'//等待对话框上显示的提示内容
			}
		});
	});
	
	$("#btn-register")[0].addEventListener("tap", function(){
		$.openWindow({
			url : "/mem/registerPage",
			show : {
				autoShow:true,//页面loaded事件发生后自动显示，默认为true
			},
			waiting:{
				autoShow:true,//自动显示等待框，默认为true
				title:'正在加载...'//等待对话框上显示的提示内容
			}
		});
	});
	
})(mui);
