	$(document).ready(function() {
		$("#employeeCode").bind('keyup blur',function(){
			var employeeCode=$("#employeeCode").val();	
			 $.ajax({
		     url: '/cedugenie/serverSideValidationOfEmployeeCode.html',
		     dataType: 'json',
		     data: "employeeCode=" +employeeCode,
		     
		     success: function(data) {
		    	if(data != null && data!= ""){
		    		document.getElementById("warningbox").style.visibility='visible';
		 			$("#warningbox").text("Employee Code already exists");	
		 			document.getElementById("employeeCode").value='';
		    	  	return false;
		    		}	
		    	else{	    		
		    		document.getElementById("warningbox").style.visibility='collapse';
		    		}
		    	}  
			});			
		});
		
		
		
		
		
	});
	
	
	function new_publish(){
		var table=document.getElementById("publicationsDetails");   
		var publish_div = document.getElementById("clonePublicationsDetails");
		
		var tableClone = table.cloneNode(true);		
		tableClone.rows[1].cells[1].firstChild.value = "";
		tableClone.rows[1].cells[3].firstChild.value = "";
		tableClone.rows[2].cells[1].firstChild.value = "";
		tableClone.rows[2].cells[3].firstChild.value = "";		
		
		var rowCount = tableClone.rows.length;
		tableClone.deleteRow(rowCount-1);
		rowCount = tableClone.rows.length;
	    var row = tableClone.insertRow(rowCount);
	    
	    var cell1 = row.insertCell(0);
	    var element1 = document.createElement("button");
	    element1.type = "button";
	    element1.name="publicationDelete"; 
	    element1.setAttribute("class","submitbtn");
	    element1.setAttribute("onclick","delete_publish(this);");
	    var t1=document.createTextNode("Delete");
	    element1.appendChild(t1);
	    cell1.appendChild(element1);
	    publish_div.appendChild(tableClone);
	    //publish_div.insertBefore(tableClone, addbutton);
	} 
	
	function delete_publish(id)
	{	
		var deleteButton = document.getElementsByName("publicationDelete");
		var counter = deleteButton.length;	
		if(counter == 0){		
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Atleast One required";
		}else{		
			var publicationsDetailsTable = id.parentNode.parentNode.parentNode;
			publicationsDetailsTable.parentNode.removeChild(publicationsDetailsTable);
			counter --;
		}
	}	
	
	function addMoreExperience(){
		var table = document.getElementById("staffPreviousWorkDetailsTable");		
		var ame_div = document.getElementById("clonedstaffPreviousWorkDetailsDiv");		
		
		var tableClone = table.cloneNode(true);		
		tableClone.rows[1].cells[1].firstChild.value = "";
		tableClone.rows[1].cells[3].firstChild.value = "";
		tableClone.rows[1].cells[5].firstChild.value = "";
		tableClone.rows[2].cells[1].firstChild.value = "";
		tableClone.rows[2].cells[3].firstChild.value = "";					
		
		
		var rowCount = tableClone.rows.length;
		tableClone.deleteRow(rowCount-1);
		rowCount = tableClone.rows.length;
	    var row = tableClone.insertRow(rowCount);
	    
	    var cell1 = row.insertCell(0);
	    var element1 = document.createElement("button");
	    element1.type = "button";
	    element1.name="DeleteExperience"; 
	    element1.setAttribute("class","submitbtn");
	    element1.setAttribute("onclick","delete_experience(this);");
	    var t1=document.createTextNode("Delete");
	    element1.appendChild(t1);
	    cell1.appendChild(element1);
	    ame_div.appendChild(tableClone);		
	}
	
	function delete_experience(id)
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
	}
	
	$("#dateOfBirth").change(function(){
		var x=$(this).val();
		if(x!=""){
			x=x.split("/");
			var p=parseInt(x[2])+60;
			p=x[0]+"/"+x[1]+"/"+p;
			$("#dateOfRetirement").val(p);
		}
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
	    element.setAttribute("src","/cedugenie/images/minus_icon.png");
	    element.setAttribute("onclick","deleteChildRow(this);");
	    cell.appendChild(element);
	    
	    i++;
	    
	    createCalender();
	};
	
	function deleteChildRow(obj){	
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
		wShopCount++;
		var table = document.getElementById("workShopAndTrainingDetailsTable"); 
	    var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount-1);				
	  	       
	    var cell2 = row.insertCell(0);		
	    var element2 = document.createElement("input");
		element2.type = "text";
	    element2.name="subject";
	    element2.className ="textfield1";
	    cell2.appendChild(element2);         	

	    var cell3 = row.insertCell(1);		
	    var element3 = document.createElement("input");
	    element3.type = "text";
	    element3.name="venue";
	    element3.className ="textfield1";
	    cell3.appendChild(element3);
	    
	    var fun="calculateDateDifference("+wShopCount+");";
	    
	    var cell4 = row.insertCell(2);		
	    var element4 = document.createElement("input");
	    element4.type = "text";
	    element4.name="trainingFromDate";
	    element4.id="trainingFromDate"+wShopCount;
	    element4.setAttribute("onblur",fun);
	    element4.className ="trainingDate";
	    cell4.appendChild(element4); 
	    
	    var cell5 = row.insertCell(3);		
	    var element5 = document.createElement("input");
	    element5.type = "text";
	    element5.name="trainingToDate";
	    element5.className ="trainingDate";
	    element5.id="trainingToDate"+wShopCount;
	    element5.setAttribute("onblur",fun);
	    cell5.appendChild(element5); 
	    
	    var cell6 = row.insertCell(4);		
	    var element6 = document.createElement("input");
	    element6.type = "text";
	    element6.name="organizedBy";
	    element6.className ="textfield1";
	    cell6.appendChild(element6);
	    
	    var cell7 = row.insertCell(5);		
	    var element7 = document.createElement("input");
	    element7.type = "text";
	    element7.name="duration";
	    element7.id="duration"+wShopCount;
	    element7.className ="textfield1";
	    cell7.appendChild(element7);	
	    
	    var cell = row.insertCell(6);
	    element = document.createElement("img");
	    element.setAttribute("src","/cedugenie/images/minus_icon.png");
	    element.setAttribute("onclick","deleteChildRow(this);");
	    cell.appendChild(element);
	    
	    addCalendarForTraining();
	   
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
	
	function deleteChildRow(obj){	
		var table = document.getElementById("workShopAndTrainingDetailsTable");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
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
	    element2.className ="textfield2";
	    cell2.appendChild(element2);         	

	    var cell3 = row.insertCell(1);		
	    var element3 = document.createElement("input");
	    element3.type = "text";
	    element3.name="relationship";
	    element3.className ="textfield2";
	    cell3.appendChild(element3);    
	    
	    var cell4 = row.insertCell(2);		
	    var element4 = document.createElement("input");
		 element4.type = "text";
	    element4.name="nomineePercent";
	    element4.setAttribute("onblur","calculateHundred();");      
	    element4.className ="textfield2";
	    cell4.appendChild(element4);  
	    
	    var cell = row.insertCell(3);
	    element = document.createElement("img");
	    element.setAttribute("src","/cedugenie/images/minus_icon.png");
	    element.setAttribute("onclick","deleteChildRow(this);");
	    cell.appendChild(element);
	}
	
	
	function calculateHundred(){
		var nomineeName = document.getElementsByName("nomineeName");
		var count=0;
		for ( var i = 0; i < nomineeName.length; i++) {
			if (nomineeName[i].value != '') {
				count++;
			}
		}
		if(count>0){
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
			}
		}
	}

	function deleteChildRow(obj){	
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
	    var row = table.insertRow(rowCount-1);				
	   	    
	    var cell2 = row.insertCell(0);		
	    var element2 = document.createElement("input");
		element2.type = "text";
	    element2.name="awardName";
	    element2.className ="textfield1";
	    cell2.appendChild(element2);         	

	    var cell3 = row.insertCell(1);		
	    var element3 = document.createElement("input");
	    element3.type = "text";
	    element3.name="presentedBy";
	    element3.className ="textfield1";
	    cell3.appendChild(element3);    
	    
	    var cell4 = row.insertCell(2);		
	    var element4 = document.createElement("input");
		 element4.type = "text";
	    element4.name="presentedOn";
	    element4.className ="presentedOnClass";
	    cell4.appendChild(element4);  
	    
	    var cell = row.insertCell(3);
	    element = document.createElement("img");
	    element.setAttribute("src","/cedugenie/images/minus_icon.png");
	    element.setAttribute("onclick","deleteChildRow(this);");
	    cell.appendChild(element);
	    
	    addCalendar();
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
	
	function deleteChildRow(obj){	
		var table = document.getElementById("awardsAndRecognizationTable");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);			
		}else{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}
	}

	function createCalender(){
		$(".childDateOfBirthClass").each(function(){
			$(this).Zebra_DatePicker();
		    
		    $(this).Zebra_DatePicker({
		    	  format: 'd/m/Y'
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

	   
	  /* added by ranita.sur for userId validation on 02082017*/
	function userIdValidation(){
		/*var frstName = document.getElementById("firstName").value.toLowerCase();
   		var lastName = document.getElementById("lastName").value.toLowerCase();
   		var userId=frstName+"."+lastName;
   	 	document.getElementById("userId").value=userId;*/
		
   		
	}
	
	$("#userId").change(function(){
		$.ajax({
		     url: '/cedugenie/serverSideValidationOfUserId.html',
		     dataType: 'json',
		     data: "userId=" +$(this).val(),
		     success: function(data) {
		    	if(data != null && data!= ""){
		    		document.getElementById("warningbox").style.display='block';
		 			$("#warningbox").text("User Name already exists");
		    	  	return false;
		    	}else{
		    		document.getElementById("warningbox").style.display='none';
	    		}
	    	}
		});
	})