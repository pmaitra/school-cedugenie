
//Added By ranita.sur on 08/08/2017 ford edit daybook details
function showdayBookDetails(date,ledgerCode,voucherType,voucherNumber,narration,amount,debit,index){
	$('#daybookDetails> tbody').empty();
 	if(date != null && date!=""){
 		/*Modified for ledger and vouchertype in popup 22082017*/
 		var option="<option value=''>Select</option>";
 		
 		$.ajax({
 		    url: '/cedugenie/getAllLedgerForDayBookEdit.html',
 		    dataType: 'json',
 		    success: function(dataDB){
 		  	if(dataDB != null && dataDB != ""){
 		    		var arr = dataDB.split("~");
 		    		for(var i=0;i<arr.length-1;i++){
 		   			if(arr[i]==ledgerCode.trim()){
 		   				option+="<option value='"+arr[i]+"' selected>"+arr[i]+"</option>";
 		    			}else{
 		    				option+="<option value='"+arr[i]+"'>"+arr[i]+"</option>";
 		    			}
 		    		}
 		    	document.getElementById('ledgerCode').innerHTML=option;
 		        }
 		    }
 			});
 		/*Modified for ledger and vouchertype in popup 22082017*/ 
 		var option1="<option value=''>Select</option>";
 		$.ajax({
 		    url: '/cedugenie/getAllVoucherForDayBookEdit.html',
 		    dataType: 'json',
 		    success: function(dataDB){
 		  if(dataDB != null && dataDB != ""){
 		  			var arr = dataDB.split("~");
 		    		for(var i=0;i<arr.length-1;i++){
 		   			if(arr[i]==voucherType.trim()){
 		   			option1+="<option value='"+arr[i]+"' selected>"+arr[i]+"</option>";
 		    			}else{
 		    				option1+="<option value='"+arr[i]+"'>"+arr[i]+"</option>";
 		    			}
 		    		}
 		    	document.getElementById('voucherType').innerHTML=option1;
 		        }
 		    }
 			});
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='date' id='date' class = 'form-control'  value='"+date+"'></td>" +
 		/*Modified for ledger and vouchertype in popup 22082017*/
 				"<td><select id='ledgerCode'></select></td>" +
 				"<td><select id='voucherType'></select></td>" +
 				"<td><input type='text' name='voucherNumber' id='voucherNumber' class = 'form-control'  value='"+voucherNumber+"' readonly></td>" +
 				"<td><input type='text' name='narration' id='narration' class = 'form-control'  value='"+narration+"'></td>";
 				if(debit=="true"){
 					row+="<td><input type='text' name='amount' id='amount' class = 'form-control'  value='"+amount+"'></td><td></td></tr>";
 				}
 				else{
 					row+="<td></td><td><input type='text' name='amount' id='amount' class = 'form-control'  value='"+amount+"'></td></tr>";
 				}
 					
 					
 					
 		$("#daybookDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateDaybook");
 	btn.setAttribute("onclick","saveData('"+index+"','"+date+"','"+ledgerCode+"','"+voucherType+"','"+voucherNumber+"','"+narration+"','"+amount+"','"+debit+"');");
	
	}

function saveData(rowId,date,ledgerCode,voucherType,voucherNumber,narration,amount,debit){
	
	var dateDetail=document.getElementById("date").value;
	/*Modified for ledger and vouchertype in popup 22082017*/
	var ledgerName=document.getElementById("ledgerCode").value;
	var vouchertype=document.getElementById("voucherType").value;
	var vouchernumber=document.getElementById("voucherNumber").value;
	var narrations=document.getElementById("narration").value;
    var amountDetail=document.getElementById("amount").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getNewDate").value = dateDetail;
	/*Modified for ledger and vouchertype in popup 22082017*/
	document.getElementById("getNewLedgerName").value = ledgerName;
	document.getElementById("getNewVoucherType").value = vouchertype;
	document.getElementById("getNewVoucherNo").value = vouchernumber;
	document.getElementById("getNewNarration").value = narrations;
	document.getElementById("getDebitStatus").value = debit;
	if(debit=="true"){
		document.getElementById("getNewDebit").value = amountDetail;
    }else{
	    document.getElementById("getNewCredit").value = amountDetail;
		
	}
	document.editDaybookDetails.submit();

}
$(document).ready(function() {	

	

});