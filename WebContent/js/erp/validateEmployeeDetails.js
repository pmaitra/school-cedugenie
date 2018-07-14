

var obj,val;
var intRegx=/^[0-9]{1,}$/;
var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
var mobileRegx = /^\d{10,11}$/;
var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
var alphaNumericWithoutSpace=/^[A-Za-z0-9]{1,50}$/;
var alphabetic=/^[A-Za-z ]{1,50}$/;
var userIdOnly=/^[A-Za-z.]{1,50}$/;
var alphabeticWithoutSpace=/^[A-Za-z]{1,50}$/;
var email1=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{2,3})+\.+([a-z0-9_-]{2,3})$/;
var email2=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{3})$/;
var addressRegx=/^[A-Za-z0-9().#-\/ \\]{1,50}$/;
var pinRegx=/^[0-9]{6}$/;
var ran = /^[A-Za-z ]{1,}$/;
var allowDot = /^[A-Za-z0-9_.-]{1,}$/;
var staffCodeRegx = /^[-A-Z0-9//]{1,}$/;
var year =  /^\s*-?[0-9]{4}\s*$/;
var ifscRegx=/^[A-Za-z0-9 ]{11}$/;

function validateEmployeeBasicDetails(){	
	obj=document.getElementById("userId");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(allowDot)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid User Id";
		alert("Invalid User Id");
		return false;
	}
	
	obj=document.getElementById("employeeCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(staffCodeRegx)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Employee Code";
		alert("Invalid Employee Code");
		return false;
	}
	
	obj=document.getElementById("dateOfBirth");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Date Of Birth";
		alert("Select Date Of Birth");
		return false;
	}		
	
	obj=document.getElementById("dateOfJoining");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Date Of Joining";
		alert("Select Date Of Joining");
		return false;
	}
	
	obj=document.getElementById("employeeTypeName");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Employee Type";
		alert("Select Employee Type");
		return false;
	}
	
	obj=document.getElementById("jobTypeName");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Job Type";
		alert("Select Job Type");
		return false;
	}
	
	obj=document.getElementById("designationName");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Designation";
		alert("Select Designation");
		return false;
	}
	
	obj=document.getElementById("department");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Department";
		alert("Select Department");
		return false;
	}	
	
	obj=document.getElementById("qualificationSummary");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val==""){		
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Qualification Summary";
		alert("Invalid Qualification Summary");
			return false;
		}
	return true;	
}

function validateEmployeePersonalDetails(){		
	obj=document.getElementById("firstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid First Name";
		alert("Invalid First Name");
		return false;
	}
	
	obj=document.getElementById("middleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Middle Name";
			alert("Invalid Middle Name");
			return false;
		}
	}
	
	obj=document.getElementById("lastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Last Name";
		alert("Invalid Last Name");
		return false;
	}	
	
	obj=document.getElementById("category");
	val=obj.value;
	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Category.";
		alert("Select Category.");
		return false;
	}
	
	obj=document.getElementById("votingConstituency");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Voting Constituency Number";
			return false;
		}
	}
	
	obj=document.getElementById("religion");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Religion";
		alert("Invalid Religion");
		return false;
	}
	
	obj=document.getElementById("nationality");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Nationality";
		alert("Invalid Nationality");
		return false;
	}
	
	obj=document.getElementById("motherTongue");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
	if(!val.match(alphabetic)){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Invalid Mother Tongue";
		alert("Invalid Mother Tongue");
		return false;
		}
	}
	
	obj=document.getElementById("parliamentaryConstituency");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Parliamentary Constituency Number";
			return false;
		}
	}
	
	obj=document.getElementById("passportNo");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Passport Number";
			return false;
		}
	}
	
	obj=document.getElementById("panCardNo");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Pan Number";
			return false;
		}
	}
	
	obj=document.getElementById("aadharCardNo");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Aadhar Card Number";
			return false;
		}
	}
	
	obj=document.getElementById("voterCardNo");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Voter Card Number";
			return false;
		}
	}
	
	obj=document.getElementById("fatherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Father's First Name";
			return false;
		}
	}
	
	obj=document.getElementById("fatherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Father's Middle Name";
			return false;
		}
	}
	
	obj=document.getElementById("fatherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Father's Last Name";
			return false;
		}
	}
	
	obj=document.getElementById("motherFirstName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's First";
			return false;
		}
	}
	
	obj=document.getElementById("motherMiddleName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Middle Name";
			return false;
		}
	}
	
	obj=document.getElementById("motherLastName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Mother's Last Name";
			return false;
		}
	}
	
	obj=document.getElementById("maritalStatus");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Marital Status";
		return false;
	}
	obj=document.getElementById("spouseName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	maritalStatusValue = document.getElementById("maritalStatus");
	if(maritalStatusValue.value == 'MARRIED'){
		if(!val.match(alphabetic)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid spouse Name";
			return false;
		}
	}
	if(maritalStatusValue.value == 'UNMARRIED'){
		obj.value="";
	}
	alert("one all right:");
    return true;
}

