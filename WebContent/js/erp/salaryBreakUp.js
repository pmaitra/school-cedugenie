function deleteAction(){	
 return true;	
}
 ran = /^[A-Za-z 0-9 () .-]{1,}$/;
 var chk=0;
 function newSalaryStructureValidation(){
	 
	 var salaryBreakUpName=document.getElementById("newSalaryBreakUpName").value.replace(/\s{1,}/g,'');
	 var checkBoxVal = document.getElementsByName("slab");
	
	 if(salaryBreakUpName.length==0){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Please Enter New Salary Structure Name";
		 alert("Please Enter New Salary Structure Name");
		 return false;
	 }
	 if (salaryBreakUpName.length != 0) {
		if (!salaryBreakUpName.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Invalid New Salary Structure Name";
			alert("Invalid New Salary Structure Name");
			return false;
		}
	}
	 if(document.getElementById("newSalaryBreakUpType").value.replace(/\s{1,}/g,'').length==0){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Please Select One New Salary Structure Type";
		 alert("Please Select One New Salary Structure Type");
		 return false;
	 }		 	
	 for(var i=0;i<checkBoxVal.length;i++){	
		 if(checkBoxVal[i].checked){
			 chk = 1;
		 }
	 }	
	 if (chk==0){
//		 document.getElementById("warningbox").style.visibility = "visible";
//		 document.getElementById("warningmsg").innerHTML= "Must check some option!";
		 alert("Must check some option!");
		  	return false;
	  }
	 return true;
 }
 