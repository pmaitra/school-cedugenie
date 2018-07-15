var allnames;
var buttonName="";
$(".vendorNameClass").each(function(){
	$(this).click(function(){
		buttonName=$(this);
		$.ajax({
			url:'/cedugenie/getNameOfVendors.html',
			dataType:'json',
			success:function(data){
				allnames = data.split("*");
				$(buttonName).autocomplete({
					source: allnames,
					select: function (event, ui){
						var vendorName = ui.item.value;
						$.ajax({
							url: '/cedugenie/getIdAgainstName.html',
							dataType: 'json',
							data: "vendorName=" + vendorName,
							success: function(data) {
								if(data != null && data!=""){
									$(buttonName).parent().prev().find('input').val(data);
								}
								else{
									alert("User Name Not Found");
								}
							}			        
						});
					}
				});
			}
		});
	});
});

var flag = 0;
function calculate(count){
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var	individualTotOrder="individualTotOrder"+count;
	var	individualPrice="individualPrice"+count;
	var	individualDiscount="individualDiscount"+count;
	var	tax="tax"+count;
	var	total="total"+count;
	var individualTotOrderVal=document.getElementById(individualTotOrder).value;
	var individualPriceVal=document.getElementById(individualPrice).value;
	var individualDiscountVal=document.getElementById(individualDiscount).value;
	var taxVal=document.getElementById(tax).value;
	
	individualTotOrderVal=individualTotOrderVal.replace(/\s{1,}/g,'');
	individualPriceVal=individualPriceVal.replace(/\s{1,}/g,'');
	individualDiscountVal=individualDiscountVal.replace(/\s{1,}/g,'');
	taxVal=taxVal.replace(/\s{1,}/g,'');
	
	if(individualTotOrderVal != ""){
		individualTotOrderVal=parseFloat(individualTotOrderVal);
	}
	
	if(individualPriceVal != ""){
		if(!(individualPriceVal.match(regPositiveNum))){
			flag = 1;
			document.getElementById(individualPrice).value = "0.0";
		}
		if((individualPriceVal.match(regPositiveNum))){
			flag = 0;
		}
	}
	if(individualPriceVal == ""){
		flag = 1;
		document.getElementById(individualPrice).value = "0.0";
	}
	
	if(individualDiscountVal != ""){
		if(!(individualDiscountVal.match(regPositiveNum))){
			flag = 1;
			document.getElementById(individualDiscount).value = "0.0";
		}
		if((individualDiscountVal.match(regPositiveNum))){
			flag = 0;
		}
	}
	if(individualDiscountVal == ""){
		flag = 1;
		document.getElementById(individualDiscount).value = "0.0";
	}
	
	if(taxVal != ""){
		if(!(taxVal.match(regPositiveNum))){
			flag = 1;
			document.getElementById(tax).value = "0.0";
		}
		if((taxVal.match(regPositiveNum))){
			flag = 0;
		}
	}
	if(taxVal == ""){
		flag = 1;
		document.getElementById(tax).value = "0.0";
	}
	
	
	if(flag == 0){
		var totalBill=individualTotOrderVal*individualPriceVal;
		var grossTotal=(totalBill-((totalBill*individualDiscountVal)/100));
		var netTotal=(grossTotal+((grossTotal*taxVal)/100));	
		document.getElementById(total).value=netTotal;	
		
		
	/*added by ranita.sur on 04082017 for book No decreasing when budget is exceeded	*/
		var totalExpence = parseFloat(parseFloat(budgetExpence) + parseFloat(netTotal));
		var balance = parseFloat(parseFloat(budgetBalance) - parseFloat(netTotal));
		document.getElementById("totalExpence").value = totalExpence;
		document.getElementById("balance").value = balance;
		
		if(parseFloat(balance) < 0){
			//<!-- added by ranita.sur on 08092017 -->
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "The current purchase order is exceeding budget allocated.";
			document.getElementById("individualTotOrder"+count).removeAttribute("readonly");
			document.getElementById("submit").setAttribute("disabled","disabled");
		}
		else
			{
			document.getElementById("submit").removeAttribute("disabled");
			}
	}
	if(flag == 1){
		alert("Please Enter Proper Numeric Data.");		
	}
	
}


function validateReceivedRequisition(){
	var rname = /^[A-Za-z ]{2,}$/;
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var vendorName = document.getElementsByName("vendorName");
	var individualPrice = document.getElementsByName("individualPrice");
	var individualDiscount = document.getElementsByName("individualDiscount");
	var tax = document.getElementsByName("tax");
		
	for(var i = 0; i<vendorName.length; i++){
		var vName = vendorName[i].value;
		if(vName == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter Vendor name. "+(i+1)+" .";		
			return false;
		}
		if(vName != ""){
			if (!vName.match(rname)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter a valid Vendor name";
				return false;
			}
		}
	}
	for(var j = 0; j<individualPrice.length; j++){
		var iPrice = individualPrice[j].value;
		if(iPrice == "0.0"){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML = "Please Provide Price of row no."+(j+1)+".";
			return false;
		}
		if(iPrice != "0.0"){
			if (!iPrice.match(regPositiveNum)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Please Provide Price in numeric value of row no."+(j+1)+".";
				return false;
			}
		}
	}	
	for(var k = 0; k<individualDiscount.length; k++){
		var iDiscount = individualDiscount[k].value;
		if(iDiscount != "0.0"){
			if (!iDiscount.match(regPositiveNum)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Please Provide Discount in numeric value of row no."+(k+1)+".";
				return false;
			}
		}
	}	
	for(var m = 0; m<tax.length; m++){
		var taxVal = tax[m].value;
		if(taxVal != "0.0"){
			if (!taxVal.match(regPositiveNum)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Please Provide Tax in numeric value of row no."+(m+1)+".";
				return false;
			}
		}
	}	
}

/* added by sourav.bhadra on 22-06-2017 */
function getTaxPercentages(rowid){
	var taxCode = document.getElementById("taxStatus"+rowid).value;
	$.ajax({
        url: '/cedugenie/getTaxPercentageAgainstTaxCode.html',
        data: "taxCode="+(taxCode),
        dataType: 'json',
        success: function(data) {
			if(data != ""){
				document.getElementById("tax"+rowid).value = data;
				calculate(rowid);
			}
        }
	});
	
}
 /* Added By ranita.sur on 03082017 for deleting row */ 
$(document).ready(function() {	
	
		//document.getElementById("departmentBudgetTable").style.display="none";
		var dept = "LIBRARY DEPARTMENT";
		$.ajax({
		    url: '/cedugenie/getDepartmentBudgetDetails.html',
		    	dataType: 'json',
		    	data: "departmentCode=" + dept,		    	
		    	success: function(data) {
		    		if(null != data){
		    			var budget = data.split("~");
		    			window.budgetExpence=budget[1];
		    			window.budgetBalance=budget[2];
		    			document.getElementById("budgetAmount").value=budget[0];
		    			document.getElementById("totalExpence").value=budget[1];
		    			document.getElementById("balance").value=budget[2];
		    			//document.getElementById("departmentBudgetTable").style.display="block";
		    		}
		    	}
		});

});

