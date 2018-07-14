function validate(){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var templateName=document.getElementById("templateName").value;	
	templateName=templateName.replace(/\s{1,}/g,' ');
	templateName=templateName.trim();
	templateName=templateName.toUpperCase();
	document.getElementById("templateName").value=templateName;	
	
	/*if(!templateName.match(reg1)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Template Name. (Alpha-Numeric. Atleat 1 character.)";
		return false;
	}*/
	var oldFeesTemplateName = document.getElementsByName("oldFeesTemplateName");
	for(var i=0; i<oldFeesTemplateName.length;i++){
		if(oldFeesTemplateName[i].value==templateName){
			/*document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Template Name Already Exixts";*/
			alert("Template Name Already Exixts.");
			return false;
		}
	}
	
	
	
	var amountBox=getElementsByClassName("textfield2");
	for(var i=0;i<amountBox.length;i++){
		var amount=amountBox[i].value;
		amount=amount.replace(/\s{1,}/g,'');
		amountBox[i].value=amount;
		
		if((!amount.match(intRegx)) && (!amount.match(doubleRegx))){
			/*document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Amount. (Numeric. Atleat 1 character.)";*/
			alert("This amount is invalid. Amount should be numeric(atleast one character).");
			return false;
		}else{
			amount = parseFloat(amount);
			if(amount<0){
				/*document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Invalid Amount. (+ve Numbers Only.)";*/
				alert("Invalid Amount. (zero and positive numbers only.)");
				return false;
			}
		}
	}
	
	$("#feesname").attr("value", "0.00");
	return true;
}