function validateEmployeeQualificationDetails(){
	
	// Class 10
	secondaryExamName = document.getElementById("examName0").value;	
	secondaryExamName = secondaryExamName.replace(/\s{1,}/g, '');
//	if (secondaryExamName == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 10 Exam Name";
//		return false;
//	}
	if (secondaryExamName != '') {
		if (!secondaryExamName.match(allowDot)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Class 10 Exam Name";
			alert("Invalid Class 10 Exam Name");
			return false;
		}
	}

	secondarySpecialization = document.getElementById("specialization0").value;
	secondarySpecialization = secondarySpecialization.replace(/\s{1,}/g, '');		
	if (secondarySpecialization != '') {
		if (!secondarySpecialization.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 10 specialization";
			alert("Invalid Class 10 specialization");
			return false;
		}
	}	
	
	secondarySchoolCollege = document.getElementById("schoolCollege0").value;
	secondarySchoolCollege = secondarySchoolCollege.replace(/\s{1,}/g, '');
//	if (secondarySchoolCollege == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 10 School Name";
//		return false;
//	}
	if (secondarySchoolCollege != '') {
		if (!secondarySchoolCollege.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 10 School Name";
			alert("Invalid Class 10 School Name");
			return false;
		}
	}
	
	secondaryBoardUniversity = document.getElementById("boardUniversity0").value;
	secondaryBoardUniversity = secondaryBoardUniversity.replace(/\s{1,}/g, '');
//	if (secondaryBoardUniversity == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 10 Board";
//		return false;
//	}
	if (secondaryBoardUniversity != '') {
		if (!secondaryBoardUniversity.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 10 Board";
			alert("Invalid Class 10 Board");
			return false;
		}
	}	

	secondaryMarks = document.getElementById("marks0").value;
	secondaryMarks = secondaryMarks.replace(/\s{1,}/g, '');
//	if (secondaryMarks == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 10 Marks";
//		return false;
//	}
	if (secondaryMarks != '') {
		if (isNaN(secondaryMarks) || (secondaryMarks) > 100 || (secondaryMarks) < 0) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Marks";
			alert("Please Enter Valid Marks");
			return false;
		}
	}

	secondaryPassingYear = document.getElementById("passingYear0").value;
	secondaryPassingYear = secondaryPassingYear.replace(/\s{1,}/g, '');
//	if (secondaryPassingYear == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Please Enter Class 10 Passing Year";
//		return false;
//	}
	if (secondaryPassingYear != '') {
		if (isNaN(secondaryPassingYear) || (secondaryPassingYear) > 2200 || (secondaryPassingYear) < 1900) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Please Enter Valid Year Of Passing";
			alert("Please Enter Valid Year Of Passing");
			return false;
		}
	}
	
	if (secondaryPassingYear != '') {
		if (!secondaryPassingYear.match(year)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Year Of Passing";
			alert("Please Enter Valid Year Of Passing");
			return false;
		}
	}
	
	// Class 12
	higherSecondaryExamName = document.getElementById("examName1").value;
	higherSecondaryExamName = higherSecondaryExamName.replace(/\s{1,}/g, '');
