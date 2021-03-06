(function($, doc) {
	var nickName = doc.getElementById("nickName-link");
	nickName.addEventListener("tap", function() {
		$.ajax("/member/getMember", {
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(data) {
				$.prompt("昵称在2-10个字符之间", "请输入昵称", "修改昵称", ["确认", "取消"], function(e) {
					if(e.index == 0) {
						changeNickName(e.value);
					}
				});
				document.querySelector('.mui-popup-input input').value = data.nickName;
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
		});
		
	});
	
	//更换邮箱
	doc.getElementById("email-link").addEventListener("tap", function(){
		$.ajax("/member/getMember", {
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(data) {
				$.prompt("请注意邮箱格式", "请输入邮箱", "修改邮箱", ["确认", "取消"], function(e) {
					if(e.index == 0) {
						changeEmail(e.value);
					}
				});
				document.querySelector('.mui-popup-input input').value = data.email;
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
		});
	});
	

	doc.getElementById("img-head").addEventListener("tap", function() {
		
		jQuery("#upload").click();

	}, false);
	
	
	//修改邮箱
	function changeEmail(email){
		
		if(!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email)){
			$.toast("邮箱格式不正确");
			return;
		}
		
		$.ajax("/member/updateEmail", {
			data:{
				email: email
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(data) {
				if(data.status == 500){
					$.toast(data.message);
				}else if(data.status == 200){
					$("#email")[0].innerText = data.message;
					$.toast("邮箱修改成功");
				}
			},
			error: function(xhr, type, errorThrown) {
				$.toast("系统异常");
			}
		});
		
	}

})(mui, document);

function changeNickName(nickName){
	if(nickName.length < 2 || nickName.length > 10){
		mui.toast("昵称长度在2-10个字符之间");
		return;
	}
	
	mui.ajax("/member/changeNickName",{
		data:{
			nickName : nickName
		},
		dataType: 'json', //服务器返回json格式数据
		type: 'post', //HTTP请求类型
		timeout: 10000, //超时时间设置为10秒
		success: function(result) {
			document.getElementById("nickName").innerText =　result.message;
			mui.toast("昵称修改成功");
		},
		error: function(xhr, type, errorThrown) {
			mui.toast("系统异常");
		}
	});
	
}

function uploadFile(file) {
	var type = file.files[0].type;
	debugger;
	if (type == 'image/png' || type == 'image/jpg' || type == 'image/jpeg'
			|| type == 'image/gif' || type == 'image/bmp') {
		if (file.files && file.files[0]) {
			if(file.files[0].size > 3*1024*1024){
				alert("图片最大3Mb");
				return;
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				var image = evt.target.result;
				jQuery.ajaxFileUpload({
					fileElementId : "upload",
					url : "/member/uploadPhoto",
					type : "post",
					dataType : 'json',
					data : {
						upload : image
					},
					success : function(result) {
						console.log(result);
					},
					error : function() {
						alert("服务器繁忙");
					}
				});
			}
			reader.readAsDataURL(file.files[0]);
		}
	} else {
		//清空选择的文件
		jQuery(file).val("");
	}
}