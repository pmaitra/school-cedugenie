function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--){
   		table.deleteRow(i-1);
    }
}

$("#financialYear").change(function() {
	var table = document.getElementById("budgetTable");
	$("#totalIncomediv").css('display','block');
	deleteRow(table);
	if($("#financialYear").val()!=""){
		$.ajax({
	        url: '/icam/getBudgetOfSubDeptsForAFinancialYear.html',
	        data: "financialYear="+($("#financialYear").val()) + "&department="+($("#department").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == ""){
	        		$("#budgetTable").css('visibility','collapse');
	        	}
	        	if(data != ""){
	        		var datas = data.split("#");
	        		var parentDeptBudgetDetails = datas[0].split("*");
	        		var budgetDetails = datas[1].split("@@");
	        		var budgetStatus = budgetDetails[0];
	        		var subDeptBudgetDetails = budgetDetails[1].split("~*~");
	        		
	        		document.getElementById("allocatedAmount").value = parentDeptBudgetDetails[0];
	        		document.getElementById("availableFund").value = parentDeptBudgetDetails[1];
	        		document.getElementById("reserveFund").value = parentDeptBudgetDetails[2];
	        		
		        	$("#budgetTable").css('visibility','visible');
		        	for(var i=0;i<subDeptBudgetDetails.length-1;i++){
		        		var dep=subDeptBudgetDetails[i].split("*~*");			        		
		        		var rowCount = table.rows.length;
		        		var row = table.insertRow(rowCount);
		        		var cell,element;
		        		
		        		cell = row.insertCell(0);
		        		element = document.createElement("input");
		        		element.type = "hidden";
		        		element.value=dep[0];
		        		element.name="subDepartment";
		        		cell.appendChild(element);
		        		element = document.createTextNode(dep[0]);
		        		cell.appendChild(element);
		        		
		        		cell = row.insertCell(1);
		        		element = document.createElement("input");
		        		element.type = "text";
		        		element.value=dep[1];
		        		element.name=dep[0]+"_percentage" ;
		        		element.id=dep[0]+"_percentage" ;
		        		element.setAttribute("onblur","percentageCal(this);");
		        		element.setAttribute("onfocus","this.value='';");
		        		element.setAttribute("class","form-control");
		        		element.size=8;
		        		cell.appendChild(element);

		        		cell = row.insertCell(2);
		        		element = document.createElement("input");
		        		element.type = "text";
		        		element.value=dep[2];
		        		element.setAttribute("readonly","readonly");
		        		element.name=dep[0]+"ActualAmount";
		        		element.id=dep[0]+"ActualAmount";
		        		element.setAttribute("class","form-control");
		        		cell.appendChild(element);
		        		
		        		cell = row.insertCell(3);
		        		element = document.createElement("input");
		        		element.type = "text";
		        		element.value=dep[3];
		        		element.name=dep[0]+"_totalExpences";
		        		element.id=dep[0]+"_totalExpences" ;
		        		element.setAttribute("readonly","readonly");
		        		element.setAttribute("class","form-control");
		        		cell.appendChild(element);

		        		cell = row.insertCell(4);
		        		element = document.createElement("input");
		        		element.type = "text";
		        		element.value=dep[4];
		        		element.setAttribute("readonly","readonly");
		        		element.setAttribute("class","form-control");
		        		element.name=dep[0]+"balance";
		        		element.id=dep[0]+"balance" ;
		           		cell.appendChild(element);
		           		
		           		cell = row.insertCell(5);
		        		element = document.createElement("input");
		        		element.type = "text";
		        		element.value=dep[5];
		        		element.setAttribute("readonly","readonly");
		        		element.setAttribute("class","form-control");
		        		element.name=dep[0]+"reserveBalance";
		        		element.id=dep[0]+"reserveBalance" ;
		           		cell.appendChild(element);
		           		
		        	}
		        	
		        	if(budgetStatus=='allocated'){
		        		$("input[name*='_percentage']").each(function(){ 
		        			$(this).attr("readonly","readonly");
		        		});
		        		document.getElementById("submitDiv").style.display='none';
		        		document.getElementById("messageDiv").style.display='block';
		        	}
	        	}
	       }
		});
	}else{
		$("#budgetTable").css('visibility','collapse');
	}
});

function percentageCal(percentageId){
	var id = percentageId.id;
	var actualamountId = id.split("_");
	var totalBudget=document.getElementById("allocatedAmount").value;	
	var percentage=document.getElementById(percentageId.id).value;	
	var calculatedValue=(totalBudget)*(percentage)/100;
	
	document.getElementById(actualamountId[0]+"ActualAmount").value=calculatedValue;
	document.getElementById(actualamountId[0]+"balance").value=calculatedValue;
	
	var totalAmount = parseFloat(0.0);
	$("input[name*='ActualAmount']").each(function(){ 
		var amount = parseFloat($(this).val());
		totalAmount = parseFloat(totalAmount+amount);
	});
	var remainingBalance = parseFloat(totalBudget-totalAmount);
	
	document.getElementById("availableFund").value=remainingBalance;
}

function calculateTotalAmount(){
	var unallocated = parseFloat(document.getElementById("unallocatedBalance").value);
	var reserve = parseFloat(document.getElementById("reserveBalance").value);
	var current = parseFloat(document.getElementById("currentAmount").value);
	var total = parseFloat(unallocated+reserve+current);
	document.getElementById("totalIncome").value=total;
	document.getElementById("remainingBalance").value=total;
}