//	if (higherSecondaryExamName == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Please Enter Class 12 Exam Name";
//		return false;
//	}
	if (higherSecondaryExamName != '') {
		if (!higherSecondaryExamName.match(allowDot)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 12 Exam Name";
			alert("Invalid Class 12 Exam Name");			
			return false;
		}
	}

	higherSecondarySpecialization = document.getElementById("specialization1").value;
	higherSecondarySpecialization = higherSecondarySpecialization.replace(/\s{1,}/g, '');
//	if (higherSecondarySpecialization == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 12 Subject specialization";
//		return false;
//	}
	if (higherSecondarySpecialization != '') {
		if (!higherSecondarySpecialization.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 12 specialization";
			alert("Invalid Class 12 specialization");			
			return false;
		}
	}

	higherSecondarySchoolCollege = document.getElementById("schoolCollege1").value;
	higherSecondarySchoolCollege = higherSecondarySchoolCollege.replace(/\s{1,}/g, '');
//	if (higherSecondarySchoolCollege == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 12 School Name";
//		return false;
//	}
	if (higherSecondarySchoolCollege != '') {
		if (!higherSecondarySchoolCollege.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Class 12 School Name";
			alert("Invalid Class 12 School Name");
			return false;
		}
	}
	
	higherSecondaryBoardUniversity = document.getElementById("boardUniversity1").value;
	higherSecondaryBoardUniversity = higherSecondaryBoardUniversity.replace(/\s{1,}/g, '');
//	if (higherSecondaryBoardUniversity == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 12 Board";
//		return false;
//	}
	if (higherSecondaryBoardUniversity != '') {
		if (!higherSecondaryBoardUniversity.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Class 12 Board";
			alert("Invalid Class 12 Board");
			return false;
		}
	}

	higherSecondaryMarks = document.getElementById("marks1").value;
	higherSecondaryMarks = higherSecondaryMarks.replace(/\s{1,}/g, '');
//	if (higherSecondaryMarks == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 12 Marks";
//		return false;
//	}
	if (higherSecondaryMarks != '') {
		if (isNaN(higherSecondaryMarks) || (higherSecondaryMarks) >= 100 || (higherSecondaryMarks) <= 0) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Class12 Marks";
			alert("Please Enter Valid Class12 Marks");
			return false;
		}
	}

	higherSecondaryPassingYear = document.getElementById("passingYear1").value;
	higherSecondaryPassingYear = higherSecondaryPassingYear.replace(/\s{1,}/g, '');
//	if (higherSecondaryPassingYear == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Class 12 Passing Year";
//		return false;
//	}
	if (higherSecondaryPassingYear != '') {
		if (isNaN(higherSecondaryPassingYear) || (higherSecondaryPassingYear) >= 2200
				|| (higherSecondaryPassingYear) <= 1900) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Year Of Passing";
			alert( "Please Enter Valid Year Of Passing");
			return false;
		}
	}
	
	//graduation
	
	graduationExamName = document.getElementById("examName2").value;
	graduationExamName = graduationExamName.replace(/\s{1,}/g, '');
//	if (graduationExamName == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Please Enter Graduation Exam Name";
//		return false;
//	}
	if (graduationExamName != '') {
		if (!graduationExamName.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Graduation Exam Name";
			alert("Invalid Graduation Exam Name");
			return false;
		}
	}
	
	graduationSpecialization = document.getElementById("specialization2").value;
	graduationSpecialization = graduationSpecialization.replace(/\s{1,}/g, '');
//	if (graduationSpecialization == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Please Enter Graduation Subject Specialization";
//		return false;
//	}
	if (graduationSpecialization != '') {
		if (!graduationSpecialization.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Graduation Subject specialization";
			alert("Invalid Graduation Subject specialization");
			return false;
		}
	}
	
	graduationSchoolCollege = document.getElementById("schoolCollege2").value;
	graduationSchoolCollege = graduationSchoolCollege.replace(/\s{1,}/g, '');
