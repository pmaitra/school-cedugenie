function updateTax(rowId){
	
	var taxName = document.getElementById("taxName"+rowId).value;
	
	var percentage = document.getElementById("percentage"+rowId).value;
	var taxStatus = document.getElementById("taxStatus"+rowId).value;
	
	var status1 = "ACTIVE";
	var status2 = "INACTIVE";
	var status3 = "Active";
	var status4 = "Inactive";
	var options="";
	if(taxStatus == status1){
		options=options+"<option value='"+status1+"' selected>"+status3+"</option>";
		options=options+"<option value='"+status2+"'>"+status4+"</option>";
	}else{
		options=options+"<option value='"+status1+"'>"+status3+"</option>";
		options=options+"<option value='"+status2+"' selected>"+status4+"</option>";
	}
	
	$('#updateTaxDetails > tbody').empty();
 	if((percentage != null && percentage!="") && (taxStatus != null && taxStatus!="")){
 		var row = "<tbody>";
 		row += "<tr><td><input type='hidden' name='taxName' id='taxName' readonly>"+taxName+" </td><td><input type='text' id='percentage' name='percentage' class='form-control' value='"+percentage+"' required></td>"
 		+"<td><select id='taxStatus' name='taxStatus' class='form-control'>'"+options+"'</select></td></tr>";    				
 		
 		$("#updateTaxDetails").append(row);
 	}
	$('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateTaxButton");
 	btn.setAttribute("onclick","saveTax('"+rowId+"','"+taxName+"');");
};
function saveTax(rowid,taxName){
	var reg1=/^([0-9]*[.])?[0-9]+$/;
	var newpercentage = document.getElementById("percentage").value;
	var newtaxstatus = document.getElementById("taxStatus").value;
	
	
	if(newpercentage == null || newpercentage == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Tax Percemntage can not be Empty.";
		return false;
	}else{
		if(!newpercentage.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Tax Percemntage. (Numeric. Alphabets  and special caracter not allowed.)";
			return false;
		}else{
		
			document.getElementById("saveId").value=rowid;
			document.getElementById("getPercentage").value=newpercentage;
			document.getElementById("getStatus").value=newtaxstatus;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.editTaxPercentages.submit();
		
		}}
	
};
function closeWarning()
{
	document.getElementById("warningmsg").style.display = 'none';	
	}