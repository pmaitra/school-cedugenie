$(document).ready(function(){
		 $('#date').Zebra_DatePicker();
		 
		 $('#date').Zebra_DatePicker({
		 	  format: 'd/m/Y'
		 	});
	
	
	
	
	var table = document.getElementById("feesTable");
	var paymentTable = document.getElementById("paymentTable");
	paymentTable.style.visibility='collapse';
	table.style.visibility='collapse';
	var button = document.getElementById("submitButtonDiv");
	button.style.visibility='collapse';
	
	 $("#standard").change(function(){
		 table.style.visibility='collapse';
		 paymentTable.style.visibility='collapse';
		 button.style.visibility='collapse';
		 deleteRow(table);
		 
		var sectionObject=document.getElementById("section");				
		removeOption(sectionObject);
		
		var rollNumberObject=document.getElementById("rollNumber");				
		removeOption(rollNumberObject);
		$.ajax({
	        url: '/cedugenie/getSectionAgainstStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($(this).val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select</option>";
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split(";");
					for (var i=0;i<arr.length;i++){   
						var section = arr[i].split("*");
						options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					}
				}
	        	sectionObject.innerHTML=options;
	       }
		});
		
		
		$.ajax({
	        url: '/cedugenie/getNewStudents.html',
	        dataType: 'json',
	        data: "standard=" + ($(this).val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select</option>";
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split(";");
					for (var i=0;i<arr.length;i++){   
						var student = arr[i].split("*");
						options=options+"<option value='"+student[0]+"'>"+student[1]+"</option>";
					}
				}
	        	rollNumberObject.innerHTML=options;
	       }
		});
		
		
	});
	 
	 
	 $("#section").change(function(){
		 table.style.visibility='collapse';
		 paymentTable.style.visibility='collapse';
		 button.style.visibility='collapse';
		 deleteRow(table);
		 
			var rollNumberObject=document.getElementById("rollNumber");				
			removeOption(rollNumberObject);
			$.ajax({
		        url: '/cedugenie/getStudentsAgainstStandardAndSection.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val())+ "&section=" + ($(this).val()),
		        success: function(dataDB) {
		        	var options="<option value=''>Select</option>";
		        	if(dataDB != "null" && dataDB !="")
					{
		        		var arr = dataDB.split(";");
						for (var i=0;i<arr.length;i++){   
							var student = arr[i].split("*");
							options=options+"<option value='"+student[0]+"'>"+student[1]+" ("+student[0]+")</option>";
						}
					}
		        	rollNumberObject.innerHTML=options;
		       }
			});
		});
	
	 $("#rollNumber").change(function(){
		 table.style.visibility='collapse';
		 paymentTable.style.visibility='collapse';
		 button.style.visibility='collapse';
		 deleteRow(table);
		 
		 var check=0;
		 if(($(this).val())!=""){
			$.ajax({
		        url: '/cedugenie/getStudentFeesPaymentDetails.html',
		        dataType: 'json',
		        data: "rollNumber=" + ($(this).val())+ "&standard=" + ($("#standard").val())+ "&section=" + ($("#section").val()),
		        success: function(dataDB) {
//		        	alert(dataDB);
		        	if(null!=dataDB && dataDB!=""){		        	
			        	table.style.visibility='visible';
			        	paymentTable.style.visibility='visible';
			        	button.style.visibility='visible';
			        	
			        	document.getElementById("warningbox").style.visibility='collapse';
		        		document.getElementById("warningmsg").innerHTML="";
		        		
			        	dataDB=dataDB.split("~");
			        	
			        	var status=dataDB[dataDB.length-1];
			        	document.getElementById("status").value=status;
			        	
			        	for(var i=0;i<dataDB.length-1;i++){
			        		var data=dataDB[i].split("*");
			                var rowCount = table.rows.length;
			                var row = table.insertRow(rowCount);
			                
			                var cell,element;
			                
			                cell = row.insertCell(0);		
			                element = document.createElement("input");
			                element.type = "hidden";
			                element.name="fees";
			                element.value=data[0];
			                cell.appendChild(element);
			                element = document.createTextNode(data[0]);
			                cell.appendChild(element);
			                
			                cell = row.insertCell(1);		
			                element = document.createElement("input");
			                element.type = "hidden";
			                element.name=data[0]+"Ledger";
			                element.id=data[0]+"-Ledger";
			                element.value=data[4];
			                cell.appendChild(element);
			                element = document.createTextNode(data[4]);
			                cell.appendChild(element);
			                if(data[4]=="Not Found")
			                	check=1;
			                
			                cell = row.insertCell(2);
			                element = document.createElement("input");
			                element.type = "hidden";
			                element.name=data[0]+"Total";
			                element.id=data[0]+"-Total";
			                element.value=data[1];
			                cell.appendChild(element);
			                element = document.createTextNode(data[1]);
			                cell.appendChild(element);
			                
			            	cell = row.insertCell(3);
			            	element = document.createElement("input");
			                element.type = "hidden";
			                element.name=data[0]+"Paid";
			                element.id=data[0]+"-Paid";
			                element.value=data[2];
			                cell.appendChild(element);
			                element = document.createTextNode(data[2]);
			                cell.appendChild(element);
			                
			            	cell = row.insertCell(4);
			            	element = document.createElement("input");
			                element.type = "hidden";
			                element.name=data[0]+"Pending";
			                element.id=data[0]+"-Pending";
			                element.value=data[3];
			                cell.appendChild(element);
			                element = document.createTextNode(data[3]);
			                cell.appendChild(element);
			                
			            	cell = row.insertCell(5);
			            	element = document.createElement("input");
			                element.type = "text";
			                element.name=data[0]+"Payable";
			                element.id=data[0]+"-Payable";
			                element.className="textfield1";
			                element.value="0.0";
			                element.setAttribute("onblur","calculateAmount(this);");
			                element.setAttribute("onfocus","clearAmount(this);");
			                cell.appendChild(element);
			        	}
			        	if(check==1){
			        		button.style.visibility='collapse';
				        	paymentTable.style.visibility='collapse';
				    	   document.getElementById("warningbox").style.visibility='visible';
				    	   document.getElementById("warningmsg").innerHTML="Ledger Not Mapped For One Or More Fees.<br>Fees Cannot Be Submitted";
			        	}
		        	}else{
			    	   table.style.visibility='collapse';
			        	paymentTable.style.visibility='collapse';
			    	   button.style.visibility='collapse';
			    	   document.getElementById("warningbox").style.visibility='visible';
			    	   document.getElementById("warningmsg").innerHTML="Fees Not Found.";
			       }
		       },
		       error:function(data) {
		       }
			});
		 }
		});
	

});
function clearAmount(box){
	if(box.value=="0.0" || box.value=="0"){
		box.value="";
	}
}
function calculateAmount(box){	
	var intRegx=numeric=/^[0-9]{1,}$/;
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var amount=box.value;
	amount=amount.replace(/\s{1,}/g,'');
	box.value=amount;
	if((!amount.match(intRegx)) && (!amount.match(doubleRegx))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Amount. (Numeric. Atleat 1 character.)";
		box.value="0.0";
		return false;
	}else{
		amount = parseFloat(amount);
		if(amount<0){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Amount. (+ve Numbers Only.)";
			box.value="0.0";
			return false;
		}else{
			var id=box.id.replace("Payable","Pending");
			var pending=document.getElementById(id).value;
			pending=parseFloat(pending);			
			if(amount>pending){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Amount Cannot Be Greater Than Pending Amount";
				box.value="0.0";
				return false;
			}
		}
	}
}
function removeOption(x)
{
	for(var i=x.length;i>=0;i--)
	{
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select</option>";
}

function deleteRow(table)
{
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--)
	{
   		table.deleteRow(i-1);
           
    }
}