//	if (graduationSchoolCollege == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter College Name";
//		return false;
//	}
	if (graduationSchoolCollege != '') {
		if (!graduationSchoolCollege.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid College Name";
			alert("Invalid College Name");
			return false;
		}
	}
	graduationBoardUniversity = document.getElementById("boardUniversity2").value;
	graduationBoardUniversity = graduationBoardUniversity.replace(/\s{1,}/g, '');
//	if (graduationBoardUniversity == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter University Name";
//		return false;
//	}
	if (graduationBoardUniversity != '') {
		if (!graduationBoardUniversity.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid University Name";
			alert("Invalid University Name");
			return false;
		}
	}
	
	graduationMarks = document.getElementById("marks2").value;
	graduationMarks = graduationMarks.replace(/\s{1,}/g, '');
//	if (graduationMarks == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Graduation Marks";
//		return false;
//	}
	if (graduationMarks != '') {
		if (isNaN(graduationMarks) || (graduationMarks) >= 100 || (graduationMarks) <= 0) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Marks";
			alert("Please Enter Valid Marks");
			return false;
		}
	}	

	graduationPassingYear = document.getElementById("passingYear2").value;
	graduationPassingYear = graduationPassingYear.replace(/\s{1,}/g, '');
//	if (graduationPassingYear == "") {
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML =  "Please Enter Graduation Passing Year";
//		return false;
//	}
	if (graduationPassingYear != '') {
		if (isNaN(graduationPassingYear) || (graduationPassingYear) >= 2200	|| (graduationPassingYear) <= 1900) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Year Of Passing";
			alert("Please Enter Valid Year Of Passing");
			return false;
		}
	}
	
	// Post Graduation
	
	postGraduationExamName = document.getElementById("examName3").value;
	postGraduationExamName = postGraduationExamName.replace(/\s{1,}/g, '');	
	if (postGraduationExamName != '') {
		if (!postGraduationExamName.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Post Graduation Exam Name";
			alert("Invalid Post Graduation Exam Name");
			return false;
		}
	}
	
	postGraduationSpecialization = document.getElementById("specialization3").value;
	postGraduationSpecialization = postGraduationSpecialization.replace(/\s{1,}/g, '');	
	if (postGraduationSpecialization != '') {
		if (!postGraduationSpecialization.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Post Graduation Subject specialization";
			alert("Invalid Post Graduation Subject specialization");
			return false;
		}
	}
	
	postGraduationSchoolCollege = document.getElementById("schoolCollege3").value;
	postGraduationSchoolCollege = postGraduationSchoolCollege.replace(/\s{1,}/g, '');	
	if (postGraduationSchoolCollege != '') {
		if (!postGraduationSchoolCollege.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid College Name";
			alert("Invalid College Name");
			return false;
		}
	}	
	
	postGraduationBoardUniversity = document.getElementById("boardUniversity3").value;
	postGraduationBoardUniversity = postGraduationBoardUniversity.replace(/\s{1,}/g, '');	
	if (postGraduationBoardUniversity != '') {
		if (!postGraduationBoardUniversity.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Invalid Post University Name";
			alert("Invalid Post University Name");
			return false;
		}
	}
	
	postGraduationMarks = document.getElementById("marks3").value;
	postGraduationMarks = postGraduationMarks.replace(/\s{1,}/g, '');
	if (postGraduationMarks != '') {
		if (isNaN(postGraduationMarks) || (postGraduationMarks) >= 100 || (postGraduationMarks) <= 0) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Enter Valid Marks";
			alert("Please Enter Valid Marks");
			return false;
		}
	}
	
	postGraduationPassingYear = document.getElementById("passingYear3").value;
	postGraduationPassingYear = postGraduationPassingYear.replace(/\s{1,}/g, '');	
	if (postGraduationPassingYear != '') {
		if (isNaN(postGraduationPassingYear)
				|| (postGraduationPassingYear) >= 2200
				|| (postGraduationPassingYear) <= 1900) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Post Graduation Passing Year";
			alert("Invalid Post Graduation Passing Year");
			return false;
		}
	}
	
	//Others	
	othersExamName = document.getElementsByName("othersExamName");
	for ( var i = 0; i < othersExamName.length; i++) {
		if (othersExamName[i].value != '') {
			if (!othersExamName[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid Exam Name";
				alert("Invalid Exam Name");
				return false;
			};
		};
	}	

	othersSpecialization = document.getElementsByName("othersSpecialization");
	for ( var i = 0; i < othersSpecialization.length; i++) {

		if (othersSpecialization[i].value != '') {
			if (!othersSpecialization[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML =  "Invalid Specilization";
				alert("Invalid Specilization");
				return false;
			};
		};
	}	
	
	othersSchoolCollege = document.getElementsByName("othersSchoolCollege");
	for ( var i = 0; i < othersSchoolCollege.length; i++) {

		if (othersSchoolCollege[i].value != '') {
			if (!othersSchoolCollege[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML =  "Invalid Organization";
				alert("Invalid Organization");
				return false;
			};
		};
	}
	
	othersBoardUniversity = document.getElementsByName("othersBoardUniversity");
	for ( var i = 0; i < othersBoardUniversity.length; i++) {

		if (othersBoardUniversity[i].value != '') {
			if (!othersBoardUniversity[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid University";
				alert("Invalid University");
				return false;
			};
		};
	}

	othersMarks = document.getElementsByName("othersMarks");
	for ( var i = 0; i < othersMarks.length; i++) {
		if (othersMarks[i].value != '') {
			if (isNaN(othersMarks[i].value) || (othersMarks[i].value) >= 100 || (othersMarks[i].value) <= 0) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Please Enter Valid Marks";
				alert("Please Enter Valid Marks");
				return false;
			};
		};
	}

	othersPassingYear = document.getElementsByName("othersPassingYear");
	for ( var i = 0; i < othersPassingYear.length; i++) {

		if (othersPassingYear[i].value != '') {
			if (isNaN(othersPassingYear[i].value)
					|| (othersPassingYear[i].value) >= 2200
					|| (othersPassingYear[i].value) <= 1900) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Please Enter Valid Year Of Passing";
				alert("Please Enter Valid Year Of Passing");
				return false;
			};
		};
	}		
	return true;	
}

function validateEmployeeAddress(){
	obj=document.getElementById("presentAddressLine");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address";
			alert("Invalid Correspondence Address");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Landmark";
			alert("Invalid Correspondence Address Landmark");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address City/Village.";
			alert("Invalid Correspondence Address City/Village");
			return false;
		}
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
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Post Office.";
			alert("Invalid Correspondence Address Post Office");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Police Station";
			alert("Invalid Correspondence Address Post Office");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(pinRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address Pin Code";
			alert("Invalid Correspondence Address Pin Code");
			return false;
		}
	}
	
	obj=document.getElementById("presentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Correspondence Address District";
			alert("Invalid Correspondence Address District");
			return false;
		}
	}
	
	
	
