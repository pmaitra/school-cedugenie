$(document).ready(function() {
	$("#assetName").bind('keyup blur',function(){
		var assetName = $("#assetName").val();	
		$.ajax({
		    url: '/cedugenie/assetNameValidation.html',
		    data: "assetName=" +assetName,
		    dataType: 'json',
		    success: function(data) {
		    	if(data != null && data != ""){
		    		document.getElementById("warningbox").style.visibility = 'visible';
		 			$("#warningbox").text("An Asset with this name already exists");	
		 			document.getElementById("assetName").value = '';
		    	  	return false;
		    	}else{	    		
		    		document.getElementById("warningbox").style.visibility = 'collapse';
		    	}
	    	}  
		});			
	});
	
	$("#assetType").change(function(){
		document.getElementById("assetSubType").innerHTML="<option value=''>Select...</option>";
		var options="<option value=''>Select...</option>";
		$.ajax({
		    url: '/cedugenie/getAssetSubTypeForAssetType.html',		   
		    dataType: 'json',
		    data: "assetType=" + ($(this).val()),
		    success: function(data) {		    	
		    	data=data.split(";");
		    	for(var i=0;i<data.length-1;i++){
		    		var x=data[i].split("#");
		    		options=options+"<option value='"+x[0]+"'>"+x[1]+"</option>";
		    	}
		    	document.getElementById("assetSubType").innerHTML=options;
		    } 
		});
	});
	
});

function makeEditable(){
	document.getElementById("departmentName").removeAttribute("disabled");
	document.getElementById("assetProperty").removeAttribute("readonly");
	document.getElementById("amount").removeAttribute("readonly");
	document.getElementById("purchaseDate").removeAttribute("disabled");
	document.getElementById("submit").removeAttribute("disabled");
	document.getElementById("ledgerBalance").removeAttribute("readonly");
	document.getElementById("ledgerNumber").removeAttribute("readonly");
	document.getElementById("pageNumber").removeAttribute("readonly");
	document.getElementById("assetType").removeAttribute("disabled");
	document.getElementById("assetSubType").removeAttribute("disabled");
	document.getElementById("assetUnit").removeAttribute("disabled");
}

function validateAssetDetails(){
	var digits =/^[0-9]{1,}(\.\d{0,2}){0,1}$/;
	var deptment = document.getElementById("departmentName").value;		
	if(deptment == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select a Department";
		return false;
	}
	var asset = document.getElementById("assetName").value;		
	if(asset == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Enter Asset name";
		return false;
	}
	var assetType = document.getElementById("assetType").value;		
	if(assetType == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select an Asset Type";
		return false;
	}
	var assetSubType = document.getElementById("assetSubType").value;		
	if(assetSubType == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select Asset Sub Type";
		return false;
	}
	var amount = document.getElementById("amount").value;		
	if(amount == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please give a Price amount";
		return false;
	}
	if(amount != ""){
		if (!amount.match(digits)) {
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML = "Enter a valid Price amount";
			return false;
		}
	}
	var purchaseDate = document.getElementById("purchaseDate").value;		
	if(purchaseDate == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Enter a Purchase Date";
		return false;
	}
	var ledgerBalance = document.getElementById("ledgerBalance").value;		
	if(ledgerBalance == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Enter a Ledger Balance";
		return false;
	}
	if(ledgerBalance != ""){
		if (!ledgerBalance.match(digits)) {
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Enter valid Ledger Balance";
			return false;
		}
	}
	var ledgerNumber = document.getElementById("ledgerNumber").value;		
	if(ledgerNumber == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Enter a Ledger number";
		return false;
	}
	var pageNumber = document.getElementById("pageNumber").value;		
	if(pageNumber == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Enter a Page number";
		return false;
	}
}