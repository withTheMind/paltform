(function($){
	$(".btn-update-password")[0].addEventListener("tap", function(){
		
		var oldPwd = $("#oldPwd")[0].value;
		var tip = $("#error-tip")[0];
		if(oldPwd == "" || oldPwd == "null"){
			tip.innerText = "原密码不能为空";
			return;
		}
		
		if(oldPwd.length < 6 || oldPwd.length > 20){
			tip.innerText = "原密码长度应再6-20位之间";
			return;
		}
		
		var pwd = $("#pwd")[0].value;
		if(pwd == "" || pwd == "null"){
			tip.innerText = "新密码不能为空";
			return;
		}
		if(pwd.length < 6 || pwd.length > 20){
			tip.innerText = "新密码长度应再6-20位之间";
			return;
		}
		if(pwd == oldPwd){
			tip.innerText = "新密码不能和原密码一样";
			return;
		}
		var surePwd = $("#surePwd")[0].value;
		if(surePwd != pwd){
			tip.innerText = "新密码和确认密码不一致";
			return;
		}
		
		$.ajax("/member/updatePwd", {
			data:{
				oldPwd : oldPwd,
				pwd : pwd,
				surePwd : surePwd
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(result) {
				if(result.status == 200){
					$.toast(result.message);
					setTimeout(function(){
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
					}, 1000);
				} else if(result.status == 500){
					tip.innerText = result.message;
				}
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
			
		});
	});
})(mui);