	function removeDisabled(rd,erb,er){	
		document.getElementById('warningbox').style.visibility = "collapse";
		valradio(rd,erb,er);
		
		document.getElementById("submitt").removeAttribute('disabled');
		var radios = document.getElementsByName("employeeTypeCode");
		for(var i=0; i<=radios.length; i++){
			if(document.getElementById("radioEmpType"+i).checked == true){
				document.getElementById("textEmpType"+i).removeAttribute('disabled');
				break;
			}
		}
	}
	
	function validateEmployeeTypeForm(){	
		var ran = /^[A-Za-z]{1,}$/;				
		// Validate Employee Type Name
		var employeeTypeName = document.getElementById("employeeTypeName").value;
		employeeTypeName = employeeTypeName.replace(/\s{1,}/g, '');
		if (employeeTypeName == "") {
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Please Enter Employee Type Name";
			return false;
		}
		if (employeeTypeName != '') {
			if (!designationName.match(ran)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningbox").innerHTML = "Invalid Employee Type Name";
				return false;
			}
		}
		
	}