(function($, doc) {

	var link = doc.getElementById("send-yzm");

	link.addEventListener("tap", function() {

		var time = 120;

		//标志
		var mark = jQuery(link).attr("data-mark");
		if(mark == 0) {
			
			var msg = $("#error-value")[0];
			var phone = $("#phone")[0].value;
			if(phone == "" || phone == "null"){
				msg.innerText = "手机号不能空";
				return;
			}
			if(!/^1[34578]\d{9}$/.test(phone)) {
				msg.innerText = "手机号格式错误";
				return;
			}
			
			
			//发送验证码
			$.ajax("/mem/sendYzm", {
				data:{
					phone : phone
				},
				dataType: 'json', //服务器返回json格式数据
				type: 'post', //HTTP请求类型
				timeout: 10000, //超时时间设置为10秒
				success: function(result) {
					if(result.status == 200){
						jQuery(link).attr("data-mark", 1);
						jQuery(link).addClass("disabled");
						var html = "剩余时间<span id='time'>" + time + "</span>s";
						jQuery(link).html(html);
						msg.innerText = "";

						var clearTime = setInterval(function() {
							time--;
							jQuery("#time").text(time);
							if(time <= 0) {
								clearInterval(clearTime);

								jQuery(link).removeClass("disabled");
								jQuery(link).attr("data-mark", 0);
								jQuery(link).html("获取验证码");
							}

						}, 1000);
						
						$.toast(result.message);
						
					} else if(result.status == 500){
						msg.innerText = result.message;
					}
				},
				error: function(xhr, type, errorThrown) {
					$.toast("系统异常");
				}
				
			});
		}

	}, true);
	
	$(".btn-register")[0].addEventListener("tap", function(){
		
		var msg = $("#error-value")[0];
		$.ajax("/mem/register", {
			data:{
				phone : $("#phone")[0].value,
				yzm : $("#yzm")[0].value
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(result) {
				if(result.status == 200){
					window.location = "/mem/settingPwd";
				}else if(result.status == 500){
					msg.innerText = result.message;
				}
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
		});
		
	});

})(mui, document);