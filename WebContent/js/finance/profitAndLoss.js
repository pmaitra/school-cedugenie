$(document).ready(function(){
	$("#get").click(function(){
		 if(!(validateDateRange("fromDate","toDate"))){
				alert("Enter Valid Date Range");
		}else{
			$.ajax({			
		    url: '/cedugenie/getProfitAndLoss.html',
		    	dataType: 'json',
		    	data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val()),		    	
		    	success: function(data) {
		  			document.getElementById("tbDiv").innerHTML=data; 		
		    	}
			});
		 }
	 });
});