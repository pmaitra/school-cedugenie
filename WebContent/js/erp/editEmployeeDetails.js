$(document).ready(function() {
	$(".presentedOnClass").each(function(){
		$(this).Zebra_DatePicker();			    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y',
	    	  direction:false
	    	});
	});
	
	$(".childDateOfBirthClass").each(function(){
		$(this).Zebra_DatePicker();
	    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y'
	    	});
	});
	
	
	
	
});
var p = 2;
var i = 1;
function addChildRows(){
    var table = document.getElementById("employeesDependentsTable");        
    var rowCount = table.rows.length;        
    var row = table.insertRow(rowCount-1);  
    
    var cell = row.insertCell(0);		
    var element = document.createElement("input");
    element.type = "text";       
    element.className = "textfield1";
    element.name = "childName";
    element.id = "childName"+i;
    cell.appendChild(element);
    
    var cell = row.insertCell(1);		
    var element = document.createElement("select");
    element.className = "defaultselect";
    element.name = "childGender";
    element.id = "childGender"+i;
    element.add(new Option("MALE", "M"));
    element.add(new Option("FEMALE", "F"));
    cell.appendChild(element);    
    
    var cell = row.insertCell(2);		
    var element = document.createElement("input");
    element.type = "text";	      
    element.className = "childDateOfBirthClass";
    element.name = "childDateOfBirth";
    element.id = "childDateOfBirth"+i;
    element.setAttribute('readonly', 'readonly');
    cell.appendChild(element);
    
    var cell = row.insertCell(3);
    element = document.createElement("img");
    element.setAttribute("src","/icam/images/minus_icon.png");
    element.setAttribute("onclick","deleteEmployeeDependents(this);");
    cell.appendChild(element);
    
    i++;
    
    createCalender();
};

function createCalender(){
	$(".childDateOfBirthClass").each(function(){
		$(this).Zebra_DatePicker();
	    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y'
	    	});
	});
}


function deleteEmployeeDependents(obj){	
	var table = document.getElementById("employeesDependentsTable");
	var rowCount = table.rows.length;
	
	if(rowCount>3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);			
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}

var wShopCount=0;
function addMoreWorkShop(){
	
	//wShopCount++;
	var table = document.getElementById("workShopAndTrainingDetailsTable"); 
    var rowCount = table.rows.length;
    //alert("rowCount===="+rowCount);
    var row = table.insertRow(rowCount);				
    wShopCount=rowCount-1;
  
    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="subject";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="venue";
    element3.className ="form-control";
    cell3.appendChild(element3);
    
    var fun="calculateDateDifference("+wShopCount+");";
    
    var cell4 = row.insertCell(2);		
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name="trainingFromDate";
    element4.id="trainingFromDate"+wShopCount;
    element4.setAttribute("onblur",fun);
    element4.className ="form-control";
    cell4.appendChild(element4); 
    
    var cell5 = row.insertCell(3);		
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name="trainingToDate";
    element5.className ="form-control";
    element5.id="trainingToDate"+wShopCount;
    element5.setAttribute("onblur",fun);
    cell5.appendChild(element5); 
    
    var cell6 = row.insertCell(4);		
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name="organizedBy";
    element6.className ="form-control";
    cell6.appendChild(element6);
    
    var cell7 = row.insertCell(5);		
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name="duration";
    element7.id="duration"+wShopCount;
    element7.className ="form-control";
    cell7.appendChild(element7);	
    
    var cell = row.insertCell(6);
    var element= document.createElement('a');	
    element.setAttribute("class","fa fa-minus-square");
    element.setAttribute("onclick", "deleteWorkShopRow(this);");
    element.setAttribute("href","#");
    cell.appendChild(element);	
    
  //  addCalendarForTraining();
   
}

function addCalendarForTraining(){			
	$(".trainingDate").each(function(){
		$(this).Zebra_DatePicker();			    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y',
	    	  direction:true
	    	});
	});
}
    
  
function calculateDateDifference(c)
{
	var start=document.getElementById("trainingFromDate"+c).value;
	var end=document.getElementById("trainingToDate"+c).value;
	if((start!= "") && (end!= "")){
		start = start.split("/");
		end = end.split("/");
		var sDate = new Date(start[1]+"/"+start[0]+"/"+start[2]);
		var eDate = new Date(end[1]+"/"+end[0]+"/"+end[2]);

		var startDays = Math.floor(sDate.getTime()/(3600*24*1000));
		var endDays = Math.floor(eDate.getTime()/(3600*24*1000));
		var diff = (endDays - startDays) + 1;
		document.getElementById("duration"+c).value=diff;
		 
	
	}  

}




