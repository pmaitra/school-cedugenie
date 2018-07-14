$(document).ready(function(){
	


	$("#submitButton").click(function(){
		var retVal = true;
		var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
		var itemCodeRadio = $("input:checked" ).length;
		if(itemCodeRadio == 0){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Please check at least one Asset.";
			alert("Please check at least one Asset.");
			return false;
		}
		if(itemCodeRadio != 0){		
			$('input:checkbox').each(function(){	
				if ($(this).is(':checked'))
				{	
					document.getElementById("warningbox").style.visibility="collapse";
					var allottTo = $(this).parent().next().next().next().find('input').val();
	
					if(allottTo == "0.0"){
//						$("#warningbox").css('visibility','visible');
//						$("#warningmsg").text('Enter a proper price rate.');
						alert("Please enter a proper price rate.");
						retVal = false;
						return false;
					}
					if(allottTo != ""){
						if(!allottTo.match(regPositiveNum)){
//							$("#warningbox").css('visibility','visible');
//							$("#warningmsg").text('Enter a proper numeric price rate.');
							alert("Price rate must be numeric.");
							retVal = false;
							return false;
						}					
					}
				}	
			});
		} 	
		return retVal;
	}); 

});