//表单提交
function formSubmit(options){
	var html = '', form = document.createElement('form'), body = document.body;
	form.action = options.url;
	form.target = options.target || '_self';
	form.method = options.method || 'get';
	form.enctype = /post/ig.test(form.method) ? "application/x-www-form-urlencoded" : "";
	for(var d in options.data ){
		html += '<input type="hidden" name="' +d+ '" value=\'' +options.data[d]+ '\'>';
	}
	form.innerHTML = html;
	body.appendChild(form);
	form.submit();
	body.removeChild(form);
}