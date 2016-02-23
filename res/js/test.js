function display(name) {
	var a = 10;
	var buffer = '';
	for (var i = 0; i < 30; i++) {
		buffer += i + ' ';
	}
	return displaysub(name);
	// return 'the name is : ' + name+' buffer = '+buffer;
}
function getUrls(baseUrl, html) {
	var urls = html.match(/<a +href=[^>]*>.*?<\/a>/g);
	if (!baseUrl.endsWith('/')) {
		baseUrl += '/'
	}
	var regex = /"[^"]+"/;
	for (var i = 0; i < urls.length; i++) {
		var item = urls[i];
		var name = item.replace(/<[^>]*>/g,'');
		var match = item.match(regex);
		var temStr = match[0];
		temStr = temStr.substr(1, temStr.length - 2);
		temStr = temStr.replace(/\\/g, '/');
		if (temStr.indexOf('.') == 0) {
			temStr = temStr.substr(1);
			if (temStr.indexOf('/') == 0) {
				temStr = temStr.substr(1);
			}
			temStr = encodeURI(temStr);
			temStr = baseUrl + temStr;
			urls[i] = name+'<>'+temStr;
		}
	}
	return urls.join("@");
}
