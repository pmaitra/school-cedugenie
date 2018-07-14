$(document).ready(function() {
	$("#candidateId").change(		
		function() {
			if(this.value!=""){
					$.ajax({
				        url: '/icam/getCandidateName.html',
				        dataType: 'json',
				        data: "strParam=" + ($(this).val()),
				        success: function(data) {
				        	$('#name').val(data);				        	
				        	var arr=data.split("*");				        	
				        	document.getElementById("name").value=arr[0];
				        	document.getElementById("qualification").value=arr[1];
				        	document.getElementById("specialization").value=arr[2];
				        	document.getElementById("referredBy").value=arr[3];
				    }				
			});
		}
	});		
});


function addRow(tableID){				
    var table = document.getElementById(tableID); 
    var rowCount = table.rows.length;    
    var row = table.insertRow(rowCount); 
     
    var cell2 = row.insertCell(0);
    var element2 = document.createElement("input");
    element2.type = "text";
	element2.name="subject";
	element2.setAttribute("class","form-control");
    cell2.appendChild(element2);
    
	var cell3 = row.insertCell(1);
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="subMarks";
    element3.setAttribute("class","form-control");
	cell3.appendChild(element3);
	
	var cell = row.insertCell(2);
	element = document.createElement("img");
	element.setAttribute("src","/icam/images/minus_icon.png");
	element.setAttribute("onclick","deleteRow(this);");
	cell.appendChild(element);
}
 
 function validate() {	
 var sub=document.getElementsByName("subject");
 var mar=document.getElementsByName("subMarks");
 var len=sub.length; 
 	for(var i=0;i<len;i++) {		 
 		subj=sub[i].value;
		mark=mar[i].value;
		 if (subj=="")	{
			 alert("Please Enter Subject");
			 	//document.getElementById("warningbox").style.visibility='visible';
			 	//document.getElementById("warningbox").innerHTML="Please Enter Subject";
				return false;
			}
			if(mark=="")	{
				alert("Please Enter Marks");
				//document.getElementById("warningbox").style.visibility='visible';
				//document.getElementById("warningbox").innerHTML="Please Enter Marks";
				return false;
			}
			if(isNaN(mark))	{
				alert("Please Enter Valid Marks");
				//document.getElementById("warningbox").style.visibility='visible';
				//document.getElementById("warningbox").innerHTML="Please Enter Valid Marks";
				return false;
			}	
 	} 	
 return true;
 } 

 function deleteRow(obj)	{
			var table = document.getElementById("dataTable");
			var rowCount = table.rows.length;
			
			if(rowCount>2){
				var p = obj.parentNode.parentNode;
				p.parentNode.removeChild(p);
			}else{
				alert("Atleast One Row Required");
				//document.getElementById("warningbox").style.visibility='visible';
				//document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
			}
	}
    
    function valir()   {
    	fi=document.getElementById("candidateId").value;    	
    	v=document.getElementsByName("status");    	
    	//Form Id Validation
    	if(fi=="")   	{
    		//document.getElementById("warningbox").style.visibility='visible';
    		//document.getElementById("warningbox").innerHTML="Please Select Candidate ID";
    		alert("Please Select Candidate ID");
    		return false;
    	} 
    	if(validate()!=true){
    		return false;
    	}
    	//Decision Validation
    	for (var i=0; i<v.length; i++){
    		if(v[i].checked)
    			break;
    	}
    	if (i==v.length){
    		alert("Please Select Your Decision");
    		//document.getElementById("warningbox").style.visibility='visible';
    		//document.getElementById("warningbox").innerHTML="Please Select Your Decision";
    		return false;
    	}    	
    	return true;
    }
    
  