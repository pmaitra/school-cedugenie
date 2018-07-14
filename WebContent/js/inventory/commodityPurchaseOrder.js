
function getRequisitionDetails(){
	//alert("hiii");
	$.ajax({
	    url: '/icam/requisitionDetailsAgainstRequisitionCode.html',
	    	dataType: 'json',
	    	data: "requisitionCode=" + ($("#purchaseOrderDesc").val()),		    	
	    	success: function(data) {
	    			if(data != ""){
	    				data = data.split("+-+");
		    		 
	    				var table = document.getElementById("commodityTable");
		    			deleteRows(table);
		    		
			    		$("#departmentCode").val(data[0]);
			    		$("#vendor").val(data[1]);
			    		$("#totalQtyOrdered").val(data[2]);
			    		$("#totalCommodityAmount").val(data[3]);
			    		$("#vendorCode").val(data[5]);

			    		var dataArr = data[4].split("*");
		    		
			    		var p = 1;
			    		
			    		for(var i=0;i<dataArr.length-1;i++){		
		    			  		
			    			var innerData = dataArr[i].split("##");	 
			    			 
			    	        var rowCount = table.rows.length;
			    	        var row = table.insertRow(rowCount-1);
			    	        
							/*var cell;
							
			    	        cell = row.insertCell(0);
			    	        cell.innerHTML = innerData[0];
			    	        
			    	        cell = row.insertCell(1);
			    	        cell.innerHTML = innerData[3];
			    	        
			    	        cell = row.insertCell(2);
			    	        cell.innerHTML = innerData[2];
			    	        
			    	        cell = row.insertCell(3);
			    	        cell.innerHTML = innerData[1];
			    	        
			    	        cell = row.insertCell(4);
			    	        cell.innerHTML = innerData[4];*/
			    	        var cell,element;
							
			    	        cell = row.insertCell(0);
			    	        element = document.createElement("input");
			    	        element.type = "hidden";
			    	        element.name="commodityCode";
			    	        element.id="commodityCode"+p;
			    	        element.value=innerData[0];
			    	        element.setAttribute("class","form-control");
			    	        element.setAttribute("readonly","readonly");
			    	        cell.appendChild(element);	
			    	        
							element = document.createElement("input");
			    	        element.type = "text";
			    			element.name="commodityName";
			    			element.id="commodityName"+p;
		    				element.value=innerData[0];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    	            cell.appendChild(element);
			    			
		    				cell = row.insertCell(1);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="purchaseRate";
		    	            element.id="purchaseRate"+p;
		    				element.value=innerData[3];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    				cell.appendChild(element);
		    				
		    				/* added by sourav.bhadra on 28-07-2017 
		    				 * to display commodity unit */
		    				cell = row.insertCell(2);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="commodityUnit";
		    	            element.id="commodityUnit"+p;
		    				element.value=innerData[2];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    				cell.appendChild(element);
		    				
		    				cell = row.insertCell(3);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="quantity";
		    	            element.id="quantity"+p;
		    				element.value=innerData[1];
		    				element.setAttribute("class","form-control");
		    				 element.setAttribute("readonly","readonly");
		    				cell.appendChild(element);
		    				
							cell = row.insertCell(4);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="total";
		    	            element.value=innerData[4];
		    	            element.setAttribute("class","form-control");
		    	            element.setAttribute("readonly","readonly");
		    	            element.id="total"+p;
		    				cell.appendChild(element);
			    	        
			    	        p++;
			    	       
		    		
	    		}
			    		
			    		/* new added by sourav.bhadra
			    		 * on 17-06-2017
			    		 * for expence */
			    		var table1 = document.getElementById("expenseTable");
			    		deleteRow(table1);
			    		var q=1;
			    		//ssalert("dataArr="+dataArr);
			    		for(var j=0;j<dataArr.length-1;j++){		    		
			    			var innerData = dataArr[j].split("##");	 
			    			 
			    	        var rowCount = table1.rows.length;
			    	        var row = table1.insertRow(rowCount-1);
			    	        
							var cell,element;
							
			    	        cell = row.insertCell(0);
			    	        element = document.createElement("input");
			    	        element.type = "hidden";
			    	        element.name="commodityCodeInExpence";
			    	        element.id="commodityCodeInExpence"+q;
			    	        element.value=innerData[0];
			    	        element.setAttribute("class","form-control");
			    	        //element.setAttribute("readonly","readonly");
			    	        cell.appendChild(element);	
			    	        
							element = document.createElement("input");
			    	        element.type = "text";
			    			element.name="commodityNameInExpence";
			    			element.id="commodityNameInExpence"+q;
		    				element.value=innerData[0];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    	            cell.appendChild(element);
			    			
		    				cell = row.insertCell(1);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="expenceDescription";
		    	            element.id="expenceDescription"+q;
		    				element.setAttribute("class","form-control");
		    				cell.appendChild(element);
		    				
		    				var option = "<option value=''>Select...</option>"
		    					+"<option value='PERHOUR'>Per Hour</option>"
								+"<option value='FIXED'>Fixed</option>";
		    				
		    				cell = row.insertCell(2);
		    	            element = document.createElement("select");
		    	            element.name="expenceType";
		    	            element.id="expenceType"+q;
		    				element.setAttribute("class","defaultselect");
		    	            element.innerHTML=option;
		    				cell.appendChild(element);
		    				
		    				cell = row.insertCell(3);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="expenceAmount";
		    	            element.value="0.00";
		    	            element.id="expenceAmount"+q;
		    	            element.setAttribute("placeholder","0.00");  //modified by sourav.bhadra on 26-02-2018 
		    	            element.setAttribute("class","form-control");
		    	            element.setAttribute("onfocus","this.value=''");
		    	            element.setAttribute("onblur","totalExpenceAmountCalculation()");
		    	            cell.appendChild(element);
							
							q++;
			    		}
			    		/*document.getElementById("commodityDiv").style.display = "block";
			    		document.getElementById("expenseDiv").style.display = "block";
			    		document.getElementById("amountTable").style.display = "block";*/
	    	}
	    			
	    	document.getElementById("commodityDiv").style.display = "block";
	    	document.getElementById("expenseDiv").style.display = "block";
	    }
	});
}
		

