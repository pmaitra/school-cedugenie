var _init = $.ui.dialog.prototype._init;
$.ui.dialog.prototype._init = function() {
	_init.apply(this, arguments);
	
	var dialog_element = this;
	var dialog_id = this.uiDialogTitlebar.next().attr('id');
	this.uiDialogTitlebar.append('<a href="#" id="' + dialog_id + 
	'-minbutton" class="ui-dialog-titlebar-minimize ui-corner-all">'+
	'<span class="ui-icon ui-icon-minusthick" onclick="minFunction();"></span></a>');
	
	$('#dialog_window_minimized_container').append(
		'<div class="dialog_window_minimized ui-widget ui-state-default ui-corner-all" id="' + 
		dialog_id + '_minimized">' + this.uiDialogTitlebar.find('.ui-dialog-title').text() + 
		'<span class="ui-icon ui-icon-newwin"></span</div>');
		
	$('#' + dialog_id + '-minbutton').hover(function() {
		$(this).addClass('ui-state-hover');
	}, function() {
		$(this).removeClass('ui-state-hover');
	}).click(function() {
		dialog_element.close();
				 
	$('#' + dialog_id + '_minimized').show();
	 }); 
	
	$('#' + dialog_id + '_minimized').click(function() {
		$(this).hide();
		dialog_element.open();
	});
	
};

function minFunction(){
	var create_dialog = $('#dialog_window_1');
	 if( create_dialog.dialog('isOpen') ) {
			create_dialog.dialog('close');
			$('#dialog_window_1_minimized').show();
		} else {
			create_dialog.dialog('open');
		} 
}