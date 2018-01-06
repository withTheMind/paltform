(function($){
	$("#datalist").on("tap", "li", function(){
		window.location = "note-detail.html";
	});
})(mui);

jQuery(function() {
	jQuery(".input-search").keyup(function(e) {
		if(e.keyCode == 13) {
			var keywords = jQuery("#keywords").val();
			if(keywords != "" && keywords != "null" && keywords != undefined) {
				console.log(keywords);
			}
		}
	});
});

/**
 * 上拉加载具体业务实现
 */
function pullupRefresh() {
	setTimeout(function() {
		console.log("上拉");
		mui('#refreshContainer').pullRefresh().endPullupToRefresh(false); //参数为true代表没有更多数据了。
	}, 1000);
}