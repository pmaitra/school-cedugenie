	function editableVendorDetailsForm(){
		document.getElementById('vendorName').readOnly = false;
		document.getElementById('vendorContactNo1').readOnly = false;
		document.getElementById('vendorContactNo2').readOnly = false;
		document.getElementById('address').readOnly = false;
		document.getElementById("delete").removeAttribute("disabled");
		document.getElementById("update").removeAttribute("disabled");
	}
	
	function validateVendorDetailsForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		//Validate Vendor Name
		vendorName = document.getElementById("vendorName").value;
		vendorName = vendorName.replace(/\s{1,}/g,'');
		if(vendorName == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Vendor Name";
			return false;
		}
		
	}
	