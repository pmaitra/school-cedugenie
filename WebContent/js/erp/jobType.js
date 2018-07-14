	function validateJobTypeForm(){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
		
		// Validate Job Type Name
		var obj = document.getElementById("jobTypeName");
		var jobTypeName=obj.value;
		jobTypeName=jobTypeName.replace(/\s{1,}/g,' ');
		jobTypeName=jobTypeName.trim();
		jobTypeName=jobTypeName.toUpperCase();
		obj.value=jobTypeName;
		
		if (jobTypeName == "") {			
			/*document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Enter Job Type";*/
			alert("Please Enter Job Type");
			return false;
		}
		if (jobTypeName != '') {
			if (!jobTypeName.match(alphaNumeric)) {
				/*document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Invalid Job Type";*/	
				alert("Invalid Job Type");
				return false;
			}
		}
		var oldJobTypeNames = document.getElementsByName('oldJobTypeNamesForDuplicateCheck');
		for(var i=0; i<oldJobTypeNames.length;i++){
			if(oldJobTypeNames[i].value==jobTypeName){			
				/*document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Job Type Name Already Exists";*/
				alert("Job Type Name Already Exists");
				return false;
			}
		}
		return true;
	}
	
	function validateEditJobTypeForm(rowId){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
		var obj=document.getElementById(rowId);
		val=obj.value;		
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		val=val.toUpperCase();
		obj.value=val;
		if (val == "") {
			/*document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Enter Job Type";*/
			alert("Please Enter Job Type");
			return false;
		}
		else{					
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "";
		}
		if(!val.match(alphaNumeric)){
			/*document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Job Type";*/
			alert("Invalid Job Type");
			return false;
		}
		else{		
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "";
		}
		
		var jobTypeList = document.getElementsByName("oldJobTypeNamesForDuplicateCheck");		
		for(var i=0; i<jobTypeList.length; i++){
			var oldVal=jobTypeList[i].value;
			if(oldVal==val){
				/*document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Duplicate Job Type";*/
				alert("Duplicate Job Type");
				return false;
			}
		}
		return true;
	}