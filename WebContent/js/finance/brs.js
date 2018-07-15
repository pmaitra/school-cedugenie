function validateBRS(){
	if(!(nullValidation(document.getElementById("bank")))){
		alert("Select Valid Bank");
	}
	else if(!(validateDateRange("fromDate","toDate"))){
 		alert("Enter Valid Date Range");
	}
 	else{
 		alert("In else");
 		$.ajax({
		    url:'/cedugenie/getBrs.html',
	    	dataType: 'json',
	    	data: "fromDate=" + ($("#fromDate").val())+ "&toDate=" + ($("#toDate").val())+ "&bank=" + ($("#bank").val()),		    	
	    	success: function(data) {
		    	alert(data);
		    	document.getElementById("tbDiv1").style.display = "block";
		    	var splitedTables = data.split("#");
	  			document.getElementById("tbDiv2").innerHTML = splitedTables[0]; 
	  			document.getElementById("tbDiv3").innerHTML = splitedTables[1]; 
	    	}
		});
 	}
}