function checkDuplicate(){
	
//	alert("hii");
	var taskStatus = $("#taskStatus").val();
	var taskType = $("input[name=approval]").val();
	var type = $("input[name=ticketDesc]").val();
	var ticketStatus = $("#status").val();
//	alert("taskStatus =="+taskStatus);
	//alert("taskType =="+taskType);
	//alert("type =="+type);
//	alert("ticketStatus =="+ticketStatus);
	$.ajax({
		url: '/icam/getTaskStatusForDuplicateCheck.html',
		dataType: 'json',
		data: "taskType="+taskType+"&type="+type+"&ticketStatus="+ticketStatus,
		success: function(data){ 
		//	alert("data=="+data);
			if(data != "" && data != null){
				//alert("data");
				document.getElementById("warningmsg2").style.display = 'block';			
				document.getElementById("warningmsg2").innerHTML = "Duplicate Task Status ";
				return false;
			}else{
				//alert("no data");
				document.getElementById("warningmsg2").style.display = 'none';
				document.getElementById("warningmsg2").innerHTML = "";
				document.createTaskStatus.submit();
			}
		}
	});
	
}



/**Edit Task Status Details
	By Ranita.Sur 24102017**/
function updateTaskStatus(rowId){
		//alert("ESSEE ");
	    var status = document.getElementById("status"+rowId).value;
	    //alert("status===="+status);
	    var approval = document.getElementById("approval"+rowId).value;
	    //alert("approval===="+approval);
		var ticketSatusCode = document.getElementById("description"+rowId).value;
		// alert("ticketDesc===="+ticketDesc);
		var option="<option value=''>Select</option>";
		var oldTicketType="";
		var oldTicketStatusNames="";
		
		$("#oldTaskType input[name='oldTask']").each(function(){
			oldTicketType += $(this).val() + ";";
		});
		//alert("LN15 : "+oldTicketType);
		
		$("#oldTaskType input[name='oldTicketStatusName']").each(function(){
			oldTicketStatusNames += $(this).val() + ";";
		});
		//alert("LN58 : "+oldTicketStatusNames)
		
		
		
		var oldTicketList = oldTicketType.split(";");
		
		var oldTicketStautsNameList = oldTicketStatusNames.split(";");
		
		
		for( var i=0;i<oldTicketList.length-1;i++){
			var val=oldTicketList[i].value;
			//alert(val);
			 if(oldTicketList[i]==ticketSatusCode.trim()){
				 option+="<option value='"+oldTicketList[i]+"' selected>"+oldTicketStautsNameList[i]+"</option>";
	    	}else{
	    		option+="<option value='"+oldTicketList[i]+"'>"+oldTicketStautsNameList[i]+"</option>";
	    	}
		}	
		
		var selections="";
		if(approval == "APPROVAL"){
			selections+="<input type='radio' name='approvalType' id='approvalType' value='APPROVAL' checked='checked' disabled>Approval<br>";
			selections+="<input type='radio' name='approvalType' id='approvalType' value='NONAPPROVAL' disabled>Non Approval";
		}else{
			selections+="<input type='radio' name='approvalType' id='approvalType' value='APPROVAL' disabled>Approval<br>";
			selections+="<input type='radio' name='approvalType' id='approvalType' value='NONAPPROVAL' checked='checked' disabled>Non Approval";
		}
		
		$('#updateTaskStatusDetails > tbody').empty();
	 	if(status != null && status!=""){
	 		var row = "<tbody>";
	 		row += "<tr><td><input type='text' name='status' id='status' class = 'form-control'  value='"+status+"'></td>" 
	 				+"<td>"+selections+"</td>" +
	 				"<td><select id='ticketDesc' name='ticketDesc' class='form-control' disabled>'"+option+"'</select></td></tr>";
	 		$("#updateTaskStatusDetails").append(row);
	 	}
	 	
	 	$('#modalInfo').fadeIn("fast");
	 	var btn = document.getElementById("updateTaskStatusButton");
	 	btn.setAttribute("onclick","saveData('"+rowId+"');");
		
		
	}
	
	/**Edit Task Status Details
	By Ranita.Sur 24102017**/
	function saveData(rowid){
		var newTaskName = document.getElementById("status").value;
		var newTaskDesc = $("input[name=approvalType]:checked").val();
		var newTaskApprove = document.getElementById("ticketDesc").value;
		document.getElementById("saveId").value=rowid;
		document.getElementById("getTaskStatus").value=newTaskName;
		document.getElementById("getTaskType").value=newTaskDesc;
		document.getElementById("getTicketStatus").value=newTaskApprove;
    	document.getElementById("warningmsg1").style.display = 'none';
    	
		var status=validateEditNewTaskStatusForm(rowid);
			if(status==true)
			{
				document.editTaskStatus.submit();
			}
		
			
		
	};
	
	
	
	/*modified by ranita.sur on 24102017*/
	function validateEditNewTaskStatusForm(rowId){
	  	var alphaNumeric=/^[a-zA-Z ]+$/;			
		var taskStatusName = document.getElementById("status").value;
		var newtaskStatusName = document.getElementById("getTaskStatus").value;
		newtaskStatusName=newtaskStatusName.trim();
		newtaskStatusName=newtaskStatusName.toUpperCase();
		
		var taskStatusList = document.getElementsByName("oldTaskStatusNamesForDuplicateCheck");		
		for(var i=0; i<taskStatusList.length; i++){
			var oldVal=taskStatusList[i].value;
			if(oldVal==newtaskStatusName){
				document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Duplicate Task Status Name";
				return false;
			}
		}
		
		 if(taskStatusName ==""|| taskStatusName==null ){
				document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Enter Task Status Name"; 
				return false;
			}else if (alphaNumeric.test(taskStatusName)==false)
		    {
		    	document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Task Status Name can contain alphabets and spaces  between words !!";
				return false;
		    }else
				{
				return true;
				}
				
	}
	function closeWarning()
	{
		document.getElementById("warningmsg1").style.display = 'none';	
		}
