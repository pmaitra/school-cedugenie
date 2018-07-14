var credit=1;
var debit=1;
var checkNo=1;
function addCredit(){
	var table = document.getElementById("creditTable"); 
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	
	var cell;
	var element;
	
	cell = row.insertCell(0);
	element = document.getElementById("allLedger").cloneNode(true);
	element.name="creditLedger";
	element.required = true;
	element.id="creditLedger"+credit;
	element.setAttribute("class","form-control");
	cell.appendChild(element);
	element = document.createElement("input");
	element.type = "hidden";	    
	element.name="creditBank";
	element.id="creditBank"+credit;
	cell.appendChild(element);
	
	cell = row.insertCell(1);
	    
	cell = row.insertCell(2);
	element = document.createElement("input");
	element.type = "text";
	element.name="creditAmount";
	element.required = true;
	element.id="creditAmount"+credit;
	element.setAttribute("class","form-control");
	cell.appendChild(element);
	
	cell = row.insertCell(3);
	element = "<img src='/icam/images/minus_icon.png' onclick='deleteCredit(this);'>";
	cell.innerHTML=element;
	
	credit++;
}

function addDebit(){
	var table = document.getElementById("debitTable"); 
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	
	var cell;
	var element;
	
	cell = row.insertCell(0);
	element = document.getElementById("allLedger").cloneNode(true);
	element.name="debitLedger";
	element.required = true;
	element.id="debitLedger"+debit;
	element.setAttribute("class","form-control");
	cell.appendChild(element);
	element = document.createElement("input");
	element.type = "hidden";	    
	element.name="debitBank";
	element.id="debitBank"+debit;
	cell.appendChild(element);
	
	cell = row.insertCell(1);
	    
	cell = row.insertCell(2);
	element = document.createElement("input");
	element.type = "text";
	element.name="debitAmount";
	element.required = true;
	element.id="debitAmount"+debit;
	element.setAttribute("class","form-control");
	cell.appendChild(element);
	
	cell = row.insertCell(3);
	element = "<img src='/icam/images/minus_icon.png' onclick='deleteDebit(this);'>";
	cell.innerHTML=element;
	
	debit++;
}

function deleteCredit(obj){	
	var table = document.getElementById("creditTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
//		document.getElementById("warningbox").style.visibility='collapse';
//		document.getElementById("warningmsg").innerHTML="";
	}else{
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Atleast One Credit Row Required";
		alert("Atleast One Credit Row Required.");
	}
}

function deleteDebit(obj){	
	var table = document.getElementById("debitTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Atleast One Debit Row Required";
		alert("Atleast One Debit Row Required");
	}
} 

function createCheckNumber(sel){
	$.ajax({			
	    url: '/icam/checkForBankGroup.html',
	    	dataType: 'json',
	    	data: "ledger=" + sel.value,		    	
	    	success: function(data) {
	    		var row=sel.parentNode.parentNode;
	    		var cell=row.getElementsByTagName('td')[1];
	    		var tbid="";
	    		
	    		if(sel.name=="creditLedger")
	    			tbid=(sel.id).replace("creditLedger","creditBank");
	    		else
	    			tbid=(sel.id).replace("debitLedger","debitBank");
	    		
	    		if(data=="TRUE"){
	    			document.getElementById(tbid).value="BANK-checkNo"+checkNo;
	    			cell.innerHTML="<input type='text' class='form-control' name='checkNo"+checkNo+"'>";
	    		}else{
	    			cell.innerHTML="";	    			
	    			document.getElementById(tbid).value="OTHERS-null";
	    		}
	    		checkNo++;
	    	}
		});
	
}

function showBankDetails(mode){
	if(mode.value=="CASH"){
		document.getElementById("chequeNo").value="";
		document.getElementById("bankName").value="";
		document.getElementById("chequeNo").setAttribute('disabled', 'disabled');
		document.getElementById("bankName").setAttribute('disabled', 'disabled');
	}else{
		document.getElementById("chequeNo").removeAttribute('disabled');
		document.getElementById("bankName").removeAttribute('disabled');
	}
}
function valvcbidate() {
	var creditAmountBox=document.getElementsByName("creditAmount");
	var debitAmountBox=document.getElementsByName("debitAmount");
		
	var creditAmount=0.0;
	var debitAmount=0.0;
	for(var i=0;i<creditAmountBox.length;i++){		
		var ledId=creditAmountBox[i].id.replace("creditAmount","creditLedger");
		if(!(nullValidation(document.getElementById(ledId)))){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Select Valid Credit Ledger";
			alert("Select Valid Credit Ledger");
			return false;
		}
		if(!(numericValidation(creditAmountBox[i]))){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Enter Valid Credit Amount";
			alert("Enter Valid Credit Amount");
			return false;
		}
		var amount=parseFloat(creditAmountBox[i].value);
		creditAmount=creditAmount+amount;
	}
	for(var i=0;i<debitAmountBox.length;i++){
		var ledId=debitAmountBox[i].id.replace("debitAmount","debitLedger");
		if(!(nullValidation(document.getElementById(ledId)))){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Select Valid Debit Ledger";
			alert("Select Valid Debit Ledger");
			return false;
		}
		if(!(numericValidation(debitAmountBox[i]))){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML = "Enter Valid Debit Amount";
			alert("Enter Valid Debit Amount");
			return false;
		}
		
		var amount=parseFloat(debitAmountBox[i].value);
		debitAmount=debitAmount+amount;
	}
	if(creditAmount!=debitAmount){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Debit And Credit Amount Should Be Equal";
		alert("Debit And Credit Amount Should Be Equal");
		return false;
	}
	return true;
}

