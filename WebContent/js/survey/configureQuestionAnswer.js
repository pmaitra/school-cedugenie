/*var index=0;
var newIndex = 1;*/


var index=1;
var newIndex = 0;
function addNewRows(){
	//alert("hii");
	var table = document.getElementById("addNewRowsBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="answer";
	element0.id="answer"+index;
	element0.className="form-control";
	element0.setAttribute("required", "");
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="weightage";
	element1.className="form-control";
	element1.id="weightage"+index;
	element1.setAttribute("required", "");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	/*var element2 = document.createElement("img");
	element2.setAttribute("src","/cedugenie/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");*/
	var element2= document.createElement('a');	
	 element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell2.appendChild(element2);		
		

	index++;
	
}


function addrows(thisClassNode){		
	alert("thisValue====="+thisClassNode.value);
	var table = document.getElementById("tbl_posts_body");
	var tableTdFirst = document.getElementById("tdOne"+index);
	var tableTdSecond = document.getElementById("tdTwo"+index);
	var x = thisClassNode.parentElement.getElementsByTagName('td');
	alert("x==="+x);
	/*var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	            
	            
	var cell1 = row.insertCell(1);	*/	
	        
		        
	/*var cell2= row.insertCell(2);
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="answer";
	element0.id="answer"+index;
	element0.className="form-control";	
	element0.setAttribute( "required","");
	cell2.appendChild(element0);
	
	var cell3= row.insertCell(3);
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="weightage";
	element1.id="weightage"+index;
	element1.className="form-control";	
	element1.setAttribute( "required","");
	cell3.appendChild(element1);*/
	var element2 = document.createElement('a');
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell3.appendChild(element2);

	index++;
}

function deleteRow(obj){	
	var table = document.getElementById("addNewRowsBody");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
} 

function cloneRow(){		

	var table = document.getElementById("tbl_posts_body");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
	row.id = "rec-"+newIndex;	         
	var cell0 = row.insertCell(0);		
	var element = document.createElement("input");
	element.type = "text";
	element.name="question";
	element.id="question"+newIndex;
	element.className="form-control";	
	element.setAttribute( "required","");
	cell0.appendChild(element);           
	            
	var cell1 = row.insertCell(1);	
	var element3 = document.createElement("Select");
	var opt0 = document.createElement("option");
	opt0.value = '';
	opt0.innerHTML = 'Select';
	element3.appendChild(opt0);
	
	var opt1 = document.createElement("option");
	opt1.value = 'text';
	opt1.innerHTML = 'Text';
	element3.appendChild(opt1);
	
	var opt2 = document.createElement("option");
	opt2.value = 'checkbox';
	opt2.innerHTML = 'Checkbox';
	element3.appendChild(opt2);
	
	var opt3 = document.createElement("option");
	opt3.value = 'radio';
	opt3.innerHTML = 'Radio';
	element3.appendChild(opt3);
	
	var opt4 = document.createElement("option");
	opt4.value = 'textarea';
	opt4.innerHTML = 'TextArea';
	element3.appendChild(opt4);
	
	element3.type = "select";
	element3.name="answer_type";
	element3.id="answer_type"+newIndex;
	element3.className="form-control";	
	element3.setAttribute( "required","");
	cell1.appendChild(element3);          
		        
	var cell2= row.insertCell(2);
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="answer";
	element0.id="answer"+newIndex;
	element0.className="form-control";	
	element0.setAttribute( "required","");
	cell2.appendChild(element0);
	
	var cell3= row.insertCell(3);
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="weightage";
	element1.id="weightage"+newIndex;
	element1.className="form-control";	
	element1.setAttribute( "required","");
	cell3.appendChild(element1);
	
	var element2 = document.createElement('a');
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell3.appendChild(element2);
	
	var cell4= row.insertCell(4);
	var element4 = document.createElement('a');
	element4.setAttribute("class","btn btn-xs btn-danger delete-record");
	element4.setAttribute("onclick", "deleteRow(this);");
	element4.setAttribute("href","#");
	cell4.appendChild(element4);
	newIndex++;
}