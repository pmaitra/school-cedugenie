
window.onload = function()
{
	document.getElementById('guardianDetails').style.display = 'none';
	document.getElementById('location').style.display = 'none';
	document.getElementById('studentPreviousEducationalDetails').style.display = 'none';
	document.getElementById('personalDetails').style.display = 'none';
	document.getElementById('uploadImage').style.display = 'none';
	
	
};

$(document).ready(function() {
//	 $('#dateOfBirth').Zebra_DatePicker();
//	 $('#dateOfAdmission').Zebra_DatePicker();
//	 
//	 $('#dateOfBirth').Zebra_DatePicker({
//	 	  format: 'd/m/Y',
//		  direction: false
//	 	});
//	 $('#dateOfAdmission').Zebra_DatePicker({
//	 	  format: 'd/m/Y',
//	 	 direction: false
//	 	});
	 
	 
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
		        url: '/icam/getStateList.html',
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
		 var oldSec=document.getElementById("oldSec").value;
		 if(oldSec!="NA"){
			var newStd=$(this).val();
			var oldStd=document.getElementById("oldStd").value;
			if(newStd!=oldStd){
				document.getElementById("sectionSpan").innerHTML='Not Defined Yet<input type="hidden" name="section" id="section" value="NA">';
			}else{
				
				document.getElementById("sectionSpan").innerHTML='<input type="text" id="section" name="section" value="'+oldSec+'" readonly="readonly" class="textfield1">';
			}
		 }
		 
		 
		 var secondLanguageObject=document.getElementById("secondLanguage");				
			removeOption(secondLanguageObject);
			$.ajax({
		        url: '/icam/getSubjectsForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($(this).val()),
		        success: function(dataDB) {
		        	var options="<option value=''>Select</option>";
		        	if(dataDB != "null" && dataDB !="")
					{
		        		var arr = dataDB.split("*~*");
						for (var i=0;i<arr.length-1;i++){
							options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
						}
					}
		        	secondLanguageObject.innerHTML=options;
		       }
			});
		 
		 
	 });
	 
	 var p = 1;
	 $(".addFileClassName").each(function(){
    	$(this).click(function(){                        		
    		var tableNode = $(this).parent().parent().parent();
    		var row = $('<tr><td><span class="btn btn-default btn-file"><input type="file" name="resource.uploadFile.fileData" id="fileData'+p+'"/><span></td><td><img  src="images/minus_icon.png" onclick="deleteThisRow(this);"></td></tr><br></br>');
            $(tableNode).append(row); 
            p++;
		});
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
	if(bvalue=="Expand")	{
	document.getElementById(divId).style.display = 'none';
	document.getElementById(imgId).value = 'Collapse';
	document.getElementById(imgId).src = 'images/plus_icon.png';
	return true;
	}
	else{
	document.getElementById(divId).style.display = 'block';
	document.getElementById(imgId).value = 'Expand';
	document.getElementById(imgId).src = 'images/minus_icon.png';
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
	obj=document.getElementById("rollNumber");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(intRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Roll Number. (Numeric. Atleat 1 digit.)";
		return false;
	}
	
	obj=document.getElementById("firstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Student First Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
	obj=document.getElementById("middleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Student Middle Name. (Alphabetic. Atleat 1 character.)";
			return false;
		}
	}
	
	obj=document.getElementById("lastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Student Last Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
	obj=document.getElementById("standard");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select A Standard.";
		return false;
	}
	
	obj=document.getElementById("section");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select A Section.";
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
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Date Of Birth.";
		return false;
	}
	
	obj=document.getElementById("dateOfAdmission");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Date Of Admission.";
		return false;
	}
	
	obj=document.getElementById("stateOfDomicile");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select State Of Domicile.";
		return false;
	}
	
//	obj=document.getElementById("bankName");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Bank Name. (Alphabetic. Atleat 1 character.)";
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
//		document.getElementById("warningmsg").innerHTML="Invalid Bank Branch. (Alphabetic. Atleat 1 character.)";
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
//		document.getElementById("warningmsg").innerHTML="Invalid Religion Name. (Alphabetic. Atleat 1 character.)";
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
//		document.getElementById("warningmsg").innerHTML="Invalid Mother Tongue. (Alphabetic. Atleat 1 character.)";
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
//		document.getElementById("warningmsg").innerHTML="Invalid Nationality. (Alphabetic. Atleat 1 character.)";
//		return false;
//	}
//	
//	obj=document.getElementById("emailId");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//	    if(!(email1.test(val) || email2.test(val)))	{
//	    	document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Student Email.";
//			return false;
//	    }
//	}
    return true;
}

