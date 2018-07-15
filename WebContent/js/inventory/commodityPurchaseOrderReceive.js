function validate(tbox){
	var val=tbox.value;
	val=val.replace(/\s{1,}/g,'');
	tbox.value=val;
	if(val==""){
		tbox.value="0.00";
	}
	if(isNaN(val)){	
		//alert("Non Numeric Value Not Allowed");
		/* added by sourav.bhadra on 02-11-2017 */
		document.getElementById("messageDiv").innerHTML = "Non Numeric Value Not Allowed.";
		document.getElementById("messageDiv").style.display = "block";
		tbox.value="0.00";
		return false;
	}else{
		val=parseFloat(val);
		if(val<0.0){
			//alert("Negative Value Not Allowed");
			/* added by sourav.bhadra on 02-11-2017 */
			document.getElementById("messageDiv").innerHTML = "Negative Value Not Allowed.";
			document.getElementById("messageDiv").style.display = "block";
			tbox.value="0.00";
			return false;
		}
	}
	var idNum=(tbox.id).replace(tbox.id.charAt(0),"");
	var remeaning=document.getElementById("C"+idNum).value;
	var receiving=document.getElementById("A"+idNum).value;
	var defect=document.getElementById("B"+idNum).value;
	
	remeaning=parseFloat(remeaning);
	receiving=parseFloat(receiving);
	defect=parseFloat(defect);
	
	if((receiving-defect)>remeaning){
		//alert("Receiving Qty Cannot Be Greater Than Remeaning Qty");
		/* added by sourav.bhadra on 02-11-2017 */
		/* modified by sourav.bhadra on 09-11-2017 */
		document.getElementById("qtyMessage"+idNum).innerHTML = "Cannot Be Greater Than Remeaning Quantity";
		document.getElementById("qtyMessage"+idNum).style.display = "block";
		document.getElementById("A"+index).value = "0.0";
		setTimeout(function() {
			document.getElementById("qtyMessage"+idNum).style.display = "none";
        }, 2000);
		tbox.value="0.00";
		return false;
	}
}

/* modified by Sourav.Bhadra on 25-10-2017 */
function calculateAmount(index){
	var rate = parseFloat(document.getElementById("rate"+index).value);
	var discount = parseFloat(document.getElementById("discount"+index).value);
	var tax = parseFloat(document.getElementById("taxPercentage"+index).value);
	var receivingQnty = parseFloat(document.getElementById("A"+index).value);
	var damageQnty = parseFloat(document.getElementById("B"+index).value);
	var payableAmt = parseFloat(document.getElementById("payAmount").value);
	var advanceAmount = parseFloat(document.getElementById("advanceAmount").value);
	var calculateAmt = 0.0;
	var pendingAmount = parseFloat(document.getElementById("pendingAmount").value);
	var totalAmt = parseFloat(document.getElementById("total").value);
	var amountPayToVendor = 0.0;
	
	var remainingQty = parseFloat(document.getElementById("C"+index).value);
	
	if(receivingQnty > remainingQty){
		//alert("Receiving Quantity can not be greater than Remaining Quantity.");
		/* added by sourav.bhadra on 02-11-2017 */
		/* modified by sourav.bhadra on 09-11-2017 */
		document.getElementById("qtyMessage"+index).innerHTML = "Cannot Be Greater Than Remeaning Quantity";
		document.getElementById("qtyMessage"+index).style.display = "block";
		receivingQnty = 0.0;
		remainingQty = 0.0;
		document.getElementById("A"+index).value = remainingQty;
		setTimeout(function() {
			document.getElementById("qtyMessage"+index).style.display = "none";
        }, 2000);
	}
	
	if(null == receivingQnty || isNaN(receivingQnty)){
		document.getElementById("A"+index).value = "0.0";
		receivingQnty = 0.0;
	}
	if(null == damageQnty || isNaN(damageQnty)){
		document.getElementById("B"+index).value = "0.0";
		damageQnty = 0.0;
	}
	
	if(damageQnty > receivingQnty){
		//alert("Damaged Quantity can not be greater than received quantity.");
		/* added by sourav.bhadra on 02-11-2017 */
		/* modified by sourav.bhadra on 09-11-2017 */
		document.getElementById("damageMessage"+index).innerHTML = "Can not be greater than received quantity.";
		document.getElementById("damageMessage"+index).style.display = "block";
		document.getElementById("B"+index).value = "0.0";
		setTimeout(function() {
			document.getElementById("damageMessage"+index).style.display = "none";
        }, 2000);
	}else{
		var payableQty = receivingQnty-damageQnty;
		if(pendingAmount == 0){
			//alert("Total payment done");
			/* added by sourav.bhadra on 02-11-2017 */
			document.getElementById("messageDiv").innerHTML = "Total payment done.";
			document.getElementById("messageDiv").style.display = "block";
			calculateAmt = "0.0";
		}else{
			calculateAmt = parseFloat(rate*payableQty);
			calculateAmt = parseFloat(calculateAmt-(calculateAmt*discount)/100);
			calculateAmt = parseFloat(calculateAmt + ((calculateAmt * tax)/100));
			individualTotal = calculateAmt;
			calculateAmt = parseFloat(calculateAmt+payableAmt);
		}
		document.getElementById("individualTotal"+index).value = individualTotal;
	}
	
	$("input[name*='perTotal']").each(function() {
		var total = parseFloat(this.value);
		amountPayToVendor = parseFloat(amountPayToVendor + total);
	});
	document.getElementById("total").value = amountPayToVendor;
	/** modified by sourav.bhadra on 19-10-2017 **/
	/*if(calculateAmt < advanceAmount){
		calculateAmt = "0.0";
	}else*/ if(amountPayToVendor > pendingAmount){
		amountPayToVendor = pendingAmount;
	}
	document.getElementById("payAmount").value = amountPayToVendor;
	if(amountPayToVendor>0){
		/* added by sourav.bhadra on 31-07-2017 
		 * to fetch and display vendor's ledger */
		var vendorCode = document.getElementById("vendorCode").value;
		getVendorsLedger(vendorCode);
	}
}

