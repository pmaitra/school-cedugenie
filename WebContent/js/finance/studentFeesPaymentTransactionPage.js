$(document).ready(function() {
	 $('#transactionDate').Zebra_DatePicker();
	 
	 $('#transactionDate').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
 });
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
    element.id="creditLedger"+credit;
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
    element.id="creditAmount"+credit;
    element.setAttribute("class","textfield");
    cell.appendChild(element);
    
    cell = row.insertCell(3);
    element = "<img src='/sms/images/minus_icon.png' onclick='deleteCredit(this);'>";
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
    element.id="debitLedger"+debit;
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
    element.id="debitAmount"+debit;
    element.setAttribute("class","textfield");
    cell.appendChild(element);
    
    cell = row.insertCell(3);
    element = "<img src='/sms/images/minus_icon.png' onclick='deleteDebit(this);'>";
    cell.innerHTML=element;
    
    debit++;
}

function deleteCredit(obj){	
	var table = document.getElementById("creditTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Credit Row Required.";
	}
}
function deleteDebit(obj){	
	var table = document.getElementById("debitTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Debit Row Required.";
	}
} 



function createCheckNumber(sel){
	$.ajax({			
	    url: '/sms/checkForBankGroup.html',
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
	    			cell.innerHTML="<input type='text' class='textfield' name='checkNo"+checkNo+"'>";
	    		}else{
	    			cell.innerHTML="";	    			
	    			document.getElementById(tbid).value="OTHERS-null";
	    		}
	    		checkNo++;
	    	}
		});
	
}

function validate() {
	var creditAmountBox=document.getElementsByName("creditAmount");
	var debitAmountBox=document.getElementsByName("debitAmount");
	
	if(!(nullValidation(document.getElementById("transactionDate")))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML = "Enter Transaction Date";
		return false;
	}
	
	var creditAmount=0.0;
	var debitAmount=0.0;
	for(var i=0;i<creditAmountBox.length;i++){		
		var ledId=creditAmountBox[i].id.replace("creditAmount","creditLedger");
		if(!(nullValidation(document.getElementById(ledId)))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Select Valid Credit Ledger";
			return false;
		}
		if(!(numericValidation(creditAmountBox[i]))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Enter Valid Credit Amount";
			return false;
		}
		var amount=parseFloat(creditAmountBox[i].value);
		creditAmount=creditAmount+amount;
	}
	for(var i=0;i<debitAmountBox.length;i++){
		var ledId=debitAmountBox[i].id.replace("debitAmount","debitLedger");
		if(!(nullValidation(document.getElementById(ledId)))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Select Valid Debit Ledger";
			return false;
		}
		if(!(numericValidation(debitAmountBox[i]))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Enter Valid Debit Amount";
			return false;
		}
		
		var amount=parseFloat(debitAmountBox[i].value);
		debitAmount=debitAmount+amount;
	}
	if(creditAmount!=debitAmount){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML = "Debit And Credit Amount Should Be Equal";
		return false;
	}
	return true;
}