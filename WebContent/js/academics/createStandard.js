function makeEditable(rowId){
		rowId=rowId.replace("edit","");
		document.getElementById("standardName"+rowId).removeAttribute("readonly");
		document.getElementById("section"+rowId).removeAttribute("readonly");
		
	};
	


function addStandard(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var reg2=/^[A-Za-z,]{1,}$/;
	var standardName=document.getElementById("standardName").value;	
	standardName=standardName.replace(/\s{1,}/g,' ');
	standardName=standardName.trim();
	standardName=standardName.toUpperCase();
	document.getElementById("standardName").value=standardName;	
	/*if(!standardName.match(reg1)){
		alert("Invalid Standard Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}*/
	var oldStandardNames = document.getElementsByName('oldStandardNames');
	for(var i=0; i<oldStandardNames.length;i++){
		if(oldStandardNames[i].value==standardName){
			alert("Course Name Already Exixts");
			return false;
		}
	}
/*	var sections=document.getElementById("sections").value;	
	sections=sections.replace(/\s{1,}/g,'');
	sections=sections.trim();
	sections=sections.toUpperCase();
	while(sections.charAt(0)==','){
		sections=sections.substr(1);
		sections=sections.trim();
	}
	while(sections.charAt(sections.length - 1)==','){
		sections=sections.substring(0, sections.length - 1);
		sections=sections.trim();
	}
	sections=sections.replace(/,{1,}/g,',');
	document.getElementById("sections").value=sections;	
	if(sections!=""){
		if(!sections.match(reg2)){
			alert("Invalid Section Name. (Alphabetic only.)");
			return false;
		}
	}*/
	
	
	/*document.standardForm.method="Post";
	document.standardForm.action="addStandard.html";
	document.standardForm.submit();*/
	return true;
}
function editStandard(rowId){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var reg2=/^[A-Za-z,]{1,}$/;
	var standardCode = document.getElementById('standardCode'+rowId).value;
	var oldStandardNames = document.getElementsByName('oldStandardNames');
	var oldSectionNames = document.getElementsByName("oldSectionNames"+rowId);
	//alert("oldSectionNames===="+oldSectionNames.length);
		for(var j=0; j<oldStandardNames.length;j++){
			standardCode = standardCode.replace(/\s{1,}/g,' ');
			standardCode = standardCode.trim();
			standardCode = standardCode.toUpperCase();
			document.getElementById('standardName'+rowId).value = standardCode;
			
					id = 'section'+rowId;
					var sections=document.getElementById(id).value;	
					sections=sections.replace(/\s{1,}/g,'');
					sections=sections.trim();
					sections=sections.toUpperCase();
					while(sections.charAt(0)==','){
						sections=sections.substr(1);
						sections=sections.trim();
					}
					while(sections.charAt(sections.length - 1)==','){
						sections=sections.substring(0, sections.length - 1);
						sections=sections.trim();
					}
					sections=sections.replace(/,{1,}/g,',');
					document.getElementById(id).value=sections;	
					if(sections!=""){
						if(!sections.match(reg2)){
							//document.getElementById("warningbox1").style.visibility='visible';
							//document.getElementById("warningmsg1").innerHTML="Invalid Section Name. (Alphabetic only.)";
							alert("Invalid Section Name. (Alphabetic only.)");
							return false;
						}
					}
					/*if(sections!=""){
						for(var j=0; j<oldSectionNames.length;j++){
							for(var i=0; i<sections.length;i++){
								if(oldSectionNames[j].value == sections[i]){
									alert("Section already exist");
									return false;
								}
							}
							
						}
						
					}*/
				//}
			//}
		//}
	}
	
	
	//document.standardForm.method="Post";
	//document.standardForm.action="editStandard.html";
	//document.standardForm.submit();
	return true;
}



