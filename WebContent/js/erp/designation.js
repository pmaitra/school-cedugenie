	function validateDesignationForm(){			
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;	
		// Validate Designation Name
		var obj = document.getElementById("designationName");
		var designationName=obj.value;
		designationName=designationName.replace(/\s{1,}/g,' ');
		designationName=designationName.trim();
		designationName=designationName.toUpperCase();
		obj.value=designationName;
		
		var resourceType = document.getElementById("resourceTypeName").value;		
		if(resourceType == ""){
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Select Resource Type";
			return false;
		}		
		if (designationName == "") {
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Enter designation Name";				
			return false;
		}
		if (designationName != '') {
			if (!designationName.match(alphaNumeric)) {
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Name";		
				return false;
			}
		}
		
		var oldDesignationNames = document.getElementsByName('oldDesignationNamesForDuplicateCheck');		
		for(var i=0; i<oldDesignationNames.length;i++){
			if(oldDesignationNames[i].value==designationName){					
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Designation Name Already Exists";
				return false;
			}
		}		
		return true;		
	}
	
	/*modified by ranita.sur on 11092017*/
	function validateEditDesignationForm(rowId){
	  	var alphaNumeric=/^[a-zA-Z ]+$/;			
		var designationName = document.getElementById("designationName").value;
		var newDesignationName = document.getElementById("getNewDesignation").value;
		newDesignationName=newDesignationName.trim();
		newDesignationName=newDesignationName.toUpperCase();
		var desigList = document.getElementsByName("oldDesignationNamesForDuplicateCheck");		
		for(var i=0; i<desigList.length; i++){
			var oldVal=desigList[i].value;
			if(oldVal==newDesignationName){
				document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Duplicate Designation";
				return false;
			}
		}
		 if(designationName ==""|| designationName==null ){
				document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Enter Designation Name"; 
				return false;
			}else if (alphaNumeric.test(designationName)==false)
		    {
		    	document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Designation Name can contain alphabets and spaces  between words !!";
				return false;
		    }else
				{
				return true;
				}
				
	}
	
	function showDesignationDetails(rowId,designationName,designationTypeName){
	    var designationName = document.getElementById("designationName"+rowId).value;
		var designationTypeName = document.getElementById("designationTypeDesignationTypeName"+rowId).value;
		var option="<option value=''>Select</option>";
		var oldDesignationType="";
		
		$("#oldDesignationType input[name='olddesignation']").each(function(){
			oldDesignationType += $(this).val() + ";";
		});
		var oldDesignationList = oldDesignationType.split(";");
		for( var i=0;i<oldDesignationList.length-1;i++){
			 if(oldDesignationList[i]==designationTypeName.trim()){
				 option+="<option value='"+oldDesignationList[i]+"' selected>"+oldDesignationList[i]+"</option>";
	    	}else{
	    		option+="<option value='"+oldDesignationList[i]+"'>"+oldDesignationList[i]+"</option>";
	    	}
		}		
		$('#designationTypeNames > tbody').empty();
	 	if(designationName != null && designationName!=""){
	 		var row = "<tbody>";
	 		row += "<tr><td><input type='text' name='designationName' id='designationName' class = 'form-control'  value='"+designationName+"'></td>" 
	 				+"<td><select id='designationTypeName' name='designationTypeName' class='form-control'>'"+option+"'</select></td></tr>";
	 		$("#designationTypeNames").append(row);
	 	}
	 	
	 	$('#modalInfo').fadeIn("fast");
	 	var btn = document.getElementById("updateDesignation");
	 	btn.setAttribute("onclick","saveData('"+rowId+"');");
		
		
	}
	
	function saveData(rowId){
		var newDesignationName = document.getElementById("designationName").value;
		var newDesignationType = document.getElementById("designationTypeName").value;
		document.getElementById("saveId").value=rowId;	
		document.getElementById("getNewDesignation").value=newDesignationName;
		document.getElementById("getNewDesignationType").value=newDesignationType;
		var status=validateEditDesignationForm(rowId);
		if(status==true)
			{
			document.editDesignationForm.submit();
			}
	};
	function closeWarning(){
		document.getElementById("warningmsg1").style.display = 'none';	
	}
	