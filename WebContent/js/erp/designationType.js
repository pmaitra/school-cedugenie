function validateDesignationTypeForm(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var successmsg=document.getElementById("successmsg");
	if(null != successmsg){
		successmsg.innerHTML = "";
		document.getElementById("successbox").style.visibility='collapse';
	}				
	// Validate DesignationType Name
	var obj = document.getElementById("designationTypeName");	
	var designationTypeName=obj.value;
	designationTypeName=designationTypeName.replace(/\s{1,}/g,' ');
	designationTypeName=designationTypeName.trim();
	designationTypeName=designationTypeName.toUpperCase();
	obj.value=designationTypeName;
	if (designationTypeName == "") {			
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter DesignationType";		
		return false;
	}
	
	if (designationTypeName != '') {
		if (!designationTypeName.match(alphaNumeric)) {			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Type ";
			return false;
		}
	}
	
		var oldDesignationTypeNames = document.getElementsByName('oldDesgnationTypeNamesforDuplicateChecking');
		for(var i=0; i<oldDesignationTypeNames.length;i++){
			if(oldDesignationTypeNames[i].value==designationTypeName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Designation Type Already Exists";				
				return false;
			}
		}		
}

function validateEditDesignationTypeForm(rowID ){	
	var designationTypeName = document.getElementById("designationTypeName").value;
	var alphaNumeric=/^[a-zA-Z \s]+$/;
	var olddesignationTypeName=document.getElementsByName("oldDesgnationTypeNamesforDuplicateChecking");
	var newdesignationTypeName = document.getElementById("getDesignationType").value;
	newdesignationTypeName=newdesignationTypeName.trim();
	newdesignationTypeName=newdesignationTypeName.toUpperCase();
	
	for(var i=0;i<olddesignationTypeName.length-1;i++)
	{
	
		if(olddesignationTypeName[i].value==newdesignationTypeName )
			{
			//alert("LN 50 ::"+olddesignationTypeName[i].value);
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Designation Type Already Exists";
			return false;
			}
		
		}

	if(designationTypeName ==""|| designationTypeName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter DesignationType Name"; 
		return false;
	}else if (alphaNumeric.test(designationTypeName)==false)
    {
    	document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "DesignationType name can contain alphabets and spaces between words !!";
		return false;
    }else
		{
		return true;
		}
		
}
	/*var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;			
	var val="";
	var obj = document.getElementById(rowID);
	alert("OBJ"+obj);
	val=obj.value;		
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	alert("Val"+val);
	if (val == "") {					
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Please Enter Designation Type";
		return false;
	}else{					
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "";
	}
	var designationTypeName=document.getElementById("designationTypeName"+rowID).value;
	alert("designationTypeName"+designationTypeName);
	if(designationTypeName ==""|| designationTypeName==null ){
		alert("HII");
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter DesignationType Name"; 
		return false;
	}else{					
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "";
	}
	if(!val.match(alphaNumeric)){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Invalid Designation Type";				
		return false;
	}else{
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "";
	}*/	
	
	
	
	/*var desigTypeList = document.getElementById("oldDesgnationTypeNamesforDuplicateChecking"+rowID);		
	for(var i=0; i<desigTypeList.length-1; i++){
		var oldVal=desigTypeList[i].value;
		alert("LN 72 ::"+desigTypeList);
		if(desigTypeList==val){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Duplicate Designation Type";
			return false;
		}
	}		
	return true;
	*/
//}


function showDesignationTypeDetails(designationTypeName,index)
{
	//alert("HII");
	//alert("LN 97 ::"+designationTypeName);
	$('#designationType> tbody').empty();
 	if(designationTypeName != null && designationTypeName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='designationTypeName' id='designationTypeName' class = 'form-control'  value='"+designationTypeName+"'> </td></tr>";
 		$("#designationType").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateDesignationType");
 	btn.setAttribute("onclick","saveData('"+index+"','"+designationTypeName+"');");
	
	}

function saveData(rowId,designationTypeName){
	var designationTypeName=document.getElementById("designationTypeName").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getDesignationType").value = designationTypeName;
   /* if(designationTypeName ==""|| designationTypeName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter DesignationType Name"; 
		return false;
	}
	 else{
		 document.editDesignationType.submit();
			
	}*/
	var validateStatus = validateEditDesignationTypeForm(rowId);
	//alert("LN 145 ::"+validateStatus);
	if(validateStatus == true ){
		document.editDesignationType.submit();
	}
}

function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}