function showProgramGroupDetails(row_id)
{
	//alert("hii");
	//alert(approverGroupDetails);
	//alert("Row_id :: "+row_id);
	//alert("program=="+program);
	var length= document.getElementsByName("oldSectionNames"+row_id).length;
	//alert("x===="+length);
	
	var programName = document.getElementById("standardName"+row_id).value;
	//alert("program=="+programName);
	//var section = document.getElementsByName("oldSectionNames"+row_id).value;
//	var section = document.querySelector("oldSectionNames").value;
	
	//var section = document.getElementById("oldSectionNames"+row_id).value;
	//alert("section=="+section);
	var sections,add;
	sections = document.getElementsByName("oldSectionNames"+row_id)[0].value;
	for(var i=1;i<length;i++){
		sections = sections+","+ document.getElementsByName("oldSectionNames"+row_id)[i].value;
	}
	//alert(sections);
		//add=sections+",";
	    //alert("adding====="+sections);
	//alert("section=="+section1);
	//for(var i=0;i<document.getElementsByName("details").length;i++){
		//if(document.getElementsByName("details")[i].value!="NA")
		//	{
		//var k=document.getElementsByName("details")[i].value+"#"+document.getElementsByName("details")[i+1].value+"#"+document.getElementsByName("details")[i+2].value;
		//alert("ranita==="+k);
		//	}
		//}
			
	    	 $('#programGroupDetails > tbody').empty();
	     	if(row_id != null && row_id!=""){
	     		
	     		var row = "<tbody>";
	     			row += "<tr><td><input type='text' id='name' name='name' class='form-control' value='"+programName+"' required></td>" +
	     					"<td><input type='text' id='sectionName' name='sectionName' class='form-control'onclick='message()' value='"+sections+"' required></td></tr>";   
	     			$("#programGroupDetails").append(row);
    			}
	     		
	     		
	     $('#modalInfo').fadeIn("fast");
	     var btn=document.getElementById("updateProgramGroup");
	     btn.setAttribute("onclick","saveData('"+row_id+"');");	

};
function saveData(rowId){
	
	//alert("11");
	var newStandardName=document.getElementById("name").value;
	//alert("newStandardName==="+newStandardName);
	var newSectionName= document.getElementById("sectionName").value;
	//alert("newSectionName==="+newSectionName);

	

		document.getElementById("saveId").value=rowId;
		document.getElementById("newProgramName").value=newStandardName;
		document.getElementById("newBatchName").value=newSectionName;
	
		
		var name=document.getElementById("newProgramName").value;
		var sec=document.getElementById("newBatchName").value;
		var status=validatingField(rowId);
		if (status==true)
			{
			//alert("22");
			document.editStandard.submit();
			}
		
}
		
		
function validatingField(rowId)
{
		//alert("totally");
		var reg=/^[a-zA-Z\s]+$/;
		var reg1=/^[a-zA-Z,\s]+$/;
		var reg2=/^[a-zA-Z\s\,]+$/;
	
		
	
		var oldProgramName = document.getElementsByName("oldStandardNames");
		
		var name=document.getElementById("newProgramName").value;
		var sec=document.getElementById("newBatchName").value;
		name=name.trim();
		name=name.toUpperCase();
		
		if(reg2.test(sec)==false)
		{
			//alert("sec alp check");
			$('#javascriptmsg').css('display','block');
			document.getElementById("javascriptmsg").innerHTML = "Section Name must be in alphabets only";
			//alert("in1");
		
		return false;
		}
		
		else if(name == ""||name =='null')
		{
			//alert("name null check");
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please enter Course Name";	
			//alert("in2");
			return false;
		}
		else if(reg.test(name)==false)
		{
			//alert("na alp check");
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Course Name must be in alphabets only";	
			//alert("in3");
			return false;
		}
		
		else if(sec == ""||sec =='null')
		{
			//alert("in sec null check");
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Section Name cannot be Empty";	
			//alert("in4");
			return false;
		}
		
		
	/*	for(var i=0; i<oldSectionName.length;i++)
		{
			if(oldSectionName[i].value==sec)
			{
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Section Name Already Exist";
				
				return false;
			}
		}*/
			for(var i=0; i<oldProgramName.length;i++)
			{
				if(oldProgramName[i].value==name)
				{
					//alert("in5");
					var length= document.getElementsByName("oldSectionNames"+i).length;
					
					var oldSectionName,add;
					oldSectionName = document.getElementsByName("oldSectionNames"+i)[0].value;
					
					for(var j=1;j<length;j++){
						
						oldSectionName = oldSectionName+","+ document.getElementsByName("oldSectionNames"+i)[j].value;
						
					}
					if(oldSectionName==sec)
						{
							document.getElementById("javascriptmsg").style.display = 'block';			
							document.getElementById("javascriptmsg").innerHTML = "Name with same section already Exists";
							//alert("in6");
							return false;
						}
					return true;
				}
			}	
			return true;
		}




function closeWarning(){
	document.getElementById("javascriptmsg").style.display = 'none';	
}
function message()
{
	document.getElementById("javascriptmsg").style.display = 'block';			
	document.getElementById("javascriptmsg").innerHTML = "comma seperated values";
	}

function validating()
{
	
	var oldName=document.getElementById("standardName").value;
	oldName=oldName.trim();
	oldName=oldName.toUpperCase();
	
	var oldProgramName = document.getElementsByName("oldStandardNames");
	for(var i=0; i<oldProgramName.length;i++)
	{
		if(oldProgramName[i].value==oldName)
		{
			
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "Programme Name already Exists";
			return false;
		}
	
	}
	return true;
}