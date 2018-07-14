$(function(){
    $("#venueCode0").change(function() {
				$.ajax({
			        url: '/cedugenie/getVenueDetailsAgainstVenueCode.html',
			        dataType: 'json',
			        data: "venueCode=" + ($(this).val()),
			        success: function(dataDB) {
			        	
			        	if(dataDB != "null" && dataDB !=""){
			        		document.getElementById("availableSeat0").value = dataDB;							
						}
			       }
				});	      
	});
  
    $("#exam").change(function() {
		$.ajax({
	        url: '/cedugenie/getVenueDetailsAgainstExam.html',
	        dataType: 'json',
	        data: "exam=" + ($(this).val()),
	        success: function(dataDB) {
	        	//alert("dataDB==="+dataDB);
	        	 $('#seatingArrangements > tbody').empty();
	        	var table = document.getElementById("seatingArrangements");
	        	var arr = dataDB.split(";");
	        	for(var k = 0;k<(arr.length)-1;k++){
	        		var algoAndVenue =  arr[k].split("#*#");
	        		var algoArr = algoAndVenue[1].split("**");
		        	document.getElementById("algorithm").value = algoArr[0];	
		        	//alert("length==="+arr.length);
		        	var rowCount = table.rows.length;
		        	//alert("rowCount=="+rowCount);
		            var row = table.insertRow(rowCount);
		            cell = row.insertCell(0);	
		            var venueSpan1 = document.createElement('span')
	            	venueSpan1.innerHTML = algoAndVenue[0];
		            cell.appendChild(venueSpan1);
		            var rollArr = algoArr[1].split("##");
		        	for(var i = 0;i<(rollArr.length)-1;i++){
		        		var seats = rollArr[i].split(":");
		        		//alert("seats==="+seats);
		        		 var rowCount = table.rows.length;
			             var row = table.insertRow(rowCount);
			             for(var j = 0;j<(seats.length)-1;j++){
			            	
				            // alert("row==="+row);
			            	 cell = row.insertCell(j);	

			            	 var dateSpan = document.createElement('span')
			            	 //dateSpan.innerHTML = dateString;
			            	 dateSpan.setAttribute("class","desk");
			            	 
			            	 var dateSpan1 = document.createElement('span')
			            	 dateSpan1.innerHTML = seats[j];
				           
				             
				             cell.appendChild(dateSpan1);
				             
				             cell.appendChild(dateSpan);
			             }
		        	}
	        	}
	        	
	        	
	       }
		});	      
});
   
 });

function Validation(){
	 var rowNumber = document.getElementById("rowNumber").value;
     var columnNumber = document.getElementById("columnNumber").value;
     var actualCapacity = document.getElementById("capacity").value;
     var expectedCapacity = rowNumber*columnNumber;
     if(expectedCapacity>actualCapacity){
    	 document.getElementById("errorMsg").style.display = "block"; 
    	 return false;
     }else{
    	 return true;
     }
}


$("#programName0").change(function() {
	
	$.ajax({
		url: '/cedugenie/getTotalNoOfStudentForAProgram.html',
		dataType: 'json',
		data: "programCode=" + $("#programName0").val(),
	    success: function(data) {
	    	document.getElementById("capacity0").value = data; 
	    	if(data == 0){
	    		totalCapacity = 0;
	    	}else{
	    		totalCapacity = totalCapacity + parseInt(data);
	    	}
	    	
	    	document.getElementById("serialId").value = totalCapacity ;
	    }	
	});
	
});

var index=1;
var newIndex = 1;
var programIndex = 0;
var totalCapacity = 0;
var venueIndex = 0;

