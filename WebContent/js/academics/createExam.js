var y = 1;
	function addExamRow(){	 
     var table = document.getElementById("examTab");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		var cn = document.getElementById("className0");
		var etn = document.getElementById("examType0");		
		var classNameList = new Array();
		var examTypeList = new Array();		
		for(var i = 0; i<cn.length;i++){			
			classNameList[i] = cn.options[i].value;
		}
		for(var i = 0; i<etn.length;i++){			
			examTypeList[i] = etn.options[i].value;
		}	
		
		var cell,element;		

		cell = row.insertCell(0);
		element = document.createElement("select");
		element.className = "from-control";
		element.id = "className"+ y;
		element.name = "className";
		element.setAttribute("onchange","getCourse(this);");		
		element.add(new Option("Select...", ""));
		for(var i = 1; i<classNameList.length;i++){
			element.add(new Option(classNameList[i], classNameList[i]));
		}
		cell.appendChild(element);	
		
		cell = row.insertCell(1);
		element = document.createElement("input");
		element.type = "text";
		element.className = "from-control";
		element.name = "examName";
		element.id = "examName"+ y;
		cell.appendChild(element);	
		
		cell = row.insertCell(2);
       
        element = document.createElement("select");
      //  element.type = "text";
        element.name= "courseNames";
		element.id= "courseName"+y;
		element.add(new Option("Select", ""));
		element.setAttribute("class","form-control");
        cell.appendChild(element);
        //cell.appendChild(Div);
		
		
		cell = row.insertCell(3);
		element = document.createElement("select");
		element.className = "form-control";
		element.id = "examType" + y;
		element.name = "examType";
		element.add(new Option("Select", ""));
		for(var i = 1; i<examTypeList.length;i++){
			element.add(new Option(examTypeList[i], examTypeList[i]));
		}
		cell.appendChild(element);
		
		cell= row.insertCell(4);
		element = document.createElement("img");
		var element = document.createElement('a');
		element.setAttribute("class","fa fa-minus-square");
		//element.setAttribute("src","/sms/images/minus_icon.png");		
		element.setAttribute("onclick", "deleteRow(this);");	
		element.setAttribute("href","#");
		cell.appendChild(element);		
		y++;
		var innerHeight2=document.body.scrollHeight;
	   	var frame=window.parent.document.getElementById("frame");	    	
	   	frame.style.height = innerHeight2+25+ 'px';

};

	function deleteRow(obj){	
	var table = document.getElementById("examTab");
	var rowCount = table.rows.length;
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
	
	}


	$("#className0").change(function (){
		$.ajax({
			    url: '/icam/getSubjectsForACourse.html',
			    dataType: 'json',
			    data:"course=" + $("#className0").val(),
			    success: function(data) {
			    	var options='<option value="">Select...</option>';
			    	if(data!=null && data != ""){
	        			var courseArr1 = data.split("*~*");
	        			for(var a=0; a<courseArr1.length;a++){
	        				if(courseArr1[a] != null && courseArr1[a] != ""){
	        					var courseNameAndCode=courseArr1[a].split("#@#");  
    								options=options+'<option value="'+courseNameAndCode[0]+'">'+courseNameAndCode[1]+'</option>';
	        				}        				
	        			}	        				
			    	}
			    	document.getElementById("courseName0").innerHTML=options;
			    }
			}); 
		$.ajax({
		    url: '/icam/getTermsForACourse.html',
		    dataType: 'json',
		    data:"course=" +  $("#className0").val(),
		    success: function(data) {
		    	var options='<option value="">Select</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("*~*");
	    			for(var a=0; a<termArr1.length;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					var termNameAndCode=termArr1[a].split("#@#");  
								options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
	    				}        				
	    			}	        				
		    	}
		    	document.getElementById("term0").innerHTML=options;
		    }
		}); 
});
	
var index = 0;
	function makeEditable(rowId){	
		rowId=rowId.replace("edit","");
		var courseCode = document.getElementById("standardCode"+rowId).value;
		index = rowId;
		document.getElementById("examTypeName"+rowId).removeAttribute("disabled");
		$.ajax({
		    url: '/icam/getTermsForACourse.html',
		    dataType: 'json',
		    data:"course=" + courseCode,
		    success: function(data) {
		    	var options='<option value="">Select</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("*~*");
	    			for(var a=0; a<termArr1.length;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					var termNameAndCode=termArr1[a].split("#@#");  
								options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
	    				}        				
	    			}	        				
		    	}
		    	document.getElementById("termCode"+rowId).innerHTML=options;
		    }
		}); 
		$("#examTypeName"+index).change(function (){
			if(document.getElementById("examTypeName"+index).value == 'TERM'){
				document.getElementById("termCode"+index).removeAttribute("disabled");
			}
			if(document.getElementById("examTypeName"+index).value != 'TERM'){
				document.getElementById("termCode"+index).setAttribute("disabled","disabled");
			}
		});
	}
	function saveData(rowId,standardCode,examName){
		rowId=rowId.replace("save","");
		document.getElementById("saveId").value=rowId;
		var newExam = document.getElementById("examName").value;
		document.getElementById("getExamName").value = newExam;
		document.editExam.submit();
	}
	function showExamDetails(rowId,standardCode,subject,examName){
		var course = document.getElementById("courseName"+rowId).value;
	 	$('#approverGroupDetails > tbody').empty();
	 	if(standardCode != null && standardCode!=""){
	 		var row = "<tbody>";
     		row += "<tr><td><input type='text' name='proggrameName' id='proggrameName' class = 'form-control' readonly value='"+standardCode+"'></td><td><input type='text' name='subject'id='subject' class = 'form-control' readonly value='"+subject+"'></td><td><input type='text' name='examName'id='examName' class = 'form-control' value='"+examName+"'></td></tr>";
     		//<td><select id='examTypeName' name='examTypeName' class = 'form-control'><option value='"+duration1+"' >"+duration1+"</option><option value='"+duration2+"' >"+duration2+"</option><option value='"+duration3+"' >"+duration3+"</option></select></td>
     		$("#approverGroupDetails").append(row);
		}
	 	$('#modalInfo').fadeIn("fast");
	 	var btn = document.getElementById("updateTerms");
	 	btn.setAttribute("onclick","saveData('"+rowId+"','"+standardCode+"','"+course+"','"+examName+"');");
	}
	

	
function validatingExam(){
	var progName= document.getElementById("className0").value;
	$.ajax({
	    url: '/icam/getNameAgainstCode.html',
	    dataType: 'json',
	    data:"course=" +  progName,
	    success: function(data) {
	    	var examName = document.getElementById("examName0").value;
	    	examName=examName.trim();
	    	examName=examName.toUpperCase();
	    	var oldPrognames=document.getElementsByName("oldProgNames");
	    	for(var i=0;i<oldPrognames.length;i++){
	    		if(data==oldPrognames[i].value){
	    			var oldExamNames=document.getElementsByName("oldExamNames");
	    			for(var j=0;j<oldExamNames.length;j++){
		    			if(examName==oldExamNames[j].value){
		    				document.getElementById("javascriptmsg2").style.display = 'block';			
		    				document.getElementById("javascriptmsg2").innerHTML = "Programme Name with same Exam Name already exists";	
		    				return false;
						}
	    			}
	    		}    		
	    	}
	    	return true;	
	    }
	});
}