function addSubjectGroup(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;

	var subjectGroupName=document.getElementById("subjectGroupName").value;	
	subjectGroupName=subjectGroupName.replace(/\s{1,}/g,' ');
	subjectGroupName=subjectGroupName.trim();
	subjectGroupName=subjectGroupName.toUpperCase();
	document.getElementById("subjectGroupName").value=subjectGroupName;	
	
	/*if(subjectGroupName == ""||subjectGroupName =='null'){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Invalid Subject Group Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("Please Enter SubjectGroupName");
		return false;
	}
	
	if(!subjectGroupName.match(reg1)){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Invalid Subject Group Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("Invalid Subject Group Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}*/
	var oldSubjectGroupNames = document.getElementsByName("oldSubjectGroupNames");
	
	for(var i=0; i<oldSubjectGroupNames.length;i++){
		if(oldSubjectGroupNames[i].value==subjectGroupName){
			alert("Course Name Already Exixts");
			return false;
		}
	}
	if(validateOrder(document.getElementById("subjectGroupOrderId"))=="invalid"){
		return false;
	}
	//document.subjectGroupForm.method="Post";
	//document.subjectGroupForm.action="addSubjectGroup.html";
	//document.subjectGroupForm.submit();
	return true;
}
function editSubjectGroup(rowId){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var subjectGroupCode = document.getElementsByName('subjectGroupName'+rowId);
	/*var counter=0;
	for(var i=0; i<subjectGroupCode.length;i++){
		if(subjectGroupCode[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox1").style.visibility='visible';
		document.getElementById("warningmsg1").innerHTML="Select atleast one subject group to update";
		return false;
	}else{*/
		var oldSubjectGroupNames = document.getElementsByName('oldSubjectGroupNames');
		for(var j=0; j<oldSubjectGroupNames.length;j++){
				var id = 'subjectGroupName'+rowId;
				var newName=document.getElementById(id).value;
				newName=newName.replace(/\s{1,}/g,' ');
				newName=newName.trim();
				newName=newName.toUpperCase();
				document.getElementById(id).value=newName;
				if(!newName.match(reg1)){
						/*document.getElementById("warningbox1").style.visibility='visible';
						document.getElementById("warningmsg1").innerHTML="Invalid Subject Group Name. (Alpha-Numeric. Atleat 1 character.)";*/
						alert("Invalid Subject Group Name. (Alpha-Numeric. Atleat 1 character.)");
						return false;
					}
					if(oldSubjectGroupNames[j].value==newName){					
						alert("Subject Group Name Already Exixts");
						return false;
						
					}
					if(validateOrder(document.getElementById("subjectGroupOrderId"+rowId))=="invalid"){
						return false;
					}
				
			//}
		}
	//}
	
	//document.subjectGroupForm.method="Post";
	//document.subjectGroupForm.action="editSubjectGroup.html";
	//document.subjectGroupForm.submit();
	return true;
}

function clearOrder(box){
	if(box.value=="0"){
		box.value="";
	}
}
function validateOrder(box){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var order=box.value;
	order=order.replace(/\s{1,}/g,'');
	box.value=order;
	
	if(order == "" || order == 0){
		alert("Select Subject Group Order.");
		return "invalid";
	}
	if(!order.match(intRegx)){
		//document.getElementById(id1).style.visibility='visible';
		//document.getElementById(id2).innerHTML="Subject Group Order Must Be Integer Value";
		alert("Subject Group Order Must Be Integer Value");
		box.value="0";
		return "invalid";
	}else{
		order = parseInt(order);
		if(order<0){
			//document.getElementById(id1).style.visibility='visible';
			//document.getElementById(id2).innerHTML="Invalid Order. (+ve Numbers Only).";
			alert("Invalid Order. (+ve Numbers Only).");
			box.value="0";
			return "invalid";
		}
	}
	return "valid";
}
function showCourseDetails(id,type,name,credit,hrscourse)
{
	/*var taskCodes = document.getElementsByName("taskCodes");
	var taskNames = document.getElementsByName("taskNames");
	
	alert(taskCodes);
	alert(taskNames);*/
	//alert("value="+taskcodes[0].value);
	
	    	/* $('#approverGroupDetails > tbody').empty();
	     	if(credit != null && credit!=""){
	     		var row = "<tbody>";
	     		row += "<tr><td><input type='hidden' name='type' id='type'>"+type+"</td>" +
	     				"<td><input type='hidden' name='name' id='name'>"+name+"</td>" +
	     				"<td><input type='text' name='id' id='code'  class = 'form-control' value='"+credit+"'></td>" +
	     				"<td><input type='text' name='hrs' id='hrs'  class = 'form-control' value='"+hrscourse+"'></td>" +
	     				"<td><select name='taskNo' id='taskNoEdit'  class = 'form-control'></select></td>" +
	     				"<td><input type='text' class= 'form-control'  name='ticketNo' id='ticketNoEdit' value='' readonly></td></tr>";
    				
    			$("#approverGroupDetails").append(row)
    		
    			
    			
	     		}*/
				if(credit != null && credit!=""){
					
					$("#type").val(type);
					$("#name").val(name)
					$("#code").val(credit);
					$("#hrs").val(hrscourse);
					
				}
	     	/*if(taskCodes != ""){
	     		var  select = document.getElementById("taskNoEdit");
	     		for(var i=0;i<taskCodes.length;i++){
		     		alert("value="+taskCodes[i].value);
		     		
		     		

		    		$('#taskNoEdit')
		            .append($("<option></option>")
		                       .attr("value",taskCodes[i].value)
		                       .text(taskNames[i].value)); 
		     		var opt = document.createElement('option');
		     	    opt.value = taskCodes[i].value;
		     	    opt.innerHTML = taskNames[i].value;
		     	    select.appendChild(opt);
		    	}
	     	}*/
	     	
	    	
	     $('#modalInfo').fadeIn("fast");
	     
	     var btn = document.getElementById("updateTerms");
	 	btn.setAttribute("onclick","saveData('"+id+"','"+name+"');");
	    
}

