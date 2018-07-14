function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--){
   		table.deleteRow(i-1);
    }
}
window.budgetStatus = "";
$("#academicYear").change(function() {
	var table = document.getElementById("budgetTable");
	$("#totalIncomediv").css('display','block');
	deleteRow(table);
	if($("#academicYear").val()!=""){
		$.ajax({
	        url: '/icam/getBudgetForAcademicYear.html',
	        data: "academicYear="+($("#academicYear").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == ""){
	        		$("#budgetTable").css('visibility','collapse');
	        	}
	        	if(data != ""){
		        	$("#budgetTable").css('visibility','visible');
		        	var status=data.split("@@");
		        	budgetStatus = status[0];
		        	
		        	var d=status[1].split("~*~");
		        	var totalReserveFund = 0.0;
		        	for(var i=0;i<d.length-1;i++){
		        		var dep=d[i].split("*~*");			        		
		        		var rowCount = table.rows.length;
		        		var row = table.insertRow(rowCount);
		        		var cell,element;
		        		
		        		cell = row.insertCell(0);
		        		element = document.createElement("input");
		        		element.type = "hidden";
		        		element.value=dep[0];
		        		element.name="department";
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
		        		element.setAttribute("onblur","expenceCal(this);");
		        		element.setAttribute("onfocus","this.value='';");
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
		           		
		           		var reserve = parseFloat(dep[5]);
		           		totalReserveFund = parseFloat(totalReserveFund+reserve);
		        	}
		        	
		        	document.getElementById("reserveBalance").value=totalReserveFund;
		        	
		        	if(status[0]=='allocated'){
		        		
		        		document.getElementById("balanceDiv").style.display='none';
		        		document.getElementById("remainingDiv").style.display='none';
		        		$("input[name*='_percentage']").each(function(){ 
		        			$(this).attr("readonly","readonly");
		        		});
		        		$("input[name*='_totalExpences']").each(function(){ 
		        			$(this).attr("readonly","readonly");
		        		});
		        		document.getElementById("submitDiv").style.display='none';
		        		document.getElementById("messageDiv").style.display='block';
		        		/* modified by sourav.bhadra on 15-03-2018 */
		        		$.ajax({
		        	        url: '/icam/getPreviousYearUnallocatedFund.html',
		        	        data: "academicYear="+($("#academicYear").val()),
		        	        dataType: 'json',
		        	        success: function(data) {
		        	        	var fund = data.split("*#*");
		        	        	var status1 = budgetStatus;
		        	        	
		        	        	document.getElementById("unallocatedBalance").value=fund[1];
		        	        	if(status1=='allocated'){
		        	        		document.getElementById("totalIncome").value=fund[0];
		        	        	}
		        	        }
		        		});
		        	}
	        	}
	       }
		});
	}else{
		$("#budgetTable").css('visibility','collapse');
	}
});

function validate(){
	if(!(nullValidation(document.getElementById("academicYear")))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML = "Select Valid Academic Year";
		return false;
	}
	var amount=getElementsByClassName("textfield1");
	for(var i=0;i<amount.length;i++){
		if(!(numericValidation(amount[i]))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Enter Valid Amount";
			return false;
		}
	}
	return true;
}

/* modified by sourav.bhadra on 17-04-2018 */
function percentageCal(percentageId){
	var id = percentageId.id;
	var actualamountId = id.split("_");
	var totalBudget=document.getElementById("totalIncome").value;	
	var percentage=document.getElementById(percentageId.id).value;	
	var calculatedValue=(totalBudget)*(percentage)/100;
	
	document.getElementById(actualamountId[0]+"ActualAmount").value=calculatedValue;
	document.getElementById(actualamountId[0]+"balance").value=calculatedValue;
	
	var totalPercentage = parseFloat(0.0);
	$("input[name*='_percentage']").each(function(){ 
		var percent = parseFloat($(this).val());
		totalPercentage = parseFloat(totalPercentage+percent);
	});
	var r=document.getElementById("remainingPercentage").value=parseFloat(100-totalPercentage);
	if(r<percentage){
		document.getElementById("remainingPercentage").value=0;
	}
	var totalAmount = parseFloat(0.0);
	$("input[name*='ActualAmount']").each(function(){ 
		var amount = parseFloat($(this).val());
		totalAmount = parseFloat(totalAmount+amount);
	});
	var remainingBalance = parseFloat(totalBudget-totalAmount);
	
	
	
	document.getElementById("remainingBalance").value=remainingBalance;
}

function expenceCal(totalExpencesId){
	var id = totalExpencesId.id;
	var expencesamountId = id.split("_");
	var totalExpence=document.getElementById(totalExpencesId.id).value;	
	var actualAmount=document.getElementById(expencesamountId[0]+"ActualAmount").value;
	var balance=actualAmount-totalExpence;
	document.getElementById(expencesamountId[0]+"balance").value=balance;
	document.getElementById(expencesamountId[0]+"reserveBalance").value=balance;
}

function calculateTotalAmount(){
	var unallocated = parseFloat(document.getElementById("unallocatedBalance").value);
	var reserve = parseFloat(document.getElementById("reserveBalance").value);
	var current = parseFloat(document.getElementById("currentAmount").value);
	var total = parseFloat(unallocated+reserve+current);
	document.getElementById("totalIncome").value=total;
	document.getElementById("remainingBalance").value=total;
}