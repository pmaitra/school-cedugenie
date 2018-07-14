$(document).ready(function() {
	/*$("#courseId").change(function(){
		var courseId = $("#courseId").val();
		$.ajax({
			url: '/cedugenie/getAdmissionDriveNameAgainstCourseId.html',
			dataType: 'json',
			data: "courseId="+courseId,
			success: function (data){
				
				if(null!=data && data.length!=0){
					var idArray = data.split("*");
					var options= "<option value=''>Please Select</option>";
					for(var i=1;i<idArray.length;i++){
						options=options+"<option value='"+idArray[i]+"'>"+idArray[i]+"</option>";
					}
					document.getElementById("driveId").innerHTML=options;
				}else{
					alert("No drive available for this course");
				}	
			}
		});
	});	*/
	$("#userId").bind('keyup blur',function(){
		var userId=$("#userId").val();	
		 $.ajax({
	     url: '/cedugenie/serverSideValidationOfUserId.html',
	     dataType: 'json',
	     data: "userId=" +userId,
	     success: function(data) {
	    	if(data != null && data!= ""){
	    		document.getElementById("warningbox").style.display='block';
	 			$("#warningbox").text("User Name already exists");
	 			
	    	  	return false;
	    		}	
	    	else{	    		
	    		document.getElementById("warningbox").style.display='none';
	    		}
	    	}  
		});			
	});
	$("#driveId").change(function(){
		var courseId = $("#courseId").val();
		$.ajax({
			url: '/cedugenie/getFormIdAgainstCourseId.html',
			dataType: 'json',
			data: "courseId="+courseId+"&driveName="+$("#driveId").val(),
			success: function (data){
				if(null!= data && data!=""){
					var idArray = data.split("#");
					var options= "<option value=''>Please Select</option>";
					for(var i=0;i<idArray.length;i++){
						options=options+"<option value='"+idArray[i]+"'>"+idArray[i]+"</option>";
					}
					strFormId.innerHTML=options;
				}else{
					alert("No form id available for this course");
				}
			}
		});
		
		/*$.ajax({
		    url: '/cedugenie/getTermsForACourse.html',
		    dataType: 'json',
		    data:"course=" +  $("#courseId").val(),
		    success: function(data) {	
		    	//alert("data ====="+data);
		    	var options='<option value="">Select</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("*~*");
	    			//var courseArr = courseArr1[0].split("#@#");
	    		
	    			for(var a=0; a<termArr1.length;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					var termNameAndCode=termArr1[a].split("#@#");  
									options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
							
	    				}        				
	    			}	        				
		    	}
		    	document.getElementById("term").innerHTML=options;
		    	else{
		    		alert("No Course Found For Class ")+ (thisClassNode.value);
		    	}
		    }
		}); */
	});
	$("#strFormId").change(function(){
		 var formId = $(this).val();
		 
		$.ajax({
	        url: '/cedugenie/getCandidateDetailsAgainstFromId.html',
	        dataType: 'json',
	        data: "formId=" + ($(this).val())+"&driveName="+$("#driveId").val()+"&courseId="+$("#courseId").val(),
	        success: function(data) {
	        	if(data != null && data!=""){
	        		var arr = data.split("*");
				 	document.getElementById("firstName").value = arr[1];
				 	document.getElementById("middleName").value = arr[2];
				 	document.getElementById("lastName").value = arr[3];
				 	document.getElementById("dateOfBirth").value = arr[4];
				 	document.getElementById("emailId").value = arr[5];
				 	//document.getElementById("registrationId").value = arr[8];
				 //	alert("arr[6]=="+arr[6]);
				 	if(arr[6] != 'null' && arr[6] != ""){
				 		document.getElementById("userId").value = arr[6];
				 	}else{
				 		document.getElementById("userId").value = 'NA';
				 	}
				 	//alert("arr7=="+arr[7]);
				 	document.getElementById("dateOfAdmission").value = arr[7];
				 	if(null!= arr[9] && arr[9]!= ""){
				 		if(arr[9]=='M'){
				 			document.getElementById("genderMale").checked=true;
				 		}else{
				 			document.getElementById("genderFemale").checked=true;
				 		}
				 	}
	        	}else{
	        		alert("No details found against this form id");
	        	}
	        }
		});
	});

	
	 $("#presentAddressCountry, #permanentAddressCountry, #guardianAddressCountry").change(function(){
		var stateObject;	
		 var objectId=$(this).attr("id");
			objectId=objectId.replace("AddressCountry","");
			if(objectId=="present"){
				stateObject=document.getElementById("presentAddressState");
			}else if(objectId=="permanent"){
				stateObject=document.getElementById("permanentAddressState");
			}else if(objectId=="guardian"){
				stateObject=document.getElementById("guardianAddressState");
			}
			
			removeOption(stateObject);
			
			$.ajax({
		        url: '/cedugenie/getStateList.html',
		        dataType: 'json',
		        data: "country=" + ($(this).val()),
		        success: function(dataDB) {
		        	
		        	if(dataDB != "null" && dataDB !="")
					{
		        		var options="";
		        		var arr = dataDB.split(";");
						for (var i=0;i<arr.length;i++){   
							var state = arr[i].split("*");
							options=options+"<option value='"+state[0]+"'>"+state[1]+"</option>";
						}
						stateObject.innerHTML=options;
					}
		       }
			});
		});
	 
	 
	 $("#standard").change(function(){
			$.ajax({
		        url: '/cedugenie/getSubjectsForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($(this).val()),
			});
		});
	 
	 var p = 1;
	 $(".addFileClassName").each(function(){
    	$(this).click(function(){                        		
    		var tableNode = $(this).parent().parent().parent();
    		var row = $('<tr><td><span class="btn btn-default btn-file"><input type="file" name="resource.uploadFile.fileData" id="fileData'+p+'"/><span></td><td><img  src="images/minus_icon.png" onclick="deleteThisRow(this);"></td></tr><br>');
            $(tableNode).append(row); 
            p++;
		});
	 });
	 
	 $("#rollNumber").bind('blur',function(){
			var intRegx=numeric=/^[0-9]{1,}$/;
			var rNum=document.getElementById("rollNumber").value;
			var formId = document.getElementById("formId").value;
			rNum=rNum.trim();
			rNum=rNum.replace(/\s{1,}/g,'');
			document.getElementById("rollNumber").value=rNum;
			if(rNum!=""){
				if(!rNum.match(intRegx)){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML="Only Numeric Roll Number Allowed";
				}else{
					if(formId == ""){
						$.ajax({
					        url: '/cedugenie/checkAvailableRollNumber.html',
					        data: "rollNumber=" + rNum,
					        dataType: 'json',
					        success: function(data){
					        	if(data == 'not-available'){		        		
					        		document.getElementById("warningbox").style.visibility='visible';
					    			document.getElementById("warningmsg").innerHTML="Roll Number "+rNum+" Not Available";
					    			document.getElementById("rollNumber").value="";
					        	}else{
//					        		document.getElementById("warningbox").style.visibility='collapse';
//					    			document.getElementById("warningmsg").innerHTML="";
					        	}
					        }
					    });
					}
				}
			}		
		});
	});

