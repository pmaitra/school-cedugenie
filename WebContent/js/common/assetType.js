	function removeDisabled(rd,erb,er){	
		document.getElementById('warningbox').style.visibility = "collapse";
		valradio(rd,erb,er);
		
		document.getElementById("submitt").removeAttribute('disabled');
		var radios = document.getElementsByName("assetTypeId");
		for(var i=0; i<=radios.length; i++){
			if(document.getElementById("radioAssetType"+i).checked == true){
				document.getElementById("textAssetType"+i).removeAttribute('disabled');
				break;
			}
		}
	}
	
	function validateAssetTypeForm(){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;	
		var successmsg=document.getElementById("successmsg");
		if(null != successmsg){
			successmsg.innerHTML = "";
			document.getElementById("successbox").style.visibility = 'collapse';
		}
		// Validate asset Type Name
		var obj = document.getElementById("assetTypeName");
		var assetTypeName=obj.value;
		assetTypeName=assetTypeName.replace(/\s{1,}/g,' ');
		assetTypeName=assetTypeName.trim();
		assetTypeName=assetTypeName.toUpperCase();
		obj.value=assetTypeName;
		
		if (assetTypeName == "") {
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningbox").innerHTML = "Please Enter Asset Type Name";
			return false;
		}
		if (assetTypeName != '') {
			if (!assetTypeName.match(alphaNumeric)) {
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningbox").innerHTML = "Please Enter Proper Asset Type Name";
				return false;
			}
		}
		var oldAssetTypeNames = document.getElementsByName('oldAssetTypeNames');
		for(var i=0; i<oldAssetTypeNames.length;i++){
			if(oldAssetTypeNames[i].value==assetTypeName){				
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningmsg").innerHTML = "Asset Type Name Already Exists";
				return false;
			}
		}
		return true;
	}
	
	function validateEditAssetTypeForm(){			
		
		var radios = document.getElementsByName("assetTypeId");
		var val="";		
		for(var i=0; i<radios.length; i++){
			if(document.getElementById("radioAssetType"+i).checked == true){
				var obj=document.getElementById("textAssetType"+i);
				val=obj.value;		
				val=val.replace(/\s{1,}/g,' ');
				val=val.trim();
				val=val.toUpperCase();
				obj.value=val;
				if (val == "") {
					document.getElementById("warningbox").style.visibility = 'visible';
					document.getElementById("warningbox").innerHTML = "Please Enter Asset Type Name";
					return false;
				}
				else{
					document.getElementById("warningbox").style.visibility = 'collapse';
					document.getElementById("warningbox").innerHTML = "";
				}
			}
		}
		for(var i=0; i<radios.length; i++){
			var oldVal = radios[i].value;
			var oldValue = document.getElementById("oldAssetTypeNamesId"+oldVal).value;
//			alert("OLD - "+oldValue+" :: NEW - "+val);
			if(oldValue == val){
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningbox").innerHTML = "Can not Create Duplicate Asset Type";
				return false;
			}
		}
		document.getElementById("warningbox").style.visibility = 'collapse';
		document.getElementById("warningbox").innerHTML = "";
		return true;
	}