function calculatePayingAmount(rowid){
	var payableAmt = parseFloat(document.getElementById("payAmount").value);
	var calculateAmt = parseFloat(0.0);
	var totalExpenceAmt = parseFloat(0.0);
	var total = parseFloat(document.getElementById("total").value);
	var netAmount = parseFloat(document.getElementById("expenceAmount"+rowid).value);
	var expenceAmt = parseFloat(document.getElementById("expenceAmt"+rowid).value);
	var pendingAmount = parseFloat(document.getElementById("pendingAmount").value);
	var advanceAmount = parseFloat(document.getElementById("advanceAmount").value);
	if(pendingAmount == 0){
		document.getElementById("expenceAmt"+rowid).value="0.0";
	}else{
		if(expenceAmt>netAmount){
			//alert("Paying Amount Can not be greater than Net Amount.");
			/* added by sourav.bhadra on 02-11-2017 */
			document.getElementById("messageDiv").innerHTML = "Paying Amount Can not be greater than Net Amount.";
			document.getElementById("messageDiv").style.display = "block";
			document.getElementById("expenceAmt"+rowid).value=netAmount;
		}
		$("input[name*='expenceAmt']").each(function(){ 
			var amount = parseFloat($(this).val());
			calculateAmt = parseFloat(calculateAmt+amount);
		});
		totalExpenceAmt = parseFloat(total+calculateAmt);
		document.getElementById("grandTotal").value = totalExpenceAmt;
		if(totalExpenceAmt >= pendingAmount){
			totalExpenceAmt = pendingAmount;
		}
		document.getElementById("payAmount").value=totalExpenceAmt;
	}
}


/* added by sourav.bhadra on 31-07-2017
 * to get vendor's ledger */
function getVendorsLedger(vendorCode){
	
	$.ajax({
        url: '/cedugenie/getVendorsLedgerForCommodityPO.html',
        data: "vendorCode="+(vendorCode),
        dataType: 'json',
        success: function(data) {
			if(data != ""){
				document.getElementById("ledger").value = data;
			}
        }
	});
}