function deleteWorkShopRow(obj){	
	var table = document.getElementById("workShopAndTrainingDetailsTable");
	var rowCount = table.rows.length;
	
	if(rowCount>1){
		var p =obj.parentNode.parentNode;
		p.parentNode.removeChild(p);			
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}


function addNominee(){
	var table = document.getElementById("nomineeTable"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount-1);		    
    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="nomineeName";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="relationship";
    element3.className ="form-control";
    cell3.appendChild(element3);    
    
    var cell4 = row.insertCell(2);		
    var element4 = document.createElement("input");
	 element4.type = "text";
    element4.name="nomineePercent";
    element4.setAttribute("onblur","calculateHundred();"); 
    element4.className ="textfield2";
    cell4.appendChild(element4);  
    
  /*  var cell = row.insertCell(3);
    element = document.createElement("img");
    element.setAttribute("src","/icam/images/minus_icon.png");
    element.setAttribute("onclick","deleteNomineeRow(this);");
    cell.appendChild(element);*/
    
    var cell5 = row.insertCell(3);		
	var element5= document.createElement('a');	
	element5.setAttribute("class","fa fa-minus-square");
	element5.setAttribute("onclick", "deleteNomineeRow(this);");
	element5.setAttribute("href","#");
	cell5.appendChild(element5);	
}

function calculateHundred(){
	var intRegx=/^[0-9]{1,}$/;
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var percentageSum=0;
	var qtyProducedForCB = document.getElementsByName("nomineePercent");
	for ( var i = 0; i < qtyProducedForCB.length; i++) {
		
		if((!qtyProducedForCB[i].value.match(intRegx)) && (!qtyProducedForCB[i].value.match(doubleRegx))){
			qtyProducedForCB[i].value="0.0";
		}
		var value=parseFloat(qtyProducedForCB[i].value);
		percentageSum = percentageSum + value;			
	}
	
	if(percentageSum != 100){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Summation Of Nominee Percentage must be 100";
		return false;
	}else{
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
		return true;
	}
}

function deleteNomineeRow(obj){	
	var table = document.getElementById("nomineeTable");
	var rowCount = table.rows.length;
	
	if(rowCount>3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);			
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}

function addMoreAwardsAndRecognization(){
	var table = document.getElementById("awardsAndRecognizationTable"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);				
   	    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="awardName";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="presentedBy";
    element3.className ="form-control";
    cell3.appendChild(element3);    
    
    var cell4 = row.insertCell(2);		
    var element4 = document.createElement("input");
	 element4.type = "text";
    element4.name="presentedOn";
    element4.className ="form-control";
    cell4.appendChild(element4);  
    
    var cell5 = row.insertCell(3);		
	var element5= document.createElement('a');	
	element5.setAttribute("class","fa fa-minus-square");
	element5.setAttribute("onclick", "deleteAwardRecognizationRow(this);");
	element5.setAttribute("href","#");
	cell5.appendChild(element5);	
    
   // addCalendar();
}

function addCalendar(){			
	$(".presentedOnClass").each(function(){
		$(this).Zebra_DatePicker();			    
	    $(this).Zebra_DatePicker({
	    	  format: 'd/m/Y',
	    	  direction:false
	    	});
	});
}

function deleteAwardRecognizationRow(obj){	
	var table = document.getElementById("awardsAndRecognizationTable");
	var rowCount = table.rows.length;
	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}




function new_publish(){
	var table=document.getElementById("publicationsDetails");   
	
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);				
   	    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="publicationName";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="coPublisher";
    element3.className ="form-control";
    cell3.appendChild(element3);    
    
    var cell4 = row.insertCell(2);		
    var element4 = document.createElement("input");
	 element4.type = "text";
    element4.name="dateOfPublication";
    element4.className ="form-control";
    cell4.appendChild(element4);  
    
    var cell = row.insertCell(3);		
    var element = document.createElement("input");
	 element.type = "text";
    element.name="publicationDesc";
    element.className ="form-control";
    cell.appendChild(element);  
    
    var cell5 = row.insertCell(4);		
	var element5= document.createElement('a');	
	element5.setAttribute("class","fa fa-minus-square");
	element5.setAttribute("onclick", "delete_publish(this);");
	element5.setAttribute("href","#");
	cell5.appendChild(element5);	
} 

function delete_publish(id)
{	
	var table=document.getElementById("publicationsDetails");   
	var rowCount = table.rows.length;
	
	if(rowCount>1){
		var p = id.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}	

function addMoreExperience(){
	var table = document.getElementById("staffPreviousWorkDetailsTable");		
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);				
    					
    /*var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    element1.name="chkbox[]";
    cell1.appendChild(element1);    */
    
    var cell0 = row.insertCell(0);		
    var element0 = document.createElement("input");
    element0.type = "text";
    element0.name="organizationName";
    element0.className ="form-control";
    cell0.appendChild(element0);         	

    var cell1 = row.insertCell(1);		
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.name="fromDate";
    element1.className ="form-control";
    cell1.appendChild(element1);
    
    var cell2 = row.insertCell(2);		
    var element2 = document.createElement("input");
    element2.type = "text";
    element2.name="toDate";
    element2.className ="form-control";
    cell2.appendChild(element2);
    
    var cell3 = row.insertCell(3);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="organizationContactNo";
    element3.className ="form-control";
    cell3.appendChild(element3);
    
    var cell4 = row.insertCell(4);		
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name="organizationWebSite";
    element4.className ="form-control";
    cell4.appendChild(element2);
    
    
    var cell5 = row.insertCell(5);		
	var element5= document.createElement('a');	
	element5.setAttribute("class","fa fa-minus-square");
	element5.setAttribute("onclick", "delete_experience(this);");
	element5.setAttribute("href","#");
	cell5.appendChild(element5);
}


function delete_experience(obj){	
	var table = document.getElementById("staffPreviousWorkDetailsTable");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}


/*function delete_experience(id)
{	
	var deleteButton = document.getElementsByName("DeleteExperience");
	var counter = deleteButton.length;	
	if(counter == 0){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Atleast One required";
	}else{		
		var experienceDetailsTable = id.parentNode.parentNode.parentNode;
		experienceDetailsTable.parentNode.removeChild(experienceDetailsTable);
		counter --;
	}
}*/
