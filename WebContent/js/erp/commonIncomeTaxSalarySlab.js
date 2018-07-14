	function removeZero(tBox){	
		if(tBox.value=="0.00"){
			tBox.value="";
		}
	}
	
	function setZero(tBox){
		if(tBox.value==""){
			tBox.value="0.00";
		}		
		if(isNaN(tBox.value)){
//			document.getElementById('warningbox').style.visibility="visible";
//			document.getElementById('warningmsg').innerHTML="Please Enter Numeric Value";
			alert("Please Enter Numeric Value");
			tBox.value="0.00";
		}else{
			var p=parseFloat(tBox.value);
			if(p<0.0){
//				document.getElementById('warningbox').style.visibility="visible";
//				document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
				alert("Please Enter a positive Number");
				tBox.value="0.00";
			}
		}		
	}	
	
	function validateITSlabSubmitForm(){		
		if (document.getElementById("salaryBreakUp").value == "") {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please Select Parameter Basis";
			alert("Please Select Parameter Basis");
			return false;
		}
		
		var itParameterNames = document.getElementsByName("itParameter");
		for(var x=0; x<itParameterNames.length; x++){
			if (document.getElementById(itParameterNames[x].value).value == "") {
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningbox").innerHTML =  "Please Select Figure Type";
				alert("Please Select Figure Type");
				return false;
			}
		}
	}
	
	function validateForm(){		
		incomeAge = document.getElementsByName("incomeAge");
		if ((incomeAge[0].checked == false) && (incomeAge[1].checked == false) && (incomeAge[2].checked == false) && (incomeAge[3].checked == false)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML =  "Please choose one Age Category";	
			alert("Please choose one Age Category");
			return false;
		}		
		
	}
	