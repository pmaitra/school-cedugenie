function loadPopupBox() {    // To Load the Popupbox
    $('#dialog').fadeIn("fast");
    $("#wrap").css({ // this is just for style
        "opacity": "0.3"
   });  
 } 
function unloadPopupBox() {    // TO Unload the Popupbox
    $('#dialog').fadeOut("fast");
    $("#wrap").css({ // this is just for style       
        "opacity": "1" 
    });
}
function loadPopupBox1() {    // To Load the Popupbox
	$('#Duration').fadeIn("fast");
	$("#wrap").css({ // this is just for style
	    "opacity": "0.3"

	});  
 } 

function unloadPopupBox1() {    // TO Unload the Popupbox
	
$('#Duration').fadeOut("fast");
$("#wrap").css({ // this is just for style       
    "opacity": "1" 
});
} 

$(document).ready(function(){
	$('#popupBoxNo').click( function() {           
	    unloadPopupBox();
	}); 
	$('#popupBoxNo1').click( function() {
		unloadPopupBox1();
	});
	
	$('#PeriodstartTime').ptTimeSelect();
	$('#hiddenEndTimetoset').ptTimeSelect(); 
	
	
});