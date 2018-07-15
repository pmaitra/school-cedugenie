function checkGroupName(nameBox){
	var oldNamesBox=document.getElementsByName("oldGroupNames");
	var checker="valid";
	for(var i=0;i<oldNamesBox.length;i++){
		if(oldNamesBox[i].value==(nameBox.value).toUpperCase()){
			checker="invalid";
			break;
		}
	}
	if(checker=="invalid"){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Group Name Already Exists.";
		alert("Group Name Already Exists.");
		document.getElementById("groupName").value="";
	}else{
		document.getElementById("warningbox").style.visibility='collapse';
	}
}




function validateGroup() {
	if(!(nullValidation(document.getElementById("groupName")))){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Enter Valid Group Name";
		alert("Enter Valid Group Name");
		return false;
	}
	if(!(nullValidation(document.getElementById("groupTypeCode")))){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Select A Grouptype";
		alert("Select Group Type");
		return false;
	}
	var names=document.getElementsByName("names");
	var newname=document.getElementById("groupName").value;
	newname = newname.trim().toUpperCase();
	
	for (var i=0;i<names.length;i++){
		if(newname=names[i].value){
			document.getElementById("warningDiv").style.display = 'block';			
			document.getElementById("warningMsg").innerHTML = "Group Name Already Exists";
			return false;
			}
		}
	return true;
}

function validateLedger() {
	var oldNamesBox=document.getElementsByName("oldLedgerName");
	/*alert("oldledgerValue==="+oldNamesBox.length);*/
	var newLedgerName=document.getElementById("ledgerName").value;
	newLedgerName=newLedgerName.trim();
	newLedgerName=newLedgerName.toUpperCase();
	/*alert("new LedgerName"+newLedgerName);*/
	

	/*if(!(nullValidation(document.getElementById("ledgerName")))){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Enter Valid Ledger Name";
		alert("Enter Valid Ledger Name");
		return false;
	}
	if(!(nullValidation(document.getElementById("parentGroupCode")))){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Select A Parent Group";
		alert("Select A Parent Group");
		return false;
	}*/
	/*if(!(numericValidation(document.getElementById("openingBal")))){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML = "Enter Valid Opening Balance";
		alert("Enter Valid Opening Balance");
		return false;
	}*/
	//var oldNamesBox = document.getElementById("oldLedgerName").getAttribute('value');   
	//var oldNamesBox=$('#oldLedgerName').attr("value");
	
	
	for(var i=0;i<oldNamesBox.length;i++)
	{
	/*alert(oldNamesBox[i].value);*/
	if(oldNamesBox[i].value==newLedgerName)
		{
		document.getElementById("javascriptmsg1").style.display = 'block';			
		document.getElementById("javascriptmsg1").innerHTML = "Ledger Name Already Exists";
		//alert("Ledger Name Already Exists");
		return false;
	
		}
	}


	return true;;
}

$("#parentGroupCode").change(function (){

	$.ajax({
        url: '/cedugenie/getSubGroup.html',
        dataType: 'json',
        data:"parent=" + $("#parentGroupCode").val(),
        success: function(dataDB) {
        	var options="<option value=''>Select</option>";
        	if(dataDB != "null" && dataDB !="")
			{
        		var arr = dataDB.split("~");
				for (var i=0;i<arr.length-1;i++){   
					var sub = arr[i].split("*");
					options=options+"<option value='"+sub[0]+"'>"+sub[1]+"</option>";
				}
			}
        	document.getElementById("subGroup").innerHTML=options;
       }
	});
});