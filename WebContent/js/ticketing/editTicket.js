

//$(document).ready(
  //      function() {
            //add more file components if Add is clicked
           
            	$(".addFileClassName").click(function(){                      		
            		var tableNode = $(this).parent().parent().parent().parent();
            		
            		 var row = $('<tr>'); 
            		 //var row = $('<tr><td><input type="file" name="uploadFile.ticketingRelatedFile" /></td><td><img  src="/cedugenie/images/minus_icon.png" onclick="deleteThisRow(this);"></td></tr>');
	                 row.append($('<td><input type="file" name="uploadFile.ticketingRelatedFile" /></td><td><img  src="/cedugenie/images/minus_icon.png" onclick="deleteThisRow(this);"></td>'));
	            $(tableNode).append(row); 
				});
            //});
      
function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}

function getUserId(thisClassNode){
	
	var split= (thisClassNode.id).split("individual");
	var index=split[1];
	//alert("index===="+index)
	//alert($("#individual"+index).val());
	
	if(($("#individual"+index).val()!=null)){
		
		$("#resource"+index).autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#individual"+index).val()) ,
			/*select: function (event, ui){
				var userId = ui.item.value;
				
			}*/
		})
	}
}


var room = 1;
function address_fields() {
 
		room++;
	var table = document.getElementById("address_fields");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);  
	row.setAttribute("class","removeclass"+room);
	
	var cell0 = row.insertCell(0);
	//element = document.createElement("text");
	cell0.innerHTML = room ; 
	
	var taskCodes = document.getElementsByName("hiddenTaskCode");
	var taskNames = document.getElementsByName("hiddenTaskName");
	
	var taskCode = null;
	var taskName = null;
	for(var i=0;i<taskCodes.length;i++){
		taskCode = taskCode + "*" +taskCodes[i].value;
		taskName = taskName + "*" + taskNames[i].value;
	}
	var taskCodeArr = taskCode.split("*");
	var taskNameArr = taskName.split("*");
	
	
	
	var cell1 = row.insertCell(1);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "task" + room;
	element.name = "task";

	element.add(new Option("Please Select", ""));
	 for(var i = 1; i<taskCodeArr.length;i++){
		element.add(new Option(taskNameArr[i], taskCodeArr[i]));
	} 
	cell1.appendChild(element);            
	            
	      
		        
	var cell2= row.insertCell(2);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "level" + room;
	element.name = "level";
	element.add(new Option("Please Select", ""));
	element.add(new Option(1, 1));
	element.add(new Option(2, 2));
	cell2.appendChild(element);		
	
	var cell3= row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "approval" + room;
	element.name = "approval";
	element.setAttribute("onchange","checkresource(this);");
	element.add(new Option("Please Select", ""));
	element.add(new Option("Group", "group"));
	element.add(new Option("Individual", "individual")); 
	cell3.appendChild(element);
	
	var resourceTypeCodes = document.getElementsByName("resourceTypeCode");
	var resourceTypeNames = document.getElementsByName("resourceTypeName");
	
	var resourceTypeCode = null;
	var resourceTypeName = null;
	for(var i=0;i<resourceTypeCodes.length;i++){
		resourceTypeCode = resourceTypeCode + "*" +resourceTypeCodes[i].value;
		resourceTypeName = resourceTypeName + "*" + resourceTypeNames[i].value;
	}
	var resourceCode = resourceTypeCode.split("*");
	var resourceName = resourceTypeName.split("*")
	
	//var cell3 = row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "individual" + room;
	element.name = "individual"+room;
	//element.setAttribute( "required","required");
	element.setAttribute("onchange","getUserId(this);");	
	element.setAttribute("style","display:none; margin-top:10px");
	element.add(new Option("Please Select Resource Type", ""));
	for(var i = 1; i<resourceCode.length;i++){
		element.add(new Option(resourceName[i], resourceCode[i]));
	}
	cell3.appendChild(element);
	
	
	element = document.createElement("input");
	element.className = "form-control";
	element.id = "resource" + room;
	element.name = "resource"+room;
	element.setAttribute("style","display:none; margin-top:10px");
	element.setAttribute("placeholder","Select User Id");
	cell3.appendChild(element);
	
	
	var groupCodes = document.getElementsByName("groupCode");
	var groupNames = document.getElementsByName("groupName");
	
	var groupCode = null;
	var groupName = null;
	for(var i=0;i<groupCodes.length;i++){
		groupCode = groupCode + "*" +groupCodes[i].value;
		groupName = groupName + "*" + groupNames[i].value;
	}
	var groupCodeArr = groupCode.split("*");
	var groupNameArr = groupName.split("*")
	
	//var cell3 = row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "group" + room;
	element.name = "group"+room;
	//element.setAttribute( "required","required");
		
	element.setAttribute("style","display:none; margin-top:10px");
	element.add(new Option("Please Select Recipient Group", ""));
	for(var i = 1; i<groupCodeArr.length;i++){
		element.add(new Option(groupNameArr[i], groupCodeArr[i]));
	}
	cell3.appendChild(element);
	
	
	var cell4 = row.insertCell(4);
	cell4.setAttribute("class","text-center");
	var element2= document.createElement('a');	
	element2.id = "delete" + room;
	element2.setAttribute("class","btn btn-danger btn-sm fa fa-times");
	element2.setAttribute("onclick", "remove_address_fields(this);");
	cell4.appendChild(element2);
	/* element2.setAttribute("href","#");  */
	
		

	
}
function checkresource(thisClassNode){

    
    var split= (thisClassNode.id).split("approval");
	var room=split[1];
	//alert("room=="+room);
    var approval = $("#approval"+room).val();
    //alert("approval=="+approval);
    if(approval == "" || approval==" "){
    	$("#individual"+ room).hide('slow');
   	 	$("#resource"+ room).hide('slow');
   	 	$("#group"+ room).hide('slow');
    }
    if(approval == 'group'){
    	 $("#individual"+ room).hide('slow');
    	 $("#resource"+ room).hide('slow');
    	 $("#group"+ room).show('slow');
    }
    
	if(approval == 'individual'){
		 $("#individual"+ room).show('slow');
		 $("#resource"+ room).show('slow');
		 $("#group"+ room).hide('slow');
    }
	
	
}
function remove_address_fields(thisClassNode) {
	
	var split= (thisClassNode.id).split("delete");
    var rid=split[1]
    //alert("rid=="+rid);
	$('.removeclass'+rid).remove();
}



