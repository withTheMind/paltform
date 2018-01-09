
/**
 * 上拉加载具体业务实现
 */
function pullupRefresh() {
	setTimeout(function() {
		
		var pageNo = mui("#pageNo")[0].value;
		if(pageNo == ""){
			pageNo = 0;
		}
		mui.ajax("/note/queryPage", {
			data:{
				pageNo : ++pageNo
			},
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒
			success: function(data) {
				
				var datalist = mui(".datalist")[0];
				
				mui.each(data.list, function(i, e){
					var li = document.createElement("li");
					li.className = "mui-table-view-cell";
					var html = "";
					html += "<a class=\"mui-navigate-right list-value\">" + 
									"<label class=\"mui-col-xs-4\">"+jQuery.formatDate(e.createTime, "yyyy-MM-dd")+"</label>" + 
									"<span class=\"mui-col-xs-8 value\">"+e.content+"</span>" + 
								"</a>";
					li.innerHTML = html;
					datalist.appendChild(li);
				});
				
				var result = false;
				if(data.list.length == 0){
					result = true;
				}
				mui("#pageNo")[0].value = pageNo;
				mui('#refreshContainer').pullRefresh().endPullupToRefresh(result); //参数为true代表没有更多数据了。
			},
			error: function(xhr, type, errorThrown) {
				mui.toast("系统异常");
			}
		});
		
		
	}, 1000);
}