function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}


function removeOption(x)
{
	for(var i=x.length;i>=0;i--)
	{
		x.remove(i);
	}
}

function copyMotherDetailsToGuardian(copyToGuardianDetailsBox){
	if(copyToGuardianDetailsBox.checked == true){
		$("#guardianFirstName").val($("#motherFirstName").val());
		$("#guardianMiddleName").val($("#motherMiddleName").val());
		$("#guardianLastName").val($("#motherLastName").val());
		$("#guardianMobile").val($("#motherMobile").val());
		$("#guardianEmail").val($("#motherEmail").val());
	}else{
		$("#guardianFirstName").val('');
		$("#guardianMiddleName").val('');
		$("#guardianLastName").val('');
		$("#guardianMobile").val('');
		$("#guardianEmail").val('');
	}
}

function copyFatherDetailsToGuardian(copyToGuardianDetailsBox){
	if(copyToGuardianDetailsBox.checked == true){
		$("#guardianFirstName").val($("#fatherFirstName").val());
		$("#guardianMiddleName").val($("#fatherMiddleName").val());
		$("#guardianLastName").val($("#fatherLastName").val());
		$("#guardianMobile").val($("#fatherMobile").val());
		$("#guardianEmail").val($("#fatherEmail").val());
	}else{
		$("#guardianFirstName").val('');
		$("#guardianMiddleName").val('');
		$("#guardianLastName").val('');
		$("#guardianMobile").val('');
		$("#guardianEmail").val('');
	}
}