var room1 = 1;
function additional_address_fields() {
 
		room1++;
	var table = document.getElementById("additional_address_fields");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);  
	row.setAttribute("class","removeclass"+room1);
	
	var cell0 = row.insertCell(0);
	//element = document.createElement("text");
	cell0.innerHTML = room1 ; 
	
	var taskCodes = document.getElementsByName("hiddenAllTaskCode");
	var taskNames = document.getElementsByName("hiddenAllTaskName");
	
	var taskCode = null;
	var taskName = null;
	for(var i=0;i<taskCodes.length;i++){
		taskCode = taskCode + "*" +taskCodes[i].value;
		taskName = taskName + "*" + taskNames[i].value;
	}
	var taskCodeArr = taskCode.split("*");
	var taskNameArr = taskName.split("*");
	
	
	
	var cell1 = row.insertCell(1);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "additinalTask" + room1;
	element.name = "additinalTask";

	element.add(new Option("Please Select", ""));
	 for(var i = 1; i<taskCodeArr.length;i++){
		element.add(new Option(taskNameArr[i], taskCodeArr[i]));
	} 
	cell1.appendChild(element);            
	            
	      
		        
	var cell2= row.insertCell(2);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "additionalLevel" + room1;
	element.name = "additionalLevel";
	element.add(new Option("Please Select", ""));
	element.add(new Option(1, 1));
	element.add(new Option(2, 2));
	cell2.appendChild(element);		
	
	var cell3= row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "additionalApproval" + room1;
	element.name = "additionalApproval";
	element.setAttribute("onchange","checkResource(this);");
	element.add(new Option("Please Select", ""));
	element.add(new Option("Group", "group"));
	element.add(new Option("Individual", "individual")); 
	cell3.appendChild(element);
	
	var resourceTypeCodes = document.getElementsByName("resourceTypeCode");
	var resourceTypeNames = document.getElementsByName("resourceTypeName");
	
	var resourceTypeCode = null;
	var resourceTypeName = null;
	for(var i=0;i<resourceTypeCodes.length;i++){
		resourceTypeCode = resourceTypeCode + "*" +resourceTypeCodes[i].value;
		resourceTypeName = resourceTypeName + "*" + resourceTypeNames[i].value;
	}
	var resourceCode = resourceTypeCode.split("*");
	var resourceName = resourceTypeName.split("*")
	
	//var cell3 = row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "additionalIndividual" + room1;
	element.name = "additionalIndividual"+room1;
	//element.setAttribute( "required","required");
	element.setAttribute("onchange","getUserIdNew(this);");	
	element.setAttribute("style","display:none; margin-top:10px");
	element.add(new Option("Please Select Resource Type", ""));
	for(var i = 1; i<resourceCode.length;i++){
		element.add(new Option(resourceName[i], resourceCode[i]));
	}
	cell3.appendChild(element);
	
	
	element = document.createElement("input");
	element.className = "form-control";
	element.id = "additionalResource" + room1;
	element.name = "additionalResource"+room1;
	element.setAttribute("style","display:none; margin-top:10px");
	element.setAttribute("placeholder","Select User Id");
	cell3.appendChild(element);
	
	
	var groupCodes = document.getElementsByName("groupCode");
	var groupNames = document.getElementsByName("groupName");
	
	var groupCode = null;
	var groupName = null;
	for(var i=0;i<groupCodes.length;i++){
		groupCode = groupCode + "*" +groupCodes[i].value;
		groupName = groupName + "*" + groupNames[i].value;
	}
	var groupCodeArr = groupCode.split("*");
	var groupNameArr = groupName.split("*")
	
	//var cell3 = row.insertCell(3);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "additionalGroup" + room1;
	element.name = "additionalGroup"+room1;
	//element.setAttribute( "required","required");
		
	element.setAttribute("style","display:none; margin-top:10px");
	element.add(new Option("Please Select Recipient Group", ""));
	for(var i = 1; i<groupCodeArr.length;i++){
		element.add(new Option(groupNameArr[i], groupCodeArr[i]));
	}
	cell3.appendChild(element);
	
	
	var cell4 = row.insertCell(4);
	cell4.setAttribute("class","text-center");
	var element2= document.createElement('a');	
	element2.id = "delete" + room1;
	element2.setAttribute("class","btn btn-danger btn-sm fa fa-times");
	element2.setAttribute("onclick", "remove_address_fields(this);");
	cell4.appendChild(element2);
	/* element2.setAttribute("href","#");  */
	
		

	
}

