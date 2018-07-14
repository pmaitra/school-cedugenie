function funFormValidate(val){
	var payementModeValue = "";
	var payementForHostelModeValue = "";
	var concatinatedValue = "";
	var textNodes = document.getElementsByName("actualCategoryAmount");
	for(var index = 0;index <textNodes.length; index++){
		var feesCategoryNodeValue = textNodes[index].parentNode.previousSibling.previousSibling.previousSibling.innerHTML;
		var feesAmountNodeValue = document.getElementById("feesCategoryAmount"+index).value;
		var paymentModeNodeForPaid = document.getElementById("radioFeesPaid"+index);
		//var paymentModeNodeForWaived = document.getElementById("radioFeesWaived"+index);
		var payableAmountValue = textNodes[index].value;
		/* added by sourav.bhadra on 18-09-2017 for due */
		var dueAmount = document.getElementById("dueAmount"+index).value;
		
		if(paymentModeNodeForPaid.checked == true){
			payementModeValue = paymentModeNodeForPaid.value;
		}
		/*if(paymentModeNodeForWaived.checked == true){
			payementModeValue = paymentModeNodeForWaived.value;
		}*/
		concatinatedValue = feesCategoryNodeValue + ";" + feesAmountNodeValue + ";" + payementModeValue + ";" + payableAmountValue + "/" + concatinatedValue ;
	}
	document.getElementById("totalSubmittedValue").value = concatinatedValue;
	if (document.getElementById("formId").value ==""){
		alert("Please Select Form Id");
		return false;
	}
	
	if(parseFloat(document.getElementById("totAmmount").value) > 0.00){
		
	}
	 if(!funAmountCal()){
			return false;
	 }		
	return true;
} 
 
function activeInactiveFieldsForChequeCash(val){
	if(document.getElementById("paymentModeBank").checked == true){
		document.getElementById("chequeNo").removeAttribute('readonly');
		document.getElementById("bankName").removeAttribute('readonly');
		document.getElementById("bankCode").removeAttribute('readonly');
		document.getElementById("bankLocation").removeAttribute('readonly');
		document.getElementById("bankList").removeAttribute('readonly');/* added by sourav.bhadra on 19-09-2017 for done */
		document.getElementById("bankList").setAttribute('required','required');/* added by sourav.bhadra on 19-09-2017 for done */
	}else{
		document.getElementById("chequeNo").value='';
		document.getElementById("bankName").value='';
		document.getElementById("bankCode").value='';
		document.getElementById("bankLocation").value='';
		document.getElementById("bankList").value='';/* added by sourav.bhadra on 19-09-2017 for done */
		document.getElementById("chequeNo").setAttribute('readonly','readonly');
		document.getElementById("bankName").setAttribute('readonly','readonly');
		document.getElementById("bankCode").setAttribute('readonly','readonly');
		document.getElementById("bankLocation").setAttribute('readonly','readonly');
		document.getElementById("bankList").setAttribute('readonly','readonly');/* added by sourav.bhadra on 19-09-2017 for done */
		document.getElementById("bankList").removeAttribute('required');/* added by sourav.bhadra on 19-09-2017 for done */
	}
 }
 
