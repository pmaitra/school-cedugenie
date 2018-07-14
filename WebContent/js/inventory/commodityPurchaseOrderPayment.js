function bankDetails(mode){
	if(mode.value=="CASH"){
		document.getElementById("chequeNo").value="";
		document.getElementById("bankName").value="";
		document.getElementById("bankCode").value="";
		document.getElementById("bankLocation").value="";
		
		document.getElementById("chequeNo").setAttribute("readonly","readonly");
		document.getElementById("bankName").setAttribute("readonly","readonly");
		document.getElementById("bankCode").setAttribute("readonly","readonly");
		document.getElementById("bankLocation").setAttribute("readonly","readonly");
	}else{
		document.getElementById("chequeNo").removeAttribute("readonly");
		document.getElementById("bankName").removeAttribute("readonly");
		document.getElementById("bankCode").removeAttribute("readonly");
		document.getElementById("bankLocation").removeAttribute("readonly");
	}
}

function validate(payAmount){	
	var val=payAmount.value;
	val=val.replace(/\s{1,}/g,'');
	payAmount.value=val;
	
	
	
	if(val=="")
	{
		payAmount.value="0.00";
	}
	if(isNaN(val))
	{	
		alert("Non Numeric Value Not Allowed");
		
		payAmount.value="0.00";
		return false;
	}else{
		val=parseFloat(val);
		if(val<0.0){
			alert("Negative Value Not Allowed");
			
			payAmount.value="0.00";
			return false;
		}
	}
	
	
	
	var pending=document.getElementById("pendingAmount").value;
	var giving=payAmount.value;
	
	pending=parseFloat(pending);
	giving=parseFloat(giving);
	
	if(giving>pending){
		alert("Pay Amount Cannot Be Greater Than Pending Amount");
		
		payAmount.value="0.00";
		return false;
	}
}