/* new added by sourav.bhadra on 26-06-2017*/
function bankDetails(mode){
	if(mode.value=="BANK"){
		/*<!-- modified by ranita.sur on 25082017 for petty cash entry -->*/
		var a=document.getElementById("voucherTypeCode").value;
		if( a==('PAYMENT')){
			document.getElementById("forBankReciept").style.display = 'none';	
			document.getElementById("forBank").style.display = 'block';
			
			document.getElementById("chequeNo").removeAttribute("readonly");
			document.getElementById("bankName").removeAttribute("readonly");
			document.getElementById("bankName").setAttribute("required", "required");
			
			/* modified on 29-08-2017 */
			document.getElementById("bankNames").removeAttribute("required");
			document.getElementById("chequeNos").removeAttribute("required");
			document.getElementById("bankCodes").removeAttribute("required");
			document.getElementById("bankLocations").removeAttribute("required");
		}else if( a==('RECEIPT')){
			document.getElementById("forBank").style.display = 'none';
			document.getElementById("forBankReciept").style.display = 'block';
			document.getElementById("bankNames").setAttribute("required", "required");
			document.getElementById("chequeNos").setAttribute("required", "required");
			document.getElementById("bankCodes").setAttribute("required", "required");
			document.getElementById("bankLocations").setAttribute("required", "required");
			
			/* modified on 29-08-2017 */
			document.getElementById("bankName").removeAttribute("required");
		}
	}else{
		document.getElementById("forBank").style.display = 'none';
		
		/* modified on 29-08-2017 */
		document.getElementById("forBankReciept").style.display = 'none';
		document.getElementById("bankNames").removeAttribute("required");
		document.getElementById("chequeNos").removeAttribute("required");
		document.getElementById("bankCodes").removeAttribute("required");
		document.getElementById("bankLocations").removeAttribute("required");
		document.getElementById("bankName").removeAttribute("required");
	}
	
}


/*<!-- modified by ranita.sur on 25082017 for petty cash entry -->*/
function getval(sel){
bankDetails(sel);
	if(sel.value == 'BANK'){
		$("#bankName").change(function (){
			$.ajax({
		    url: '/icam/getBankAllDetails.html',
		    dataType: 'json',
		    data:"bankName=" + $("#bankName").val(),
		    success: function(dataDB) {
		    	if(dataDB != "null" && dataDB !=""){
		    		var arr = dataDB.split("~");
		    		document.getElementById("bankCode").value=arr[0];
		    		document.getElementById("accountNo").value=arr[1];
		    		document.getElementById("bankLocation").value=arr[2];
		        }
		    }
			});
		});
	}
}
/*<!-- modified by ranita.sur on 25082017 for petty cash entry -->*/
function checkValues(){
	document.getElementById("departmentDiv").style.display = "none";
	document.getElementById("incExpDiv").style.display = "none";
	document.getElementById("ticketNoDiv").style.display = "none";
	document.getElementById("debitAdd").style.display = "none";
	$.ajax({
	    url: '/icam/getVoucherTypeDetails.html',
	    dataType: 'json',
	    data:"voucherTypeCode=" + $("#voucherTypeCode").val(),
	    success: function(dataDB) {
	    	if(dataDB != "null" && dataDB !=""){
	    		/* department --> income/expence --> ticket no --> multi debit ledger */
	    		var arr = dataDB.split("*");
	    		if(arr[0] == "true"){
	    			document.getElementById("departmentDiv").style.display = "block";
	    		}
				if(arr[1] == "true"){
					document.getElementById("incExpDiv").style.display = "block";
				}
				if(arr[2] == "true"){
					document.getElementById("ticketNoDiv").style.display = "block";
				}
				if(arr[3] == "true"){
					document.getElementById("debitAdd").style.display = "block";
				}
	        }
	    }
		});
	/**/
	$("#paymentMode").val('0');
	var a=document.getElementById("voucherTypeCode").value;
	if( a==('PAYMENT')|| a==('RECEIPT')){
		document.getElementById("paymentModeDiv").style.display='block';
	}else{
		document.getElementById("paymentModeDiv").style.display='none';
	}
	/* added by sourav.bhadra on 23-04-2018 */
	if(a==('JOURNAL')){
		$.ajax({
		    url: '/icam/getLedgerListForJournalVoucher.html',
		    dataType: 'json',
		    data:"voucherTypeCode=" + $("#voucherTypeCode").val(),
		    success: function(dataDB) {
		    	if(dataDB != "null" && dataDB !=""){
		    		var arr = dataDB.split("*");
		    		var option = "<option value='0'>Select...</option>";
		    		for(var i=0; i<arr.length-1; i++){
		    			option += "<option value='"+arr[i]+"'>"+arr[i]+"</option>";
		    		}
		    		document.getElementById("creditLedger0").innerHTML = option;
		    		document.getElementById("debitLedger0").innerHTML = option;
		        }
		    }
			});
	}
}