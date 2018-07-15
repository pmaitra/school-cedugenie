$(document).ready(function() {	 
	 
	 $("#get").click(function(){
		 if(!(validateDateRange("fromDate","toDate"))){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Enter Valid Date Range";
			 	alert("Enter Valid Date Range");
			 }else{
			//	 document.getElementById("warningbox").style.visibility='collapse';
				 document.getElementById("blncSheet").style.display='block';
				$.ajax({			
			    url: '/cedugenie/getBalanceSheet.html',
			    	dataType: 'json',
			    	data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val()),		    	
			    	success: function(data) {
			    		//alert(data);
			  			document.getElementById("blncSheet").innerHTML=data; 		
			    	}
				});
			 }
	 });
	 /*  $("#fromDate").datepicker({
	        minDate: '0',
	        maxDate: '+1Y+6M',
			dateFormat: 'dd/mm/yyyy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $('#toDate').datepicker('option', 'minDate', min || '0'); // Set other max, default to +18 months
	           // $("#toDate").removeAttr('disabled','disabled');
	        }
	    });
	    
	    $("#toDate").datepicker({
	        minDate: '0',
	        maxDate: '',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var max = $(this).datepicker('getDate'); // Get selected date
	            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	           // $("#scrutinyDate").removeAttr('disabled','disabled');
	        }
	    });*/
 });