$(document).ready(function() {		
	$("#formId").change(function() {				 
		$.ajax({
	        url: '/icam/getFeeStructureForStudent.html',
	        dataType: 'json',
	        data: "klass=" + ($("#klass").val()) + "&formId=" + ($(this).val()) + "&year=" + ($("#year").val())+ "&driveName=" + ($("#driveName").val())+ "&courseName=" + ($("#courseName").val()),
	        success: function(dataDB) {	
	        	if(dataDB != "null" && dataDB !=""){ 
// 			        		document.getElementById("warningbox").style.display = "none";
// 			        		document.getElementById("warningmsg").innerHTML = "";
	        		var totVal=0;
	        		
	        		/* added by sourav.bhadra 19-09-2017 for bank */
	        		var dataDBArr = dataDB.split("/bank/");
	        		var bankArr = dataDBArr[1].split(";");
	        		var bankOption = "<option value=''>Select..</option>";
	        		if(bankArr.length != 0){
	        			for(var b=0;b<bankArr.length-1;b++){
		        			bankOption += "<option value='"+bankArr[b]+"'>"+bankArr[b]+"</option>";
		        		}
	        		}
	        		
	        		/**/
	        		var arr = dataDBArr[0].split("~");
	        		if(arr[0]!=null && arr[0]!=""){
	        			var candidateInfo = arr[0].split("#@");
	        			document.getElementById("candidateName").value=candidateInfo[0];
	        			if(document.getElementById("mGender").value==candidateInfo[1]){
	        				document.getElementById("mGender").setAttribute('checked','checked');
	        			}
	        			if(document.getElementById("fGender").value==candidateInfo[1]){
	        				document.getElementById("fGender").setAttribute('checked','checked');
	        			}
	        			document.getElementById("category").value=candidateInfo[2];
	        		}
	        if(arr[1]!=null && arr[1]!=''){
			  $('#table2 th').hide();
    			 $('#table2 tbody').remove();		    			 
    			 var row = "";
    			 var arr1 = arr[1].split("#");			    			 
    			 $('#table2 th').show();
    			  
		  		 row = $('<tr>'); 
    	         row.append($('<th style="text-align: center">  </th>').html('<u>Fees Category</u>'));
                 row.append($('<th>  </th>').html('<u>Fees Amount</u>'));
                 row.append($('<th>  </th>').html('<u>Payment Mode</u>'));
                 row.append($('<th>  </th>').html('<u>Payable Amount</u>'));
                 row.append($('<th>  </th>').html('<u>Due Amount</u>'));
                 $('#table2').append(row);
                var outerTab;
		    	for(var clist=0;clist<arr1.length-1;clist++){		
		    				var arr2 = arr1[clist].split("*");
		    				/**/
	    	                 var tabIndex = parseInt(clist+2);
	    	                 outerTab = parseInt(tabIndex);
	    	                 for(var cfees=0;cfees<arr2.length;cfees++){	
		    	                row = $('<tr>'); 
		    	                row.append($('<td style="text-align: center"></td>').html(arr2[0]));
		    	                 
		    	                 totVal=totVal+parseFloat(arr2[1]);
		    	                 
		    	                 var p='<td >'+arr2[1]+'<input type="hidden" id="feesCategoryAmount'+clist+'"  value='+arr2[1]+' readonly="readonly"></input>';
		    	                 p=p+'</td >';
		    	                 row.append(p);
		    	                
		    	                 var p='<td >';
		    	                 p=p+'<input type="radio" class="feesPaid" id="radioFeesPaid'+clist+'" name="feesPaidWaived['+clist+']" value="PAID" checked="checked" onClick="activeInactiveAmountText('+clist+');">PAID</input>';/*<input type="radio" id="radioFeesWaived'+clist+'" name="feesPaidWaived['+clist+']" class="feesPaidWaived" value="WAIVED" onClick="activeInactiveAmountText('+clist+');">WAIVED</input>';*/
		    	                 p=p+'</td >';
		    	                 row.append(p);
		    	                
		    	                 var p='<td >';
				    			 p=p+'<input type="text" class="form-control feesAmountClass" id="feesAmount'+clist+'" name="actualCategoryAmount" tabindex="'+tabIndex+'" value="0.0" onblur="calculateTotalAndDueAmount('+clist+')"> </input>';
				    			 p=p+'</td >';
				    			 row.append(p);
				    			 
				    			 /* added by sourav.bhadra on 15-09-2017 for dues */
				    			 var p='<td >';
				    			 p=p+'<input type="text" class="form-control feesAmountClass" id="dueAmount'+clist+'" name="actualCategoryDueAmount" value="'+arr2[1]+'"  readonly="readonly"> </input>';
				    			 p=p+'</td >';
				    			 row.append(p);
				    			 
		    					}
		    				$('#table2').append(row);
		    			} 				    			
    	                 
    	                 row = $('<tr>'); 
			    		 row.append($('<td style="text-align: center"></td>').html('TOTAL AMOUNT'));
			    		 document.getElementById("amountExceptHostel").value=arr[2];
    	                 row.append($('<td><input style="text-align:right;" class="form-control" type="text" id="totAmmount" name="totAmmount" value="" readonly="readonly"/></td>'));
    	                 row.append($('<td></td>').html(''));
    	                 row.append($('<td><input style="text-align:right;" class="form-control" type="text" id="totPaidAmmount" name="totAmmount" value="0.0" readonly="readonly"/></td>'));
    	                 row.append($('<td><input style="text-align:right;" class="form-control" type="text" id="totDueAmmount" name="totAmmount" value="0.0" readonly="readonly"/></td>'));
    	                 
			    		 $('#table2').append(row);
    	                 $("#totAmmount").val(arr[2]);	
    	                 $("#totDueAmmount").val(arr[2]);
    	                 
    	                 //hello
    	                 outerTab = parseInt(outerTab + 1);
    	                row = $('<tr>');
    	              	row.append($('<td style="text-align: center"></td>').html('Payment Mode'));
    	              	var paymentMode='<td ><input type="radio" id="paymentModeCash" name="paymentMode" value="CASH" tabindex="'+outerTab+'" checked onClick="activeInactiveFieldsForChequeCash(this);"/>'+"CASH";
    	              	paymentMode=paymentMode+'<td ><input style="text-align:right;"  type="radio" id="paymentModeBank" name="paymentMode" value="BANK" onClick="activeInactiveFieldsForChequeCash(this);"/>'+"CHEQUE";
    	              	paymentMode=paymentMode+'</td>';
    	                row.append($(paymentMode ));	
    	                $('#table2').append(row);
    	                
    	                
    	                row = $('<tr>'); 
    	                row.append($('<td style="text-align: center"></td>').html('Cheque No'));
    	                row.append($('<td ></td>').html(''));
    	              	var chequeNo='<td ><input style="text-align:right;" class="form-control" type="text" id="chequeNo" name="chequeNo" value="" readonly="readonly"/>';
    	              		chequeNo=chequeNo+'</td>';
    	                row.append($(chequeNo ));	
	    	           	$('#table2').append(row);
    	                
    	                row = $('<tr>'); 
    	                row.append($('<td style="text-align: center"></td>').html('Bank Name'));
    	                row.append($('<td ></td>').html(''));
    	              	var bankName='<td ><input style="text-align:right;" class="form-control" type="text" id="bankName" name="bankName" value="" readonly="readonly"/>';
    	              	bankName=bankName+'</td>';
    	                row.append($(bankName ));	
	    	           	$('#table2').append(row);
    	                
    	                row = $('<tr>'); 
    	                row.append($('<td style="text-align: center"></td>').html('Bank Code'));
    	                row.append($('<td ></td>').html(''));
    	              	var bankCode='<td ><input style="text-align:right;" class="form-control" type="text" id="bankCode" name="bankCode" value="" readonly="readonly"/>';
    	              	bankCode=bankCode+'</td>';
    	                row.append($(bankCode ));	
	    	           	$('#table2').append(row);
    	                
    	                row = $('<tr>'); 
    	              	row.append($('<td style="text-align: center"></td>').html('Bank Location'));
    	              	row.append($('<td ></td>').html(''));
    	              	var bankLocation='<td ><input style="text-align:right;" class="form-control" type="text" id="bankLocation" name="bankLocation" value="" readonly="readonly"/>';
    	              	bankLocation=bankLocation+'</td>';
    	                row.append($(bankLocation ));	
	    	           	$('#table2').append(row);
    	                
    	                /* added by sourav.bhadra on 19-09-2017
    	                 * to add bank list */
    	                row = $('<tr>'); 
    	              	row.append($('<td style="text-align: center"></td>').html('Bank'));
    	              	row.append($('<td ></td>').html(''));
    	              	var bankList='<td ><select class="form-control" id="bankList" name="bankList" readonly="readonly">'+bankOption+'</select>';
    	              	bankList=bankList+'</td>';
    	                row.append($(bankList ));	
    	                $('#table2').append(row);
    	                 //hello
    	                
    	                outerTab = parseInt(outerTab + 1);
	    				row = $('<tr>');
	              		row.append($('<td style="text-align: center"><input type="submit" value="Submit" tabindex="'+outerTab+'" id="submitValue" class="submitbtn btn btn-primary" onclick="return funFormValidate();"></input> <input type="reset" class="clearbtn btn btn-warning" value="Clear" onClick="undoHostelFees();"></input></td>'));
	              		row.append($('<td colspan="2"></td>').html(''));
	              		$('#table2').append(row);
    	                 	
				}else{
					alert("Fees Category not available");
				}
	        }
	       }
		});
	});	
});

