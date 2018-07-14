/*function editable(){
	alert("Hii");
	document.getElementById('clearButton').removeAttribute('disabled');
	document.getElementById('submitButton').removeAttribute('disabled');

	var financialYearCode=document.getElementsByName("financialYearCode");
	alert(financialYearCode);
	for(var i=0;i<financialYearCode.length;i++){
			if(financialYearCode[i].checked==true){
				var code=financialYearCode[i].id;
				document.getElementById('financialYearName'+code).removeAttribute("disabled");
				}
		}
	
};*/
$("#edit").click(function(){
	
	document.getElementById("clearButton").removeAttribute('disabled');
	document.getElementById("submitButton").removeAttribute('disabled');

	var financialYearCode=document.getElementsByName("financialYearCode");
	
	for(var i=0;i<financialYearCode.length;i++){
			if(financialYearCode[i].checked==true){
				var code=financialYearCode[i].id;
				document.getElementById('financialYearName'+code).removeAttribute("disabled");
				}
		}
});

var fyID;
function validateEdit(){
	var financialYearCode=document.getElementsByName("financialYearCode");	
	
	for(var i=0;i<financialYearCode.length;i++){
		if(financialYearCode[i].checked==true){
			fyID=financialYearCode[i].id;				
		}
	}
	var financialYearName = document.getElementById("financialYearName"+fyID).value;
	if(financialYearName == ""){
		alert("Please Provide Financial Year Name");
		return false;
	}
	var financialYearClass = document.getElementsByName("financialYearNames");
	for(var varf=0;varf<financialYearClass.length;varf++){
		if(financialYearClass[varf].value==financialYearName){
			alert("Financial Year Name Already Exists");
			return false;
			}
		}
	return true;
}


function validateFrom(){
	var financialYearName = document.getElementById("financialYearName").value;	
	var sessionStartDate = document.getElementById("sessionStartDate").value;
	var sessionEndDate = document.getElementById("sessionEndDate").value;
	
	if(sessionStartDate>sessionEndDate){
		alert("Session End Date Can not before Session End Date");
		return false;
	}
	var financialYearClass=document.getElementsByName("financialYearNames");
	for(var varf=0;varf<financialYearClass.length;varf++){
		if(financialYearClass[varf].value==financialYearName){
			alert("Financial Year Name Already Exists");
			return false;
			}
		}
	return true;
}