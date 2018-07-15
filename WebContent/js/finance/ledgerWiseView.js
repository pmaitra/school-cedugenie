$(document).ready(function() {
	$("#get").click(function(){
		 if(!(nullValidation(document.getElementById("ledger")))){
			alert("Select Valid Ledger!");
			return false;
		}
	 	if(!(validateDateRange("fromDate","toDate"))){
			alert("Enter Valid Date Range!");
		 }else{
			$.ajax({
		    url: '/cedugenie/getLedgerWiseView.html',
		    	dataType: 'json',
		    	data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val())+ "&ledger=" + ($("#ledger").val()),		    	
		    	success: function(data) {
		    		alert(data);
		    		document.getElementById("dayBookDetailsDiv").style.display = 'block';
		  			document.getElementById("tbDiv").innerHTML=data; 		
		    	}
			});
		 }
	 });
 });