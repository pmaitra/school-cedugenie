$(document).ready(function() {
	
	$(".textfield1").each(function(){
		$(this).Zebra_DatePicker();
	    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y',
	    	  direction: true
	    	});
	});

});