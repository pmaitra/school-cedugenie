$(document).ready(function(){
	 $("#detailsButton").click(function(){
		var intRegx=numeric=/^[0-9]{1,}$/;
		var roll=document.getElementById("rollNumber").value;
		roll=roll.replace(/\s{1,}/g,'');
		document.getElementById("rollNumber").value=roll;
		if(roll =="" || !roll.match(intRegx)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Roll Number";
			document.getElementById("rollNumber").value="";
		}else{
		$.ajax({
	        url: '/icam/getNameStandardSectionForRollNumber.html',
	        dataType: 'json',
	        data: "rollNumber=" + ($("#rollNumber").val()),
	        success: function(dataDB) {
	        	if(dataDB != "null" && dataDB !=""){
	    			document.getElementById("warningbox").style.visibility='collapse';
	        		document.getElementById("infomsgbox").style.visibility='collapse';
	        		document.getElementById("subjectDetailTable").style.visibility='visible';
	        		dataDB=dataDB.split("*");
	        		document.getElementById("name").value=dataDB[0];
	        		document.getElementById("standard").value=dataDB[1];
	        		document.getElementById("section").value=dataDB[2];
	        		
	        		$.ajax({
	    		        url: '/icam/getStudentFeesPaymentStatus.html',
	    		        dataType: 'json',
	    		        data: "rollNumber=" + ($("#rollNumber").val()),
	    		        success: function(data) {
	    		        	data=data.split("*");
	    		        	var status="Student Fees Status :: "+data[0]+"<br>Library Fine Status :: "+data[1]+"<br>Library Book Status :: "+data[2];
	    		        	if(data[0]=="REMAINING" || data[1]=="PENDING" || data[2]=="ALLOTED"){
	    		        		status=status+"<br>TC Cannot Be Granted";
	    		        		document.getElementById("submitButtonDiv").style.visibility='collapse';
	    		        	}else{
	    		        		document.getElementById("submitbtn").removeAttribute('disabled');
	    		        		document.getElementById("submitButtonDiv").style.visibility='visible';
	    		        	}
	    		        	document.getElementById("infomsgbox").style.visibility='visible';
	    		     		document.getElementById("infomsg").innerHTML=status;
	    		       },
	    		       error:function(data) {
	    		    	   alert("Error");
	    		       }
	    			});
	        		
	        		
				}else{
					document.getElementById("subjectDetailTable").style.visibility='collapse';
					document.getElementById("infomsgbox").style.visibility='visible';
		     		document.getElementById("infomsg").innerHTML="Student Details Not Found";
					
				}
	       }
		});
	 }
		
	});
	  
	 
	 $("#rollNumber").autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType=STUDENT',
			success: function(data) {
			        	 var results=$.ui.autocomplete.filter(data,request.term);
		      			 response(results.slice(0,5));
			         },
	      minLength:3
		});
	
});
	
	 
	 
	 
	 
	 
	 