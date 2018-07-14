function resizeIframe(obj) {
	var height=obj.contentWindow.document.body.scrollHeight;
	obj.style.height = height + 420 + 'px';
}