function validateFamilyDetails(){	
	obj=document.getElementById("fatherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Father's First Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
	obj=document.getElementById("fatherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Father's Middle Name. (Alphabetic. Atleat 1 character.)";
			return false;
		}
	}
	
	obj=document.getElementById("fatherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Father's Last Name. (Alphabetic. Atleat 1 character.)";
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
//
//	obj=document.getElementById("fatherMobile");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(mobileRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Father's Mobile Number. (10 digit number preceded by 0.)";
//			return false;
//		}
//	}
//	
//	obj=document.getElementById("fatherEmail");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//	    if(!(email1.test(val) || email2.test(val)))	{
//	    	document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Father's Email.";
//			return false;
//	    }
//	}
	
	obj=document.getElementById("motherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Mother's First Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
	obj=document.getElementById("motherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Middle Name. (Alphabetic. Atleat 1 character.)";
			return false;
		}
	}
	
	obj=document.getElementById("motherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Mother's Last Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}

	obj=document.getElementById("motherMobile");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(mobileRegx)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Mobile Number. (10 digit number preceded by 0.)";
			return false;
		}
	}
	
	obj=document.getElementById("motherEmail");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
	    if(!(email1.test(val) || email2.test(val)))	{
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Email.";
			return false;
	    }
	}
	
	obj=document.getElementById("fatherIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Father's Income.";
			return false;
	    }
	}
	
	
	obj=document.getElementById("motherIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Income.";
			return false;
	    }
	}
	
	obj=document.getElementById("studentIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Student's Income.";
			return false;
	    }
	}
	
	obj=document.getElementById("familyIncome");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Family's Income.";
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
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Guardian's First Name. (Alphabetic. Atleat 1 character.)";
			return false;
		}
		
		obj=document.getElementById("guardianMiddleName");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(val!=""){
			if(!val.match(alphabetic)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Invalid Guardian's Middle Name. (Alphabetic. Atleat 1 character.)";
				return false;
			}
		}
		
		obj=document.getElementById("guardianLastName");
		val=obj.value;
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Guardian's Last Name. (Alphabetic. Atleat 1 character.)";
			return false;
		}
		
		obj=document.getElementById("guardianMobile");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(val!=""){
			if(!val.match(mobileRegx)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Invalid Guardian's Mobile Number. (10 digit number preceded by 0.)";
				return false;
			}
		}
		
		obj=document.getElementById("guardianEmail");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if(val!=""){
		    if(!(email1.test(val) || email2.test(val)))	{
		    	document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Invalid Guardian's Email.";
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
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address.";
		return false;
	}
	
	obj=document.getElementById("presentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Landmark.";
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address City/Village.";
		return false;
	}
	
//	obj=document.getElementById("presentAddressRailwayStation");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Railway Station.";
//			return false;
//		}
//	}
	
	obj=document.getElementById("presentAddressPostOffice");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Post Office.";
		return false;
	}
	
	obj=document.getElementById("presentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Police Station.";
		return false;
	}
	
	obj=document.getElementById("presentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(pinRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Pin Code.";
		return false;
	}
	
	obj=document.getElementById("presentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphaNumeric)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address District.";
		return false;
	}
	
	obj=document.getElementById("presentAddressState");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Correspondence Address State.";
		return false;
	}
	
	obj=document.getElementById("presentAddressCountry");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Correspondence Address Country.";
		return false;
	}
	
//	obj=document.getElementById("presentAddressPhone");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(intRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Phone.(Numeric. Atleast 1 digit)";
//			return false;
//		}
//	}
	
	
	obj=document.getElementById("permanentAddressLine");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Landmark.";
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(addressRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address City/Village.";
		return false;
	}
	
//	obj=document.getElementById("permanentAddressRailwayStation");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Railway Station.";
//			return false;
//		}
//	}
	
	obj=document.getElementById("permanentAddressPostOffice");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Post Office.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Police Station.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(!val.match(pinRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Pin Code.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphaNumeric)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Permanent Address District.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressState");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Permanent Address State.";
		return false;
	}
	
	obj=document.getElementById("permanentAddressCountry");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Permanent Address Country.";
		return false;
	}
	
//	obj=document.getElementById("permanentAddressPhone");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(intRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Phone.(Numeric. Atleast 1 digit)";
//			return false;
//		}
//	}
	
	
//	obj=document.getElementById("guardianAddressLine");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,' ');
//	val=val.trim();
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressLandmark");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(val!=""){
//			if(!val.match(addressRegx)){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Landmark.";
//				return false;
//			}
//		}
//		
//		obj=document.getElementById("guardianAddressCityVillage");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address City/Village.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressRailwayStation");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(val!=""){
//			if(!val.match(alphabetic)){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Railway Station.";
//				return false;
//			}
//		}
//		
//		obj=document.getElementById("guardianAddressPostOffice");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Post Office.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressPoliceStation");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Police Station.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressPinCode");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,'');
//		obj.value=val;
//		if(!val.match(pinRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Pin Code.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressDistrict");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,' ');
//		val=val.trim();
//		obj.value=val;
//		if(!val.match(alphaNumeric)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Guardian Address District.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressState");
//		val=obj.value;
//		if(val==""){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Select Guardian Address State.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressCountry");
//		val=obj.value;
//		if(val==""){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Select Guardian Address Country.";
//			return false;
//		}
//		
//		obj=document.getElementById("guardianAddressPhone");
//		val=obj.value;
//		val=val.replace(/\s{1,}/g,'');
//		obj.value=val;
//		if(val!=""){
//			if(!val.match(intRegx)){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Invalid Guardian Address Phone.(Numeric. Atleast 1 digit)";
//				return false;
//			}
//		}
//	}else{
//		document.getElementById("guardianAddressLandmark").value="";
//		document.getElementById("guardianAddressCityVillage").value="";
//		document.getElementById("guardianAddressRailwayStation").value="";
//		document.getElementById("guardianAddressPostOffice").value="";
//		document.getElementById("guardianAddressPoliceStation").value="";
//		document.getElementById("guardianAddressPinCode").value="";
//		document.getElementById("guardianAddressDistrict").value="";
//		document.getElementById("guardianAddressState").value="";
//		document.getElementById("guardianAddressCountry").value="";
//		document.getElementById("guardianAddressPhone").value="";
//	}
	
	
	return true;
}

function validatePreviousEducationDetails(){
	obj=document.getElementById("previousSchoolName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	
	if(val!="" && !val.match(alphabetic)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Student Previous School Name. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
	obj=document.getElementById("previousSchoolPhone");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(intRegx)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Student Previous School Phone.(Numeric. Atleast 1 digit)";
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
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Previous School Email.";
			return false;
	    }
	}
	
	obj=document.getElementById("previousSchoolAddress");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!="" && !val.match(addressRegx)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Previous School Address. (Alphabetic. Atleat 1 character.)";
		return false;
	}
	
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

