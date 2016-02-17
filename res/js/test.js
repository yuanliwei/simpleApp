function display(name) {
	var a = 10;
	var buffer = '';
	for (var i = 0; i < 30; i++) {
		buffer += i + ' ';
	}
	return displaysub(name);
	// return 'the name is : ' + name+' buffer = '+buffer;
}
function getUrls(html) {
	var urls = html.match(/<a +href=[^>]*>.*?<\/a>/g);
	return urls.join("@");
}
