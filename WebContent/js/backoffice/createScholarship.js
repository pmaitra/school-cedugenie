function validation(){
	var oldScholarshipNames = document.getElementsByName("oldScholarshipNames");
	var scholarshipName = document.getElementById("scholarshipName").value;
	scholarshipName = scholarshipName.trim();
	scholarshipName=scholarshipName.toUpperCase();
	alert("anup2::"+scholarshipName);
	for(var i=0; i<oldScholarshipNames.length;i++){
		if(oldScholarshipNames[i].value==scholarshipName){
			alert("Scholarship Name Already Exixts");
			return false;
		}
	}
	return true;
}

function editValidation(rowId){
	var oldScholarshipNames = document.getElementsByName("oldScholarshipNames");
	var scholarshipName = document.getElementById("name"+rowId).value;
	scholarshipName=scholarshipName.trim();
	scholarshipName=scholarshipName.toUpperCase();
	for(var i=0; i<oldScholarshipNames.length;i++){
		if(oldScholarshipNames[i].value==scholarshipName){
			alert("Scholarship Name Already Exixts");
			return false;
		}
	}
	return true;
}