function addrows(){	
	var programCode = document.getElementsByName("programCode");
	
	var programCodes = null;
	for(var i=0;i<programCode.length;i++){
		programCodes = programCodes + "*" + programCode[i].value;
	}
	var progCode = programCodes.split("*");
	
	var table = document.getElementById("programTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount); 
	
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "programName" + index;
	element.name = "programName" + index;
	element.setAttribute( "required","required");
	element.setAttribute("onchange","getTotalCapacityOfProgram(this);");	
	element.add(new Option("Select", ""));
	for(var i = 1; i<progCode.length;i++){
		element.add(new Option(progCode[i], progCode[i]));
	}
	cell0.appendChild(element);
	
	
	var cell1 = row.insertCell(1);
	element = document.createElement("input");
	element.type = "text";
	element.className = "form-control";
	element.id = "capacity" + index;
	element.name = "capacity" + index;
	element.setAttribute( "required","required");
	element.setAttribute( "readonly","readonly");
	cell1.appendChild(element);
	
	var cell2= row.insertCell(2);
	var element2 = document.createElement('a');
	element2.id = "delete" + index;
	element2.name = "delete" + index;
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell2.appendChild(element2);
	
	programIndex = index;
	index++
	
}
 

function getTotalCapacityOfProgram(thisClassNode){
	
	
	var split= (thisClassNode.id).split("programName");
	var index=split[1];

	
	if($("#programName"+index).val()!=null){
		
			$.ajax({
				url: '/cedugenie/getTotalNoOfStudentForAProgram.html',
				dataType: 'json',
				data: "programCode=" + $("#programName"+index).val(),
			    success: function(data) {
			    	
			    	if(data == 0){
			    		totalCapacity = totalCapacity - document.getElementById("capacity"+index).value   ;                            
			    	}else{
			    		totalCapacity = totalCapacity + parseInt(data);
			    	}
			    	document.getElementById("capacity"+index).value = data;
			    	
			    	
			    	document.getElementById("serialId").value = totalCapacity;
			    }	
			});
			
	
	}
	
}
function deleteRow(obj){
	
	var split = (obj.id).split("delete");
	var index = split[1];

	
	
	
	
	if(document.getElementById("capacity"+index).value != ""){
		totalCapacity = totalCapacity - parseInt(document.getElementById("capacity"+index).value);
	}else{
		totalCapacity = totalCapacity -0;
	}
	
	document.getElementById("serialId").value = totalCapacity;
	
	var table = document.getElementById("programTable");
	var rowCount = table.rows.length;	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
	
}

function addRowsForVenue(){	
	var venueCode = document.getElementsByName("venueCode");
	var venueName = document.getElementsByName("venueName");
	
	var venueCodes = null;
	var venueNames = null;
	
	for(var i=0;i<venueCode.length;i++){
		venueCodes = venueCodes + "*" + venueCode[i].value;
	}
	
	
	for(var j=0;j<venueName.length;j++){
		
		venueNames = venueNames + "*" + venueName[j].value;
	}
	
	
	var venueCodeNew = venueCodes.split("*");
	var venueNameNew = venueNames.split("*");
	
	
	var table = document.getElementById("venueTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount); 
	
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "venueCode" + newIndex;
	element.name = "venueCode"+newIndex;
	element.setAttribute( "required","required");
	element.setAttribute("onchange","getTotalCapacityOfVenue(this);");	
	element.add(new Option("Select", ""));
	for(var k = 1; k<venueCodeNew.length;k++){
		element.add(new Option(venueNameNew[k], venueCodeNew[k]));
	}
	cell0.appendChild(element);
	
	
	var cell1 = row.insertCell(1);
	element = document.createElement("input");
	element.type = "text";
	element.className = "form-control";
	element.id = "availableSeat" + newIndex;
	element.name = "availableSeat" + newIndex;
	element.setAttribute( "required","required");
	element.setAttribute( "readonly","readonly");
	cell1.appendChild(element);
	
	
	
	var cell2 = row.insertCell(2);
	element = document.createElement("input");
	element.type = "text";
	element.className = "form-control";
	element.id = "rowNumber" + newIndex;
	element.name = "rowNumber" + newIndex;
	element.setAttribute( "required","required");
	cell2.appendChild(element);
	

	var cell3 = row.insertCell(3);
	element = document.createElement("input");
	element.type = "text";
	element.className = "form-control";
	element.id = "columnNumber" + newIndex;
	element.name = "columnNumber" + newIndex;
	element.setAttribute( "required","required");
	cell3.appendChild(element);
	
	
	var cell4= row.insertCell(4);
	var element2 = document.createElement('a');
	element2.id = "deleteVenue" + newIndex;
	element2.name = "deleteVenue" + newIndex;
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRowVenue(this);");
	element2.setAttribute("href","#");
	cell4.appendChild(element2);
	
	venueIndex = newIndex;
	
	newIndex++
	
}

function getTotalCapacityOfVenue(thisClassNode){
	
	
	var split= (thisClassNode.id).split("venueCode");
	var index=split[1];
	//alert("index=="+index);
	//alert($("#venueCode"+index).val());
	if($("#venueCode"+index).val()!=null){
		//alert("hiiii");
		// $("#venueCode"+index).change(function() {
				$.ajax({
			        url: '/cedugenie/getVenueDetailsAgainstVenueCode.html',
			        dataType: 'json',
			        data: "venueCode=" + ($("#venueCode"+index).val()),
			        success: function(dataDB) {
			        	//alert("dataDB==="+dataDB);
			        	if(dataDB != "null" && dataDB !=""){
			        		document.getElementById("availableSeat"+index).value = dataDB;							
						}
			       }
				});	      
		// });
			
	
	}
	
}

function deleteRowVenue(obj){
	
	var split = (obj.id).split("deleteVenue");
	var index = split[1];

	
	
	
	
	
	var table = document.getElementById("venueTable");
	var rowCount = table.rows.length;	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
	
}
 

function setIndex(){
	document.getElementById("venueIndex").value = venueIndex;
	document.getElementById("programIndex").value = programIndex;
}