//	obj=document.getElementById("presentAddressState");
//	val=obj.value;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Correspondence Address State.";
//		return false;
//	}
	
//	obj=document.getElementById("presentAddressCountry");
//	val=obj.value;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Correspondence Address Country.";
//		return false;
//	}
	
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
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address";
			alert("Invalid Permanent Address");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressLandmark");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Landmark";
			alert("Invalid Permanent Address Landmark");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressCityVillage");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(addressRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address City/Village";
			alert("Invalid Permanent Address City/Village");
			return false;
		}
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
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Post Office";
			alert("Invalid Permanent Address Post Office");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressPoliceStation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Police Station";
			alert("Invalid Permanent Address Police Station");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressPinCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if(val!=""){
		if(!val.match(pinRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Pin Code";
			alert("Invalid Permanent Address Pin Code");
			return false;
		}
	}
	
	obj=document.getElementById("permanentAddressDistrict");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address District";
			alert("Invalid Permanent Address District");
			return false;
		}
	}
	
//	obj=document.getElementById("permanentAddressState");
//	val=obj.value;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Permanent Address State.";
//		return false;
//	}
	
//	obj=document.getElementById("permanentAddressCountry");
//	val=obj.value;
//	if(val==""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Select Permanent Address Country";
//		return false;
//	}
	
