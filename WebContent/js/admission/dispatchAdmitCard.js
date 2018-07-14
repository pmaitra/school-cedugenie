	


$(document).ready(function() { 	
	
	 $('#examDate').Zebra_DatePicker({
		  format: 'd/m/Y',
		  direction: true
		});
	 $('#examTime').ptTimeSelect();
	 
	 $('#venue').change(function(){
			     $('#getCandidateForm').submit();
	 });
	 
//	 $("#getCandidateSubmitButton").click(function() {
//		    document.getElementById("infomsgbox").style.visibility='collapse';
//     		document.getElementById("infomsg").innerHTML="";
//			document.getElementById("warningbox").style.visibility="collapse";
//			document.getElementById("warningmsg").innerHTML="";
//			//Validate Venue Name
//			venue = document.getElementById("venue").value;
//			venue = venue.replace(/\s{1,}/g,'');
//			if(venue == ""){	
//				document.getElementById("warningbox").style.visibility="visible";
//				document.getElementById("warningmsg").innerHTML="Please Select Venue";
//				return false;
//			}
//	 });
//	 
//		$("#venue").change(function() {
//			document.getElementById("warningbox").style.visibility='collapse';
//			document.getElementById("warningmsg").innerHTML="";
//			document.getElementById("infomsgbox").style.visibility='collapse';
//			document.getElementById("infomsg").innerHTML="";
//			document.getElementById("submitButton").style.visibility='hidden';
//  			document.getElementById("clearbtn").style.visibility='hidden';
//			document.getElementById("startSeatRollNo").value="";
//			document.getElementById("endSeatRollNo").value="";
//			var venue=$("#venue").val();	
//			 $.ajax({
//		     url: '/icam/getSeatAllotmentForVenue.html',
//		     dataType: 'json',
//		     data: "venue=" +venue,
//		     success: function(data) {
//		    	if(data != null && data!= ""){
//		    		var dataArray=data.split("#");
//		    	  	if(dataArray[0]!='' && dataArray[0] !='0' && dataArray[1]!='' && dataArray[1] !='0' ){
//		    	  		document.getElementById("startSeatRollNo").value=dataArray[0];
//		    	  		document.getElementById("endSeatRollNo").value=dataArray[1];
//		    	  		if(dataArray[2]!='' && dataArray[2]!='0'){
//		    	  			document.getElementById("submitButton").style.visibility='visible';
//		    	  			document.getElementById("clearbtn").style.visibility='visible';
//		    	  		}else{
//		    	  			document.getElementById("infomsgbox").style.visibility='visible';
//				    		document.getElementById("infomsg").innerHTML="Dispatched all admit card";
//		    	  		}
//		    	  	}else{
//		    	  		document.getElementById("warningbox").style.visibility='visible';
//			    		document.getElementById("warningmsg").innerHTML="No venue allotmnet found ";
//		    	  	}
//		    	}else{	    		
//		    		document.getElementById("warningbox").style.visibility='visible';
//		    		document.getElementById("warningmsg").innerHTML="No venue allotmnet found ";
//		    	}
//		    }  
//			});			
//		});
	});
	
	function validateSubmitDispatchAdmitCardForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		document.getElementById("infomsgbox").style.visibility='collapse';
		document.getElementById("infomsg").innerHTML="";
		
		//Validate examDate
		examDate = document.getElementById("examDate").value;
		examDate = examDate.replace(/\s{1,}/g,'');
		if(examDate == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Examination Date";
			return false;
		}
		
		//Validate examTime
		examDate = document.getElementById("examTime").value;
		examDate = examDate.replace(/\s{1,}/g,'');
		if(examDate == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Examination Time";
			return false;
		}
		
	}
	