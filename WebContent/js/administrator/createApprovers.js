
	
	$("#resourceTypeName").change(function (){
		if(($("#resourceTypeName").val()!=null)){
			
			$("#userId0").autocomplete({
				source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
				select: function (event, ui){
					var userId0 = ui.item.value;
					$.ajax({
						url: '/icam/getUserNameForId.html',
						dataType: 'json',
						data: "userId=" + userId0,
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
	
	


function auto(userId,name){
	//alert("Phase2");
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
	
	//document.getElementById("warningbox").style.visibility='collapse';
	
	var userIds=document.getElementsByName("userName");
	var names=document.getElementsByName("name");
	
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
		
	}
	
	
	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="userName";
	element0.id="userId"+index;
	element0.className="form-control";	
	element0.setAttribute( "required","");
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="name";
	element1.className="form-control";
	element1.id="name"+index;
	element1.size=25;
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	/*var element2 = document.createElement("img");
	element2.setAttribute("src","/icam/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");	*/	
	var element2 = document.createElement('a');
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell2.appendChild(element2);		
		
	var userId="#userId"+index;
	var name="#name"+index;
	auto(userId,name);
	index++;
}
 
function deleteRow(obj){	
//	document.getElementById("warningbox").style.visibility='collapse';
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
} 

function showApproverGroupDetails(approverGroupDetails)
{
	//alert("hii");
	//alert(approverGroupDetails);
	$.ajax({
		url: '/icam/getApproverGroupDetails.html',
		dataType: 'json',
		data: "approverGroupCode=" + approverGroupDetails,
	    success: function(data) {
	    	 $('#approverGroupDetails > tbody').empty();
	     	if(data != null && data!=""){
	     		var dataarr = data.toString().split("#");
	     		var row = "<tbody>";
	     		var arr = dataarr[1].toString().split("*");
	     		for(var i=0; i<arr.length; i++){
    				var arr2 = arr[i].toString().split("-");
    				row += "<tr><td>"+arr2[0]+"</td><td>"+arr2[1]+"</td></tr>";    				
    			}
	     		
	     		$("#approverGroupDetails").append(row);
	     		
		     	}
	     	$('#modalInfo').fadeIn("fast");
	     	
	     	
	    }
	});
}
function deleteApproverGroupDetails(url){
	//alert("inactiveApproverGroupDetails.html?approverGroupCode="+url);
	window.location="inactiveApproverGroupDetails.html?approverGroupCode="+url;
}
function validate(){ 
	var approverGroupName = document.getElementById("approverGroupName").value;
	var approverGroupNameList = document.getElementsByName("hiddenApproverGroupName");
	approverGroupName = approverGroupName.replace(/\s{1,}/g,' ');
	approverGroupName = approverGroupName.trim();
	for(var i=0;i<approverGroupNameList.length;i++){
		if(approverGroupNameList[i].value == approverGroupName){
			alert("Group Name already Exists");
			return false;
		}
	}
	return true;
}