var pay=1;
function addLedger(){
	var table = document.getElementById("paymentTable"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    var cell;
    var element;
    
    cell = row.insertCell(0);
    element = document.getElementById("bankLedger").cloneNode(true);
    element.name="bankLedger";
    element.id="bankLedger"+pay;
    element.setAttribute("onchange","makeChequeReadOnly(this, 'chequeDraft"+pay+"');");
    cell.appendChild(element);
    
    cell = row.insertCell(1);
    element = document.createElement("input");
    element.type = "text";
    element.name="ledgerAmount";
    element.value="0.0";
    element.setAttribute("onblur","checkLedgerAmount(this);");
    element.setAttribute("onfocus","clearAmount(this);");
    element.id="ledgerAmount"+pay;
    element.setAttribute("class","textfield3");
    cell.appendChild(element);
    
    cell = row.insertCell(2);
    element = document.createElement("input");
    element.type = "text";
    element.name="chequeDraft";
    element.id="chequeDraft"+pay;
    element.setAttribute("class","textfield2");
    element.setAttribute("readonly","readonly");
    cell.appendChild(element);
    
    cell = row.insertCell(3);
    element = "<img src='/cedugenie/images/minus_icon.png' onclick='deleteLedger(this);'>";
    cell.innerHTML=element;
    
    pay++;
}



function deleteLedger(obj){	
	var table = document.getElementById("paymentTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Payment Row Required.";
	}
} 
function makeChequeReadOnly(select, chequeId){
	if(select.value=="CASH A/C"){
		document.getElementById(chequeId).value="";
		document.getElementById(chequeId).setAttribute("readonly","readonly");
	}else{
		document.getElementById(chequeId).removeAttribute("readonly","readonly");
	}
}
function checkLedgerAmount(box){	
	var intRegx=numeric=/^[0-9]{1,}$/;
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var amount=box.value;
	amount=amount.replace(/\s{1,}/g,'');
	box.value=amount;
	if((!amount.match(intRegx)) && (!amount.match(doubleRegx))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Amount. (Numeric. Atleat 1 character.)";
		box.value="0.0";
	}else{
		amount = parseFloat(amount);
		if(amount<0){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Amount. (+ve Numbers Only.)";
			box.value="0.0";
		}
	}
}

function validate(){
	var amountBox1=getElementsByClassName("textfield1");
	var amountBox2=getElementsByClassName("textfield3");
	var amount1=0,amount2=0;
	for(var i=0;i<amountBox1.length;i++){
		amount1=amount1+parseFloat(amountBox1[i].value);
	}
	for(var i=0;i<amountBox2.length;i++){
		amount2=amount2+parseFloat(amountBox2[i].value);
	}
//	alert(amount1+"\t\t"+amount2);
	if(amount1<=0.0 || amount2<=0.0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Payable And Ledger Amount Should Must Not Be 0.0)";
		return false;
	}
	if(amount1!=amount2){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Payable And Ledger Amount Should Must Be Equal.)";
		return false;
	}
	return true;
}