//	obj=document.getElementById("permanentAddressPhone");
//	val=obj.value;
//	val=val.replace(/\s{1,}/g,'');
//	obj.value=val;
//	if(val!=""){
//		if(!val.match(intRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Permanent Address Phone";
//			return false;
//		}
//	}		
	
	return true;
}

function validateEmployeeDependentsDetails(){
	
	var employeeChildName = document.getElementsByName("childName");
	for ( var i = 0; i < employeeChildName.length; i++) {
		if (employeeChildName[i].value != '') {
			if (!employeeChildName[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Child Name";
				alert("Invalid Child Name");
				return false;
			};
		};
	}	
	
//	var employeeChildDOB = document.getElementsByName("childDateOfBirth");
//	for ( var i = 0; i < employeeChildDOB.length; i++) {
//		if (employeeChildDOB[i].value == "") {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Enter Child's Date Of Birth";
//				return false;			
//		}
//	}	
	return true;
}

function validateNomineeDetails(){
	
	var nomineeName = document.getElementsByName("nomineeName");
	for ( var i = 0; i < nomineeName.length; i++) {
		if (nomineeName[i].value != '') {
			if (!nomineeName[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Nominee Name";
				alert("Invalid Nominee Name");
				return false;
			};
		};
	}	
	
	var relationship = document.getElementsByName("relationship");
	for ( var i = 0; i < relationship.length; i++) {
		if (relationship[i].value != '') {
			if (!relationship[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Relatioship";
				alert("Invalid Relatioship");
				return false;
			};
		};
	}
	
	var nomineePercent = document.getElementsByName("nomineePercent");
	for ( var i = 0; i < nomineePercent.length; i++) {
		if (nomineePercent[i].value != '') {
			if (!nomineePercent[i].value.match(intRegx)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Percentage";
				alert("Invalid Percentage");
				return false;
			};
		};
	}
	return true;
}

function validateEmployeeBankDetails(){
	obj=document.getElementById("bankName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Bank Name";
			alert("Invalid Bank Name");
			return false;
		}
	}
	
	obj=document.getElementById("  ");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Branch Code";
			alert("Invalid Branch Code");
			return false;
		}
	}
	
	obj=document.getElementById("accountType");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Account Type";
			alert("Invalid Account Type");
			return false;
		}
	}
	
	obj=document.getElementById("accountHolderName");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Account Holder Name";
			alert("Invalid Account Holder Name");
			return false;
		}
	}
	
	
	obj=document.getElementById("branchIFSCCode");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(ifscRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="IFSC Code should be 11 digit. Please Check it";
			alert("IFSC Code should be 11 digit. Please Check it");
			return false;
		}
	}
	
	
	obj=document.getElementById("accountNumber");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(intRegx)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Invalid Account Number";
			alert("Invalid Account Number");
			return false;
		}
	}
	return true;
}

