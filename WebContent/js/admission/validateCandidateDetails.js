function validateForm()
{
	document.getElementById("warningbox").style.visibility="collapse";
	document.getElementById("warningmsg").innerHTML="";

ran=/^[A-Za-z ]{1,}$/;
ran1=/^[A-Za-z0-9_.-\/]{1,50}$/;
ran2=/^[A-Za-z0-9_.-]{1,50}$/;
em1=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{2,3})+\.+([a-z0-9_-]{2,3})$/;
em2=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{3})$/;
phoneno = /^\d{11}$/;  
pin = /^\d{6}$/; 


//Validate Class
kls=document.getElementById("standard").value;
if(kls=="" || kls=="NULL")
{
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Select Class";
	return false;
}


//Validate First Name
candidateFName = document.getElementById("firstName").value;
candidateFName = candidateFName.replace(/\s{1,}/g,'');
if(candidateFName == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter First Name";
	return false;
}
if(candidateFName!='')
{
	if(!candidateFName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid First Name of Candidate";
		return false;
	}
}

//Validate Last Name
candidateLName = document.getElementById("lastName").value;
candidateLName = candidateLName.replace(/\s{1,}/g,'');
if(candidateLName == ""){	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Candidate Last Name";
	return false;
}
if(candidateLName!=''){
	if(!candidateLName.match(ran)){
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Candidate Last Name of Student";
		return false;
	}
}
//Validate Gender
gndr = document.getElementsByName("resource.gender");
if ( ( gndr[0].checked == false ) && ( gndr[1].checked == false ))
{
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please choose your Gender: Male or Female";
	return false;
}

//Validate DOB
dob=document.getElementById("dateOfBirth").value;
dob=dob.replace(/\s{1,}/g,'');
if(dob=="")
{
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Select Date Of Birth";
	return false;
}


//Validate Blood Group
bg = document.getElementById("bloodGroup").value;
bg = bg.replace(/\s{1,}/g,'');
if(bg == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Blood Group";
	return false;
}

//Validate category
ct = document.getElementById("category").value;
ct = ct.replace(/\s{1,}/g,'');
if(ct == ""){	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Category";
	return false;
}

//Validate Religion
relgn = document.getElementById("religion").value;
relgn = relgn.replace(/\s{1,}/g,'');
if(relgn == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Religion";
	return false;
}
if(relgn!='')
{
	if(!relgn.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Religion";
		return false;
	}
}

//Validate Nationality
n = document.getElementById("nationality").value;
n = n.replace(/\s{1,}/g,'');
if(n == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Nationality";
	return false;
}
if(n!='')
{
	if(!n.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Nationality";
		return false;
	}
}


//----------------------------VALIDATE FATHERS DETAILS--------------------------------------
//Validate FATHERS First Name
fatherFName = document.getElementById("fatherFirstName").value;
fatherFName = fatherFName.replace(/\s{1,}/g,'');
if(fatherFName == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Father's First Name";
	return false;
}
if(fatherFName!='')
{
	if(!fatherFName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid First Name of Father";
		return false;
	}
}

//Validate FATHERS Last Name
fatherLName = document.getElementById("fatherLastName").value;
fatherLName = fatherLName.replace(/\s{1,}/g,'');
if(fatherLName == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Father's Last Name";
	return false;
}
if(fatherLName!='')
{
	if(!fatherLName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Last Name of Father";
		return false;
	}
}
//----------------------------VALIDATE MOTHER DETAILS--------------------------------------
//Validate MOTHER First Name
motherFName = document.getElementById("motherFirstName").value;
motherFName = motherFName.replace(/\s{1,}/g,'');
if(motherFName == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Mother's First Name";
	return false;
}
if(motherFName!='')
{
	if(!motherFName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid First Name of Mother";
		return false;
	}
}
//Validate MOTHER Last Name
motherLName = document.getElementById("motherLastName").value;
motherLName = motherLName.replace(/\s{1,}/g,'');
if(motherLName == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Mother's Last Name";
	return false;
}
if(motherLName!='')
{
	if(!motherLName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Last Name of Mother";
		return false;
	}
}