function checkResource(thisClassNode){

	//alert("hiii");
    //alert("thisClassNode==="+thisClassNode.id);
    var split1= (thisClassNode.id).split("additionalApproval");
	var room2 = split1[1];
	//alert("room2=="+room2);
    var approval = $("#additionalApproval"+room2).val();
   // alert("approval=="+approval);
    if(approval == "" || approval==" "){
    	$("#additionalIndividual"+ room2).hide('slow');
   	 	$("#additionalResource"+ room2).hide('slow');
   	 	$("#additionalGroup"+ room2).hide('slow');
    }
    if(approval == 'group'){
    	 $("#additionalIndividual"+ room2).hide('slow');
    	 $("#additionalResource"+ room2).hide('slow');
    	 $("#additionalGroup"+ room2).show('slow');
    }
    
	if(approval == 'individual'){
		 $("#additionalIndividual"+ room2).show('slow');
		 $("#additionalResource"+ room2).show('slow');
		 $("#additionalGroup"+ room2).hide('slow');
    }
	
	
}


function getUserIdNew(thisClassNode){
	
	var split= (thisClassNode.id).split("additionalIndividual");
	var index=split[1];
	//alert("index===="+index)
	//alert($("#additionalIndividual"+index).val());
	
	if(($("#additionalIndividual"+index).val()!=null)){
		
		$("#additionalResource"+index).autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#additionalIndividual"+index).val()) ,
			/*select: function (event, ui){
				var userId = ui.item.value;
				
			}*/
		})
	}
}
	