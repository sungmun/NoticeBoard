function ajax(url, data, successfunction) {
	$.ajax({
		url : url,
		data : data,
		method : "post",
		dataType : "json",
		success : successfunction
	});
}