$(document).ready(function(){	
	$("#submit").click(function(){
		rname = /[a-zA-Z][a-zA-Z ]+[a-zA-Z]$/;
		phoneno = /^\d{10}$/;
		accnumber = /^\d{12}$/;
		bcode=/^[a-zA-Z0-9]+$/;
		
		var bankName = $("#bankName").val();
		var branchName = $("#branchName").val();
		var accno = $("#accountNo").val();
		var bankIfscCode = $("#bankIfscCode").val();
		var location = $("#bankLocation").val();
		
		if(bankName == ""){
			alert("Please Provide an bankName.");
			return false;
		}
		if(branchName == ""){
			alert("Please Provide an branchName.");
			return false;
		}
		if(accno == " "){
			alert("Please Enter a valid account Number.");
			return false;
		}
		
		if(bankIfscCode == ""){
			alert("Please Enter a valid IFSC Code.");
			return false;
		}
		if(location == ""){
			alert("Please Enter a valid location.");
			return false;
		}
		else{
			return true;
		}
	});
});