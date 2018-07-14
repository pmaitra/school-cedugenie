var validNavigation = false;
var valid_count=0; 
var formSubmit=true;
function endSession() {
  // Browser or broswer tab is closed
	$.ajax({
			url: 'logOut.html',
			async: false
		});
}
 
function wireUpEvents() {  
  window.onbeforeunload = function() {
      if (!validNavigation) {
         endSession();
         valid_count=0; 
      }
      if(valid_count>1 && validNavigation && formSubmit){    	  
    	  endSession();
          valid_count=0;    	  
      }
    validNavigation = false;      
  }
 
  // Attach the event keypress to exclude the F5 refresh
  $(document).bind('keypress', function(e) {
	 var keyCode = e.keyCode || e.which;
//	 alert(keyCode);
    if (keyCode == 116){
      validNavigation = true;
      valid_count++;
    }
  });
 
  // Attach the event click for all links in the page
  $("a").bind("click", function() {
    validNavigation = true;
    formSubmit=false;
    valid_count++;
  });
 
  // Attach the event submit for all forms in the page
  $("form").bind("submit", function() {
    validNavigation = true;
    formSubmit=false;
    valid_count++;
  });
 
  // Attach the event click for all inputs in the page
  $("input[type=submit]").bind("click", function() {
    validNavigation = true;
    formSubmit=false;
    valid_count++;
  });
   
}
 
// Wire up the events as soon as the DOM tree is ready
$(document).ready(function() {
  wireUpEvents(); 
});