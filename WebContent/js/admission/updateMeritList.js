	$(document).ready(function() {
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
		document.getElementById("infomsgbox").style.visibility='collapse';
		document.getElementById("infomsg").innerHTML="";
		
		$("#standard").change(function() {
			document.getElementById("meritListType").value="";
			document.getElementById("admissionFormId").value="";
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
		});
		$("#meritListType").change(function() {
			document.getElementById("admissionFormId").value="";
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
		});
		$("#admissionFormId").autocomplete({
			source: function(request, response) {
			      $.ajax({
			    	 url: '/icam/getAdmissionFormId.html',
			         dataType: "json",
			         data: {
			        	 term: request.term,
			        	 standard:$("#standard").val(),
			        	 meritListType:$("#meritListType").val()
			         },
			         success: function(data) {
			            response(data);
			         }
			      });
			   },
		}); 
		$("#submit").click(function() {
	      $.ajax({
		    	 url: '/icam/getCandidateDetailsForMeritList.html',
		         dataType: "json",
		         data: {
		        	 admissionFormId: $("#admissionFormId").val(),
		        	 standard:$("#standard").val(),
		        	 meritListType:$("#meritListType").val()
		         },
		         success: function(data) {
		        	 if(data != null && data!= ""){
		        		 document.getElementById("tabSubmitMeritList").style.visibility='visible';
		        		 var dataArray=data.split('#');
		        		 document.getElementById("tdFormID").innerHTML=dataArray[0];
		        		 document.getElementById("tdCandidateName").innerHTML=dataArray[1];
		        		 document.getElementById("tdDateOfBirth").innerHTML=dataArray[2];
		        		 document.getElementById("tdGender").innerHTML=dataArray[3];
		        		 document.getElementById("tdCategory").innerHTML=dataArray[4];
		        		 document.getElementById("tdStandard").innerHTML=dataArray[5];
		        		 document.getElementById("meritListFormId").value=dataArray[0];
		        		 document.getElementById("meritListStandard").value=dataArray[5];
		        		 document.getElementById("meritListMeritListType").value=dataArray[6];
		        		 
		        		 var meritListType = document.getElementById("meritListType").value;
		        		 if(meritListType!=null && meritListType != "" && meritListType=='MEDICAL'){
		        			 document.getElementById("waiting").style.visibility='visible'; 
		        		 }
		        		 
		        	 }else{
		        		 document.getElementById("tdFormID").innerHTML="";
		        		 document.getElementById("tdCandidateName").innerHTML="";
		        		 document.getElementById("tdDateOfBirth").innerHTML="";
		        		 document.getElementById("tdGender").innerHTML="";
		        		 document.getElementById("tdCategory").innerHTML="";
		        		 document.getElementById("tdStandard").innerHTML="";
		        		 document.getElementById("meritListFormId").value="";
		        		 document.getElementById("meritListStandard").value="";
		        		 document.getElementById("meritListMeritListType").value="";
		        		 document.getElementById("tabSubmitMeritList").style.visibility='hidden';
		        		 document.getElementById("waiting").style.visibility='hidden'; 
		        		 document.getElementById("warningbox").style.visibility='visible';
		        		 document.getElementById("warningmsg").innerHTML="Candidate details not found.";
		        	 }
		         }
		 });
		
		
		});
		
	});
	
	function validateCandidateDetailsForMeritListForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		document.getElementById("infomsgbox").style.visibility='collapse';
		document.getElementById("infomsg").innerHTML="";
		numeric=/^[0-9]{1,}$/;
		//Validate Standard Name
		standard = document.getElementById("standard").value;
		standard = standard.replace(/\s{1,}/g,'');
		if(standard == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Class";
			return false;
		}
		
		//Validate Merit List Type
		meritListType = document.getElementById("meritListType").value;
		meritListType = meritListType.replace(/\s{1,}/g,'');
		if(meritListType == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Merit List Type";
			return false;
		}
		
		//Validate form id
		admissionFormId = document.getElementById("admissionFormId").value;
		admissionFormId = admissionFormId.replace(/\s{1,}/g,'');
		if(admissionFormId == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Admission Form Id";
			return false;
		}
		if(admissionFormId!='' && admissionFormId!='0'){
			if(!admissionFormId.match(numeric)){
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Invalid Form Id. Should be Numeric";
				return false;
			}
		}
		
		
	}
	