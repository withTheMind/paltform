(function($) {
	//登录按钮
	$(".btn-login")[0].addEventListener("tap", function() {

		var tip = $("#error-tip")[0];

		//手机号
		var phone = $("#phone")[0].value;
		if(phone == "" || phone == "null") {
			tip.innerText = "手机号不能为空";
			return;
		}
		if(!/^1[34578]\d{9}$/.test(phone)) {
			tip.innerText = "手机号格式错误";
			return;
		}

		//密码
		var password = $("#password")[0].value;
		if(password == "" || password == "null") {
			tip.innerText = "密码不能为空";
			return;
		}

		$.ajax("/mem/loginValide", {
			data: {
				phone: phone,
				password: password
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(result) {
				if(result.status == 500) {
					tip.innerText = result.message;
				} else if(result.status == 200) {
					window.location = "/mem/login";
				}
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
		});
	});
})(mui);