function deleteRows(obj){	
	var table = document.getElementById("commodityTable");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

function deleteRow(obj){	
	var table = document.getElementById("expenseTable");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

/*$(document).ready(function() {	
	$("#vendor").change(function(){
		$("#vendorCode").val($("#vendor").val());
		if($("#vendor").val()!=""){
			$.ajax({
		    url: '/icam/vendorCommodityList.html',
		    	dataType: 'json',
		    	data: "vendorCode=" + ($("#vendor").val()),		    	
		    	success: function(data) {
		    		if(data != ""){
			    		data=data.split("+-+");
			    		 
			    		var table=document.getElementById("commodityTable");
			    		deleteRows("commodityTable");
			    		
			    		var p=1;
			    		for(var i=0;i<data.length-1;i++){		    		
			    			var innerData=data[i].split("##");	 
			    			 
			    	        var rowCount = table.rows.length;
			    	        var row = table.insertRow(rowCount-1);
			    	        
							var cell,element;
							
			    	        cell = row.insertCell(0);
			    	        element = document.createElement("input");
			    	        element.type = "hidden";
			    	        element.name="commodityCode";
			    	        element.id="commodityCode"+p;
			    	        element.value=innerData[0];
			    	        element.setAttribute("class","form-control");
			    	        element.setAttribute("readonly","readonly");
			    	        cell.appendChild(element);	
			    	        
							element = document.createElement("input");
			    	        element.type = "text";
			    			element.name="commodityName";
			    			element.id="commodityName"+p;
		    				element.value=innerData[0];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    	            cell.appendChild(element);
			    			
		    				cell = row.insertCell(1);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="purchaseRate";
		    	            element.id="purchaseRate"+p;
		    				element.value=innerData[1];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    				cell.appendChild(element);
		    				
		    				 added by sourav.bhadra on 28-07-2017 
		    				 * to display commodity unit 
		    				cell = row.insertCell(2);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="commodityUnit";
		    	            element.id="commodityUnit"+p;
		    				element.value=innerData[2];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    				cell.appendChild(element);
		    				
		    				cell = row.insertCell(3);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="quantity";
		    	            element.id="quantity"+p;
		    				element.value="0.00";
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("onfocus","this.value=''");
		    				element.setAttribute("onblur","CalculateTotal("+p+");");
		    				cell.appendChild(element);
		    				
		    				cell = row.insertCell(4);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="discount";
		    	            element.value="0.00";
		    	            element.id="discount"+p;
		    	            element.setAttribute("class","form-control");
		    	            element.setAttribute("onfocus","this.value=''");
		    	            element.setAttribute("onblur","CalculateTotal("+p+");");
		    				cell.appendChild(element);
							
							cell = row.insertCell(5);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="total";
		    	            element.value="0.00";
		    	            element.setAttribute("class","form-control");
		    	            element.setAttribute("readonly","readonly");
		    	            element.id="total"+p;
		    				cell.appendChild(element);
			    	        
			    	        p++;
			    		}
			    		
			    		 new added by sourav.bhadra
			    		 * on 17-06-2017
			    		 * for expence 
			    		var table1=document.getElementById("expenseTable");
			    		deleteRows("expenseTable");
			    		var q=1;
			    		for(var i=0;i<data.length-1;i++){		    		
			    			var innerData=data[i].split("##");	 
			    			 
			    	        var rowCount = table1.rows.length;
			    	        var row = table1.insertRow(rowCount-1);
			    	        
							var cell,element;
							
			    	        cell = row.insertCell(0);
			    	        element = document.createElement("input");
			    	        element.type = "hidden";
			    	        element.name="commodityCodeInExpence";
			    	        element.id="commodityCodeInExpence"+q;
			    	        element.value=innerData[0];
			    	        element.setAttribute("class","form-control");
			    	        //element.setAttribute("readonly","readonly");
			    	        cell.appendChild(element);	
			    	        
							element = document.createElement("input");
			    	        element.type = "text";
			    			element.name="commodityNameInExpence";
			    			element.id="commodityNameInExpence"+q;
		    				element.value=innerData[0];
		    				element.setAttribute("class","form-control");
		    				element.setAttribute("readonly","readonly");
		    	            cell.appendChild(element);
			    			
		    				cell = row.insertCell(1);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="expenceDescription";
		    	            element.id="expenceDescription"+q;
		    				element.setAttribute("class","form-control");
		    				cell.appendChild(element);
		    				
		    				var option = "<option value=''>Select...</option>"
		    					+"<option value='PERHOUR'>Per Hour</option>"
								+"<option value='FIXED'>Fixed</option>";
		    				
		    				cell = row.insertCell(2);
		    	            element = document.createElement("select");
		    	            element.name="expenceType";
		    	            element.id="expenceType"+q;
		    				element.setAttribute("class","defaultselect");
		    	            element.innerHTML=option;
		    				cell.appendChild(element);
		    				
		    				cell = row.insertCell(3);
		    	            element = document.createElement("input");
		    	            element.type = "text";
		    	            element.name="expenceAmount";
		    	            element.value="0.00";
		    	            element.id="expenceAmount"+q;
		    	            element.setAttribute("placeholder","0.00");  modified by sourav.bhadra on 26-02-2018 
		    	            element.setAttribute("class","form-control");
		    	            element.setAttribute("onfocus","this.value=''");
		    	            element.setAttribute("onblur","totalExpenceAmountCalculation()");
		    	            cell.appendChild(element);
							
							q++;
			    		}
			    		document.getElementById("commodityDiv").style.display = "block";
			    		document.getElementById("expenseDiv").style.display = "block";
			    		document.getElementById("amountTable").style.display = "block";
			    	 }else{
		    			document.getElementById("commodityDiv").style.display = "block";
		    			document.getElementById("expenseDiv").style.display = "block";
		    			document.getElementById("amountTable").style.display = "block";
		    			
		    			alert("No Commodity Has Been Mapped For Selected Vendor");
			    	 }
		    	}
			});
		}
		 added by sourav.bhadra on 31-07-2017 
		 * to get vendor's ledger for PO 
		getVendorsLedger($("#vendor").val());
	});

	
	
	$("#addNewCommodityButton").click(function(){
		$.ajax({			
	    url: '/icam/getRemeaningCommodities.html',
	    	dataType: 'json',
	    	data: "vendorCode=" + ($("#vendor").val()),		    	
	    	success: function(data) {
		    	if(data != ""){
		    		data=data.split("~*~");
		    		dataLength=data.length-1;
		    		 
		    		var table=document.getElementById("commodityTable");
		    		var p=(table.rows.length);  modified by sourav.bhadra on 28-07-2017 
		    			 
		    	    var rowCount = table.rows.length;
		    	    var row = table.insertRow(rowCount-1);
		    	       
		    	    var cell,element;
		    	    
		    	    cell = row.insertCell(0);
		    	    element = document.createElement("input");
		    	    element.type = "hidden";
		    	    element.name="commodityCode";
		    	    element.id="commodityCode"+p;
		    	    element.setAttribute("class","form-control");
		    	    element.setAttribute("readonly","readonly");
		    	    cell.appendChild(element);
		    	    
		    	    element = document.createElement("select");
		    		element.name="commodityName";
    				element.setAttribute("class","defaultselect");
    				element.id="commodityName"+p;
    				element.add(new Option("Select...", ""),null);		    				
					for(var i=0;i<dataLength;i++)
					{
						var item=data[i].split("*-*");
						var Code=item[0];
						var Name=item[1];						
						element.add(new Option(Name, Code),null);
					}
					var id="commodityCode"+p;
					var mat="setCommodityCode(this.value,\""+id+"\",\""+p+"\");";  modified 
					element.setAttribute("onchange",mat);
    	            cell.appendChild(element);
		    				
    				cell = row.insertCell(1);
    	            element = document.createElement("input");
    	            element.type = "text";
    	            element.name="purchaseRate";
    	            element.id="purchaseRate"+p;
    	            element.value="0.00";
    	            element.setAttribute("onfocus","this.value=''");
    	            element.setAttribute("onblur","validateRate(this);");//arnab
    	            element.setAttribute("class","form-control");
    				cell.appendChild(element);
    				
    				 added by sourav.bhadra on 28-07-2017 
    				 * to display commodity unit 
    				cell = row.insertCell(2);
    	            element = document.createElement("input");
    	            element.type = "text";
    	            element.name="commodityUnit";
    	            element.id="commodityUnit"+p;
    				element.setAttribute("class","form-control");
    				element.setAttribute("readonly","readonly");
    				cell.appendChild(element);
    				
    				cell = row.insertCell(3);
    	            element = document.createElement("input");
    	            element.type = "text";
    	            element.name="quantity";
    	            element.id="quantity"+p;
    				element.value="0.00";
    				element.setAttribute("class","form-control");
    				element.setAttribute("onfocus","this.value=''");
    				element.setAttribute("onblur","CalculateTotal("+p+");"); 
    				cell.appendChild(element);
    				
    				cell = row.insertCell(4);
    	            element = document.createElement("input");
    	            element.type = "text";
    	            element.name="discount";
    	            element.value="0.00";
    	            element.id="discount"+p;
    	            element.setAttribute("class","form-control");
    	            element.setAttribute("onfocus","this.value=''");
    	            element.setAttribute("onblur","CalculateTotal("+p+");");
    				cell.appendChild(element);
    				
    				cell = row.insertCell(5);
    	            element = document.createElement("input");
    	            element.type = "text";
    	            element.name="total";
    	            element.value="0.00";
    	            element.setAttribute("readonly","readonly");
    	            element.setAttribute("class","form-control");
    	            element.id="total"+p;
    				cell.appendChild(element);	
    				
    				var element8= document.createElement('a');	
					element8.setAttribute("class","fa fa-minus-square");
					element8.setAttribute("onclick", "deleteRow(this);");
					element8.setAttribute("href","#");
					cell.appendChild(element8);
    					
		    	}else{
		    		alert("No More Assets Found");
		    	}
		    }
		});
	});
	
	added by sourav.bhadra on 27-07-2017
	$("#departmentCode").change(function(){
		document.getElementById("departmentBudgetTable").style.display="none";
		var dept = $(this).val();
		$.ajax({
		    url: '/icam/getDepartmentBudgetDetails.html',
		    	dataType: 'json',
		    	data: "departmentCode=" + dept,		    	
		    	success: function(data) {
		    		if(null != data){
		    			var budget = data.split("~");
		    			document.getElementById("budgetAmount").value=budget[0];
		    			document.getElementById("totalExpence").value=budget[1];
		    			document.getElementById("balance").value=budget[2];
		    			document.getElementById("departmentBudgetTable").style.display="block";
		    			 added by sourav.bhadra on 04-08-2017 
		    			 * to store departmental budget 
		    			saveBalance(budget);
		    			 added by sourav.bhadra on 18-08-2017 
		    			if(budget[0]=="0.0"){
		    				document.getElementById("budgetProblem").style.display="block";
		    			}else{
		    				document.getElementById("budgetProblem").style.display="none";
		    			}
		    		}
		    	}
		});
	});
	
});

function setCommodityCode(code,id,rowid){  modified by sourav.bhadra on 28-07-2017 
	document.getElementById(id).value=code;
	
	 added by sourav.bhadra on 28-07-2017 
	 * to display commodity unit against selected commodity 
	
	$.ajax({
        url: '/icam/getCommodityUnitForPO.html',
        data: "commodity="+(code),
        dataType: 'json',
        success: function(data) {
			if(data != ""){
				document.getElementById("commodityUnit"+rowid).value=data;
			}
        }
	});
	
}



function addServiceChargeToTotal(serviceChargeBox){
	if(serviceChargeBox.value.replace(/^\s+|\s+$/g,'')==""){
		serviceChargeBox.value="0.00";
		document.getElementById("addNewServiceButton").setAttribute("disabled","disabled");
	}else{
		if(isNaN(serviceChargeBox.value.replace(/^\s+|\s+$/g,''))) {
			serviceChargeBox.value="0.00";alert("Non Numeric Charge For Service Not Allowed.");
			document.getElementById("addNewServiceButton").setAttribute("disabled","disabled");
		}else{
			if(parseFloat(serviceChargeBox.value.replace(/^\s+|\s+$/g,''))<0.0){
				serviceChargeBox.value="0.00";alert("Negative Charge For Service Not Allowed.");
				document.getElementById("addNewServiceButton").setAttribute("disabled","disabled");
			}
		}
	}
	var totSerAmt=0.0;
	var serviceCharge=document.getElementsByName("serviceCharge");
	var serviceTax=parseFloat(document.getElementById("serviceTax").value);
	for(var i=0;i<serviceCharge.length;i++){		
		totSerAmt=totSerAmt + parseFloat(serviceCharge[i].value.replace(/^\s+|\s+$/g,''));
	}
	totSerAmt=totSerAmt+((totSerAmt*serviceTax)/100);
	document.getElementById("totalServiceAmount").value=totSerAmt;
	var comTot=parseFloat(document.getElementById("totalCommodityAmount").value);
	document.getElementById("netTotal").value=(comTot+totSerAmt);
	pendingCal();
}

function CalculateTotal(p){
	var q="quantity"+p;
	var r="purchaseRate"+p;
	var d="discount"+p;
	var t="total"+p;
	
	var quant = document.getElementById(q).value.replace(/^\s+|\s+$/g,'');	
	if(quant != ""){
		if(quant != "0.00"){
			if(isNaN(quant)) {
				document.getElementById(q).value="0.00";
				alert("Please Enter Numeric Quantity for Item.");
				return false;
			}
		}
	}else{
		document.getElementById(q).value="0.00";
	}
	
	var disc = document.getElementById(d).value.replace(/^\s+|\s+$/g,'');
	if(disc != ""){
		if(disc != "0.00"){
			if(isNaN(disc)) {
				document.getElementById(d).value="0.00";
				alert("Please Enter Numeric Discount for Item.");
				return false;
			}
		}
	}else{
		document.getElementById(d).value="0.00";
	}
		
	var quantity=parseFloat(document.getElementById(q).value.replace(/^\s+|\s+$/g,''));
	if(quantity<0.0)
		quantity=0.0;
		
	if(quantity==0.0){
		document.getElementById(d).value="0.00";
		document.getElementById(s).value="0.00";
	}
	
	var itemRate=parseFloat(document.getElementById(r).value.replace(/^\s+|\s+$/g,''));
	if(itemRate<0.0)
		itemRate=0.0;
	
	var discount=parseFloat(document.getElementById(d).value.replace(/^\s+|\s+$/g,''));
	if(discount<0.0)
		discount=0.0;
	
	var total=(quantity*itemRate);
	total=(total-((discount*total)/100));
	document.getElementById(t).value=total;
	
	var totalQtyOrdered=0;
	var totalCommAmount=0;	
	q=document.getElementsByName("quantity");
	t=document.getElementsByName("total");
	for(var i=0;i<q.length;i++){		
		totalQtyOrdered=totalQtyOrdered + parseFloat(q[i].value);
		totalCommAmount=totalCommAmount + parseFloat(t[i].value);
	}
	document.getElementById("totalQtyOrdered").value = totalQtyOrdered;
	document.getElementById("totalCommodityAmount").value = totalCommAmount;
	
	
	document.getElementById("netTotal").value = totalCommAmount;
	document.getElementById("vendorPayable").value = totalCommAmount;
	document.getElementById("totalCommodityExpenceAmount").value = totalCommAmount;
	 added by sourav.bhadra on 06-11-2017 
	document.getElementById("pendingAmount").value = totalCommAmount;
	 added by sourav.bhadra on 03-08-2017 
	 * to reflect departmental balance 
	 modified by sourav.bhadra on 04-08-2017 
	 * to get stored departmental budget 
	var totalExpence = parseFloat(parseFloat(budgetExpence) + parseFloat(totalCommAmount));
	var balance = parseFloat(parseFloat(remainingBudget) - parseFloat(totalCommAmount));
	document.getElementById("totalExpence").value = totalExpence;
	document.getElementById("balance").value = balance;
	if(parseFloat(balance) < 0){
		alert("The current purchase order is exceeding budget allocated.");
		document.getElementById("submit").setAttribute("disabled", "disabled");
	}else{
		document.getElementById("submit").removeAttribute("disabled");
	}
}

function pendingCal(){
	var totSerAmt=0.0;
	var vendorPayable = document.getElementById("vendorPayable").value;
	var advAmount = document.getElementById("advanceAmount").value.replace(/^\s+|\s+$/g,'');
	if(advAmount != ""){
		if(advAmount != "0.00"){
			if(isNaN(advAmount)) {
				document.getElementById("advanceAmount").value="0.00";
				alert("Please Enter Numeric Advance Amount.");
			}
		}
	}else{
		document.getElementById("advanceAmount").value="0.00";
	}	
	
	var advanceAmount=parseFloat(document.getElementById("advanceAmount").value.replace(/^\s+|\s+$/g,''));
	if(advanceAmount<0.0)
		advanceAmount=0.0;
	if(advanceAmount>vendorPayable){
		document.getElementById("advanceAmount").value="0.00";
		advanceAmount=0.0;
		alert("Advance Amount Is Greater Than Payable Amount. Sorry !!!");
	}
	var pendingAmount = vendorPayable-advanceAmount;
	document.getElementById("pendingAmount").value = pendingAmount;
	if(advanceAmount>0.0){
		document.getElementById("vendorLedger").style.display="block";  modified 
	}
}

function validateRate(thisRate){
	var rate = thisRate.value.replace(/^\s+|\s+$/g,'');
	thisRate.value=rate;
	if(rate != ""){
		if(rate != "0.00"){
			if(isNaN(rate)) {
				thisRate.value="0.00";
				alert("Please Enter Numeric Rate.");
			}
		}
	}else{
		thisRate.value="0.00";
	}
	
	var itemRate=parseFloat(rate);
	if(itemRate<0.0){
		thisRate.value="0.00";
		alert("Please Enter Positive Rate.");
	}
}

function deleteRows(tableID){	
	var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    for(var i=rowCount-1; i>0; i--){	
    	if(i!=1)
    		table.deleteRow(i-1);
    }
}

function validateOrder(){
	var vendorId=document.getElementById("vendor").value; 
	if(vendorId==""){
		alert("Please Select Vendor Name");
		return false;
	}
	return true;
}

$("#type").change(function (){
	if($(this).val()=='EXPENSE'){
		document.getElementById("commodityDiv").style.display = "none";
    }else{
    	document.getElementById("commodityDiv").style.display = "block";
		document.getElementById("expenseDiv").style.display = "block";
    }
    
});

function totalExpenceAmountCalculation(){
	var totalExpenceAmount = 0.0;
	var totalCommodityAmount = parseFloat(document.getElementById("totalCommodityAmount").value);
	$("input[name='expenceAmount']").each(function(){ 
		var amount = parseFloat($(this).val());
		 added by sourav.bhadra on 26-02-2018 
		if(isNaN(amount)){
			amount = 0.0;
		}
		
		totalExpenceAmount = parseFloat(totalExpenceAmount+amount);
	});
	var grandTotal = parseFloat(totalExpenceAmount+totalCommodityAmount);
	document.getElementById("totalCommodityExpenceAmount").value=grandTotal;
	document.getElementById("netTotal").value = grandTotal;
	document.getElementById("vendorPayable").value = grandTotal;
	 added by sourav.bhadra on 06-11-2017 
	document.getElementById("pendingAmount").value = grandTotal;
}

 added by sourav.bhadra on 22-06-2017 
function getTaxPercentages(){
	var taxCode = document.getElementById("taxStatus").value;
	var totalAmt = parseFloat(document.getElementById("totalCommodityExpenceAmount").value);
	var actualAmt = parseFloat(0.0);
	$.ajax({
        url: '/icam/getTaxPercentageAgainstTaxCode.html',
        data: "taxCode="+(taxCode),
        dataType: 'json',
        success: function(data) {
			if(data != ""){
				document.getElementById("percentage").value = data;
				actualAmt = parseFloat(totalAmt + ((totalAmt * data)/100));
				
				document.getElementById("netTotal").value = actualAmt;
				document.getElementById("vendorPayable").value = actualAmt;
				 added by sourav.bhadra on 06-11-2017 
				document.getElementById("pendingAmount").value = actualAmt;
				
				 added by sourav.bhadra on 03-08-2017 
				 * to reflect departmental balance 
				 modified by sourav.bhadra on 04-08-2017 
    			 * to get stored departmental budget 
				var totalExpence = parseFloat(parseFloat(budgetExpence) + parseFloat(actualAmt));
				var balance = parseFloat(parseFloat(remainingBudget) - parseFloat(actualAmt));
				document.getElementById("totalExpence").value = totalExpence;
				document.getElementById("balance").value = balance;
				if(parseFloat(balance) < 0){
					alert("The current purchase order is exceeding budget allocated.");
					document.getElementById("submit").setAttribute("disabled", "disabled");
				}else{
					document.getElementById("submit").removeAttribute("disabled");
				}
				
			}
        }
	});
	
}

 added by sourav.bhadra on 31-07-2017
 * to get vendor's ledger 
function getVendorsLedger(vendorCode){
	$.ajax({
        url: '/icam/getVendorsLedgerForCommodityPO.html',
        data: "vendorCode="+(vendorCode),
        dataType: 'json',
        success: function(data) {
			if(data != ""){
				document.getElementById("ledger").value = data;
			}
        }
	});
}

 added by sourav.bhadra on 04-08-2017 
 * to store departmental budget 
function saveBalance(budget){
	window.allocatedBudget = budget[0];
	window.budgetExpence = budget[1];
	window.remainingBudget = budget[2];
}*/