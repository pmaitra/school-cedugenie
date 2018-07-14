	function removeDisabled(rd,erb,er){	
		document.getElementById('warningbox').style.visibility = "collapse";
		valradio(rd,erb,er);
		
		document.getElementById("submitt").removeAttribute('disabled');
		var radios = document.getElementsByName("departmentCode");
		for(var i=0; i<=radios.length; i++){
			if(document.getElementById("radioDept"+i).checked == true){
				document.getElementById("textDept"+i).removeAttribute('disabled');
				break;
			}
		}
	}
	
	function validateDepartmentForm(){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
		/*var successmsg=document.getElementById("successmsg");
		if(null != successmsg){
			successmsg.innerHTML = "";
			document.getElementById("successbox").style.visibility='collapse';
		}	*/			
		// Validate Department Name
		var obj = document.getElementById("departmentName");	
		var departmentName=obj.value;
		departmentName=departmentName.replace(/\s{1,}/g,' ');
		departmentName=departmentName.trim();
		departmentName=departmentName.toUpperCase();
		obj.value=departmentName;
		//<!-- added by ranita.sur on 21092017 -->
		if (departmentName == "") {	
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Enter Department Name";
			//alert("Please Enter Department Name");
			return false;
		}
		//<!-- added by ranita.sur on 21092017 -->
		if (departmentName != '') {
			if (!departmentName.match(alphaNumeric)) {
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Please Enter Department Name";
				//alert("Please Enter Department Name");
				return false;
			}
		}
		//<!-- added by ranita.sur on 21092017 -->
		var oldDepartmentNames = document.getElementsByName('oldDepartmentNamesforDuplicateChecking');
		for(var i=0; i<oldDepartmentNames.length;i++){
			if(oldDepartmentNames[i].value==departmentName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Department Name Already Exists";
				//alert("Department Name Already Exists");				
				return false;
			}
		}
		
	}
	
	function validateEditDepartmentForm(rowID ){			
		/*var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;			
		var val="";
		var obj = document.getElementById(rowID);		
		val=obj.value;		
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		
		if (val == "") {					
			alert("Please Enter Department");
			return false;
		}else{					
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "";
		}
				
		if(!val.match(alphaNumeric)){
			alert("Invalid Department");				
			return false;
		}else{
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "";
		}	
		*/
		var departmentName=document.getElementById("departmentName").value;
		var alphaNumeric=/^[a-zA-Z \s]+$/;
		//var olddesignationTypeName=document.getElementsByName("oldDesgnationTypeNamesforDuplicateChecking");
		var newDepartmentName = document.getElementById("getDepartmentType").value;
		newDepartmentName=newDepartmentName.trim();
		newDepartmentName=newDepartmentName.toUpperCase();
		
		
		var deptList = document.getElementsByName("oldDepartmentNamesforDuplicateChecking");		
		for(var i=0; i<deptList.length; i++){
			var oldVal=deptList[i].value;
			if(oldVal==newDepartmentName){
				document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Duplicate Department";
				//alert("Duplicate Department");
				return false;
			}
		}
		if(departmentName ==""|| departmentName==null ){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Enter Department Name"; 
			return false;
		}else if (alphaNumeric.test(departmentName)==false)
	    {
	    	document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Department name can contain alphabets and spaces between words !!";
			return false;
	    }else
			{
			return true;
			}
		
	}
	