function activeInactiveCashCheque(valPaidWaived){	
	
	document.getElementById("warningbox").style.display = "none";
	document.getElementById("warningmsg").innerHTML = "";
	if(valPaidWaived=='WAIVED'){
		document.getElementById("hiddenFeesPaidWaived").value='WAIVED';
		document.getElementById("newlink").style.display='block';
		document.getElementById("buttonDiv").style.display='block';
	}else{
		document.getElementById("newlink").style.display='none';
		document.getElementById("buttonDiv").style.display='none';
		//document.getElementById("cashAmount").removeAttribute('disabled');
		//document.getElementById("bankAmount").removeAttribute('disabled');
		document.getElementById("hiddenFeesPaidWaived").value='PAID';
	}
	return true;
}
	
function funAmountCal(){	
	 ran1 = /^\d+.?\d*$/;
 		document.getElementById("warningbox").style.display='none';
 		document.getElementById("warningmsg").innerHTML="";
	var totVal=0.00;
	var actualCategoryAmount=document.getElementsByName("actualCategoryAmount");
	for(var i=0;i<actualCategoryAmount.length;i++){
		var val=document.getElementById("feesAmount"+i).value.replace(/\s{1,}/g,'');
		if (!val.match(ran1)){
 			document.getElementById("warningbox").style.display='block';
 			document.getElementById("warningmsg").innerHTML = "Please Enter Numeric Positive Value";
//			alert("Please Enter Numeric Positive Value");
			return false;
		}else{
			val= parseFloat(val);
			if (val < 0.00){
 					document.getElementById("warningbox").style.display='block';
 					document.getElementById("warningmsg").innerHTML="Please Enter Positive Value";
//				alert("Please Enter Positive Value");
				return false;
			}

			totVal = totVal+val;
		}
	}
	

	document.getElementById("totAmmount").value=parseFloat(totVal).toFixed(2);
	return true;
}
	
	/* added by sourav.bhadra on 15-09-2017 */
	function calculateTotalAndDueAmount(rowId){
		var actualFeesAmount = parseFloat(document.getElementById("feesCategoryAmount"+rowId).value);
		var payingAmt = parseFloat(document.getElementById("feesAmount"+rowId).value);
		
		if(payingAmt == '' || isNaN(payingAmt)){/* modified by sourav.bhadra on 21-09-2017 */
			payingAmt = 0;
			document.getElementById("feesAmount"+rowId).value = payingAmt;
		}
		if(payingAmt>actualFeesAmount){
			alert("Paying Amount can not be greater than Actual Amount.");
			payingAmt = actualFeesAmount;
			document.getElementById("feesAmount"+rowId).value = payingAmt;
		}
		var dueAmt = parseFloat(parseFloat(actualFeesAmount)-parseFloat(payingAmt));
		document.getElementById("dueAmount"+rowId).value = dueAmt;
		
		var totalPayingAmount = 0;
		var totalDueAmount = 0;
		$('input[name="actualCategoryAmount"]').each(function(){
			var feeAmt = $(this).val();
			if(feeAmt == '' || isNaN(feeAmt)){/* modified by sourav.bhadra on 21-09-2017 */
				feeAmt = 0;
			}
			totalPayingAmount = parseFloat(parseFloat(totalPayingAmount)+parseFloat(feeAmt));
		});
		$('input[name="actualCategoryDueAmount"]').each(function(){
			var dueAmount = $(this).val();
			if(dueAmount == '' || dueAmount == 'NaN'){
				dueAmount = 0;
			}
			totalDueAmount = parseFloat(parseFloat(totalDueAmount)+parseFloat(dueAmount));
		});
		document.getElementById("totPaidAmmount").value = totalPayingAmount;
		document.getElementById("totDueAmmount").value = totalDueAmount;
	}
	