function validateEmployeeWorkDetails(){
	var organizationName = document.getElementsByName("organizationName");
	for ( var i = 0; i < organizationName.length; i++) {
		if (organizationName[i].value != '') {
			if (!organizationName[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid Organization Name ";
				alert("Invalid Organization Name ");
				return false;
			};
		};
	}
	
	var fromDate = document.getElementsByName("fromDate");
	for ( var i = 0; i < fromDate.length; i++) {
		if (fromDate[i].value != '') {
			if (!fromDate[i].value.match(staffCodeRegx)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid From Date";
				alert("Invalid From Date");
				return false;
			};
		};
	}
	
	var toDate = document.getElementsByName("toDate");
	for ( var i = 0; i < toDate.length; i++) {
		if (toDate[i].value != '') {
			if (!toDate[i].value.match(staffCodeRegx)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid To Date";
				alert("Invalid To Date");
				return false;
			};
		};
	}
	
	var organizationContactNo = document.getElementsByName("organizationContactNo");
	for ( var i = 0; i < organizationContactNo.length; i++) {
		if (organizationContactNo[i].value != '') {
			if(!val.match(intRegx)){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Invalid Organization Phone";
				alert("Invalid Organization Phone");
				return false;
			};
		};
	}	
	
	organizationWebSite = document.getElementById("organizationWebSite").value;
	organizationWebSite = organizationWebSite.replace(/^\s+|\s+$/g, '');
	if (organizationWebSite != '') {
		if (!organizationWebSite.match(allowDot)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid web site";
			alert("Invalid web site");
			return false;
		};
	}	
	return true;	
}

function validateWorkShopAndTrainingDetails(){
	
	var subject = document.getElementsByName("subject");
	for ( var i = 0; i < subject.length; i++) {
		if (subject[i].value != '') {
			if (!subject[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Subject";
				alert("Invalid Subject");
				return false;
			};
		};
	}	
	
	var venue = document.getElementsByName("venue");
	for ( var i = 0; i < venue.length; i++) {
		if (venue[i].value != '') {
			if (!venue[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Venue";
				alert("Invalid Venue");
				return false;
			};
		};
	}
	
	var organizedBy = document.getElementsByName("organizedBy");
	for ( var i = 0; i < organizedBy.length; i++) {
		if (organizedBy[i].value != '') {
			if (!organizedBy[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid organizer Name";
				alert("Invalid organizer Name");
				return false;
			};
		};
	}
	
	var duration = document.getElementsByName("duration");
	for ( var i = 0; i < duration.length; i++) {
		if (duration[i].value != '') {
			if (!duration[i].value.match(intRegx)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Duration";
				alert("Invalid Duration");
				return false;
			};
		};
	}
	var trainingFromDate = document.getElementsByName("trainingFromDate");
	var trainingToDate = document.getElementsByName("trainingToDate");
	for( var i=0;i<trainingFromDate.length;i++){
		var trainingFromDateValue = parseInt(trainingFromDate[i]);
		var trainingToDateValue = parseInt(trainingToDate[i]);
		if(trainingToDateValue<trainingFromDateValue){			
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML = "From Date can't be greater than To Date";
			alert("From Date can't be greater than To Date");
			return false;
		};
	}
	
	return true;
}

function validateAwardsAndRecognizationDetails(){
	
	var subject = document.getElementsByName("awardName");
	for ( var i = 0; i < subject.length; i++) {
		if (subject[i].value != '') {
			if (!subject[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Award Name";
				alert("Invalid Award Name");
				return false;
			};
		};
	}	
	
	var venue = document.getElementsByName("presentedBy");
	for ( var i = 0; i < venue.length; i++) {
		if (venue[i].value != '') {
			if (!venue[i].value.match(ran)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Presented By";
				alert("Invalid Presented By");
				return false;
			};
		};
	}
	
	var organizedBy = document.getElementsByName("presentedOn");
	for ( var i = 0; i < organizedBy.length; i++) {
		if (organizedBy[i].value != '') {
			if (!organizedBy[i].value.match(doubleRegx)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Invalid Presented By Name";
				alert("Invalid Presented By Name");
				return false;
			};
		};
	}	
	return true;
}

function validateEmployeePublicationDetails(){
	
	var publicationName = document.getElementsByName("publicationName");
	for ( var i = 0; i < publicationName.length; i++) {
		if (publicationName[i].value != '') {
			if (!publicationName[i].value.match(alphaNumeric)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid Publication Name";
				alert("Invalid Publication Name");
				return false;
			};
		};
	}
	
//	var dateOfPublication = document.getElementsByName("dateOfPublication");
//	for ( var i = 0; i < dateOfPublication.length; i++) { 
//		if (dateOfPublication[i].value != '') {
//				document.getElementById("warningbox").innerHTML = "Invalid Date Of Publication";
//				return false;
//			}
//		}	
	
	var coPublisher = document.getElementsByName("coPublisher");
	for ( var i = 0; i < publicationName.length; i++) {
		if (coPublisher[i].value != '') {
			if (!coPublisher[i].value.match(alphaNumeric)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid Co Publisher";
				alert("Invalid Co Publisher");
				return false;
			};
		};
	}
	
	var publicationDesc = document.getElementsByName("publicationDesc");
	for ( var i = 0; i < publicationDesc.length; i++) {
		if (publicationDesc[i].value != '') {
			if (!publicationDesc[i].value.match(alphaNumeric)) {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML = "Invalid Publication Desc";
				alert("Invalid Publication Desc");
				return false;
			};
		};
	}	
	return true;
}

function validateEmployeePublicationDetails(){
	obj=document.getElementById("confidentialInformation");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	if(val!=""){
		if(!val.match(alphabetic)){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML="Invalid confidential Information";
			alert("Invalid confidential Information");
			return false;
		}
	}
	return true;
}
	
function validateConfidentialDetails(){
	var confidentialInformation = document.getElementById("confidentialInformation").value;		
	if (confidentialInformation != '') {			
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid Publication Desc";
		alert("Invalid Publication Desc");
		return false;
	};
};

function validateEmployeeForm(){	
	if(!validateEmployeePersonalDetails()){
		alert("2 Error In Employee Personal Details");
		return false;
	}else{
		alert("2 No Error In Employee Personal Details");
	}	
	
	if(!validateEmployeeQualificationDetails()){
		alert("3 Error In Employee Qualification Details");
		return false;
	}else{
		alert("3 No Error In Employee Qualification Details");
	}	
	
	if(!validateEmployeeAddress()){
		alert("4 Error In Employee Address Details");
		return false;
	}else{
		alert("4 No Error In Employee Adress Details");
	}
	
	if(!validateEmployeeBasicDetails()){
		alert("1 Error In Employee Basic Details");
		return false;
	}else{
		alert("1 No Error In Employee Basic Details");		
	}
	if(!validateEmployeeDependentsDetails()){
		alert("5 Error In Employee Dependents Details");
		return false;
	}else{
		alert("5 No Error In Employee Dependents Details");
	}	
	
	if(!validateNomineeDetails()){
		alert("5 Error In Employee Dependents Details");
		return false;
	}else{
		alert("5 No Error In Employee Dependents Details");
	}
	if(!validateEmployeeBankDetails()){
		alert("6 Error In Employee Bank Details");
		return false;
	}else{
		alert("6 No Error In Employee Bank Details");
	}
	
	if(!validateEmployeeWorkDetails()){
		alert("7 Error In Employee Work Details");
		return false;
	}else{
		alert("7 No Error In Employee Work Details");
	}	
	
	if(!validateWorkShopAndTrainingDetails()){
		alert("8 Error In Employee Publication Details");
		return false;
	}else{
		alert("9 No Error In Employee Publication Details");
	}	
	
	if(!validateAwardsAndRecognizationDetails()){
		alert("8 Error In Employee Publication Details");
		return false;
	}else{
		alert("9 No Error In Employee Publication Details");
	}
	
	if(!validateEmployeePublicationDetails()){
		alert("8 Error In Employee Publication Details");
		return false;
	}else{
		alert("9 No Error In Employee Publication Details");
	}
	
	if(false==validateConfidentialDetails()){
		alert("8 Error In Employee Publication Details");
		return false;
	}else{
		alert("9 No Error In Employee Publication Details");
	}
	if(false==calculateHundred()){
		return false;
	}	
	return true;
}