function copyPresentAddressToPermanentAddress(copyAddressBox){
	if(copyAddressBox.checked==true){
		document.getElementById("permanentAddressCountry").value = document.getElementById("presentAddressCountry").value;
		document.getElementById("permanentAddressLine").value = document.getElementById("presentAddressLine").value;
		document.getElementById("permanentAddressLandmark").value = document.getElementById("presentAddressLandmark").value;
		document.getElementById("permanentAddressCityVillage").value = document.getElementById("presentAddressCityVillage").value;
		//document.getElementById("permanentAddressRailwayStation").value = document.getElementById("presentAddressRailwayStation").value;
		document.getElementById("permanentAddressPostOffice").value = document.getElementById("presentAddressPostOffice").value;
		document.getElementById("permanentAddressPoliceStation").value = document.getElementById("presentAddressPoliceStation").value;
		document.getElementById("permanentAddressPinCode").value = document.getElementById("presentAddressPinCode").value;
		document.getElementById("permanentAddressDistrict").value = document.getElementById("presentAddressDistrict").value;
		//document.getElementById("permanentAddressPhone").value = document.getElementById("presentAddressPhone").value;
		document.getElementById("permanentAddressState").value = document.getElementById("presentAddressState").value;
		
		document.getElementById("permanentAddressCountry").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressLine").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressLandmark").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressCityVillage").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPostOffice").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPoliceStation").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPinCode").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressDistrict").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressPhone").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressState").setAttribute("readonly","readonly");
	}else{
		document.getElementById("permanentAddressCountry").value="IND";
		document.getElementById("permanentAddressLine").value="";
		document.getElementById("permanentAddressLandmark").value="";
		document.getElementById("permanentAddressCityVillage").value="";
		//document.getElementById("permanentAddressRailwayStation").value="";
		document.getElementById("permanentAddressPostOffice").value="";
		document.getElementById("permanentAddressPoliceStation").value="";
		document.getElementById("permanentAddressPinCode").value="";
		document.getElementById("permanentAddressDistrict").value="";
		//document.getElementById("permanentAddressPhone").value="";
		document.getElementById("permanentAddressState").value="";
		
		document.getElementById("permanentAddressCountry").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressLine").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressLandmark").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressCityVillage").removeAttribute("readonly","readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPostOffice").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressPoliceStation").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressPinCode").removeAttribute("readonly","readonly");
		document.getElementById("permanentAddressDistrict").removeAttribute("readonly","readonly");
		//document.getElementById("permanentAddressPhone").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressState").removeAttribute("readonly","readonly");
	}
}

function copyPresentAddressToGuardianAddress(copyAddressBox){
	if(copyAddressBox.checked==true){
		document.getElementById("guardianAddressCountry").value = document.getElementById("presentAddressCountry").value;
		document.getElementById("guardianAddressLine").value = document.getElementById("presentAddressLine").value;
		document.getElementById("guardianAddressLandmark").value = document.getElementById("presentAddressLandmark").value;
		document.getElementById("guardianAddressCityVillage").value = document.getElementById("presentAddressCityVillage").value;
		//document.getElementById("permanentAddressRailwayStation").value = document.getElementById("presentAddressRailwayStation").value;
		document.getElementById("guardianAddressPostOffice").value = document.getElementById("presentAddressPostOffice").value;
		document.getElementById("guardianAddressPoliceStation").value = document.getElementById("presentAddressPoliceStation").value;
		document.getElementById("guardianAddressPinCode").value = document.getElementById("presentAddressPinCode").value;
		document.getElementById("guardianAddressDistrict").value = document.getElementById("presentAddressDistrict").value;
		//document.getElementById("permanentAddressPhone").value = document.getElementById("presentAddressPhone").value;
		document.getElementById("guardianAddressState").value = document.getElementById("presentAddressState").value;
		
		document.getElementById("guardianAddressCountry").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressLine").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressLandmark").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressCityVillage").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressPostOffice").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressPoliceStation").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressPinCode").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressDistrict").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressPhone").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressState").setAttribute("readonly","readonly");
	}else{
		document.getElementById("guardianAddressCountry").value="IND";
		document.getElementById("guardianAddressLine").value="";
		document.getElementById("guardianAddressLandmark").value="";
		document.getElementById("guardianAddressCityVillage").value="";
		//document.getElementById("permanentAddressRailwayStation").value="";
		document.getElementById("guardianAddressPostOffice").value="";
		document.getElementById("guardianAddressPoliceStation").value="";
		document.getElementById("guardianAddressPinCode").value="";
		document.getElementById("guardianAddressDistrict").value="";
		//document.getElementById("permanentAddressPhone").value="";
		document.getElementById("guardianAddressState").value="";
		
		document.getElementById("guardianAddressCountry").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressLine").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressLandmark").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressCityVillage").removeAttribute("readonly","readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressPostOffice").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressPoliceStation").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressPinCode").removeAttribute("readonly","readonly");
		document.getElementById("guardianAddressDistrict").removeAttribute("readonly","readonly");
		//document.getElementById("permanentAddressPhone").setAttribute("readonly","readonly");
		document.getElementById("guardianAddressState").removeAttribute("readonly","readonly");
	}
}


