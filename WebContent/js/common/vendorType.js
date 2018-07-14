function validateVendorTypeForm(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var successmsg=document.getElementById("successmsg");
	if(null != successmsg){
		successmsg.innerHTML = "";
		document.getElementById("successbox").style.visibility='collapse';
	}				
	// Validate DesignationType Name
	var obj = document.getElementById("vendorTypeName");	
	var vendorTypeName=obj.value;
	vendorTypeName=vendorTypeName.replace(/\s{1,}/g,' ');
	vendorTypeName=vendorTypeName.trim();
	//vendorTypeName=vendorTypeName.toUpperCase();
	obj.value=vendorTypeName;
	if (vendorTypeName == "") {			
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Vendor Type";		
		return false;
	}
	
	if (vendorTypeName != '') {
		if (!vendorTypeName.match(alphaNumeric)) {			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Vendor Type accepts alphabets and numbers only";
			return false;
		}
	}
	
		var oldVendorTypeNames = document.getElementsByName('oldVendorTypeNamesforDuplicateChecking');
		for(var i=0; i<oldVendorTypeNames.length;i++){
			if(oldVendorTypeNames[i].value==vendorTypeName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Vendor Type Already Exists";			
				return false;
			}
		}		
}
/*modified by ranita.sur on 14092017 for validation in popup*/
function validateEditVendorTypeForm(rowId ){			
	var vendorTypeName=document.getElementById("vendorTypeName").value;
	var alphaNumeric=/^[a-zA-Z \s]+$/;
	var newvendorTypeName = document.getElementById("getVendorType").value;
	newvendorTypeName=newvendorTypeName.trim();
	var vendorTypeList = document.getElementsByName("oldVendorTypeNamesforDuplicateChecking");		
	for(var i=0; i<vendorTypeList.length-1; i++){
		var oldVal=vendorTypeList[i].value;
		//alert("LN 48"+oldVal);
		if(oldVal==newvendorTypeName){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Duplicate Vendor Type";
			return false;
		}
	}		
	if(vendorTypeName ==""|| vendorTypeName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Vendor Typee"; 
		return false;
	}else if (alphaNumeric.test(vendorTypeName)==false)
    {
    	document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Vendor Type can contain alphabets and spaces between words !!";
		return false;
    }else{
	return true;
   }
	
}
	