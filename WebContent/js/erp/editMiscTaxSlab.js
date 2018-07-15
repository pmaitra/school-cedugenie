function removeDisabled(){
	var i;	
	var input = document.getElementsByTagName("input");	
	for(i=0; i<input.length; i++)
	{
		input[i].removeAttribute("readonly");
	}
	document.getElementById("taxBasedOn").removeAttribute("disabled");
	document.getElementById("figureType").removeAttribute("disabled");
	
	document.getElementById("addNewRows").removeAttribute("disabled");
	document.getElementById("submitButton").removeAttribute("disabled");
	
	var deleteButton= document.getElementsByName("deleteButton");			
	for(var j=0; j<deleteButton.length;j++)
	{
		deleteButton[j].removeAttribute("disabled");
	}
}

function deleteRow(obj){	
	var table = document.getElementById("slabDetails");
	var rowCount = table.rows.length;
	
	if(rowCount>4){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);

	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		return false;
	}
} 

var p = 0;
function addrows(){	
	var j;
    var table = document.getElementById("slabDetails"); 
    var rowCount = table.rows.length;        
    var row = table.insertRow(rowCount-1);    
    
    var arr = document.getElementsByName("startSlabIndex");
    for(j=0; j<arr.length; j++){
    	var idNum = arr[j].id.replace("startSlabIndex","");
    	var x = parseInt(idNum);
    	if(x > p){
    		p = x;
    	}
    }    
	
    var cell = row.insertCell(0);		
    var element = document.createElement("input");
    element.type = "text";		        	        	
    element.value = "0.00";
    element.setAttribute("style","text-align: right;");
    element.setAttribute("onfocus","this.value='';");
    element.setAttribute("onblur","setZero(this);");
    element.className = "form-control";
    element.name = "startSlabIndex";
    element.id = "startSlabIndex"+(p+1);
    cell.appendChild(element);
    
    var cell = row.insertCell(1);		
    var element = document.createElement("input");
    element.type = "text";		        	        	
    element.value = "0.00";
    element.setAttribute("style","text-align: right;");
    element.setAttribute("onfocus","this.value='';");
    element.setAttribute("onblur","setZero(this);");	       
    element.className = "form-control";
    element.name = "endSlabIndex";
    element.id = "endSlabIndex"+(p+1);
    cell.appendChild(element);
    
    var cell = row.insertCell(2);
    var element = document.createElement("input");
    element.type = "text";		        	        	
    element.value = "0.00";
    element.setAttribute("style","text-align: right;");
    element.setAttribute("onfocus","this.value='';");
    element.setAttribute("onblur","setZero(this);");       
    //element.className = "form-control";
    element.setAttribute("class","form-control");
    element.name = "taxAmount";
    element.id = "taxAmount"+(p+1);
    cell.appendChild(element);
    
    var cell = row.insertCell(3);
    var element = document.createElement("input");
    element.type = "image";		        	        
    element.name = "deleteButton";
    element.setAttribute("src","/cedugenie/images/minus_icon.png");
    element.setAttribute("onclick","return deleteRow(this);");
    cell.appendChild(element);
                 
};


function setZero(tBox){
	document.getElementById('warningbox').style.visibility = "collapse";
	if(tBox.value==""){
		tBox.value="0.00";
	}		
	if(isNaN(tBox.value)){
		document.getElementById('warningbox').style.visibility="visible";
		document.getElementById('warningmsg').innerHTML="Please Enter Proper Numeric Value";
		tBox.value="0.00";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0.0){
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
			tBox.value="0.00";
		}
	}		
}



