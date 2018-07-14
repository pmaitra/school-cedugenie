//	$(document).ready(function() {
//		document.getElementById("warningbox").style.visibility='collapse';
//		document.getElementById("warningmsg").innerHTML="";
//		
		
//		$("#standard").change(function() {
//			document.getElementById("status").value="";
//			document.getElementById("admissionFormId").value="";
//			document.getElementById("warningbox").style.visibility='collapse';
//			document.getElementById("warningmsg").innerHTML="";
//			document.getElementById("accepted").style.visibility='hidden';
//			document.getElementById("rejected").style.visibility='hidden';
//			document.getElementById("received").style.visibility='hidden';
//		});
//		$("#status").change(function() {
//			document.getElementById("accepted").style.visibility='hidden';
//			document.getElementById("rejected").style.visibility='hidden';
//			document.getElementById("received").style.visibility='hidden';
//			document.getElementById("admissionFormId").value="";
//			document.getElementById("warningbox").style.visibility='collapse';
//			document.getElementById("warningmsg").innerHTML="";
//			if($("#status").val()=='SUBMITTED'){
//				document.getElementById("received").style.visibility='visible';
//			}
//			if($("#status").val()=='RECEIVED'){
//				document.getElementById("accepted").style.visibility='visible';
//				document.getElementById("rejected").style.visibility='visible';
//			}
//		});
//		$("#admissionFormId").autocomplete({
//			source: function(request, response) {
//			      $.ajax({
//			    	 url: '/icam/getAdmissionFormIdForUpdateFormStatus.html',
//			         dataType: "json",
//			         data: {
//			        	 term: request.term,
//			        	 standard:$("#standard").val(),
//			        	 status:$("#status").val(),
//			         },
//			         success: function(data) {
//			        	 var results=$.ui.autocomplete.filter(data,request.term);
//		      			response(results.slice(0,5));
//			         }
//			      });
//			   },
//			  minLength:1,
//			  
//		}); 
		
//	});
	
	function resetCandidateStatus(index,status){
		if(status=='SUBMITTED'){
			document.getElementById("receivedStatus"+index).checked = false;
		}
		if(status=='RECEIVED'){
			document.getElementById("acceptedStatus"+index).checked = false;
			document.getElementById("rejectedStatus"+index).checked = false;
		}
		
		document.getElementById("reasonOfRejection"+index).value="";
	}
	
	function validateUpdateFormStatusForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		numeric=/^[0-9]{1,}$/;
		//Validate Standard Name
		standard = document.getElementById("standard").value;
		standard = standard.replace(/\s{1,}/g,'');
		if(standard == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Class";
			return false;
		}
		
		//Status
		status = document.getElementById("status").value;
		status = status.replace(/\s{1,}/g,'');
		if(status == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Status";
			return false;
		}
		
		//Validate form id
//		admissionFormId = document.getElementById("admissionFormId").value;
//		admissionFormId = admissionFormId.replace(/\s{1,}/g,'');
//		if(admissionFormId == ""){	
//			document.getElementById("warningbox").style.visibility="visible";
//			document.getElementById("warningmsg").innerHTML="Please Enter Admission Form Id";
//			return false;
//		}
//		if(admissionFormId!='' && admissionFormId!='0'){
//			if(!admissionFormId.match(numeric)){
//				document.getElementById("warningbox").style.visibility="visible";
//				document.getElementById("warningmsg").innerHTML="Invalid Form Id. Should be Numeric";
//				return false;
//			}
//		}
		
		
	}
	