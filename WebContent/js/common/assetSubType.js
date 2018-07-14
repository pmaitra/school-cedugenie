	$(document).ready(function() {
		$("#assetType").change(function(){
			document.getElementById('assetSubTypeName').value = '';
			document.getElementById("warningbox").style.visibility = 'collapse';
		});
	});


	function removeDisabled(rd,erb,er){	
		document.getElementById('warningbox').style.visibility = "collapse";
		valradio(rd,erb,er);
		
		document.getElementById("submitt").removeAttribute('disabled');
		document.getElementById("delete").removeAttribute('disabled');
		var radios = document.getElementsByName("assetSubTypeId");
		for(var i=0; i<=radios.length; i++){
			if(document.getElementById("radioAssetSubType"+i).checked == true){
				document.getElementById("textAssetSubType"+i).removeAttribute('disabled');
				break;
			}
		}
	}
	
	function validateAssetSubTypeForm(){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;	
		var successmsg=document.getElementById("successmsg");
		if(null != successmsg){
			successmsg.innerHTML = "";
			document.getElementById("successbox").style.visibility = 'collapse';
		}
		// Validate asset Type Name
		var assetType = document.getElementById("assetType").value;
		if (assetType == "") {
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningbox").innerHTML = "Please Select a Asset Type";
			return false;
		}
		
		var obj = document.getElementById("assetSubTypeName");
		var assetSubTypeName=obj.value;
		assetSubTypeName=assetSubTypeName.replace(/\s{1,}/g,' ');
		assetSubTypeName=assetSubTypeName.trim();
		assetSubTypeName=assetSubTypeName.toUpperCase();
		obj.value=assetSubTypeName;
		
		if (assetSubTypeName == "") {
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningbox").innerHTML = "Please Enter Asset Sub Type Name";
			return false;
		}
		if (assetSubTypeName != '') {
			if (!assetSubTypeName.match(alphaNumeric)) {
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningbox").innerHTML = "Please Enter Proper Asset Sub Type Name";
				return false;
			}
		}
		var oldAssetSubTypeNames = document.getElementsByName('oldAssetSubTypeNames');
		for(var i=0; i<oldAssetSubTypeNames.length;i++){
			if(oldAssetSubTypeNames[i].value==assetSubTypeName){				
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningmsg").innerHTML = "Asset Sub Type Name Already Exists";
				return false;
			}
		}
		return true;
	}
	
	function validateEditAssetSubTypeForm(){			
		
		var radios = document.getElementsByName("assetSubTypeId");
		var val="";		
		for(var i=0; i<radios.length; i++){
			if(document.getElementById("radioAssetSubType"+i).checked == true){
				var obj=document.getElementById("textAssetSubType"+i);
				val=obj.value;		
				val=val.replace(/\s{1,}/g,' ');
				val=val.trim();
				val=val.toUpperCase();
				obj.value=val;
				if (val == "") {
					document.getElementById("warningbox").style.visibility = 'visible';
					document.getElementById("warningbox").innerHTML = "Please Enter Asset Sub Type Name";
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
			var oldValue = document.getElementById("oldAssetSubTypeNamesId"+oldVal).value;
			if(oldValue == val){
				document.getElementById("warningbox").style.visibility = 'visible';
				document.getElementById("warningbox").innerHTML = "Can not Create Duplicate Asset Sub Type";
				return false;
			}
		}
		document.getElementById("warningbox").style.visibility = 'collapse';
		document.getElementById("warningbox").innerHTML = "";
		return true;
	}