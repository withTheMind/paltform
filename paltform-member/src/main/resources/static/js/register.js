(function($, doc) {

	var link = doc.getElementById("send-yzm");

	link.addEventListener("tap", function() {

		var time = 120;

		//标志
		var mark = jQuery(link).attr("data-mark");
		if(mark == 0) {

			jQuery(link).attr("data-mark", 1);
			jQuery(link).addClass("disabled");
			var html = "剩余时间<span id='time'>" + time + "</span>s";
			jQuery(link).html(html);

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
		}

	}, true);

})(mui, document);