var taskIndex;
var resourceIndex = 0;
$("#resourceName").change(function() {
	$.ajax({
		url: '/icam/getJobForApproverGroup.html',
		dataType: 'json',
		data: "approvarGroupCode=" + ($("#resourceName").val()),
		success: function(data){  
			var options="<option value=''>Select</option>";
			if(data != "null" && data !=""){
				var jobArray = data.split("#");
				for(var i=0;i<jobArray.length-1;i++){ 
					var jobName = jobArray[i].split("*"); 
					options=options+"<option value='"+jobName[0]+"'>"+jobName[1]+"</option>";
				}
			}
			document.getElementById("jobTypeName").innerHTML=options;
		}			
	}); 		
	document.getElementById("resource").style.display = "none";
	document.getElementById("footer").style.display = "none";
	document.getElementById("modalInfo1").style.display = "none";
	document.getElementById("tasks").style.display = "none";
});
	
$("#jobTypeName").change(function() {
	var existingJobs = document.getElementById("taskTable");
	var tableID = document.getElementById("taskTable");
	var jsonData = document.getElementById("jsonData");
	document.getElementById("tasks").style.display = "block";
	$.ajax({
		url: '/icam/getTaskDetailsAgainstJobTypeAndApproverGroup.html',
		dataType: 'json',
		data: "jobType=" + ($("#jobTypeName").val())+"&approverGroup="+($("#resourceName").val()),
		success: function(data){  
			existingJobs.removeAttribute("style");
			existingJobs.setAttribute("style","visibility: visible;");	
			jsonData.value = data;
			view(tableID);	
		}				
	}); 
	document.getElementById("resource").style.display = "none";
	document.getElementById("footer").style.display = "none";
	document.getElementById("modalInfo1").style.display = "none";
});
	
var p = 0;
function view(tableID){
	var data=document.getElementById("jsonData").value;	
	var taskList = data.split("@");
	var numberOfRows = taskList[0].split("#");	
	var resourceTypeList =taskList[1].split("#");		
	var tableBody = document.getElementById("taskTable");
	deleteRows(tableBody);	
	for(var i=0;i<numberOfRows.length-1;i++){		
		var job = numberOfRows[i].split("*");
		var jobCode = job[0];
		var jobName = job[1];
		var startDate = job[2];
		var endDate = job[3];
		var status = job[4];
		var desc = job[5];
		var ticketId = job[6];
		var rowCount = tableBody.rows.length;
		var row = tableBody.insertRow(rowCount);
		
		var cell0 = row.insertCell(0);
		cell0.innerHTML = jobName;
		var element0 = document.createElement("input");
		element0.type = "hidden";
		element0.value = jobCode;
		element0.name = "jobCode"+i;
		element0.id = "jobCode"+i;
		cell0.appendChild(element0);
		
		var cell1 = row.insertCell(1);
		cell1.innerHTML = startDate;
		var element1 = document.createElement("input");
		element1.type = "hidden";
		element1.value = jobName;
		element1.name = "jobName"+i;
		element1.id = "jobName"+i;
		cell1.appendChild(element1); 
		
		var cell2 = row.insertCell(2);
		if(endDate != "null" && endDate !=""){
			cell2.innerHTML = endDate;
		}else{
			cell2.innerHTML = "Not Closed";
		}
		
		var cell3 = row.insertCell(3);
		cell3.innerHTML = desc;
		var element3 = document.createElement("input");
		element3.type = "hidden";
		element3.value = ticketId;
		element3.name = "ticketId"+i;
		element3.id = "ticketId"+i;
		cell3.appendChild(element3);
		
		var cell4 = row.insertCell(4);
		cell4.innerHTML = status;
		var element4 = document.createElement("input");
		element4.type = "hidden";
		element4.value = status;
		element4.name = "status"+i;
		element4.id = "status"+i;
		cell4.appendChild(element4);
		
		if (status == 'OPEN'){
			var cell5 = row.insertCell(5);
			var a = document.createElement("a");
			var t = document.createTextNode("Allocate");
			a.id = "allocate"+i;
			a.name = "allocate"+i;
			a.setAttribute("class","mb-xs mt-xs mr-xs btn btn-warning");
			a.appendChild(t);
			a.addEventListener('click', function() {
				var split= (this.id).split("allocate");
				document.getElementById("modalInfo1").style.display = "none";
				document.getElementById("resource").style.display = "block";
				document.getElementById("footer").style.display = "block";
				var index=split[1];
				taskIndex = index;	
				document.allocateTask.submit();
			}, false);
			cell5.appendChild(a);
		}else{
			var cell5 = row.insertCell(5);
			var a = document.createElement("a");
			var t = document.createTextNode("Details");
			a.id = "details"+i;
			a.name = "details"+i;
			a.setAttribute("class","mb-xs mt-xs mr-xs btn btn-xs btn-primary");
			a.appendChild(t);
			a.addEventListener('click', function() {
				var split = (this.id).split("details");
				document.getElementById("resource").style.display = "none";
				document.getElementById("footer").style.display = "none";
				document.getElementById("modalInfo1").style.display = "block";
				var index=split[1];
				showDetails(index);
			},false);
			cell5.appendChild(a);
			var a = document.createElement("a");
			var t = document.createTextNode("Allocate");
			a.id = "allocate"+i;
			a.name = "allocate"+i;
			a.setAttribute("class","mb-xs mt-xs mr-xs btn btn-xs btn-warning");
			a.appendChild(t);
			a.addEventListener('click', function() {
				var split= (this.id).split("allocate");
				document.getElementById("modalInfo1").style.display = "none";
				document.getElementById("resource").style.display = "block";
				document.getElementById("footer").style.display = "block";
				var index=split[1];
				 taskIndex = index;	
			}, false);
			cell5.appendChild(a);
		}
		p++;
	}	
}	
	
