function showVendorDetails(vendorName,vendorContactNo1,address,index){
	$('#vendorDetails> tbody').empty();
 	if(vendorName != null && vendorName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='vendorName' id='vendorName' class = 'form-control'  value='"+vendorName+"'></td>"+
 		"<td><input type='text' name='vendorContactNum'id='vendorContactNum' class = 'form-control' value='"+vendorContactNo1+"'></td>"+
 		"<td><input type='text' name='address' id='address' class = 'form-control'  value='"+address+"'></td></tr>";
		
 		$("#vendorDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateVendor");
 	btn.setAttribute("onclick","saveData('"+index+"','"+vendorName+"','"+vendorContactNo1+"','"+address+"');");
}

function saveData(rowId,vendorName,vendorContactNo1,address){
	var vendorname = document.getElementById("vendorName").value;
	
	var contactno=document.getElementById("vendorContactNum").value;
	
	/* var contctno1=document.getElementById("vendorContactNo2").value;
	alert(contctno1);
	var email=document.getElementById("emailId").value;
	alert(email); */
	var add=document.getElementById("address").value;
	/* var bankname=document.getElementById("bankName").value;
	var accno=document.getElementById("accountNo").value;
	var code=document.getElementById("bankCode").value;
	var location=document.getElementById("bankLocation").value;
	var branchco=document.getElementById("branchCode").value; */
	//rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	document.getElementById("getVendorName").value = vendorname;
	document.getElementById("getContactNo1").value = contactno;
	/* document.getElementById("getContactNo2").value = contctno1;
	document.getElementById("getEmailId").value = email; */
	document.getElementById("getAddress").value = add;
	/* document.getElementById("getBankName").value = bankname;
	document.getElementById("getAccountNo").value = accno;
	document.getElementById("getBankCode").value = code;
	document.getElementById("getBankLocation").value = location;
	document.getElementById("getBranchCode").value = branchco; */
	var regx="^[a-zA-Z0-9]+$";
	//var nameregx="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
	var isNumeric=vendorname.match("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$");
	/* var oldemailIds="";
	var oldaccountNos="";
	
	$("#oldemailId input[name='oldemailId']").each(function(){
		oldemailIds += $(this).val() + ";";
	});
	
	$("#oldaccountNo input[name='oldaccountNo']").each(function(){
		oldaccountNos += $(this).val() + ";";
	});
	 
	var oldEmailList = oldemailIds.split(";");
	var oldAccountList = oldaccountNos.split(";");
	 */
	
	if(vendorname ==""|| vendorname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Vendor Name should contain atleast one character."; 
		return false;
	}else if(contactno ==""|| contactno==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Contact No."; 
		return false;
	}/* else if(email ==""|| email==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Email Id"; 
		return false;
	} */else if(add ==""|| add==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Address"; 
		return false;
	}/* else if(bankname ==""|| bankname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Name"; 
		return false;
	}else if(accno ==""|| accno==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Account No"; 
		return false;
	}else if(location ==""|| location==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Location"; 
		return false;
	} */ else{
		  /* if(regx.match(code)==false){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "IFSC Code  contain alphabets and spaces between words !!"; 
			return false;
		}else{ */
			document.editMessDailyRationVendorDetails.submit();
		//}
	}
};

function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}