function check(bvalue,divId,imgId){
	if(bvalue=="Expand"){
	document.getElementById(divId).style.display = 'none';
	document.getElementById(imgId).value = 'Collapse';
	document.getElementById(imgId).src = 'images/plus_icon.png';
	var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+250+ 'px';
	return true;
	}
	else{
	document.getElementById(divId).style.display = 'block';
	document.getElementById(imgId).value = 'Expand';
	document.getElementById(imgId).src = 'images/minus_icon.png';
	var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+250+ 'px';
	return true;
	}
}

var obj,val;
var intRegx=/^[0-9]{1,}$/;
var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
var mobileRegx = /^\d{10,11}$/;
var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
var alphaNumericWithoutSpace=/^[A-Za-z0-9]{1,50}$/;
var alphabetic=/^[A-Za-z ]{1,50}$/;
var alphabeticWithoutSpace=/^[A-Za-z]{1,50}$/;
var email1=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{2,3})+\.+([a-z0-9_-]{2,3})$/;
var email2=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{3})$/;
var addressRegx=/^[A-Za-z0-9().#-\/ \\]{1,50}$/;
var pinRegx=/^[0-9]{6}$/;

function validateStudentDetails(){
	
//	obj=document.getElementById("formId");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select form id";
//		return false;
//	}
	
	
	
	
	obj=document.getElementById("rollNumber");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(intRegx)){
		alert("Invalid Roll Number. Numeric (Atleast 1 digit) required");
		return false;
	}
	
	obj=document.getElementById("firstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Student First Name. Alphabetic( Atleast 1 character) required");
		return false;
	}
	
	obj=document.getElementById("middleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Invalid Student Middle Name. Alphabetic (Atleat 1 character) required");
			return false;
		}
	}
	
	obj=document.getElementById("lastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Student Last Name. Alphabetic (Atleast 1 character) required");
		return false;
	}
	
	obj=document.getElementById("standard");
	val=obj.value;
	if(val==""){
		alert("Select Standard.");
		return false;
	}
	
//	obj=document.getElementById("secondLanguage");
//	val=obj.value;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Second Language.";
//		return false;
//	}
	
	obj=document.getElementById("dateOfBirth");
	val=obj.value;
	if(val==""){
		alert("Select Date Of Birth.");
		return false;
	}
	
	obj=document.getElementById("dateOfAdmission");
	val=obj.value;
	if(val==""){
		alert("Select Date Of Admission.");
		return false;
	}
	
	obj=document.getElementById("stateOfDomicile");
	val=obj.value;
	if(val==""){
		alert("Select State Of Domicile.");
		return false;
	}
//	
//	obj=document.getElementById("bankName");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Bank Name. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
//	obj=document.getElementById("bankBranch");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Bank Branch. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
//	obj=document.getElementById("accountNumber");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(!val.match(intRegx)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Account Number. (Numeric. Atleat 1 character.)";
//		return false;
//	}
//	
//	obj=document.getElementById("religion");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Religion Name. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
//	obj=document.getElementById("motherTongue");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Mother Tongue. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
//	obj=document.getElementById("nationality");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Nationality. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
	obj=document.getElementById("emailId");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
	    if(!(email1.test(val) || email2.test(val)))	{
	    	alert("Invalid Student Email.");
			return false;
	    }
	}
    return true;
}

function validateFamilyDetails(){	
	obj=document.getElementById("fatherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Father's First Name is Not Valid. Alphabetic (Atleast 1 character)");
		return false;
	}
	
	obj=document.getElementById("fatherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Father's Middle Name is not valid. Alphabetic (Atleast 1 character) required.");
			return false;
		}
	}
	
	obj=document.getElementById("fatherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Father's Last Name. Alphabetic (Atleast 1 character) required.");
		return false;
	}
	