//Validate Mobile
mobile = document.getElementById("mobile").value;
mobile = mobile.replace(/^\s+|\s+$/g,'');
if(mobile == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Mobile Number";
	return false;
}
if(mobile!="")
{
	if(! mobile.match(phoneno))  
	  {  
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter 11 Digit Mobile Number including Zero";
	      return false;  
	  } 	
}

//Validate Email Id
emailId = document.getElementById("emailId").value;
emailId = emailId.replace(/\s{1,}/g,'');
if(emailId == ""){	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Email Id";
	return false;
}
if(!(em1.test(emailId) || em2.test(emailId))){

	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Valid E-mail Id";
	return false;
}


//-----------------Occupation Validate--------------------------------------------------------
fatherOccupation = document.getElementById("fatherOccupation").value;
if(fatherOccupation!='')
{
	if(!fatherOccupation.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Father Occupation";
		return false;
	}
}
fatherOccupation = document.getElementById("motherOccupation").value;
if(fatherOccupation!='')
{
	if(!motherOccupation.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Mother Occupation";
		return false;
	}
}

guardianOccupation = document.getElementById("guardianOccupation").value;
if(guardianOccupation!='')
{
	if(!guardianOccupation.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Guardian Occupation";
		return false;
	}
}
if(!validateAmount(document.getElementById("fatherAgricultureIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("fatherBusinessIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("fatherSalaryIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("fatherPensionIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("fatherOthersIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("fatherTotalIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherAgricultureIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherBusinessIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherSalaryIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherPensionIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherOthersIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("motherTotalIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianAgricultureIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianBusinessIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianSalaryIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianPensionIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianOthersIncome"))){
	return false;
   }
if(!validateAmount(document.getElementById("guardianTotalIncome"))){
	return false;
   }

//-----------------Present-Address--------------------------------------------------------

//Validate present Address 1
presentAddress1 = document.getElementById("presentAddress1").value;
if(presentAddress1 == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present Address 1";
	return false;
}

//Validate Present City
presentCity = document.getElementById("presentAddressCityVillage").value;
if(presentCity == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present City/Village";
	return false;
}

//Validate present PostOffice
presentPostOffice = document.getElementById("presentAddressPostOffice").value;
if(presentPostOffice == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present PostOffice";
	return false;
}


//Validate Present PoliceStation
presentPoliceStation = document.getElementById("presentAddressPoliceStation").value;
if(presentPoliceStation == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter presentPoliceStation";
	return false;
}

//Validate Present Pin
var presentPin = document.getElementById("presentAddressPinCode").value;
presentPin.replace(/^\s+|\s+$/g,'');
if(presentPin == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present Pin Code";
	return false;
}
if(presentPin!="")
{
	if(! presentPin.match(pin))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Valid Pin Code";
		return false;
	}	
}
//Validate Present Pin
var presentDistrict = document.getElementById("presentAddressDistrict").value;
presentDistrict.replace(/^\s+|\s+$/g,'');
if(presentDistrict == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present District";
	return false;
}
if(presentDistrict!="")
{
	if(! presentDistrict.match(presentDistrict))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Valid District";
		return false;
	}	
	
}

//Validate Present Country
presentCountry = document.getElementById("presentAddressCountry").value;
presentCountry = presentCountry.replace(/\s{1,}/g,'');
if(presentCountry == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present Country";
	return false;
}

//Validate Present State
presentState = document.getElementById("presentAddressState").value;
presentState = presentState.replace(/\s{1,}/g,'');
if(presentState == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Present State";
	return false;
}
//-------------------------------Permanent Address-------------------------


//Validate Permanent Address 1
permanentAddress1 = document.getElementById("permanentAddress1").value;
if(permanentAddress1 == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent Address 1";
	return false;
}

//Validate Permanent City
permanentCity = document.getElementById("permanentAddressCityVillage").value;
if(permanentCity == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent City";
	return false;
}

//Validate Permanent PostOffice
permanentPostOffice = document.getElementById("permanentAddressPostOffice").value;
if(permanentPostOffice == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent PostOffice";
	return false;
}


//Validate Permanent PoliceStation
permanentPoliceStation = document.getElementById("permanentAddressPoliceStation").value;
if(permanentPoliceStation == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent PoliceStation";
	return false;
}

//Validate Permanent Pin
permanentPin = document.getElementById("permanentAddressPinCode").value;
permanentPin = permanentPin.replace(/\s{1,}/g,'');
if(permanentPin == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent Pin";
	return false;
}
if(permanentPin!=""){
	if(! permanentPin.match(pin)){
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Valid Permanent Pin Code";
		return false;
	}	
	
}
//Validate Permanent District
permanentAddressDistrict = document.getElementById("permanentAddressDistrict").value;
permanentAddressDistrict = permanentAddressDistrict.replace(/\s{1,}/g,'');
if(permanentAddressDistrict == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent District";
	return false;
}


//Validate Permanent Country
permanentCountry = document.getElementById("permanentAddressCountry").value;
permanentCountry = permanentCountry.replace(/\s{1,}/g,'');
if(permanentCountry == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent Country";
	return false;
}else{
	document.getElementById("permanentAddressCountry").removeAttribute("disabled");
}

//Validate Permanent State
permanentState = document.getElementById("permanentAddressState").value;
permanentState = permanentState.replace(/\s{1,}/g,'');
if(permanentState == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Permanent State";
	return false;
}else{
	document.getElementById("permanentAddressState").removeAttribute("disabled");
}


//Validate Previous School Name
organizationName = document.getElementById("previousSchoolName").value;
if(organizationName!='')
{
	if(!organizationName.match(ran))
	{
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid Previous School Name";
		return false;
	}
}

//Validate Mobile
organizationContactNo = document.getElementById("previousSchoolContact").value;
organizationContactNo = organizationContactNo.replace(/^\s+|\s+$/g,'');
if(organizationContactNo!='')
{
	if(!organizationContactNo.match(phoneno))  
	  {  
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter 11 Digit Mobile Number including Zero";
	    return false;  
	  } 	
}



//Validate WEB SITE
organizationWebSite = document.getElementById("previousSchoolWebsite").value;
organizationWebSite = organizationWebSite.replace(/^\s+|\s+$/g,'');
if(organizationWebSite!='')
{
	if(!organizationWebSite.match(ran1))  
	  {  
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Invalid web site";
	    return false;  
	  } 	
}

//-------------------------------------Bank Details Validation------------------------------------

//Validate D.D./I.P.O. No
var bankDdIpoNo = document.getElementById("bankDdIpoNo").value;
bankDdIpoNo.replace(/^\s+|\s+$/g,'');
if(bankDdIpoNo == ""){	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Enter Bank D.D./I.P.O. No";
	return false;
}

//Validate Bank Date
var bankDate = document.getElementById("bankDate").value;
bankDate.replace(/^\s+|\s+$/g,'');
if(bankDate == "")
{	
	document.getElementById("warningbox").style.visibility="visible";
	document.getElementById("warningmsg").innerHTML="Please Select Date for Bank Draft";
	return false;
}
//Validate bankAmount
	var bankAmount = document.getElementById("bankAmount").value;
	bankAmount.replace(/^\s+|\s+$/g,'');
	if(bankAmount == "")
	{	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Amount for Bank Draft";
		return false;
	}
	
	if(!validateAmount(document.getElementById("bankAmount"))){
		return false;
	   }

	//Validate bankAmount
	var issuingBankBranchName = document.getElementById("issuingBankBranchName").value;
	issuingBankBranchName.replace(/^\s+|\s+$/g,'');
	if(issuingBankBranchName == "")
	{	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Issuing Bank Branch Name";
		return false;
	}
	if(issuingBankBranchName!="")
	{
		if(! issuingBankBranchName.match(ran))
		{
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Issuing Bank Branch Name";
			return false;
		}
}




document.getElementById('permanentState').disabled = false;		
document.getElementById('permanentCountry').disabled = false;
return true;
}

function validateAmount(element){
	document.getElementById("warningbox").style.visibility='collapse';
	document.getElementById("warningmsg").innerHTML="";
	var regAmount = '^[0-9]+$';
	var amount = document.getElementById(element.id).value;
	if(amount != ""){
		if(amount != "0.00"){
			if(!amount.match(regAmount)) {
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please Enter Correct Amount";
				return false;
			}else{
				return true; 
			}
		}else
			return true;
	}else{
		document.getElementById(element.id).value="0.00";
	}
	
	
}


