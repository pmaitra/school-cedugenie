
$(document).ready(function() { 
	 
	 $("#get").click(function(){
		 if(!(validateDateRange("fromDate","toDate"))){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Enter Valid Date Range";
			 alert("Enter Valid Date Range");
		 }else{
			 //document.getElementById("warningbox").style.visibility='collapse';
			$.ajax({			
		    url:'/icam/getTrialBalance.html',
		    	dataType: 'json',
		    	data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val()),		    	
		    	success: function(data) {
		    	//	alert(data);
		  			document.getElementById("tbDiv").innerHTML=data; 		
		    	}
			});
		 }
	 });
 });