function showDetails(index){
	document.getElementById("resourceTypeName").removeAttribute("required");   
	document.getElementById("userId0").removeAttribute("required");
	document.getElementById("name0").removeAttribute("required");
	document.getElementById("desc0").removeAttribute("required");
	var ticketId  = document.getElementById("ticketId"+index).value;
	$.ajax({
		url: '/icam/getDetailsForATicket.html',
		dataType: 'json',
		data: "ticketId=" + ticketId,
	    success: function(data) {
	    	$('#ticketDetailsTable > tbody').empty();
	    	var row = "<tbody>";
	    	if(data != null && data!=""){
	     		var dataArr = data.split("#");
	     		for(var i=1; i<dataArr.length; i++){
    				var arr = dataArr[i].split("*");
    				row += "<tr><td>"+arr[0]+"</td><td>"+arr[1]+"</td><td>"+arr[2]+"</td></tr>";    				
    			}
	     		$("#ticketDetailsTable").append(row);
	     	}	
	    }	
	});
}
	
	
function resetValue(){
	document.getElementById("tasks").style.display = "none";
	document.getElementById("resource").style.display = "none";
	document.getElementById("footer").style.display = "none";
	return false;
}
	
function deleteRows(tableID){
	var table = document.getElementById("datatable-tabletools");
	var rowCount = table.rows.length-1;
	for(var i=rowCount; i>0; i--){	
		table.deleteRow(i);
	}
}

function removeOption(){
	var resourceTypeName=document.getElementById("resourceName");
	for(var count=resourceTypeName.length;count>0;count--){
		resourceTypeName.remove(count);
	}
}

$("#resourceTypeName").change(function (){
	var type  = $("#resourceTypeName").val();
	if(($("#resourceTypeName").val()!=null)){
		$("#userId0").autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
			select: function (event, ui){
				var userId = ui.item.value;
				
				$.ajax({
					url: '/icam/getUserNameForId.html',
					dataType: 'json',
					data: "userId=" + userId,
					success: function(data) {
						if(data != null && data!=""){
							($("#name0").val(data));
						}
					}			        
				});
			}
	 	});
	}
});

function getUserId(thisClassNode){
	var split= (thisClassNode.id).split("resourceType");
	var index=split[1];
	
	if(($("#resourceType"+index).val()!=null)){
		$("#userId"+index).autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
			select: function (event, ui){
				var userId = ui.item.value;
				$.ajax({
					url: '/icam/getUserNameForId.html',
					dataType: 'json',
					data: "userId=" + userId,
					success: function(data) {
						if(data != null && data!=""){
							($("#name"+index).val(data));
						}
					}			        
				});
			}
	 	});
	}
}

function auto(userId,name){
	$(userId).autocomplete({	 
		source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()),
		select: function (event, ui){
		var userId = ui.item.value;
			$.ajax({
				url: '/icam/getUserNameForId.html',
				dataType: 'json',
				data: "userId=" + userId,
				success: function(data) {
					if(data != null && data!=""){
						($(name).val(data));
					}else{	  
						$(name).val("");
					}
				}
			});
		}	
	});	
}

var index=1;
var newIndex = 0;
function addrows(){		
	var userIds = document.getElementsByName("userName");
	var names = document.getElementsByName("name");
	var resourceTypeCodes = document.getElementsByName("resourceTypeCode");
	var resourceTypeNames = document.getElementsByName("resourceTypeName");
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
	}
	var resourceTypeCode = null;
	var resourceTypeName = null;
	for(var i=0;i<resourceTypeCodes.length;i++){
		resourceTypeCode = resourceTypeCode + "*" +resourceTypeCodes[i].value;
		resourceTypeName = resourceTypeName + "*" + resourceTypeNames[i].value;
	}
	var resourceCode = resourceTypeCode.split("*");
	var resourceName = resourceTypeName.split("*");
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "resourceType" + index;
	element.name = "resourceType";
	element.setAttribute( "required","required");
	element.setAttribute("onchange","getUserId(this);");	
	element.add(new Option("Select", ""));
	for(var i = 1; i<resourceCode.length;i++){
		element.add(new Option(resourceName[i], resourceCode[i]));
	}
	cell0.appendChild(element);
	
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="userName"+index;
	element1.id="userId"+index;
	element1.className="form-control";	
	element1.setAttribute( "required","required");
	cell1.appendChild(element1);	            
	            
	var cell2 = row.insertCell(2);		
	var element2 = document.createElement("input");
	element2.type = "text";
	element2.name="name"+index;
	element2.className="form-control";
	element2.id="name"+index;
	element2.size=25;
	element2.setAttribute("readonly","readonly");
	cell2.appendChild(element2);
	
	var cell3 = row.insertCell(3);		
	var element3 = document.createElement("textarea");
	element3.name="desc"+index;
	element3.className="form-control";
	element3.id="desc"+index;
	element3.size=25;
	element3.setAttribute( "required","required");
	cell3.appendChild(element3);	
	
	var cell4= row.insertCell(4);
	var element4 = document.createElement('a');
	element4.setAttribute("class","fa fa-minus-square");
	element4.setAttribute("onclick", "deleteRow(this);");
	element4.setAttribute("href","#");
	cell4.appendChild(element4);
	
	var userId="#userId"+index;
	var name="#name"+index;
	auto(userId,name);
	resourceIndex = index;
	index++;
}
 
function deleteRow(obj){
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

function setIndex(){
	document.getElementById("taskIndex").value=taskIndex;
	document.getElementById("resourceIndex").value=resourceIndex;
}