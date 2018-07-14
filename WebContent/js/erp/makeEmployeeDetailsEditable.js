	function makeBasicDetailsFieldEditable()
	{		
		document.getElementById("dateOfBirth").removeAttribute("disabled");
		document.getElementById("dateOfJoining").removeAttribute("disabled");
		/*document.getElementById("dateOfRetirement").removeAttribute("disabled");*/	
		document.getElementById("employeeTypeName").removeAttribute("disabled");
		document.getElementById("jobTypeName").removeAttribute("disabled");	
		document.getElementById("designationName").removeAttribute("disabled");			
		document.getElementById("department").removeAttribute("disabled");
		document.getElementById("teachingLevelName").removeAttribute("disabled");
		document.getElementById("designationLevel").removeAttribute("disabled");
		document.getElementById("qualificationSummary").removeAttribute("readonly");
		document.getElementById("basicDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("basicDetailsTableCancel").removeAttribute ("style");
		document.getElementById("designationLevelInput").removeAttribute("disabled");
		document.getElementById("designationLevelInput").setAttribute("style","display: none;");
		document.getElementById("designationLevelDiv").setAttribute ("style","display: block;");
		document.getElementById("basicDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeBasicDetailsFieldInEditable()
	{		
		document.getElementById("dateOfBirth").setAttribute("disabled","disabled");
		document.getElementById("dateOfJoining").setAttribute("disabled","disabled");
		/*document.getElementById("dateOfRetirement").setAttribute("disabled","disabled");*/
		//document.getElementById("employeeTypeName").setAttribute("disabled","disabled");
		document.getElementById("jobTypeName").setAttribute("disabled","disabled");
		document.getElementById("designationName").setAttribute("disabled","disabled");
		document.getElementById("department").setAttribute("disabled","disabled");
		document.getElementById("qualificationSummary").setAttribute("readonly","readonly");
		document.getElementById("basicDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("basicDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("basicDetailsTableEdit").removeAttribute ("style");
		document.getElementById("designationLevelInput").setAttribute("disabled","disabled");
		document.getElementById("designationLevelInput").setAttribute("style","display: block;");
		document.getElementById("designationLevelDiv").setAttribute ("style","display: none;");
		document.getElementById("employeeTypeName").setAttribute("disabled","disabled");
		document.getElementById("teachingLevelName").setAttribute("disabled","disabled");
	};
	
	function makePersonalDetailsFieldEditable()
	{
		document.getElementById("firstName").removeAttribute("readonly");
		
		document.getElementById("middleName").removeAttribute("readonly");
		
		document.getElementById("lastName").removeAttribute("readonly");
		
		document.getElementById("initialName").removeAttribute("readonly");
		
		document.getElementById("bloodGroup").removeAttribute("disabled");
		
		document.getElementById("nationality").removeAttribute("readonly");
		
		document.getElementById("motherTongue").removeAttribute("readonly");
		
		document.getElementById("category").removeAttribute("disabled");
		
		document.getElementById("religion").removeAttribute("readonly");
		
		document.getElementById("medicalStatus").removeAttribute("disabled");
		document.getElementById("passportNo").removeAttribute("readonly");
		document.getElementById("panCardNo").removeAttribute("readonly");
		document.getElementById("aadharCardNo").removeAttribute("readonly");
		document.getElementById("voterCardNo").removeAttribute("readonly");
		document.getElementById("votingConstituency").removeAttribute("readonly");
		document.getElementById("parliamentaryConstituency").removeAttribute("readonly");
		
		document.getElementById("mobile").removeAttribute("readonly");
		
		document.getElementById("emailId").removeAttribute("readonly");	
		
		document.getElementById("fatherFirstName").removeAttribute("readonly");
		
		document.getElementById("fatherMiddleName").removeAttribute("readonly");
		
		document.getElementById("fatherLastName").removeAttribute("readonly");
		//document.getElementById("fatherOccupation").removeAttribute("readonly");
		
		document.getElementById("motherFirstName").removeAttribute("readonly");
		
		document.getElementById("motherMiddleName").removeAttribute("readonly");
		
		document.getElementById("motherLastName").removeAttribute("readonly");
		//document.getElementById("motherOccupation").removeAttribute("readonly");
		
		
		
		document.getElementById("maritalStatus").removeAttribute("disabled");		
		document.getElementById("spouseName").removeAttribute("readonly");		
		
		document.getElementById("male").removeAttribute("disabled");
		document.getElementById("female").removeAttribute("disabled");
		

		document.getElementById("personalDetailsTableSubmit").setAttribute ("style", "visibility: block;");
		document.getElementById("personalDetailsTableCancel").setAttribute ("style", "visibility: block;");
		document.getElementById("personalDetailsTableEdit").setAttribute("style", "visibility: none;");																	
	};
	
	function makePersonalDetailsFieldInEditable()
	{
		document.getElementById("firstName").setAttribute("readonly", "readonly");
		document.getElementById("middleName").setAttribute("readonly", "readonly");
		document.getElementById("lastName").setAttribute("readonly", "readonly");
		document.getElementById("initialName").setAttribute("readonly", "readonly");
		document.getElementById("bloodGroup").setAttribute("disabled","disabled");
		document.getElementById("nationality").setAttribute("readonly", "readonly");
		document.getElementById("motherTongue").setAttribute("readonly", "readonly");
		document.getElementById("category").setAttribute("disabled","disabled");
		document.getElementById("religion").setAttribute("readonly", "readonly");		
		document.getElementById("medicalStatus").setAttribute("disabled","disabled");
		document.getElementById("passportNo").setAttribute("readonly", "readonly");
		document.getElementById("panCardNo").setAttribute("readonly", "readonly");
		document.getElementById("aadharCardNo").setAttribute("readonly", "readonly");
		document.getElementById("voterCardNo").setAttribute("readonly", "readonly");
		document.getElementById("votingConstituency").setAttribute("readonly", "readonly");
		document.getElementById("parliamentaryConstituency").setAttribute("readonly", "readonly");
		
		document.getElementById("fatherFirstName").setAttribute("readonly", "readonly");
		document.getElementById("fatherMiddleName").setAttribute("readonly", "readonly");
		document.getElementById("fatherLastName").setAttribute("readonly", "readonly");
	//	document.getElementById("fatherOccupation").setAttribute("readonly", "readonly");
		
		document.getElementById("motherFirstName").setAttribute("readonly", "readonly");
		document.getElementById("motherMiddleName").setAttribute("readonly", "readonly");
		document.getElementById("motherLastName").setAttribute("readonly", "readonly");
		//document.getElementById("motherOccupation").setAttribute("readonly", "readonly");
		
		document.getElementById("mobile").setAttribute("readonly", "readonly");
		document.getElementById("emailId").setAttribute("readonly", "readonly");	
		
		document.getElementById("maritalStatus").setAttribute("disabled","disabled");		
		document.getElementById("spouseName").setAttribute("readonly", "readonly");
		
		document.getElementById("male").setAttribute("disabled","disabled");
		document.getElementById("female").setAttribute("disabled","disabled");
		
		document.getElementById("personalDetailsTableSubmit").setAttribute("style", "visibility: none;");
		document.getElementById("personalDetailsTableCancel").setAttribute("style", "visibility: none;");
		document.getElementById("personalDetailsTableEdit").removeAttribute ("style", "visibility: block;");		
	};	
	
	function makeQualificationDetailsFieldEditable(){		
		document.getElementById("examName0").removeAttribute("readonly");
		document.getElementById("specialization0").removeAttribute("readonly");
		document.getElementById("schoolCollege0").removeAttribute("readonly");
		document.getElementById("boardUniversity0").removeAttribute("readonly");
		document.getElementById("marks0").removeAttribute("readonly");			
		document.getElementById("passingYear0").removeAttribute("readonly");
		document.getElementById("examName1").removeAttribute("readonly");
		document.getElementById("specialization1").removeAttribute("readonly");
		document.getElementById("schoolCollege1").removeAttribute("readonly");
		document.getElementById("boardUniversity1").removeAttribute("readonly");
		document.getElementById("marks1").removeAttribute("readonly");			
		document.getElementById("passingYear1").removeAttribute("readonly");
		document.getElementById("examName2").removeAttribute("readonly");
		document.getElementById("specialization2").removeAttribute("readonly");
		document.getElementById("schoolCollege2").removeAttribute("readonly");
		document.getElementById("boardUniversity2").removeAttribute("readonly");
		document.getElementById("marks2").removeAttribute("readonly");			
		document.getElementById("passingYear2").removeAttribute("readonly");
		document.getElementById("examName3").removeAttribute("readonly");
		document.getElementById("specialization3").removeAttribute("readonly");
		document.getElementById("schoolCollege3").removeAttribute("readonly");
		document.getElementById("boardUniversity3").removeAttribute("readonly");
		document.getElementById("marks3").removeAttribute("readonly");
		document.getElementById("passingYear3").removeAttribute("readonly");
		
		var input = document.getElementsByName("othersExamName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("othersSpecialization");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("othersSchoolCollege");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("othersBoardUniversity");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("othersMarks");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("othersPassingYear");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("addOtherQualificationDetails").style.display = "block";
		
		document.getElementById("qualificationDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("qualificationDetailsTableCancel").removeAttribute ("style");
		document.getElementById("qualificationDetailsTableEdit").setAttribute("style", "visibility: collapse;");		
	}
	
	
	function makeQualificationDetailsFieldInEditable(){			
		document.getElementById("examName0").setAttribute("readonly", "readonly");
		document.getElementById("specialization0").setAttribute("readonly", "readonly");
		document.getElementById("schoolCollege0").setAttribute("readonly", "readonly");
		document.getElementById("boardUniversity0").setAttribute("readonly", "readonly");
		document.getElementById("marks0").setAttribute("readonly", "readonly");
		document.getElementById("passingYear0").setAttribute("readonly", "readonly");
		
		document.getElementById("examName1").setAttribute("readonly", "readonly");
		document.getElementById("specialization1").setAttribute("readonly", "readonly");
		document.getElementById("schoolCollege1").setAttribute("readonly", "readonly");
		document.getElementById("boardUniversity1").setAttribute("readonly", "readonly");
		document.getElementById("marks1").setAttribute("readonly", "readonly");
		document.getElementById("passingYear1").setAttribute("readonly", "readonly");
		
		document.getElementById("examName2").setAttribute("readonly", "readonly");
		document.getElementById("specialization2").setAttribute("readonly", "readonly");
		document.getElementById("schoolCollege2").setAttribute("readonly", "readonly");
		document.getElementById("boardUniversity2").setAttribute("readonly", "readonly");
		document.getElementById("marks2").setAttribute("readonly", "readonly");
		document.getElementById("passingYear2").setAttribute("readonly", "readonly");
		
		document.getElementById("examName3").setAttribute("readonly", "readonly");
		document.getElementById("specialization3").setAttribute("readonly", "readonly");
		document.getElementById("schoolCollege3").setAttribute("readonly", "readonly");
		document.getElementById("boardUniversity3").setAttribute("readonly", "readonly");
		document.getElementById("marks3").setAttribute("readonly", "readonly");
		document.getElementById("passingYear3").setAttribute("readonly", "readonly");	
		
		var input = document.getElementsByName("othersExamName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		var input = document.getElementsByName("othersSpecialization");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		var input = document.getElementsByName("othersSchoolCollege");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		var input = document.getElementsByName("othersBoardUniversity");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		var input = document.getElementsByName("othersMarks");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		var input = document.getElementsByName("othersPassingYear");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		document.getElementById("addOtherQualificationDetails").style.display = "none";
		
		document.getElementById("qualificationDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("qualificationDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("qualificationDetailsTableEdit").removeAttribute ("style");		
		
	}
	
	function makeAddressDetailsFieldEditable()
	{
		document.getElementById("presentAddressLine").removeAttribute("readonly");
		document.getElementById("presentAddressLandmark").removeAttribute("readonly");
		document.getElementById("presentAddressCityVillage").removeAttribute("readonly");
		//document.getElementById("presentAddressRailwayStation").removeAttribute("readonly");
		document.getElementById("presentAddressPostOffice").removeAttribute("readonly");
		document.getElementById("presentAddressPoliceStation").removeAttribute("readonly");
		document.getElementById("presentAddressPinCode").removeAttribute("readonly");
		document.getElementById("presentAddressDistrict").removeAttribute("readonly");		
		document.getElementById("presentAddressState").removeAttribute("disabled");
		document.getElementById("presentAddressCountry").removeAttribute("disabled");
		//document.getElementById("presentAddressPhone").removeAttribute("readonly");
		
		document.getElementById("permanentAddressLine").removeAttribute("readonly");
		document.getElementById("permanentAddressLandmark").removeAttribute("readonly");
		document.getElementById("permanentAddressCityVillage").removeAttribute("readonly");
		//document.getElementById("permanentAddressRailwayStation").removeAttribute("readonly");
		document.getElementById("permanentAddressPostOffice").removeAttribute("readonly");
		document.getElementById("permanentAddressPoliceStation").removeAttribute("readonly");
		document.getElementById("permanentAddressPinCode").removeAttribute("readonly");
		document.getElementById("permanentAddressDistrict").removeAttribute("readonly");
		document.getElementById("permanentAddressState").removeAttribute("disabled");
		document.getElementById("permanentAddressCountry").removeAttribute("disabled");
		//document.getElementById("permanentAddressPhone").removeAttribute("readonly");
		
		document.getElementById("addressDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("addressDetailsTableCancel").removeAttribute ("style");
		document.getElementById("addressDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeAddressDetailsFieldInEditable()
	{
		document.getElementById("presentAddressLine").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressLandmark").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressCityVillage").setAttribute("readonly", "readonly");
		//document.getElementById("presentAddressRailwayStation").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressPostOffice").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressPoliceStation").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressPinCode").setAttribute("readonly", "readonly");
		document.getElementById("presentAddressDistrict").setAttribute("readonly", "readonly");		
		document.getElementById("presentAddressState").setAttribute("disabled","disabled");	
		document.getElementById("presentAddressCountry").setAttribute("disabled","disabled");	
		//document.getElementById("presentAddressPhone").setAttribute("readonly", "readonly");	
		
		document.getElementById("permanentAddressLine").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressLandmark").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressCityVillage").setAttribute("readonly", "readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressPostOffice").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressPoliceStation").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressPinCode").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressDistrict").setAttribute("readonly", "readonly");
		document.getElementById("permanentAddressState").setAttribute("disabled","disabled");	
		document.getElementById("permanentAddressCountry").setAttribute("disabled","disabled");	
		//document.getElementById("permanentAddressPhone").setAttribute("readonly", "readonly");
		
		document.getElementById("addressDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("addressDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("addressDetailsTableEdit").removeAttribute ("style");
	};
	
	
	function makeEmployeeDependentsDetailsFieldEditable()
	{
		var input = document.getElementsByName("childName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("childGender");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("childDateOfBirth");	
		for(var i=0;i<input.length;i++)
		{			
			input[i].removeAttribute("readonly");
		}	
		
		document.getElementById("addChilOfEmployee").style.display = "block";
		document.getElementById("employeeDependentsDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeDependentsDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeDependentsDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeeDependentsDetailsFieldInEditable()
	{
		var input = document.getElementsByName("childName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("childGender");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("disabled","disabled");
		}
		
		var input = document.getElementsByName("childDateOfBirth");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("disabled","disabled");
		}		
		document.getElementById("addChilOfEmployee").style.display = "none";
		document.getElementById("employeeDependentsDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeDependentsDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeDependentsDetailsTableEdit").removeAttribute ("style");
	};
	
	function makeEmployeeNomineeDetailsFieldEditable()
	{	
		var input = document.getElementsByName("nomineeName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("relationship");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("nomineePercent");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("addNominee").style.display = "block";
		
		document.getElementById("employeeNomineeDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeNomineeDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeNomineeDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeeNomineeDetailsFieldInEditable()
	{		
		var input = document.getElementsByName("nomineeName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("relationship");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("nomineePercent");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		document.getElementById("addNominee").style.display = "none";
		
		document.getElementById("employeeNomineeDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeNomineeDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeNomineeDetailsTableEdit").removeAttribute ("style");
	};
	
	
	
	
	function makeEmployeeBankDetailsFieldEditable()
	{
		document.getElementById("bankName").removeAttribute("readonly");
		document.getElementById("branchCode").removeAttribute("readonly");	
		document.getElementById("accountType").removeAttribute("readonly");			
		document.getElementById("accountHolderName").removeAttribute("readonly");
		document.getElementById("accountNumber").removeAttribute("readonly");
		document.getElementById("branchIFSCCode").removeAttribute("readonly");
		
		
		document.getElementById("employeeBankDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeBankDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeBankDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeeBankDetailsFieldInEditable()
	{
		document.getElementById("bankName").setAttribute("readonly","readonly");		
		document.getElementById("branchCode").setAttribute("readonly","readonly");		
		document.getElementById("accountType").setAttribute("readonly","readonly");
		document.getElementById("accountHolderName").setAttribute("readonly","readonly");	
		document.getElementById("accountNumber").setAttribute("readonly","readonly");	
		document.getElementById("branchIFSCCode").setAttribute("readonly","readonly");
		
		
		document.getElementById("employeeBankDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeBankDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeBankDetailsTableEdit").removeAttribute ("style");
	};
	
	
	function makeEmployeePreviousWorkDetailsFieldEditable()
	{		
		var input = document.getElementsByName("organizationName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("fromDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("toDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("organizationContactNo");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("organizationWebSite");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("staffPreviousWorkDetailsButton").style.display = "block";
		document.getElementById("experienceRelatedFile").style.display = "block";
		document.getElementById("addFile2").style.display = "block";
		
		document.getElementById("employeePreviousWorkDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeePreviousWorkDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeePreviousWorkDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeePreviousWorkDetailsFieldInEditable()
	{
		var input = document.getElementsByName("organizationName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("fromDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("toDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("organizationContactNo");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("organizationWebSite");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		document.getElementById("staffPreviousWorkDetailsButton").style.display = "none";
		document.getElementById("experienceRelatedFile").style.display = "none";
		document.getElementById("addFile2").style.display = "none";
		
		document.getElementById("employeePreviousWorkDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeePreviousWorkDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeePreviousWorkDetailsTableEdit").removeAttribute ("style");
	};
	
	
	function makeEmployeeWorkShopAndTrainingDetailsFieldEditable()
	{	
		var input = document.getElementsByName("subject");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("venue");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("trainingFromDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		var input = document.getElementsByName("trainingToDate");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("organizedBy");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("duration");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("addMoreWorkShopButton").style.display ="block";
		
		document.getElementById("employeeWorkShopAndTrainingDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeWorkShopAndTrainingDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeWorkShopAndTrainingDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeeWorkShopAndTrainingDetailsFieldInEditable()
	{		
		var input = document.getElementsByName("subject");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("venue");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("organizedBy");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("duration");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		document.getElementById("addMoreWorkShopButton").style.display ="none";
		
		document.getElementById("employeeWorkShopAndTrainingDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeWorkShopAndTrainingDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeWorkShopAndTrainingDetailsTableEdit").removeAttribute ("style");
	};
	
	
	function makeEmployeeAwardsAndRecognizationDetailsFieldEditable()
	{	
		var input = document.getElementsByName("awardName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("presentedBy");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("presentedOn");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("addAwardAndRecognition").style.display ="block";
		
		document.getElementById("employeeAwardsAndRecognizationDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeAwardsAndRecognizationDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeAwardsAndRecognizationDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeeAwardsAndRecognizationDetailsFieldInEditable()
	{		
		var input = document.getElementsByName("awardName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("presentedBy");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("presentedOn");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		document.getElementById("addAwardAndRecognition").style.display ="none";
		
		document.getElementById("employeeAwardsAndRecognizationDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeAwardsAndRecognizationDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeAwardsAndRecognizationDetailsTableEdit").removeAttribute ("style");
	};
	
	function makeEmployeePublicationDetailsFieldEditable()
	{	
		
		var input = document.getElementsByName("publicationName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("dateOfPublication");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("coPublisher");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		var input = document.getElementsByName("publicationDesc");	
		for(var i=0;i<input.length;i++)
		{
			input[i].removeAttribute("readonly");
		}
		
		document.getElementById("addPublicationsDetailsButton").style.display ="block";
		document.getElementById("addFile3").style.display ="block";
		document.getElementById("publicationRelatedFile").style.display ="block";
		
		document.getElementById("employeePublicationDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeePublicationDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeePublicationDetailsTableEdit").setAttribute("style", "visibility: collapse;");																	
	};
	
	function makeEmployeePublicationDetailsFieldInEditable()
	{
		var input = document.getElementsByName("publicationName");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("dateOfPublication");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("coPublisher");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		var input = document.getElementsByName("publicationDesc");	
		for(var i=0;i<input.length;i++)
		{
			input[i].setAttribute("readonly", "readonly");
		}
		
		document.getElementById("addPublicationsDetailsButton").style.display ="none";
		document.getElementById("addFile3").style.display ="none";
		document.getElementById("publicationRelatedFile").style.display ="none";
		
		document.getElementById("employeePublicationDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeePublicationDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeePublicationDetailsTableEdit").removeAttribute ("style");
	};
	
	function makeEmployeeConfidentialFieldEditable()
	{	
		document.getElementById("confidentialInformation").removeAttribute("readonly");
		document.getElementById("employeeConfidentialDetailsTableSubmit").removeAttribute ("style");
		document.getElementById("employeeConfidentialDetailsTableCancel").removeAttribute ("style");
		document.getElementById("employeeConfidentialDetailsTableEdit").setAttribute("style", "visibility: collapse;");													
	};
	
	function makeEmployeeConfidentialDetailsFieldInEditable()
	{
		document.getElementById("confidentialInformation").setAttribute("disabled","disabled");		
		document.getElementById("employeeConfidentialDetailsTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeConfidentialDetailsTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeConfidentialDetailsTableEdit").removeAttribute ("style");
	};
	
	function makeEmployeeImageEditable()
	{	
		document.getElementById("image_upload").removeAttribute("disabled");
		document.getElementById("employeeImageTableSubmit").removeAttribute ("style");
		document.getElementById("employeeImageTableCancel").removeAttribute ("style");
		document.getElementById("employeeImageTableEdit").setAttribute("style", "visibility: collapse;");													
	};
	
	function makeEmployeeImageInEditable()
	{
		document.getElementById("image_upload").setAttribute("disabled","disabled");		
		document.getElementById("employeeImageTableSubmit").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeImageTableCancel").setAttribute("style", "visibility: collapse;");
		document.getElementById("employeeImageTableEdit").removeAttribute ("style");
	};
	
	
	