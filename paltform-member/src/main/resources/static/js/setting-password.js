(function($){
	$(".btn-register")[0].addEventListener("tap", function(){
		
		var tip = $("#error-value")[0];
		var pwd = $("#pwd")[0].value;
		if(pwd == "" || pwd == "null"){
			tip.innerText = "密码不能为空";
			return;
		}
		if(pwd.length < 6 || pwd.length > 20){
			tip.innerText = "密码长度应再6-20位之间";
			return;
		}
		
		var surePwd = $("#surePwd")[0].value;
		if(surePwd != pwd){
			tip.innerText = "两次密码不一致";
			return;
		}
		
		$.ajax("/mem/loginPwd", {
			data:{
				pwd : pwd,
				surePwd : surePwd
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(result) {
				if(result.status == 200){
					window.location = "/mem/loginPage";
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