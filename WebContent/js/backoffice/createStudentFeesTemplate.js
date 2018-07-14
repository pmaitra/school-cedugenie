function validate(){
	var feesTemplateName = document.getElementById("studentFeesTemplateName").value;	
	feesTemplateName = feesTemplateName.replace(/\s{1,}/g,' ');
	feesTemplateName = feesTemplateName.trim();
	feesTemplateName = feesTemplateName.toUpperCase();
	
	var oldTempNames = document.getElementsByName('nameOldTemplates');
	for(var i=0; i<oldTempNames.length; i++){
		if(oldTempNames[i].value == feesTemplateName){
			alert("Template name already exixts");
			return false;
		}
	}
	
	var templatesCodes = document.getElementsByName('newtemps');
	var counter=0;
	for(var i=0; i<templatesCodes.length;i++){
		if(templatesCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Select atleast hostel.";
		alert("Select atleast one category.");
		return false;
	}
	
	return true;
}

function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("idNewTemplate"+rowId).removeAttribute("readonly");
	document.getElementById("ulList"+rowId).removeAttribute("readonly");
};



function showFeeTemplateDetails(row_id,name)
{
	
	
	var oldName = document.getElementById("idOldTemplate"+row_id).value;
	
	var category = "";
$('#category input').each(function(){
	category += ($(this).val())+"*";
});
	
	
	var array=[];
	
		
		category=category.split("*");
		var options='<option value="">Select</option>';
		
		for(var i=0;i<category.length-1;i++)
			{
			
			
			options=options+'<option value="'+category[i]+'">'+category[i]+'</option>';
			}

	
		$('#approverGroupDetails > tbody').empty();
 	if(oldName != null && oldName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='name' id='name' pattern='[a-zA-Z][a-zA-Z ]+[a-zA-Z]$'class = 'form-control' onchange='check()'  value='"+oldName+"'> </td><td><select multiple id='category' name='category' class = 'form-control'onclick='Msg()'>"+options+"</select></td></tr>";
			
		$("#approverGroupDetails").append(row)
	}
 	
 $('#modalInfo').fadeIn("fast");
 
 var btn = document.getElementById("updateFeesTemplate");
 btn.setAttribute("onclick","saveData('"+row_id+"');");
		
	}
function editValidation(rowId){

	var name = document.getElementById("name").value;
	var reg1=/^[a-zA-Z\s]+$/;
	

	if(name==null || name=="")
	{
	
	document.getElementById("javascriptmsg").style.display = 'block';			
	document.getElementById("javascriptmsg").innerHTML = " Template Cannot contain null values";
	return false;
	}
	
	if(reg1.test(name)==false)
	{
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Name can only contain characters";
		return false;
	} 
	/*for (var i = 0; i < cat1.options.length; i++) {
        if(cat1.options[i].selected ==false){
           
        	document.getElementById("javascriptmsg").style.display = 'block';			
    		document.getElementById("javascriptmsg").innerHTML = "Category cannot be null: Select atleast one Value";
    		return false;
         }
     }*/
	/*if(category==null || category=="")
		{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Category cannot be null";
		return false;
		}*/
	
	return true;
}

function saveData(rowId){

	rowId = rowId.replace("save","");
	document.getElementById("saveId").value = rowId;
	var name = document.getElementById("name").value;
	var category = document.getElementById("category").value;
	
	var categoryNames = null;
	var cat1=document.getElementById("category");
	
	for (var i = 0; i < cat1.options.length; i++) {
        if(cat1.options[i].selected ==true){
           
             categoryNames = categoryNames + "::"+cat1.options[i].value;
         }
     }
	
	document.getElementById("getcategory").value=categoryNames;
	
	document.getElementById("getName").value=name;
	
	var status = editValidation(rowId);
	if(status == true){
	document.editStudentFeesTemplate.submit();
	}
	
};

function removeMsg()
{
	document.getElementById("javascriptmsg").style.display = 'none';
	document.getElementById("javascriptmsg1").style.display = 'none';
	}
function Msg()
{
//	alert("To Select Multiple Values : Hold down the Ctrl button");
	document.getElementById("javascriptmsg1").style.display = 'block';			
	document.getElementById("javascriptmsg1").innerHTML = "To Select Multiple Values : Hold down the Ctrl button";
	}
function check(){
	var oldCategoryNames = document.getElementsByName("oldCategorydurationNames");
	
	var cat1=document.getElementById("category");
	var name = document.getElementById("name").value;
	
	name = name.trim();
	name = name.toUpperCase();
	
	for(var i=0;i<document.getElementsByName("oldCategorydurationNames").length;i++)
		{
		
		if(oldCategoryNames[i].value==name)
			{
			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Template Name Already Exists";
			return false;
			}
		}
	}