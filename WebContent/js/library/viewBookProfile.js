window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'lhis', checkbox[i]);
	}
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'vbp', checkbox[i]);
	}
};


$(document).ready( function() {
	function unloadPopupBox() {    // TO Unload the Popupbox
	    $('#popup_box').fadeOut("fast");
	    $("#viewBookProfile").css({ // this is just for style       
	        "opacity": "1" 
	    });
	}   

	function loadPopupBox() {    // To Load the Popupbox
	    $('#popup_box').fadeIn("fast");
	    $("#viewBookProfile").css({ // this is just for style
	        "opacity": "0.3" 
	    });        
	}
		

	var buttonName = "";
	$(".clearbtn").each(function(){
		$(this).click(function(){
			buttonName = $(this);
			$('#dateTable tbody').remove();
			var bookCode = $(this).parent().prev().prev().find('input').val();
			var userId = $(this).parent().prev().prev().prev().find('input').val();
			$.ajax({
				url: '/sms/getLendingDates.html',
		        data: "bookCode=" + (bookCode) + "&userId=" + (userId),
		        dataType: 'json',
		        success: function(data) {
		        	if(data != ""){
		        		var arrSplited = data.split("@");
		        		
		        		var row = $('<tr>'); 
		                row.append($('<th>USER ID :: </th>'));
		                row.append($('<td>' +userId +'</td></tr>'));
		                $('#dateTable').append(row); 
		                var row = $('<tr>');
		                row.append($('<th>BOOK CODE :: </td>'));  
		                row.append($('<td >' +bookCode +'</td></tr>'));
	    				$('#dateTable').append(row); 
	    				
	    				var row = $('<tr id="secondHeader">'); 
		                row.append($('<th>Issue Date</th>'));
		                row.append($('<th>Return Date</th></tr>'));
	    				$('#dateTable').append(row); 
		        		for(var i=0;i<arrSplited.length-1;i++){
		        			var arrDate = arrSplited[i].split(",");
		        			for(var j=0;j<arrDate.length;j++){
		        				$('#dateTable thead').show();
		    	                var row = $('<tr>'); 
		    	                row.append($('<td>'+arrDate[0]+'</td>'));
		    	                row.append($('<td>'+arrDate[1]+'</td></tr>'));  
		    				}
		        			$('#dateTable').append(row); 
		        		}
		        		$("#viewBookProfile").attr('disabled',true);
		        		loadPopupBox();
		       		}
		        }
			}); 
		});
		
	});

	$("#ok").click(function(){
		unloadPopupBox();
	});

});