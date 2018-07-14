/*
window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'rerq', checkbox[i]);
	}
};*/


function validateReceiveRequisition(){
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var receivingAmount = $(".form-control").val();
	var ledgerName = $("#ledger").val();
	if(receivingAmount == ""){
//		document.getElementById("warningbox").style.visibility="visible";
//		document.getElementById("warningmsg").innerHTML="Please Enter an Amount.";
		alert("Please enter value of quantity.");
		return false;
	}
	if(receivingAmount == "0"){
//		document.getElementById("warningbox").style.visibility="visible";
//		document.getElementById("warningmsg").innerHTML="Please Enter an Amount.";
		alert("Zero quantity isn't allowed.");
		return false;
	}
	if(receivingAmount != ""){
		if(receivingAmount != "0.00"){
			if(!receivingAmount.match(regPositiveNum)) {
//				document.getElementById("warningbox").style.visibility="visible";
//				document.getElementById("warningmsg").innerHTML="Please Enter Proper Numeric Amount.";
				alert("Please enter proper value of quantity.");
				return false;
			}
		}
	}if(ledgerName == ""){
		alert("Please select a ledger");
		return false;
	}else{
		return true;
	}
};
function checkWithDeficit(index){
	var receive,deficit ;
	receive = $("#qtyReceiving"+index).val();
	deficit = $("#qtyDeficit"+index).val();
	deficit = parseInt(deficit);
	if(receive>deficit){
		document.getElementById("qtyReceiving"+index).value = 0;
		document.getElementById("warningDiv").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="Receiving quantity can't be greater than deficit."
	}
	if(receive<=deficit){
		document.getElementById("warningDiv").style.display = 'none';
		calculatePayableAmount(index);
	}
	
}
function calculatePayableAmount(index){
	var receivingQnty;
	receivingQnty = parseFloat(document.getElementById("qtyReceiving"+index).value);
	var boxValueSatus = isNaN(receivingQnty);
	
	if(boxValueSatus==true){
		receivingQnty = 0;
	}
	//alert("receivingQnty===="+receivingQnty);
	if(receivingQnty == 'NaN'){
		receivingQnty = 0;
	}
	var rate = parseFloat(document.getElementById("rate"+index).value);
	var tax = parseFloat(document.getElementById("tax"+index).value);
	var discount = parseFloat(document.getElementById("discount"+index).value);
	var payableAmt = parseFloat(document.getElementById("payingAmount").value);
	var totalAmount = parseFloat((rate*receivingQnty));
	var discount = parseFloat(((totalAmount*discount)/100));
	var totalAmontWithoutDiscount = parseFloat(totalAmount - discount);
	//alert("totalAmontWithoutDiscount==="+totalAmontWithoutDiscount);
	var taxAmount = parseFloat(((totalAmontWithoutDiscount*tax)/100));
	//alert("taxAmount====="+taxAmount);
	var tatalAmountWithoutTax = parseFloat(totalAmontWithoutDiscount+taxAmount);
	//alert("tatalAmountWithoutTax==="+tatalAmountWithoutTax);
	var calculateAmt = parseFloat((rate*receivingQnty));
	
	/* added by sourav.bhadra on 13-09-2017 */
	document.getElementById("totalAmount"+index).value = tatalAmountWithoutTax;
	var totalAmt = 0;
	$('input[name="totalAmount"]').each(function(){
		var amt = $(this).val();
		totalAmt = parseFloat(parseFloat(totalAmt) + parseFloat(amt));
	});
	document.getElementById("payingAmount").value = totalAmt;
};

