function validation(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var oldCategoryNames = document.getElementsByName("oldCategoryNames");
	var feesCategoryName = document.getElementById("feesCategoryName").value;
	feesCategoryName = feesCategoryName.trim();
	feesCategoryName = feesCategoryName.toUpperCase();
	
	if(!feesCategoryName.match(reg1)){
		/*document.getElementById("javascriptmsg1").style.display = 'block';			
		document.getElementById("javascriptmsg1").innerHTML = "Invalid Category Name";*/
		return false;
	}
	
	for(var i=0; i<oldCategoryNames.length;i++){
		if(oldCategoryNames[i].value==feesCategoryName){
			//alert("Category name already exixts");
			document.getElementById("javascriptmsg1").style.display = 'block';			
			document.getElementById("javascriptmsg1").innerHTML = "category Name Already Exists";
			return false;
		}
	}
	return true;
}

function editValidation(rowId, name){
	var reg1=/^[a-zA-Z\s]+$/;
	var oldCategoryNames = document.getElementsByName("oldCategoryNames");
	var feesCategoryName = document.getElementById("name").value;
	var length=document.getElementsByName("oldCategoryNames").length;
	
	var oldCategoryDuration = document.getElementById("oldFeesDuration"+rowId).value;
	var newCategoryDuration = document.getElementById("duration").value;
	
	feesCategoryName = feesCategoryName.trim();
	feesCategoryName = feesCategoryName.toUpperCase();
	
	
	if(feesCategoryName==null || feesCategoryName==""){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Cannot contain null values";
		return false;
	}
	
	if(reg1.test(feesCategoryName)==false){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Name can only contain characters";
		return false;
	} 
	if(feesCategoryName != name){
		for(var i = 0; i < oldCategoryNames.length; i++){
			if(oldCategoryNames[i].value == feesCategoryName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "category Name Already Exists";
				return false;
			}
		}
	
	}
	
	return true;
}

function showfeeDetails(row_id, name, feesDuration){
	/* added by sourav.bhadra on 01-09-2017 */
	var durations = "";
	$('input[name="details"]').each(function(){
		durations += $(this).val() + "*";
	});
	var durationArr = durations.split("*");
	var options = "";//alert("LN71 :: "+feesDuration);
	for(var i=0; i<durationArr.length-1; i++){
		if(durationArr[i] == feesDuration){//alert("LN72 :: "+feesDuration+"\n"+durationArr[i]);
			options += "<option value='"+durationArr[i]+"' selected>"+durationArr[i]+"</option>";
		}else{
			options += "<option value='"+durationArr[i]+"' >"+durationArr[i]+"</option>";
		}
		
	}
	$('#approverGroupDetails > tbody').empty();
 	if(name != null && name!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='name' id='name' pattern='[a-zA-Z][a-zA-Z ]+[a-zA-Z]$'class = 'form-control'  value='"+name+"'> </td><td><select id='duration' name='duration' class = 'form-control'>'"+options+"'</select></td></tr>";
			
		$("#approverGroupDetails").append(row)
 	}
 	
	$('#modalInfo').fadeIn("fast");
	 
	var btn = document.getElementById("updateFees");
	btn.setAttribute("onclick","saveData('"+row_id+"','"+name+"');");
		
}

function saveData(rowId,name){
	rowId = rowId.replace("save","");
	document.getElementById("saveId").value = rowId;
	var newName = document.getElementById("name").value;
	var duration = document.getElementById("duration").value;
	
	document.getElementById("getDuration").value=duration;
	document.getElementById("getName").value=newName;
	
	
	var status = editValidation(rowId, name);
	
	if(status == true){
		document.getElementById("javascriptmsg").style.display = 'none';
		document.updateFeesCategories.submit();
	}
	
};

function removemsg(){	
	document.getElementById("javascriptmsg").style.display = 'none';
}