//	obj=document.getElementById("fatherInDefence");
//	val=obj.value;
//	if(val=="true"){
//		if(document.getElementById("fatherServiceStatus").value==""){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Select Father's Service Status.";
//			return false;
//		}
//		if(document.getElementById("fatherDefenceCategory").value==""){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Select Father's Defence Category.";
//			return false;
//		}
//		var fatherRankBox=document.getElementById("fatherRank");
//		var fatherRank=fatherRankBox.value;
//		fatherRank=fatherRank.replace(/\s{1,}/g,' ');
//		fatherRank=fatherRank.trim();
//		fatherRankBox.value=fatherRank;
//		if(!fatherRank.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Father's Rank. (Alpha-Numeric. Atleat 1 character.)";
//			return false;
//		}
//	}else{
//		document.getElementById("fatherServiceStatus").value=="";
//		document.getElementById("fatherDefenceCategory").value=="";
//		document.getElementById("fatherRank").value=="";
//	}

	obj=document.getElementById("fatherMobile");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(mobileRegx)){
			alert("Invalid Father's Mobile Number. (10 digit number preceded by 0.");
			return false;
		}
	}
	
	obj=document.getElementById("fatherEmail");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
	    if(!(email1.test(val) || email2.test(val)))	{
	    	alert("Invalid Father's Email.");
			return false;
	    }
	}
	
	obj=document.getElementById("motherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Mother's First Name. Alphabetic (Atleast 1 character) required.");
		return false;
	}
	
	obj=document.getElementById("motherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Invalid Mother's Middle Name. Alphabetic (Atleast 1 character) required.");
			return false;
		}
	}
	
	obj=document.getElementById("motherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Mother's Last Name. Alphabetic (Atleast 1 character) required.");
		return false;
	}

	obj=document.getElementById("motherMobile");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(mobileRegx)){
			alert("Invalid Mother's Mobile Number. (10 digit number preceded by 0.");
			return false;
		}
	}
	
	obj=document.getElementById("motherEmail");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
	    if(!(email1.test(val) || email2.test(val)))	{
	    	alert("Invalid Mother's Email.");
			return false;
	    }
	}
	
	obj=document.getElementById("fatherIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	alert("Invalid Father's Income.");
	    	return false;
	    }
	}
	obj=document.getElementById("motherIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	alert("Invalid Mother's Income.");
			return false;
	    }
	}
	obj=document.getElementById("studentIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	alert("Invalid Student's Income.");
			return false;
	    }
	}
	
	obj=document.getElementById("familyIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	alert("Invalid Family's Income.");
			return false;
	    }
	}
	obj=document.getElementById("guardianFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Invalid Guardian's First Name. Alphabetic (Atleast 1 character) required.");
			return false;
		}
		
		obj=document.getElementById("guardianMiddleName");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(val!=""){
			if(!val.match(alphabetic)){
				alert("Invalid Guardian's Middle Name. Alphabetic (Atleast 1 character) required.");
				return false;
			}
		}
		
		obj=document.getElementById("guardianLastName");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphabetic)){
			alert("Invalid Guardian's Last Name. Alphabetic (Atleast 1 character) required.");
			return false;
		}
		
		obj=document.getElementById("guardianMobile");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(val!=""){
			if(!val.match(mobileRegx)){
				alert("Invalid Guardian's Mobile Number. (10 digit number preceded by 0.");
				return false;
			}
		}
		
		obj=document.getElementById("guardianEmail");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(val!=""){
		    if(!(email1.test(val) || email2.test(val)))	{
		    	alert("Invalid Guardian's Email.");
				return false;
		    }
		}
	}
	
	return true;
}


