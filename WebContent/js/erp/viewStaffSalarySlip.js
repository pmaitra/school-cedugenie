function calculateAmount(){
	var earn=0;
	var salEarn = 0;
	var amount=document.getElementsByName("extraAmount");
	
	var table = document.getElementById("salaryStructure"); 
	var rowCount = table.rows.length;
	for(var i=1;i<rowCount;i++){
		var earningAmount = document.getElementById("salaryStructure").rows[i].cells[1].innerHTML;
		earningAmount=parseFloat(earningAmount);
		salEarn=salEarn+earningAmount;
	}
		 
	for(var i=0;i<amount.length;i++){
		var id=amount[i].id;
		var val=amount[i].value;
		val=parseFloat(val);
		
		var valOfTextBox=amount[i].value.replace(/\s{1,}/g,'');
		if (isNaN(valOfTextBox)){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML="Please Enter Numeric Value";
			alert("Please Enter Numeric Value");
			return false;
		}
		if(valOfTextBox == ""){
			document.getElementById(id).value=0;
		}
		if (valOfTextBox<0){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningbox").innerHTML="Please Enter Positive Value";
			alert("Please Enter Positive Value");
			return false;
		}
		
			earn=earn+val;
		
	}
	var preDeductionAmount = document.getElementById("totalDeductionAmount").value;
	preDeductionAmount=parseFloat(preDeductionAmount);
	var totalGross = salEarn+earn;
	var totalNet = totalGross - preDeductionAmount;
	
	document.getElementById("grossAmount").value=totalGross;
	document.getElementById("netAmount").value=totalNet;
	document.getElementById("hiddenSal").value=totalGross;
	
	
	document.getElementById("grossNetTable").rows[1].cells[0].innerHTML = totalGross;
	document.getElementById("grossNetTable").rows[1].cells[2].innerHTML = totalNet;
	///////////////////////////////////////////////////////////////////////////////////////
	$.ajax({
        url: '/icam/ajaxCallForCalculateSlabTax.html',
        dataType: 'json',
        data: "totalGross=" + (totalGross)+ "&totalNet=" + (totalNet)+ "&userId=" + ($("#hiddenId").val())+ "&month=" + ($("#selectedMonth").val())+ "&year=" + ($("#selectedYear").val()),
        success: function(dataDB) {
        	if(dataDB != null)
			{
        		var splitedData = dataDB.split("@@");	
        		for(var i=0;i<splitedData.length-1;i++)
				{
				    var arraydata = splitedData[i].split("~");
				    if(arraydata[1] == "INCOME TAX"){
				    	document.getElementById("incomeTaxTable").rows[0].cells[1].innerHTML = arraydata[3];
				    	document.getElementById("slabTaxForIncomeTax").value = arraydata[0]+"-"+arraydata[1]+"-"+arraydata[3];
				    }
				    if(arraydata[1] != "INCOME TAX"){
				    	var table = document.getElementById("salaryStructureForDeduction"); 
				    	var rowCount = table.rows.length-2;
				    	for(var j=1;j<rowCount;j++){
				    		var salaryHeadName = document.getElementById("salaryStructureForDeduction").rows[j].cells[0].innerHTML;
				    		
				    		if(salaryHeadName.trim() == arraydata[1]){
				    			document.getElementById("salaryStructureForDeduction").rows[j].cells[1].innerHTML = arraydata[3];
				    			document.getElementById(arraydata[1]).value = arraydata[0]+"-"+arraydata[1]+"-"+arraydata[3];
				    		}
				    	}
				    	
				    }
				}
        		
			}	        	
        }
	});
	///////////////////////////////////////////////////////////////////////////////////////
}

$(document).ready(function() {	
	 $("#userId").autocomplete({
	 	source: '/icam/getStaffUserIdList.html'
	 });
});