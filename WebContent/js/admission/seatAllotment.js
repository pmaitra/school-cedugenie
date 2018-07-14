	$(document).ready(function() { 	
		$("#venue").change(function() {
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("infomsgbox").style.visibility='collapse';
			document.getElementById("infomsg").innerHTML="";
			document.getElementById("startSeatRollNo").value="";
			document.getElementById("endSeatRollNo").value="";
			document.getElementById("prevStartSeatRollNo").value='0';
	  		document.getElementById("prevEndSeatRollNo").value='0';
			var venue=$("#venue").val();	
			 $.ajax({
		     url: '/cedugenie/getVenueSeatAllotmentDetails.html',
		     dataType: 'json',
		     data: "venue=" +venue,
		     success: function(data) {
		    	if(data != null && data!= ""){
		    		var dataArray=data.split("#");
		    		document.getElementById("capacity").value=dataArray[0];
		    	  	if(dataArray[1]!='0'){
		    	  		document.getElementById("startSeatRollNo").value=dataArray[1];
		    	  		document.getElementById("endSeatRollNo").value=dataArray[2];
		    	  		document.getElementById("prevStartSeatRollNo").value=dataArray[1];
		    	  		document.getElementById("prevEndSeatRollNo").value=dataArray[2];
		    	  		
		    	  	}else{
		    	  		document.getElementById("startSeatRollNo").value=dataArray[3];
		    	  	}
		    	}else{	    		
		    		document.getElementById("warningbox").style.visibility='collapse';
		    	}
		    }  
			});			
		});
		
		$("#startSeatRollNo").bind('blur',function(){
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("infomsgbox").style.visibility='collapse';
			document.getElementById("infomsg").innerHTML="";
			var seatRollNo=$("#startSeatRollNo").val();	
			if(seatRollNo!=0){
				if(validateSeatRollNo(seatRollNo,"startSeatRollNo")){
					if($("#endSeatRollNo").val()!='0'){
						if(parseInt($("#endSeatRollNo").val())<parseInt(seatRollNo)){
							document.getElementById("infomsgbox").style.visibility="collapse";
							document.getElementById("infomsg").innerHTML="";
							document.getElementById("numberOfCandidate").value="";
						}else{
							getNumberOfAdmissionFormId($("#startSeatRollNo").val(),$("#endSeatRollNo").val());
						}
					}
				}	
			}else{
				document.getElementById("infomsgbox").style.visibility="collapse";
				document.getElementById("infomsg").innerHTML="";
				document.getElementById("numberOfCandidate").value="";
			}
				
		});
		
		$("#endSeatRollNo").bind('autocompletechange',function(){
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("infomsgbox").style.visibility='collapse';
			document.getElementById("infomsg").innerHTML="";
			var seatRollNo=$("#endSeatRollNo").val();
			if(seatRollNo!=0){
				if(validateSeatRollNo(seatRollNo,"endSeatRollNo")){
					getNumberOfAdmissionFormId($("#startSeatRollNo").val(),$("#endSeatRollNo").val());
				}
			}
		});
		
		
		
		
		$("#venueWiseForm").click(function() {
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("infomsgbox").style.visibility='collapse';
			document.getElementById("infomsg").innerHTML="";
			document.getElementById("startSeatRollNo").value="";
			document.getElementById("endSeatRollNo").value="";
			document.getElementById("prevStartSeatRollNo").value='0';
	  		document.getElementById("prevEndSeatRollNo").value='0';
			var venue=$("#venue").val();
			venue = venue.replace(/\s{1,}/g,'');
			var venueWiseFormStatus = '';
			if(document.getElementById("venueWiseForm").checked){
				venueWiseFormStatus="checked";
			}
			
			if(venue == ""){	
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please Select Venue";
				return false;
			}
			 $.ajax({
		     url: '/cedugenie/getVenueSeatAllotmentDetails.html',
		     dataType: 'json',
		     data: {
		    	 venue: venue,
		    	 venueWiseFormStatus:venueWiseFormStatus
	        	
	         },
		     //data: "venue=" +venue,
		     success: function(data) {
		    	if(data != null && data!= ""){
		    		var dataArray=data.split("#");
		    		document.getElementById("capacity").value=dataArray[0];
		    	  	if(dataArray[1]!='0'){
		    	  		document.getElementById("startSeatRollNo").value=dataArray[1];
		    	  		document.getElementById("endSeatRollNo").value=dataArray[2];
		    	  		document.getElementById("prevStartSeatRollNo").value=dataArray[1];
		    	  		document.getElementById("prevEndSeatRollNo").value=dataArray[2];
		    	  		
		    	  	}else{
		    	  		document.getElementById("startSeatRollNo").value=dataArray[3];
		    	  	}
		    	}else{	    		
		    		document.getElementById("warningbox").style.visibility='collapse';
		    	}
		    }  
			});			
		});
		
		$("#endSeatRollNo").autocomplete({
			autoFocus: true,
			source: function(request, response) {
				var venueWiseFormStatus = '';
				if(document.getElementById("venueWiseForm").checked){
					venueWiseFormStatus="checked";
				}
			      $.ajax({
			    	 url: '/cedugenie/getAutoEndSeatRollNoForExamVenue.html',
			         dataType: "json",
			         data: {
			        	 term: request.term,
			        	 venueWiseFormStatus:venueWiseFormStatus,
			        	 venueId:$("#venue").val()
			         },
			         success: function(data) {
			            response(data);
			         }
			      });
			      
			   }
		
		});
		
//		$("#endSeatRollNo").autocomplete({
//		 	source: '/cedugenie/getAutoEndSeatRollNoForExamVenue.html'}); 
//		
		
		
		
		
		
		
		
	});
	
	function validateSeatRollNo(seatRollNo,id ){
		var venueWiseFormStatus = '';
		var status="";
		if(document.getElementById("venueWiseForm").checked){
			venueWiseFormStatus="checked";
			status=" or form id is not selected for the venue.";
		}
		 var checker=false;
		 $.ajax({
		     url: '/cedugenie/validateSeatRollNo.html',
		     dataType: 'json',
		     data: "seatRollNo=" +seatRollNo+"&venueId=" +$("#venue").val()+"&venueWiseFormStatus=" +venueWiseFormStatus,
		     success: function(data) {
		    	if(data == null || data== ""){
		    		document.getElementById("infomsgbox").style.visibility='collapse';
		    		document.getElementById("infomsg").innerHTML="";
		    		document.getElementById(id).value="";
		    		document.getElementById("warningbox").style.visibility='visible';
		    		document.getElementById("warningmsg").innerHTML="Seat Roll No is not valid. Form id already alloted or does not exit "+status;
		    		checker=true;
		    	}
		    }  
		});	
		 
    	if(checker!=true){
    		return true ;
    	}else{
			 return false;
    	}
	}
	
	
	function getNumberOfAdmissionFormId(startSeatRollNo,endSeatRollNo){
		var venueWiseFormStatus = '';
		if(document.getElementById("venueWiseForm").checked){
			venueWiseFormStatus="checked";
		}
		 $.ajax({
		     url: '/cedugenie/getNumberOfAdmissionFormId.html',
		     dataType: 'json',
		     data: "startSeatRollNo=" +startSeatRollNo+"&endSeatRollNo=" +endSeatRollNo+"&venueWiseFormStatus=" +venueWiseFormStatus+"&venueId=" +$("#venue").val(),
		     success: function(data) {
		    	if(data != null || data!= ""){
		    		document.getElementById("infomsgbox").style.visibility="visible";
					document.getElementById("infomsg").innerHTML="Allocated Candidate is: "+data;
					document.getElementById("numberOfCandidate").value=data;
		    	}
		    }  
		});
	}
	
	function validateSeatAllotmentForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		document.getElementById("infomsgbox").style.visibility='collapse';
		document.getElementById("infomsg").innerHTML="";

		numeric=/^[0-9]{1,}$/;
	
		//Validate Venue Name
		venue = document.getElementById("venue").value;
		venue = venue.replace(/\s{1,}/g,'');
		if(venue == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Venue";
			return false;
		}
		
		//Validate Start Seat Roll No
		startSeatRollNo = document.getElementById("startSeatRollNo").value;
		startSeatRollNo = startSeatRollNo.replace(/\s{1,}/g,'');
		if(startSeatRollNo == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Start Seat Roll No";
			return false;
		}
		if(startSeatRollNo!='' && startSeatRollNo!='0'){
			if(!startSeatRollNo.match(numeric)){
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Invalid Start Seat Roll No. Should be Numeric";
				return false;
			}
		}
		//Validate End Seat Roll No
		endSeatRollNo = document.getElementById("endSeatRollNo").value;
		endSeatRollNo = endSeatRollNo.replace(/\s{1,}/g,'');
		if(endSeatRollNo == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter End Seat Roll No";
			return false;
		}
		if(endSeatRollNo!='' && endSeatRollNo!='0'){
			if(!endSeatRollNo.match(numeric)){
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Invalid End Seat Roll No. Should be Numeric";
				return false;
			}
		}
		if(startSeatRollNo!=0 && endSeatRollNo=='0'){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="End Seat Roll No Should not be Zero";
			return false;
		}
		if(startSeatRollNo=='0' && endSeatRollNo!='0'){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Start Seat Roll No Should not be Zero";
			return false;
		}
		if(endSeatRollNo!=0 && startSeatRollNo!=0 && endSeatRollNo < startSeatRollNo){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Start Seat RollNo should be less then  End Seat Roll No ";
			return false;
		}
		
		var capacity = document.getElementById("capacity").value;
		capacity=parseInt(capacity);
		startSeatRollNo=parseInt(startSeatRollNo);
		endSeatRollNo=parseInt(endSeatRollNo);
		if(capacity<$("#numberOfCandidate").val()){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Number of seat exceeds venue capacity ";
			return false;
		}
		
		 validateSeatRollNo(startSeatRollNo,"startSeatRollNo");
		 validateSeatRollNo(endSeatRollNo,"endSeatRollNo");
	}
