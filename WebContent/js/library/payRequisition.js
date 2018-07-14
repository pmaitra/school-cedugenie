function validatePayRequisition(){
	/*document.getElementById("warningbox").style.visibility="collapse";
	document.getElementById("warningmsg").innerHTML="";*/
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var payingAmount = $("#payingAmount").val();
	if(payingAmount == ""){
		alert("Please Enter Paying Amount.");
		return false;
	}
	
	if(payingAmount == "0.00"){
		alert("Please Enter Paying Amount.");
		return false;
	}
	if(payingAmount != ""){
		if(payingAmount != "0.00"){
			if(!payingAmount.match(regPositiveNum)) {
				alert("Please Enter Proper Numeric Amount.");
				return false;
			}
		}
	}else{
		document.getElementById("warningbox").style.visibility='collapse';
	}
	calculateTotalPayableAmount();
}

function calculateTotalPayableAmount(){
	/*document.getElementById("warningbox").style.visibility="collapse";
	document.getElementById("warningmsg").innerHTML="";*/
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var totalAmount = parseFloat(document.getElementById("totalAmount").value);
	var stdsInPercent = document.getElementById("stdsInPercent").value;
	var stdsInAmount = 0.00;
	if(!stdsInPercent.match(regPositiveNum)) {
			alert("Please Enter Proper value for STDS %");
		return false;
	}else{
		 stdsInPercent = parseFloat(stdsInPercent);
		 if(stdsInPercent>100) {
				alert("STDS % should not be more then 100");
				return false;
		 }else{
				 stdsInAmount = ((totalAmount*stdsInPercent)/100);
				 document.getElementById("stdsInAmount").value = stdsInAmount;
		 }
	}
	var serviceCharge = document.getElementById("serviceCharge").value;
	if(!serviceCharge.match(regPositiveNum)) {
		alert("Please enter proper value for service charge");
		return false;
	}else{
		serviceCharge = parseFloat(serviceCharge);
	}
	var serviceTaxInPercent = document.getElementById("serviceTaxInPercent").value;
	if(!serviceTaxInPercent.match(regPositiveNum)) {
		alert("Please enter proper value for Service Tax %");
		return false;
	}else{
		serviceTaxInPercent = parseFloat(serviceTaxInPercent);
			if(serviceTaxInPercent>100) {
				alert("Service Tax % should not be more then 100");
				return false;
			}
	}
	var tdsInPercent = document.getElementById("tdsInPercent").value;
	if(!tdsInPercent.match(regPositiveNum)) {
		alert("Please enter proper value for TDS %");
		return false;
	}else{
		tdsInPercent = parseFloat(tdsInPercent);
			tdsInPercent = parseFloat(tdsInPercent);
			if(serviceTaxInPercent>100) {
				alert("TDS  % should not be more then 100");
				return false;
			}
	}
	var tdsInAmount = 0.00;
	var serviceTaxInAmount = 0.00;
	if(serviceCharge>0){
		document.getElementById("serviceTaxInPercent").value = serviceTaxInPercent;
		document.getElementById("tdsInPercent").value = tdsInPercent;
		tdsInAmount = ((serviceCharge*tdsInPercent)/100);
		document.getElementById("tdsInAmount").value = tdsInAmount;
		serviceTaxInAmount = ((serviceCharge*serviceTaxInPercent)/100);
		serviceCharge = (serviceCharge+serviceTaxInAmount)-tdsInAmount;
	}else{
		document.getElementById("serviceTaxInPercent").value = 0.00;
		document.getElementById("tdsInPercent").value = 0.00;
		document.getElementById("tdsInAmount").value = 0.00;
		//document.getElementById("payingAmount").value = 0.00;
	}
	var payingAmount= parseFloat(document.getElementById("payingAmount").value);
	document.getElementById("totalPayableAmount").value = (totalAmount-stdsInAmount)+serviceCharge;
	document.getElementById("totalPendingAmount").value = ((totalAmount-stdsInAmount)+serviceCharge)-payingAmount;
	document.getElementById("demoPendingAmount").value = (totalAmount-stdsInAmount)+serviceCharge;
}

function showBankDetails(mode){
	if(mode.value=="CASH"){
		document.getElementById("chequeNo").value="";
		document.getElementById("bankCode").value="";
		document.getElementById("bankName").value="";
		document.getElementById("bankLocation").value="";
		document.getElementById("chequeNo").setAttribute('disabled', 'disabled');
		document.getElementById("bankCode").setAttribute('disabled', 'disabled');
		document.getElementById("bankName").setAttribute('disabled', 'disabled');
		document.getElementById("bankLocation").setAttribute('disabled', 'disabled');
		document.getElementById("paymentDetails").style.visibility='collapse';
	}else{
		document.getElementById("chequeNo").removeAttribute('disabled');
		document.getElementById("bankCode").removeAttribute('disabled');
		document.getElementById("bankName").removeAttribute('disabled');
		document.getElementById("bankLocation").removeAttribute('disabled');
		document.getElementById("paymentDetails").style.visibility='visible ';
	}
}

function calculatePendingAmount(){
	var payingAmount = document.getElementById("payingAmount").value;
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var totalPendingAmount = document.getElementById("totalPendingAmount").value;
	if(!payingAmount.match(regPositiveNum)) {
		alert("Please enter proper value for Paying Amount");
		return false;
	}else{
		payingAmount = parseFloat(payingAmount);
		if(payingAmount > 0){
			document.getElementById("totalPendingAmount").value = (totalPendingAmount-payingAmount); 
		}else{
			document.getElementById("totalPendingAmount").value=document.getElementById("demoPendingAmount").value;
		}
	}
	//calculateTotalPayableAmount();
	
}
