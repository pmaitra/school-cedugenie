var p = 1;
function addrows(){	
	//document.getElementById("warningbox").style.display = 'none';
	
    var table = document.getElementById("itSecDetail"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);        
	
    var cell = row.insertCell(0);
    var element = document.createElement("input");
    element.type = "text";
    element.className = "form-control";
    element.name = "itSectionDetailsName";
    element.id = "itSectionDetailsName"+p;
    cell.appendChild(element);
    
    var cell = row.insertCell(1);
//    var element = document.createElement("<a href='#' class = 'on-default' name='deleteButton' onclick='return deleteRow(this);'>" +
//    										"<i class='fa fa-minus-square'></i></a>");
    var element = document.createElement('a');
    element.setAttribute("onclick","return deleteRow(this);");
    element.setAttribute("class","fa fa-minus-square");
    element.setAttribute("href","#");
    cell.appendChild(element);
    p++;
//    element.type = "image";
//    element.setAttribute("onclick","return deleteRow(this);");
//    element.setAttribute("src","/sms/images/minus_icon.png");	
//    element.name = "deleteButton";
    
                 
};


var q = 1;
function addEditRows(){	
	//document.getElementById("warningbox").style.display = 'none';
	
    var table = document.getElementById("itSecDetailEdit"); 
    var rowCount = table.rows.length;        
    var row = table.insertRow(rowCount-1); 
    
    var cell = row.insertCell(0);		
    var element = document.createElement("input");
    element.type = "text";	
    element.className = "form-control";
    element.name = "itSecDetailsNewName";
    element.id = "itSecDetailsNewName"+q;
    element.required = true;
    cell.appendChild(element);
    
    var cell = row.insertCell(1);		
    var element = document.createElement('a');
    element.setAttribute("onclick","return deleteRow(this);");
    element.setAttribute("class","fa fa-minus-square");
    element.setAttribute("href","#");
    cell.appendChild(element);
    q++;                 
//  var element = document.createElement("input");
//  element.type = "image";	
//  element.setAttribute("onclick","return deleteRow(this);");
//  element.setAttribute("src","/sms/images/minus_icon.png");	
//  element.name = "deleteButton";
//  cell.appendChild(element);
};

function removeDisabled(){
	var i;	
	document.getElementById("addNewRows").removeAttribute("disabled");
	document.getElementById("submitButton").removeAttribute("disabled");
	
	var input = document.getElementsByName("itSectionDetailsName");	
	for(i=0; i<input.length; i++)
	{
		input[i].removeAttribute("readonly");
	}	
	
//	var deleteButton= document.getElementsByName("deleteButton");			
//	for(var j=0; j<deleteButton.length;j++)
//	{
//		deleteButton[j].removeAttribute("disabled");
//	}
}

function deleteRow(obj){	
	var table = document.getElementById("itSecDetail");
	var rowCount = table.rows.length;
	alert(rowCount);
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		alert("Atleast One Row Required") ;
		return false;
	}
}


function inactiveRow(obj){
	var itSectionCode = obj.getAttribute("value");
	var p = obj.parentNode.parentNode;
	$.ajax({
        url: '/icam/inactiveITRebate.html',
        dataType: 'json',
        data: "itSection=" + itSectionCode,
        success: function(dataDB) {
        	p.parentNode.removeChild(p);
        	}
		});
	}

$(document).ready(function() {
	$("#itSectionCode").change(function(){	
		if(($(this).val()) != ''){
			$.ajax({
		        url: '/icam/getRebatesForITSection.html',
		        dataType: 'json',
		        data: "itSection=" + ($("#itSectionCode").val()),
		        success: function(dataDB) {
		        	if(dataDB == ""){
		        		var cell = document.getElementById("itSecDetail").rows[0].cells;
	        			cell[0].innerHTML = "Enter New Rebate For IT Section : "+($("#itSectionCode option:selected").text());
	        			document.getElementById("itSecDetailEditDiv").style.display = 'none';
		        		document.getElementById("itSecDetailInputDiv").style.display = 'block';
		        		document.getElementById("footerUpperDiv").style.display = 'block';
					}else{
						$('#itSecDetailEdit').empty();
						var sm = "";
						sm = sm + "<tr><th colspan='2'><input type='hidden' name='itSectionCode' value='"+($("#itSectionCode").val())+"'>" +
								"Rebate Names For IT Section : "+($("#itSectionCode option:selected").text())+"</th></tr>" +
								"<tr><th>Rebate Name</th><th>Delete</th></tr>";
						
						document.getElementById("itSecDetailInputDiv").style.display = 'none';
						document.getElementById("footerUpperDiv").style.display = 'none';
						document.getElementById("itSecDetailEditDiv").style.display = 'block';
						document.getElementById("footerEditDiv").style.display = 'block';
						
						var arr = dataDB.split("^^");
						for(var i=0; i<arr.length-1; i++){
							var arr2 = arr[i].split("~");
							var itSectionCode = arr2[1] ;
							sm = sm + "<tr>" +
									"<td><input type='hidden' name='itSectionDetailsCode' value='"+itSectionCode+"'><input type='text' class='form-control' id='itSectionDetailsName"+i+"' name='itSectionDetailsName' readonly='readonly' value='"+arr2[0]+"'></td>" +
									"<td><i class='fa fa-trash-o' value='"+itSectionCode+"' onclick = 'return inactiveRow(this);'></i></td>" +
									"</tr>";
						}
						sm = sm + "<tr><td></td><td><button type='button' id='addNewRows' class='btn btn-primary' onclick='addEditRows();' disabled='disabled'>Add</button></td></tr>";
						
						$('#itSecDetailEdit').append(sm);
					}
		        	/*document.getElementById("successbox").style.display = 'none';*/
		        }
			});
		}    	
	});
});