function validateAddress(){
	obj=document.getElementById("presentAddressLine");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		alert("Invalid Correspondence Address.");
		return false;
	}
	
	obj=document.getElementById("presentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
			alert("Invalid Correspondence Address Landmark.");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		alert("Invalid Correspondence Address City/Village.");
		return false;
	}
	
	obj=document.getElementById("presentAddressRailwayStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Invalid Correspondence Address Railway Station.");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressPostOffice");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Correspondence Address Post Office.");
		return false;
	}
	
	obj=document.getElementById("presentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Correspondence Address Police Station.");
		return false;
	}
	
	obj=document.getElementById("presentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(pinRegx)){
		alert("Invalid Correspondence Address Pin Code.");
		return false;
	}
	
	obj=document.getElementById("presentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphaNumeric)){
		alert("Invalid Correspondence Address District.");
		return false;
	}
	
	obj=document.getElementById("presentAddressState");
	val=obj.value;
	if(val==""){
		alert("Select Correspondence Address State.");
		return false;
	}
	
	obj=document.getElementById("presentAddressCountry");
	val=obj.value;
	if(val==""){
		alert("Select Correspondence Address Country.");
		return false;
	}
	
	obj=document.getElementById("presentAddressPhone");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(intRegx)){
			alert("Invalid Correspondence Address Phone.(Numeric. Atleast 1 digit");
			return false;
		}
	}
	
	
	obj=document.getElementById("permanentAddressLine");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		alert("Invalid Permanent Address.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
			alert("Invalid Permanent Address Landmark.");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		alert("Invalid Permanent Address City/Village.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressRailwayStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			alert("Invalid Permanent Address Railway Station.");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressPostOffice");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Permanent Address Post Office.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		alert("Invalid Permanent Address Police Station.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(pinRegx)){
		alert("Invalid Permanent Address Pin Code.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphaNumeric)){
		alert("Invalid Permanent Address District.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressState");
	val=obj.value;
	if(val==""){
		alert("Select Permanent Address State.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressCountry");
	val=obj.value;
	if(val==""){
		alert("Select Permanent Address Country.");
		return false;
	}
	
	obj=document.getElementById("permanentAddressPhone");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(intRegx)){
			alert("Invalid Permanent Address Phone.(Numeric. Atleast 1 digit ");
			return false;
		}
	}
	
	
	obj=document.getElementById("guardianAddressLine");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
			alert("Invalid Guardian Address.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressLandmark");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(val!=""){
			if(!val.match(addressRegx)){
				alert("Invalid Guardian Address Landmark.");
				return false;
			}
		}
		
		obj=document.getElementById("guardianAddressCityVillage");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(addressRegx)){
			alert("Invalid Guardian Address City/Village.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressRailwayStation");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(val!=""){
			if(!val.match(alphabetic)){
				alert("Invalid Guardian Address Railway Station.");
				return false;
			}
		}
		
		obj=document.getElementById("guardianAddressPostOffice");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphabetic)){
			alert("Invalid Guardian Address Post Office.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressPoliceStation");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphabetic)){
			alert("Invalid Guardian Address Police Station.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressPinCode");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(!val.match(pinRegx)){
			alert("Invalid Guardian Address Pin Code.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressDistrict");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphaNumeric)){
			alert("Invalid Guardian Address District.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressState");
		val=obj.value;
		if(val==""){
			alert("Select Guardian Address State.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressCountry");
		val=obj.value;
		if(val==""){
			alert("Select Guardian Address Country.");
			return false;
		}
		
		obj=document.getElementById("guardianAddressPhone");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(val!=""){
			if(!val.match(intRegx)){
				alert("Invalid Guardian Address Phone.(Numeric. Atleast 1 digit");
				return false;
			}
		}
	}else{
		document.getElementById("guardianAddressLandmark").value="";
		document.getElementById("guardianAddressCityVillage").value="";
		document.getElementById("guardianAddressRailwayStation").value="";
		document.getElementById("guardianAddressPostOffice").value="";
		document.getElementById("guardianAddressPoliceStation").value="";
		document.getElementById("guardianAddressPinCode").value="";
		document.getElementById("guardianAddressDistrict").value="";
		document.getElementById("guardianAddressState").value="";
		document.getElementById("guardianAddressCountry").value="";
		document.getElementById("guardianAddressPhone").value="";
	}
	
	
	return true;
}

function validatePreviousEducationDetails(){
//	obj=document.getElementById("previousSchoolName");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(val!= "" && !val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Student Previous School Name. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
	
	obj=document.getElementById("previousSchoolPhone");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(intRegx)){
			alert("Invalid Student Previous School Phone.(Numeric. Atleast 1 digit");
			return false;
		}
	}
	
	obj=document.getElementById("previousSchoolWebsite");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	
	obj=document.getElementById("previousSchoolEmail");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
	    if(!(email1.test(val) || email2.test(val)))	{
	    	alert("Invalid Previous School Email.");
			return false;
	    }
	}
	
//	obj=document.getElementById("previousSchoolAddress");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(val!="" && !val.match(addressRegx)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Previous School Address. Alphabetic (Atleast 1 character) required.)";
//		return false;
//	}
//	
	return true;
}

function validate(){
	if(!validateStudentDetails()){
		return false;
	}
	if(!validateFamilyDetails()){
		return false;
	}
	if(!validateAddress()){
		return false;
	}
	if(!validatePreviousEducationDetails()){
		return false;
	}
	return true;
}