	var p=2;
	function addrows(){		
        var table = document.getElementById("incomeTaxSalarySlabTable"); 
        var rowCount = table.rows.length;        
        var row = table.insertRow(rowCount-1);      
        var itParameterNames = document.getElementsByName("itParameter");
        var countScRows = document.getElementById("itParameterSize").value;
        for(var j = 0 ; j < countScRows ; j++){
	    	var cell = row.insertCell(j);		
	        var element = document.createElement("input");
	        element.type = "text";		        	        	
	        element.value = "0.00";
	        element.setAttribute("style","text-align: right;");
	        element.setAttribute("onfocus","removeZero(this);");
	        element.setAttribute("onblur","setZero(this);");
	        element.className ="form-control";
	        element.name=itParameterNames[j].value;
	        cell.appendChild(element);
	    }	
        
        cell = row.insertCell(countScRows);
        element = document.createElement("img");
        element.setAttribute("src","/cedugenie/images/minus_icon.png");
        element.setAttribute("onclick","deleteRow(this);");
        cell.appendChild(element);        
        
        document.getElementById("buttonDiv").style.visibility = "visible";
	};
	
	function deleteRow(obj){	
		var table = document.getElementById("incomeTaxSalarySlabTable");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);			
		}else{
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}
	} 
	 	

