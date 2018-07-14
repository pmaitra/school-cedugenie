var index=1;
function addrows(){
	
	var table = document.getElementById("studentAchievementsBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);    
	
	
	
	var cell0 = row.insertCell(0);
	element = document.createElement("input");
	element.type = "text";
	element.className = "form-control";
	element.id = "eventPosition" + index;
	element.name = "eventPosition";
	cell0.appendChild(element);
	
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="student";
	element1.id="student"+index;
	element1.className="form-control";	
	cell1.appendChild(element1);	            
	            
	var cell2 = row.insertCell(2);		
	var element2 = document.createElement("input");
	element2.type = "file";
	element2.name="uploadFile.achievementRelatedFile";
	element2.className="form-control";
	element2.id="uploadFile.achievementRelatedFile"+index;
	cell2.appendChild(element2);
	
	var cell3 = row.insertCell(3);
	var element3 = document.createElement('a');
	element3.setAttribute("class","fa fa-minus-square");
	element3.setAttribute("onclick", "deleteRow(this);");
	element3.setAttribute("href","#");
	cell3.appendChild(element3);
	
	index++;
}

function deleteRow(obj){
	var table = document.getElementById("studentAchievementsBody");
	var rowCount = table.rows.length;	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

$("#eventName").change(function() {
	var eventDescription = document.getElementById("eventDescription");
	//removeOption(tenderSubCategory);
	//removeOption(commodityName);
	
	$.ajax({
		url: '/cedugenie/getEventDescription.html',
		dataType: 'text',
		data: "eventName="+($("#eventName").val()),
		success: function(data){ 
			/*if successful the event description will come from the DB*/
			$('#eventDescription').text(data); 
		}
	});
});

$(document).ready(function (){
	$("#eventIncharge").autocomplete({
		source: '/cedugenie/getAllUserId.html' ,
		/*select: function (event, ui){
			var userId0 = ui.item.value;
		}*/
});
});