function saveData(rowId,name)
{
	
	
	var credit = document.getElementById("code").value;
	var hours = document.getElementById("hrs").value;
	//var taskNo = document.getElementById("taskNoEdit").value;
	//var ticketNo = document.getElementById("ticketNoEdit").value;
	
	
	document.getElementById("saveId").value = rowId;
    document.getElementById("getCredit").value = credit;
	document.getElementById("getHours").value = hours;
	//document.getElementById("taskNoUpdate").value = taskNo;
	//document.getElementById("ticketNoUpdate").value = ticketNo;
	
	

	
	if(credit<0 && hours<=100)
		{
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Credit cannot be negative";
		}
	else if(credit>9 && hours<=100){
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Credit out of range";
		} 
	else if(hours<0 && credit<=10)
	{
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration cannot be negative";
	}
	
	else if(hours>100 && credit<=10)
		{
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration out of Range";
		}
	else if(hours==0 && credit == 0)
		{
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration and Credit cannot be Empty";

		}
	else if(credit<0 && hours<0)
		{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration and Credit cannot be Negative";
		
		}
	else if(credit>9 && hours>100)
		{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration and Credit out of Range";
		}
			
/*
	else if(hours==null || hours=='')
		{
		alert("5");
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration cannot be empty";
		}
	else if(credit==null || credits=='')
		{
		alert("6");
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Credit cannot be empty";
		}
	else if (hours==null || hours=='' && credit==null || credit=='')
		{
		alert("7");
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Duration & Credit cannot be empty";
		}*/
	
	else{
	document.getElementById("javascriptmsg").style.display = 'none';
	document.getElementById("javascriptmsg").style.display = 'none';
	document.editSubjectGroup.submit();
	
		}
}

function deletemsg()
{
	
	document.getElementById("javascriptmsg").style.display = 'none';
	document.getElementById("javascriptmsg").style.display = 'none';
	}
function validating()
{
	

	var oldName=document.getElementById("subjectGroupName").value;
	oldName=oldName.trim();
	oldName=oldName.toUpperCase();

	
	
	var oldProgramName = document.getElementsByName("oldSubjectGroupNames");
	for(var i=0; i<oldProgramName.length;i++)
	{
		if(oldProgramName[i].value==oldName)
		{
			
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "Course Name already Exists";
			return false;
		}
	
	}
	return true;
	}



$("#taskNo").change(function(){
	
	$.ajax({
	    url: '/cedugenie/getTicketNoAgainstTaskCode.html',
	    	dataType: 'json',
	    	data: "taskCode="+$(this).val(),		    	
	    	success: function(data) {
	    		if(null != data){
	    			//alert("data=="+data);
	    			$("#ticketNo").val(data);
	    		}
	    	}
	});
});

$("#taskNoEdit").change(function(){
	alert("hii");
	$.ajax({
	    url: '/cedugenie/getTicketNoAgainstTaskCode.html',
	    	dataType: 'json',
	    	data: "taskCode="+$(this).val(),		    	
	    	success: function(data) {
	    		if(null != data){
	    			//alert("data=="+data);
	    			$("#ticketNoEdit").val(data);
	    		}
	    	}
	});
});